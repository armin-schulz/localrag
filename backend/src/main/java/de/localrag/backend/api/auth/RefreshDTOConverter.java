package de.localrag.backend.api.auth;

import de.localrag.backend.api.DTOConverter;
import de.localrag.backend.model.auth.LoginResult;
import org.springframework.stereotype.Component;

@Component
public class RefreshDTOConverter implements DTOConverter<LoginResult.RefreshResult, RefreshResponse> {
    @Override
    public RefreshResponse toDTO(LoginResult.RefreshResult entity) {
        return RefreshResponse.builder()
                .accessToken(entity.getAccessToken())
                .build();
    }
}
