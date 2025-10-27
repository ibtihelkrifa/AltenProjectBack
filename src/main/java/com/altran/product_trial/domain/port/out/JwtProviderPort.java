package com.altran.product_trial.domain.port.out;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public interface JwtProviderPort {

    String generateToken(UserDetails userDetails);

}

