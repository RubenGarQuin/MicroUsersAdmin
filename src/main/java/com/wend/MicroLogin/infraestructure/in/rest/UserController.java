package com.wend.MicroLogin.infraestructure.in.rest;

import com.wend.MicroLogin.config.dto.UserRequestDto;
import com.wend.MicroLogin.domain.model.User;
import com.wend.MicroLogin.domain.port.in.CreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserRequestDto userdto) {
        return ResponseEntity.ok(createUserUseCase.createUser(userdto));
    }
}