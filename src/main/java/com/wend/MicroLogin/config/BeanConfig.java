package com.wend.MicroLogin.config;

import com.wend.MicroLogin.application.service.UserService;
import com.wend.MicroLogin.domain.port.in.CreateUserUseCase;
import com.wend.MicroLogin.infraestructure.out.persistence.UserJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserJpaAdapter adapter, BCryptPasswordEncoder encoder) {
        return new UserService(encoder, adapter);
    }
}