package com.altran.product_trial.infrastructure.adapter;

import com.altran.product_trial.domain.port.out.AuthenticationProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class AuthenticationProviderAdapter implements AuthenticationProviderPort {

    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }
}
