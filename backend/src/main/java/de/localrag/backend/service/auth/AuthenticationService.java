package de.localrag.backend.service.auth;

import de.localrag.backend.model.auth.LoginResult;
import de.localrag.backend.model.auth.RefreshResult;
import de.localrag.backend.model.auth.RegisterResult;
import de.localrag.backend.model.user.User;
import de.localrag.backend.model.user.UserRole;
import de.localrag.backend.respository.user.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public LoginResult login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return LoginResult.builder()
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }

    public RegisterResult register(String email, String password, String displayName) {
        if (StringUtils.isEmpty(displayName)) {
            displayName = email;
        }
        User newUser = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .displayName(displayName)
                .role(UserRole.VIEWER)
                .active(true)
                .build();
        userRepository.save(newUser);
        return RegisterResult.builder()
                .user(newUser)
                .loginResult(LoginResult.builder()
                        .accessToken(jwtService.generateAccessToken(newUser))
                        .refreshToken(jwtService.generateRefreshToken(newUser))
                        .build())
                .build();
    }

    public RefreshResult refresh(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new AuthenticationCredentialsNotFoundException("Missing refresh token");
        }
        final String refreshToken = authorizationHeader.replace("Bearer ", "");
        final String userEmail = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userRepository.findByEmail(userEmail).orElse(null);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // <revoke token here, if wanted>
        if (!jwtService.isTokenValid(refreshToken, userDetails)) {
            throw new AuthenticationCredentialsNotFoundException("Invalid refresh token");
        }
        return RefreshResult.builder()
                .accessToken(jwtService.generateAccessToken(userDetails))
                .build();
    }
}
