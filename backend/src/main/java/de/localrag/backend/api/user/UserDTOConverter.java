package de.localrag.backend.api.user;

import de.localrag.backend.api.DTOConverter;
import de.localrag.backend.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter implements DTOConverter<User, UserDTO> {
    @Override
    public UserDTO toDTO(User entity) {
        if (entity == null) {
            return null;
        }
        return UserDTO.builder()
                .id(entity.getId())
                .displayName(entity.getDisplayName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return User.builder()
                .id(userDTO.getId())
                .displayName(userDTO.getDisplayName())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .active(userDTO.isActive())
                .createdAt(userDTO.getCreatedAt())
                .build();
    }
}
