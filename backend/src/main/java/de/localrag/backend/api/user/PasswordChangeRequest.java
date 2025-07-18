package de.localrag.backend.api.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static de.localrag.backend.api.auth.validation.ValidationConstants.PASSWORD_MIN_LENGTH;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeRequest {
    @NotBlank(message = "current password is required")
    private String currentPassword;

    @NotBlank(message = "new password is required")
    @Size(min = PASSWORD_MIN_LENGTH, message = "new password needs to have at least" + PASSWORD_MIN_LENGTH + " characters")
    private String newPassword;
}
