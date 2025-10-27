package com.altran.product_trial.infrastructure.adapter;

import com.altran.product_trial.domain.port.out.JwtProviderPort;
import com.altran.product_trial.infrastructure.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProviderAdapter implements JwtProviderPort {

    private final JwtService jwtService;

    @Override
    public String generateToken(UserDetails userDetails) {
        return jwtService.generateToken(userDetails);
    }
}
