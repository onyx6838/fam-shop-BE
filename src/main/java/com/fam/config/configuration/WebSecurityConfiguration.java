package com.fam.config.configuration;

import com.fam.config.configuration.authentication.JWTAuthenticationFilter;
import com.fam.config.configuration.authentication.JWTAuthorizationFilter;
import com.fam.service.ITaiKhoanService;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ITaiKhoanService taiKhoanService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(taiKhoanService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
                .antMatchers("/api/v1/sanphams").permitAll()
                .antMatchers("/api/v1/loaisanphams").permitAll()
                .antMatchers("/api/v1/dactrung").permitAll()
                .antMatchers("/api/v1/giohang").permitAll()
                .antMatchers("/api/v1/thuonghieu").permitAll()
                .antMatchers("/api/v1/jwt").permitAll()
                .antMatchers("/api/v1/taikhoan").authenticated()
                .antMatchers("/api/v1/login").anonymous()
                .and().httpBasic()
                .and().csrf().disable()
                .addFilterBefore(
                        JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class
                ).addFilterBefore(
                        JWTAuthorizationFilter(),
                        UsernamePasswordAuthenticationFilter.class
                );
    }

    @Bean
    public JWTAuthenticationFilter JWTAuthenticationFilter() throws Exception {
        return new JWTAuthenticationFilter("/api/v1/login", authenticationManager());
    }

    @Bean
    public JWTAuthorizationFilter JWTAuthorizationFilter() {
        return new JWTAuthorizationFilter();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}