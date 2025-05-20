package com.wend.MicroLogin.infraestructure.out.persistence;

import com.wend.MicroLogin.domain.model.User;
import com.wend.MicroLogin.infraestructure.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByName (String name);
    Optional<UserEntity> findById   (Long id);

}
