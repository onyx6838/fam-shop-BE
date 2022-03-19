package com.fam.entity.authentication;

import com.fam.entity.TaiKhoan;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RefreshToken")
@NoArgsConstructor
public class RefreshToken extends Token{
    public RefreshToken(String token, TaiKhoan taiKhoan, long expiredTime) {
        super(token, taiKhoan, expiredTime);
    }
}
