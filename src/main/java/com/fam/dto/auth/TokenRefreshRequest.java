package com.fam.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;
}
