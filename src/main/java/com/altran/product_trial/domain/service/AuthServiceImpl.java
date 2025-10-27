package com.altran.product_trial.domain.service;

import com.altran.product_trial.domain.port.in.AuthService;
import com.altran.product_trial.domain.port.out.AuthenticationProviderPort;
import com.altran.product_trial.domain.port.out.JwtProviderPort;
import com.altran.product_trial.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationProviderPort authenticationProviderPort;
    private final UserRepositoryPort userRepositoryPort;
    private final JwtProviderPort jwtProviderPort;

    public String authenticate(String email, String password) {
        authenticationProviderPort.authenticate(email, password);
        UserDetails userDetails = userRepositoryPort.findByEmail(email)
                .map(u -> User
                        .withUsername(u.getEmail())
                        .password(u.getPassword())
                        .authorities("USER")
                        .build())
                .orElseThrow();
        return jwtProviderPort.generateToken(userDetails);
    }
}