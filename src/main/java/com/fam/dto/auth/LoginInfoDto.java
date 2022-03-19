package com.fam.dto.auth;

import com.fam.entity.enumerate.TrangThaiTK;
import lombok.Data;

@Data
public class LoginInfoDto {
    private String hoTen;
    private String loaiTK;
    private String token;
    private String refreshToken;
    private TrangThaiTK status;
    private String tenTK;
    private String email;
}
