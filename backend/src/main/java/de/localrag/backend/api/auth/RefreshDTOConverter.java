package de.localrag.backend.api.auth;

import de.localrag.backend.api.DTOConverter;
import de.localrag.backend.model.auth.RefreshResult;
import org.springframework.stereotype.Component;

@Component
public class RefreshDTOConverter implements DTOConverter<RefreshResult, RefreshResponse> {
    @Override
    public RefreshResponse toDTO(RefreshResult entity) {
        return RefreshResponse.builder()
                .accessToken(entity.getAccessToken())
                .build();
    }
}
