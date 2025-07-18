package de.localrag.backend.api.auth;

import de.localrag.backend.api.auth.validation.ValidOptionalString;
import jakarta.validation.constraints.Email;
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
public class RegisterRequest {
    @NotBlank(message = "email required")
    @Email(message = "valid email required")
    private String email;
    @ValidOptionalString(message = "display name must not be blank")
    private String displayName;
    @NotBlank(message = "password is required")
    @Size(min = PASSWORD_MIN_LENGTH, message = "password needs to have at least" + PASSWORD_MIN_LENGTH + " characters")
    private String password;
}
