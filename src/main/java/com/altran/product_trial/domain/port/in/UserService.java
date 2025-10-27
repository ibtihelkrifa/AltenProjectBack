package com.altran.product_trial.domain.port.in;

import com.altran.product_trial.domain.model.User;
import com.altran.product_trial.infrastructure.dto.UserDTO;

public interface UserService {
    User createUser(User user);
}
