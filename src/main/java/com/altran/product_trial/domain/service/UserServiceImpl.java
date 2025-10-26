package com.altran.product_trial.domain.service;

import com.altran.product_trial.domain.port.in.UserService;
import com.altran.product_trial.domain.port.out.UserRepositoryPort;
import com.altran.product_trial.infrastructure.dto.UserDTO;
import com.altran.product_trial.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        userRepository.save(user);
        return dto;
    }
}
