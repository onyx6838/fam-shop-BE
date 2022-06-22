package com.fam.config.configuration.authentication;

import com.fam.service.IJWTTokenService;
import com.fam.service.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.ObjectUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    @Autowired
    private IJWTTokenService jwtTokenService;

    @Autowired
    private ITaiKhoanService taiKhoanService;

    public JWTAuthenticationFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String role;
        String userName;
        if(ObjectUtils.isEmpty(username)) {
            role = taiKhoanService.findByPhone(phone).getLoaiTK();
            userName = taiKhoanService.findByPhone(phone).getTenTK();
        }
        else {
            role = taiKhoanService.getTaiKhoanByTenTK(username).getLoaiTK();
            userName = username;
        }
        UsernamePasswordAuthenticationToken s = new UsernamePasswordAuthenticationToken(
                userName, password,
                AuthorityUtils.createAuthorityList(role)
        );
        return getAuthenticationManager().authenticate(s);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        jwtTokenService.addJWTTokenToHeader(response, authResult.getName());
    }
}
