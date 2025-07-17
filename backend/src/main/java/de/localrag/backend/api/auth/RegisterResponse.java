package de.localrag.backend.api.auth;

import de.localrag.backend.api.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private UserDTO user;
    private LoginResponse loginResponse;
}
