package com.altran.product_trial.infrastructure.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequestDTO {
    private String email;
    private String password;
}