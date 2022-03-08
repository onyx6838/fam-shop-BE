package com.fam.config.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                .antMatchers("api/v1/sanphams").permitAll()
                .antMatchers("api/v1/loaisanphams").permitAll()
                .antMatchers("api/v1/dactrung").permitAll()
                .antMatchers("api/v1/giohang").permitAll()
                .and().httpBasic()
                .and().csrf().disable();
    }
}
