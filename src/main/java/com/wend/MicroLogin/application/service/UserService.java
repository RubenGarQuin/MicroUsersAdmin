package com.wend.MicroLogin.application.service;

import com.wend.MicroLogin.config.dto.UserRequestDto;
import com.wend.MicroLogin.domain.exception.UserAlreadyExistsException;
import com.wend.MicroLogin.domain.model.User;
import com.wend.MicroLogin.domain.port.in.CreateUserUseCase;
import com.wend.MicroLogin.domain.port.out.UserRepositoryPort;
import com.wend.MicroLogin.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService implements CreateUserUseCase {


    private final BCryptPasswordEncoder encoder;
    private final UserRepositoryPort userRepository;

    public UserService(BCryptPasswordEncoder encoder, UserRepositoryPort userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }


    @Override
    public User  createUser(UserRequestDto userDto) {
        userRepository.findByEmail(userDto.getEmail()).
                ifPresent(user -> {throw new UserAlreadyExistsException("Ya existe un usario con ese email");
                });
        var user = UserMapper.toDomain(userDto);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
