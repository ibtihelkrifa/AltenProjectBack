package com.altran.product_trial.domain.service;

import com.altran.product_trial.domain.model.User;
import com.altran.product_trial.domain.port.in.UserService;
import com.altran.product_trial.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        if (userRepositoryPort.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }

        User userToSave = User.builder()
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();

        userRepositoryPort.save(userToSave);
        return user;
    }
}
