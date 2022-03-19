package com.fam.entity.authentication;

import com.fam.entity.TaiKhoan;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RegistrationUserToken")
@NoArgsConstructor
public class RegistrationUserToken extends Token {
    public RegistrationUserToken(String token, TaiKhoan taiKhoan, long expiredTime) {
        super(token, taiKhoan, expiredTime);
    }
}
