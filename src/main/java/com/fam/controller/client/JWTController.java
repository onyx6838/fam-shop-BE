package com.fam.controller.client;

import com.fam.dto.auth.TokenRefreshRequest;
import com.fam.dto.auth.TokenRefreshResponse;
import com.fam.service.IJWTTokenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "JWT REST", description = "Manage JWT Api", tags = "API liên quan đến JWT")
@RestController
@RequestMapping(value = "api/v1/jwt")
@CrossOrigin("*")
public class JWTController {
    @Autowired
    private IJWTTokenService jwtTokenService;

    // refresh token
    @PostMapping("/token/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest refreshToken) {
        if (!jwtTokenService.isValidRefreshToken(refreshToken.getRefreshToken())) {
            return new ResponseEntity<>("Refresh Token is invalid!", HttpStatus.SERVICE_UNAVAILABLE);
        }
        TokenRefreshResponse newTokenDto = jwtTokenService.refreshToken(refreshToken.getRefreshToken());
        return new ResponseEntity<>(newTokenDto, HttpStatus.OK);
    }

}
