package com.wend.MicroLogin.infraestructure.in.rest;

import com.wend.MicroLogin.application.service.AuthenticationService;
import com.wend.MicroLogin.domain.port.in.LoginUseCase;
import com.wend.MicroLogin.infraestructure.in.rest.dto.LoginRequestDto;
import com.wend.MicroLogin.infraestructure.in.rest.dto.response.LoginResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController( LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        LoginResponseDto response = loginUseCase.login(request.getEmail(),request.getPassword());
        return ResponseEntity.ok(response);
    }
}