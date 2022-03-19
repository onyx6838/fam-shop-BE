package com.fam.entity.authentication;

import com.fam.entity.TaiKhoan;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ResetPasswordToken")
@NoArgsConstructor
public class ResetPasswordToken extends Token{
    public ResetPasswordToken(String token, TaiKhoan taiKhoan, long expiredTime) {
        super(token, taiKhoan, expiredTime);
    }
}
