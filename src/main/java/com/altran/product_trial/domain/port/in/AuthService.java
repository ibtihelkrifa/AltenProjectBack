package com.altran.product_trial.domain.port.in;

import com.altran.product_trial.infrastructure.dto.AuthRequest;

public interface AuthService {

    String authenticate(AuthRequest request);
}
