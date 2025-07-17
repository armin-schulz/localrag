package de.localrag.backend.model.auth;

import de.localrag.backend.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResult {
    private User user;
    private LoginResult loginResult;
}
