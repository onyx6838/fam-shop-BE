package com.fam.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class TokenRefreshResponse {
    private int maTK;
    private String hoTen;
    private String loaiTK;
    private String token;
    private String refreshToken;
}
