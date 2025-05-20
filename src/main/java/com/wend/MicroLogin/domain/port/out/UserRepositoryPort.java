package com.wend.MicroLogin.domain.port.out;

import com.wend.MicroLogin.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    Optional<User> findById(Long id);
}
