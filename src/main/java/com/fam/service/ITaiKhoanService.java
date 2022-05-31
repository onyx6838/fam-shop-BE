package com.fam.service;

import com.fam.dto.form.ProfileDto;
import com.fam.dto.form.TaiKhoanAdminUpdateDto;
import com.fam.entity.TaiKhoan;
import com.fam.entity.authentication.ResetPasswordToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ITaiKhoanService extends UserDetailsService {
    Page<TaiKhoan> getAllTaiKhoans(Pageable pageable);

    TaiKhoan getTaiKhoanByTenTK(String tenTK);

    TaiKhoan findById(int maTK);

    void createTaiKhoan(TaiKhoan taiKhoan);

    TaiKhoan findTaiKhoanByEmail(String email);

    void changeUserProfile(String tenTK, ProfileDto dto);

    void activeAccount(String token);

    void sendConfirmAccountRegistrationViaEmail(String email);

    void resetPassword(String token, String newPassword);

    ResetPasswordToken getResetPasswordToken(String token);

    void deleteResetPasswordTokenByTaiKhoanId(int maTK);

    boolean existsByEmail(String email);

    boolean existsByTenTK(String tenTK);

    void resetPasswordViaEmail(String email);

    Page<TaiKhoan> getAccountsByLoaiTK(String loaiTK, Pageable pageable);

    boolean lockAccount(int maTK);
    boolean unlockAccount(int maTK);

    boolean updateAccountInAdmin(int maTK, TaiKhoanAdminUpdateDto form);
}
