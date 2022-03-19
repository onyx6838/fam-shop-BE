package com.fam.service;

import com.fam.entity.TaiKhoan;
import com.fam.entity.authentication.ResetPasswordToken;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ITaiKhoanService extends UserDetailsService {
    TaiKhoan getTaiKhoanByTenTK(String tenTK);

    TaiKhoan findById(int maTK);

    void createTaiKhoan(TaiKhoan taiKhoan);

    TaiKhoan findTaiKhoanByEmail(String email);

    //void changeUserProfile(String username, ChangePublicProfileDto dto);

    void activeAccount(String token);

    void sendConfirmAccountRegistrationViaEmail(String email);

    void resetPassword(String token, String newPassword);

    ResetPasswordToken getResetPasswordToken(String token);

    void deleteResetPasswordTokenByTaiKhoanId(int maTK);

    boolean existsByEmail(String email);

    boolean existsByTenTK(String tenTK);

    void resetPasswordViaEmail(String email);
}
