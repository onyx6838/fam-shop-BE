package com.fam.service.impl;

import com.fam.config.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.fam.dto.form.ProfileDto;
import com.fam.dto.form.TaiKhoanAdminCreateDto;
import com.fam.dto.form.TaiKhoanAdminUpdateDto;
import com.fam.entity.TaiKhoan;
import com.fam.entity.authentication.RegistrationUserToken;
import com.fam.entity.authentication.ResetPasswordToken;
import com.fam.entity.enumerate.TrangThaiTK;
import com.fam.repository.ITaiKhoanRepository;
import com.fam.repository.authentication.IRegistrationAccountTokenRepository;
import com.fam.repository.authentication.IResetPasswordTokenRepository;
import com.fam.service.IEmailService;
import com.fam.service.ITaiKhoanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Transactional
@Service
public class TaiKhoanService implements ITaiKhoanService {
    @Autowired
    private ITaiKhoanRepository taiKhoanRepository;

    @Autowired
    private IResetPasswordTokenRepository resetPasswordTokenRepository;

    // thong bao cho noi khac (ben thu 3)
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private IRegistrationAccountTokenRepository registrationAccountTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IEmailService emailService;

    @Value("${reset-password.token.expired-time}")
    private long resetPasswordTokenExpiredTime;

    @Value("${registration.token.expired-time}")
    private long registrationPasswordTokenExpiredTime;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenTK(username);
        if (taiKhoan == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(taiKhoan.getTenTK(), taiKhoan.getMatKhau(),
                AuthorityUtils.createAuthorityList(taiKhoan.getLoaiTK()));
    }

    @Override
    public Page<TaiKhoan> getAllTaiKhoans(Pageable pageable) {
        return taiKhoanRepository.findAll(pageable);
    }

    /**
     * Find TaiKhoan by email , tenTK
     */
    @Override
    public TaiKhoan getTaiKhoanByTenTK(String tenTK) {
        return taiKhoanRepository.findByTenTK(tenTK);
    }

    @Override
    public TaiKhoan findById(int maTK) {
        return taiKhoanRepository.findById(maTK).get();
    }

    @Override
    public TaiKhoan findByPhone(String phone) {
        return taiKhoanRepository.findBySdt(phone);
    }

    @Override
    public TaiKhoan findTaiKhoanByEmail(String email) {
        return taiKhoanRepository.findByEmail(email);
    }

    @Override
    public void changeUserProfile(String tenTK, ProfileDto dto) {
        TaiKhoan tk = getTaiKhoanByTenTK(tenTK);
        if (tk != null) {
            BeanUtils.copyProperties(dto, tk);
            taiKhoanRepository.save(tk);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return taiKhoanRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByTenTK(String tenTK) {
        return taiKhoanRepository.existsByTenTK(tenTK);
    }

    /**
     * Registration && Reset password
     */

    @Override
    public void createTaiKhoan(TaiKhoan taiKhoan) {
        // create user
        taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoan.getMatKhau()));
        taiKhoan.setLoaiTK("NGUOI_DUNG");
        taiKhoanRepository.save(taiKhoan);

        // create new user registration token
        createNewRegistrationUserToken(taiKhoan);

        // send email to confirm
        sendConfirmAccountRegistrationViaEmail(taiKhoan.getEmail());
    }

    @Override
    public void activeAccount(String token) {
        RegistrationUserToken registrationUserToken = registrationAccountTokenRepository.findByToken(token);

        TaiKhoan account = registrationUserToken.getTaiKhoan();
        account.setTrangThai(TrangThaiTK.ACTIVE);

        taiKhoanRepository.save(account);

        // remove Registration User Token
        registrationAccountTokenRepository.deleteById(registrationUserToken.getId());
    }

    @Override
    public void sendConfirmAccountRegistrationViaEmail(String email) {
        eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email)); // event de xu ly
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        // get token
        ResetPasswordToken resetPasswordToken = getResetPasswordToken(token);

        // change password
        TaiKhoan user = resetPasswordToken.getTaiKhoan();
        user.setMatKhau(passwordEncoder.encode(newPassword));
        taiKhoanRepository.save(user);

        deleteResetPasswordTokenByTaiKhoanId(resetPasswordToken.getId());
    }

    @Override
    public ResetPasswordToken getResetPasswordToken(String token) {
        return resetPasswordTokenRepository.findByToken(token);
    }

    @Override
    public void deleteResetPasswordTokenByTaiKhoanId(int maTK) {
        resetPasswordTokenRepository.deleteByUserId(maTK);
    }

    @Override
    public void resetPasswordViaEmail(String email) {
        // find user by email
        TaiKhoan account = findTaiKhoanByEmail(email);

        // remove token token if exists
        resetPasswordTokenRepository.deleteByUserId(account.getMaTK());

        // create new reset password token
        String newToken = createNewResetPasswordToken(account);

        // send email
        emailService.sendResetPassword(account, newToken);
    }

    @Override
    public Page<TaiKhoan> getAccountsByLoaiTK(String loaiTK, Pageable pageable) {
        return taiKhoanRepository.getTaiKhoansByLoaiTKContainsIgnoreCase(loaiTK, pageable);
    }

    @Override
    public boolean lockAccount(int maTK) {
        try {
            TaiKhoan tk = findById(maTK);
            tk.setTrangThai(TrangThaiTK.NOT_ACTIVE);
            taiKhoanRepository.save(tk);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean unlockAccount(int maTK) {
        try {
            TaiKhoan tk = findById(maTK);
            tk.setTrangThai(TrangThaiTK.ACTIVE);
            taiKhoanRepository.save(tk);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAccountInAdmin(int maTK, TaiKhoanAdminUpdateDto form) {
        try {
            TaiKhoan tk = findById(maTK);
            if (tk != null) {
                String oldPass = tk.getMatKhau();
                BeanUtils.copyProperties(form, tk);
                if (!ObjectUtils.isEmpty(form.getMatKhau())) tk.setMatKhau(passwordEncoder.encode(form.getMatKhau()));
                else tk.setMatKhau(oldPass);
                taiKhoanRepository.save(tk);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createAccountInAdmin(TaiKhoanAdminCreateDto form) {
        try {
            TaiKhoan tk = new TaiKhoan();
            BeanUtils.copyProperties(form, tk);
            tk.setMatKhau(passwordEncoder.encode(tk.getMatKhau()));
            tk.setTrangThai(TrangThaiTK.ACTIVE);
            taiKhoanRepository.save(tk);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void createNewRegistrationUserToken(TaiKhoan account) {
        // create new token for confirm Registration
        final String newToken = UUID.randomUUID().toString();
        RegistrationUserToken token = new RegistrationUserToken(newToken, account, registrationPasswordTokenExpiredTime);

        registrationAccountTokenRepository.save(token);
    }

    private String createNewResetPasswordToken(TaiKhoan account) {
        // create new token for Resetting password
        final String newToken = UUID.randomUUID().toString();
        ResetPasswordToken token = new ResetPasswordToken(newToken, account, resetPasswordTokenExpiredTime);

        resetPasswordTokenRepository.save(token);
        return newToken;
    }
}
