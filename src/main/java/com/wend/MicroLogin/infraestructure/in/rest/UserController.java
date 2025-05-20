package com.wend.MicroLogin.infraestructure.in.rest;

import com.wend.MicroLogin.config.ExceptionMessages;
import com.wend.MicroLogin.domain.exception.InvalidPasswordException;
import com.wend.MicroLogin.domain.port.in.ManageUserUserCase;
import com.wend.MicroLogin.infraestructure.in.rest.dto.ChangePasswordRequest;
import com.wend.MicroLogin.infraestructure.in.rest.dto.UpdateUserRequestDto;
import com.wend.MicroLogin.infraestructure.in.rest.dto.UserRequestDto;
import com.wend.MicroLogin.domain.port.in.CreateUserUseCase;
import com.wend.MicroLogin.infraestructure.in.rest.dto.response.UserResponseDto;
import com.wend.MicroLogin.infraestructure.security.CustomUserDetails;
import com.wend.MicroLogin.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    private final ManageUserUserCase manageUserUserCase;

    public UserController(CreateUserUseCase createUserUseCase, ManageUserUserCase getCurrentUser) {
        this.createUserUseCase = createUserUseCase;
        this.manageUserUserCase = getCurrentUser;
    }

    @PostMapping("create")
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserRequestDto userdto) {
        var newuser = createUserUseCase.createUser(UserMapper.toDomain(userdto));
        return ResponseEntity.ok(UserMapper.toDto(newuser));
    }

    @GetMapping("me")
    public ResponseEntity<UserResponseDto> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails){
        var user = manageUserUserCase.getCurrentUser(userDetails.getUsername());
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @PutMapping("update")
    public ResponseEntity<UserResponseDto> updateUser(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                      @RequestBody UpdateUserRequestDto updateUserRequestDto){
        var user = manageUserUserCase.updateUser(userDetails.getId(),UserMapper.toDomain(updateUserRequestDto));
        return ResponseEntity.ok(UserMapper.toDto(user));

    }

    @PutMapping("me/password")
    public ResponseEntity<Void> changePassword(@AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody ChangePasswordRequest request){
        manageUserUserCase.updatePassword(request.getCurrentPassword(),request.getNewPassword(),userDetails.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}