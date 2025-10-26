package com.altran.product_trial.domain.service;

import com.altran.product_trial.domain.port.in.AuthService;
import com.altran.product_trial.domain.port.out.UserRepositoryPort;
import com.altran.product_trial.infrastructure.dto.AuthRequest;
import com.altran.product_trial.infrastructure.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepositoryPort userRepository;
    private final JwtService jwtService;

    public String authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails userDetails = userRepository.findByEmail(request.getEmail())
                .map(u -> User
                        .withUsername(u.getEmail())
                        .password(u.getPassword())
                        .authorities("USER")
                        .build())
                .orElseThrow();
        return jwtService.generateToken(userDetails);
    }
}