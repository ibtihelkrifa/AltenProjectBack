package com.altran.product_trial.infrastructure.controller;

import com.altran.product_trial.domain.port.in.AuthService;
import com.altran.product_trial.infrastructure.dto.AuthRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody AuthRequestDTO request) {
        String token = authService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }
}