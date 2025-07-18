package de.localrag.backend.service.auth;

import de.localrag.backend.model.auth.LoginResult;
import de.localrag.backend.model.auth.RegisterResult;
import de.localrag.backend.model.user.User;
import de.localrag.backend.model.user.UserRole;
import de.localrag.backend.respository.user.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
                .accessToken(jwtService.generateToken(user))
                .refreshToken("<no refresh>")
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
                        .accessToken(jwtService.generateToken(newUser))
                        .refreshToken("no refresh")
                        .build())
                .build();
    }
}
