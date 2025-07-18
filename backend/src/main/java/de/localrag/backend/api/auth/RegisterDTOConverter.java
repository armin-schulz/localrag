package de.localrag.backend.api.auth;

import de.localrag.backend.api.DTOConverter;
import de.localrag.backend.api.user.UserDTOConverter;
import de.localrag.backend.model.auth.RegisterResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterDTOConverter implements DTOConverter<RegisterResult, RegisterResponse> {
    private final UserDTOConverter userDTOConverter;
    private final LoginDTOConverter loginDTOConverter;

    @Override
    public RegisterResponse toDTO(RegisterResult entity) {
        return RegisterResponse.builder()
                .user(userDTOConverter.toDTO(entity.getUser()))
                .loginResponse(loginDTOConverter.toDTO(entity.getLoginResult()))
                .build();
    }
}
