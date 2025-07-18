package de.localrag.backend.api.user;

import de.localrag.backend.api.DTOConverter;
import de.localrag.backend.model.user.User;
import de.localrag.backend.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final DTOConverter<User, UserDTO> userConverter;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers().stream().map(userConverter::toDTO).toList());
    }

    @PatchMapping("user/change-password")
    public ResponseEntity<Void> changePassword(Principal userPrinciple,
                                               @Valid @RequestBody PasswordChangeRequest request) {
            userService.changePassword(userPrinciple, request.getCurrentPassword(), request.getNewPassword());
            return ResponseEntity.ok().build();
    }
}
