package com.wend.MicroLogin.infraestructure.out.persistence;

import com.wend.MicroLogin.domain.model.User;
import com.wend.MicroLogin.domain.port.out.UserRepositoryPort;
import com.wend.MicroLogin.infraestructure.out.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserJpaAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    public UserJpaAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }


    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());

        UserEntity saved = userJpaRepository.save(entity);
        return new User(saved.getId(), saved.getName(), saved.getEmail(), saved.getPassword());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(e -> new User(e.getId(), e.getName(), e.getEmail(), e.getPassword()));
    }
}
