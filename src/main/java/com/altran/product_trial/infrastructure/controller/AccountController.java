package com.altran.product_trial.infrastructure.controller;

import com.altran.product_trial.domain.model.User;
import com.altran.product_trial.domain.port.in.UserService;
import com.altran.product_trial.infrastructure.dto.UserDTO;
import com.altran.product_trial.infrastructure.mapper.UserDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    private final UserDtoMapper userDtoMapper;

    @PostMapping
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO userDTO) {
        User user = userService.createUser(userDtoMapper.toDomain(userDTO));
        return ResponseEntity.ok(userDtoMapper.toDTO(user));
    }
}
