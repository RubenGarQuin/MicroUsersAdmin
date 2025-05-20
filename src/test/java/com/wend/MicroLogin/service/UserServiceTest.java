package com.wend.MicroLogin.service;


import com.wend.MicroLogin.application.service.UserService;
import com.wend.MicroLogin.infraestructure.in.rest.dto.UserRequestDto;
import com.wend.MicroLogin.domain.model.User;
import com.wend.MicroLogin.domain.port.out.UserRepositoryPort;
import com.wend.MicroLogin.infraestructure.in.rest.dto.response.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {

    private UserService userService;
    private UserRepositoryPort userRepositoryPort;
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        userRepositoryPort = Mockito.mock(UserRepositoryPort.class);
        encoder = new BCryptPasswordEncoder();
        userService = new UserService(encoder,userRepositoryPort );
    }

    @Test
    void shouldCreateUserSuccessfully() {
        User user = new User();
        user.setName("Juan");
        user.setEmail("juan@mail.com");
        user.setPassword("123456");

        Mockito.when(userRepositoryPort.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        User savedUser = userService.createUser(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo("juan@mail.com");
        assertThat(encoder.matches("123456", savedUser.getPassword())).isTrue();
    }
}
