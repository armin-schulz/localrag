package de.localrag.backend.api.auth;

import de.localrag.backend.api.user.UserDTOConverter;
import de.localrag.backend.model.auth.LoginResult;
import de.localrag.backend.model.auth.RegisterResult;
import de.localrag.backend.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserDTOConverter userDTOConverter;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(LoginResponse.builder()
                .accessToken(authenticationService.login(request.getEmail(), request.getPassword()))
                .refreshToken("no refresh")
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResult registerResult = authenticationService.register(
                request.getEmail(), request.getPassword(), request.getDisplayName());
        LoginResult loginResult = registerResult.getLoginResult();
        return ResponseEntity.status(HttpStatus.CREATED).body(
                RegisterResponse.builder()
                        .user(userDTOConverter.toDTO(registerResult.getUser()))
                        .loginResponse(LoginResponse.builder()
                                .accessToken(loginResult.getAccessToken())
                                .refreshToken(loginResult.getRefreshToken())
                                .build())
                        .build());
    }
}
