package de.localrag.backend.api.auth;

import de.localrag.backend.api.DTOConverter;
import de.localrag.backend.model.auth.LoginResult;
import org.springframework.stereotype.Component;

@Component
public class LoginDTOConverter implements DTOConverter<LoginResult, LoginResponse> {
    @Override
    public LoginResponse toDTO(LoginResult entity) {
        return LoginResponse.builder()
                .accessToken(entity.getAccessToken())
                .refreshToken(entity.getRefreshToken())
                .build();
    }
}
