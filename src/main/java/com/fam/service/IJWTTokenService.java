package com.fam.service;

import com.fam.dto.auth.TokenRefreshResponse;
import com.fam.entity.TaiKhoan;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IJWTTokenService {
    void addJWTTokenToHeader(HttpServletResponse response, String username) throws IOException;

    Authentication parseTokenToUserInformation(HttpServletRequest request);

    boolean isValidRefreshToken(String refreshToken);

    boolean isValidResetPasswordToken(String resetPasswordToken);

    String createNewRefreshToken(TaiKhoan taiKhoan);

    TokenRefreshResponse refreshToken(String refreshToken);
}
