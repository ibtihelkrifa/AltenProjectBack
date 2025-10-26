package com.altran.product_trial.infrastructure.repository;

import com.altran.product_trial.domain.port.out.UserRepositoryPort;
import com.altran.product_trial.infrastructure.entity.User;
import com.altran.product_trial.infrastructure.jpa.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    @Autowired
    UserJPARepository userJPARepository;

    @Override
    public boolean existsByEmail(String email) {
        return userJPARepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        userJPARepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJPARepository.findByEmail(email);
    }
}