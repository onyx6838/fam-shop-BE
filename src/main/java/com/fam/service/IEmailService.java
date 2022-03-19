package com.fam.service;

import com.fam.entity.TaiKhoan;

public interface IEmailService {
    void sendRegistrationUserConfirm(String email);

    void sendResetPassword(TaiKhoan account, String token);
}
