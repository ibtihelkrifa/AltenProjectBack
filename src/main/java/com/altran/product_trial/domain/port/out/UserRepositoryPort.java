package com.altran.product_trial.domain.port.out;

import com.altran.product_trial.infrastructure.entity.User;

import java.util.Optional;

public interface UserRepositoryPort {
    boolean existsByEmail(String email);

    void save(User user);

    Optional<User> findByEmail(String email);
}
