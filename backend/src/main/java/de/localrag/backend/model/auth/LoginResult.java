package de.localrag.backend.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {
    private String accessToken;
    private String refreshToken;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RefreshResult {
        private String accessToken;
    }
}
