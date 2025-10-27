package com.altran.product_trial.domain.port.in;

import com.altran.product_trial.infrastructure.dto.AuthRequestDTO;

public interface AuthService {

    String authenticate(String email, String password);
}
