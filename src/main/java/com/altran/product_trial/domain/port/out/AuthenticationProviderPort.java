package com.altran.product_trial.domain.port.out;

import org.springframework.stereotype.Component;

@Component
public interface AuthenticationProviderPort {
    void authenticate(String email, String password);
}
