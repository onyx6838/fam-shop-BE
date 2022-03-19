package com.fam.service.impl;

import com.fam.config.resourceproperty.ServerProperty;
import com.fam.entity.TaiKhoan;
import com.fam.repository.authentication.IRegistrationAccountTokenRepository;
import com.fam.service.IEmailService;
import com.fam.service.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {
    @Lazy
    @Autowired
    private ITaiKhoanService taiKhoanService;

    @Autowired
    private IRegistrationAccountTokenRepository registrationAccountTokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ServerProperty serverProperty;

    @Override
    public void sendRegistrationUserConfirm(String email) {
        TaiKhoan taiKhoan = taiKhoanService.findTaiKhoanByEmail(email);
        String token = registrationAccountTokenRepository.findByAccountId(taiKhoan.getMaTK());

        String confirmationUrl = serverProperty.getUrl() +  "/api/v1/accounts/activeUser?token=" + token;

        String subject = "Xác Nhận Đăng Ký Account";
        String content = "Bạn đã đăng ký thành công. Click vào link dưới đây để kích hoạt tài khoản \n"
                + confirmationUrl;

        sendEmail(email, subject, content);
    }

    private void sendEmail(final String recipientEmail, final String subject, final String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }

    @Override
    public void sendResetPassword(TaiKhoan account, String token) {
        String confirmationUrl = "http://localhost:3000/auth/new-password/" + token;

        String subject = "Reset Password";
        String content = "Click on the link below to reset your password (if not you, please ignore).\n"
                + confirmationUrl;

        sendEmail(account.getEmail(), subject, content);
    }
}
