package com.altran.product_trial.domain.port.in;

import com.altran.product_trial.infrastructure.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
}
