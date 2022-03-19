package com.fam.service.impl;

import com.fam.dto.auth.LoginInfoDto;
import com.fam.dto.auth.TokenRefreshResponse;
import com.fam.entity.TaiKhoan;
import com.fam.entity.authentication.RefreshToken;
import com.fam.repository.authentication.IRefreshTokenRepository;
import com.fam.repository.authentication.IResetPasswordTokenRepository;
import com.fam.service.IJWTTokenService;
import com.fam.service.ITaiKhoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.modelmapper.ModelMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Transactional
@Service
public class JWTTokenService implements IJWTTokenService {
    @Value("${jwt.token.prefix}")
    private String tokenPrefix;

    @Value("${jwt.token.authorization}")
    private String tokenAuthorization;

    @Value("${jwt.token.expired-time}")
    private long tokenExpiredTime;

    @Value("${jwt.token.secret}")
    private String tokenSecret;

    @Value("${jwt.refresh-token.expired-time}")
    private long refreshTokenExpiredTime;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ITaiKhoanService taiKhoanService;

    @Autowired
    private IRefreshTokenRepository refreshTokenRepository;

    @Autowired
    private IResetPasswordTokenRepository resetPasswordTokenRepository;

    // fix change to add to body get more data
    @Override
    public void addJWTTokenToHeader(HttpServletResponse response, String username) throws IOException {
        // get user info
        TaiKhoan account = taiKhoanService.getTaiKhoanByTenTK(username);
        // get jwt code
        String jwt = generateJWTFromUsername(username);
        String refreshToken = createNewRefreshToken(account);
        //convert account to dto
        LoginInfoDto userDto = modelMapper.map(account, LoginInfoDto.class);
        userDto.setToken(jwt);
        userDto.setRefreshToken(refreshToken);
        // convert to json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(userDto);
        // add to response body
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        //response.addHeader(tokenAuthorization, tokenPrefix + " " + jwt);
    }

    @Override
    public Authentication parseTokenToUserInformation(HttpServletRequest request) {
        String jwt = request.getHeader(tokenAuthorization);

        if (jwt == null) {
            return null;
        }

        jwt = jwt.replace(tokenPrefix, "");

        if (!isValidJwt(jwt)) {
            return null;
        }

        // parse the token
        String username = parseJWTToUsername(jwt);

        TaiKhoan user = taiKhoanService.getTaiKhoanByTenTK(username);

        return user != null ?
                new UsernamePasswordAuthenticationToken(username,
                        null, AuthorityUtils.createAuthorityList(user.getLoaiTK())) : null;
    }

    private boolean isValidJwt(String jwt) {
        try {
            Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isValidRefreshToken(String refreshToken) {
        return refreshTokenRepository.existsByTokenAndExpiredDateGreaterThan(refreshToken, new Date());
    }

    @Override
    public boolean isValidResetPasswordToken(String resetPasswordToken) {
        return resetPasswordTokenRepository.existsByTokenAndExpiredDateGreaterThan(resetPasswordToken, new Date());
    }

    @Override
    public String createNewRefreshToken(TaiKhoan taiKhoan) {
        // create new refresh token
        String newToken = UUID.randomUUID().toString();
        RefreshToken token = new RefreshToken(newToken, taiKhoan, refreshTokenExpiredTime);

        // create new token
        refreshTokenRepository.save(token);

        return newToken;
    }

    @Override
    public TokenRefreshResponse refreshToken(String refreshToken) {
        RefreshToken refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken);
        TaiKhoan taiKhoan = refreshTokenEntity.getTaiKhoan();

        // create new token
        String newToken = generateJWTFromUsername(taiKhoan.getTenTK());
        String newRefreshToken = createNewRefreshToken(taiKhoan);

        // remove old Refresh Token if exists
        refreshTokenRepository.deleteByToken(refreshToken);

        return
                TokenRefreshResponse.builder().token(newToken).refreshToken(newRefreshToken).id(taiKhoan.getMaTK())
                .fullName(taiKhoan.getHoTen()).role(taiKhoan.getLoaiTK()).build();
    }

    private String generateJWTFromUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiredTime))
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    private String parseJWTToUsername(String jwt) {
        return Jwts.parser()
                .setSigningKey(tokenSecret)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

//    private Key createKeyFromSecretToken() {
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(tokenSecret);
//        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
//    }
}
