package de.localrag.backend.api.user;

import de.localrag.backend.model.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String displayName;
    private String email;
    private UserRole role;
    private boolean active;
    private LocalDateTime createdAt;


}
