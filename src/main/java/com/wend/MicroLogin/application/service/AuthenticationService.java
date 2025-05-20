package com.wend.MicroLogin.application.service;


import com.wend.MicroLogin.domain.model.User;
import com.wend.MicroLogin.domain.port.in.LoginUseCase;
import com.wend.MicroLogin.domain.port.out.UserRepositoryPort;
import com.wend.MicroLogin.infraestructure.in.rest.dto.LoginRequestDto;
import com.wend.MicroLogin.infraestructure.in.rest.dto.response.LoginResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements LoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepositoryPort userRepository;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService, UserRepositoryPort userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public LoginResponseDto login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponseDto(token, user.getName());
    }
}