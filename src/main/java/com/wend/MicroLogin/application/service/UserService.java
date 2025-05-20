package com.wend.MicroLogin.application.service;

import com.wend.MicroLogin.config.ExceptionMessages;
import com.wend.MicroLogin.domain.exception.InvalidPasswordException;
import com.wend.MicroLogin.domain.exception.UserNotFoundException;
import com.wend.MicroLogin.domain.port.in.ManageUserUserCase;
import com.wend.MicroLogin.infraestructure.in.rest.dto.UserRequestDto;
import com.wend.MicroLogin.domain.exception.UserAlreadyExistsException;
import com.wend.MicroLogin.domain.model.User;
import com.wend.MicroLogin.domain.port.in.CreateUserUseCase;
import com.wend.MicroLogin.domain.port.out.UserRepositoryPort;
import com.wend.MicroLogin.infraestructure.in.rest.dto.response.UserResponseDto;
import com.wend.MicroLogin.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.invoke.MethodHandles;

public class UserService implements CreateUserUseCase, ManageUserUserCase {


    private final BCryptPasswordEncoder encoder;
    private final UserRepositoryPort userRepository;

    public UserService(BCryptPasswordEncoder encoder, UserRepositoryPort userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User newUser) {
        userRepository.findByEmail(newUser.getEmail()).
                ifPresent(user -> {throw new UserAlreadyExistsException(ExceptionMessages.EMAIL_ALREADY_USED.getMessage());
                });
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User getCurrentUser(String email) {
        var user = userRepository.findByEmail(email).
                orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND.getMessage()));
        return user;
    }

    @Override
    public User updateUser(Long userId, User updated) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND.getMessage()));
        if(!updated.getEmail().isBlank() && updated.getEmail() != null && !updated.getEmail().equals(user.getEmail())){
            userRepository.findByEmail(updated.getEmail()).
                    ifPresent(e -> {throw new UserAlreadyExistsException(ExceptionMessages.EMAIL_ALREADY_USED.getMessage());
                    });
            user.setEmail(updated.getEmail());
        }
        if(!updated.getName().isBlank() && updated.getName() != null && !updated.getName().equals(user.getName())){
            userRepository.findByName(updated.getName()).
                    ifPresent(e -> {throw new UserAlreadyExistsException(ExceptionMessages.EMAIL_ALREADY_USED.getMessage());
                    });
            user.setName(updated.getName());
        }

        return userRepository.save(user);
    }

    @Override
    public void updatePassword(String current, String updated, Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> { throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND.getMessage());
                });
        if(!encoder.matches(current, user.getPassword())){
            throw new InvalidPasswordException(ExceptionMessages.INCORRECT_PASSWORD.getMessage());
        }

        String encryptedPassword = encoder.encode(updated);
        user.setPassword(encryptedPassword);
        userRepository.save(user);

    }


}
