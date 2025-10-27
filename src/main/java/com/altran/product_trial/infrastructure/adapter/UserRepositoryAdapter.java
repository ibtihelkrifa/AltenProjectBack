package com.altran.product_trial.infrastructure.adapter;

import com.altran.product_trial.domain.model.User;
import com.altran.product_trial.domain.port.out.UserRepositoryPort;
import com.altran.product_trial.infrastructure.jpa.UserJPARepository;
import com.altran.product_trial.infrastructure.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJPARepository userJPARepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public boolean existsByEmail(String email) {
        return userJPARepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        userJPARepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJPARepository.findByEmail(email).map(u -> userEntityMapper.toDomain(u));
    }
}