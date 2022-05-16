package com.fam.controller.client;

import com.fam.dto.auth.UserInfoRequest;
import com.fam.dto.form.ProfileDto;
import com.fam.dto.form.TaiKhoanCreateDto;
import com.fam.entity.TaiKhoan;
import com.fam.service.ITaiKhoanService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(value = "TaiKhoan REST", description = "Manage TaiKhoan Api", tags = "API liên quan đến TaiKhoan")
@RestController
@RequestMapping(value = "api/v1/taikhoan")
@CrossOrigin("*")
public class TaiKhoanController {
    @Autowired
    private ITaiKhoanService taiKhoanService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/profile-update")
    public ResponseEntity<?> changeProfile(@RequestBody ProfileDto request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        taiKhoanService.changeUserProfile(username, request);
        return new ResponseEntity<>("thay doi profile thanh cong", HttpStatus.OK);
    }

    // refresh token
    @PostMapping("/info")
    public ResponseEntity<?> getTKByTenTK(@RequestBody UserInfoRequest request) {

        TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByTenTK(request.getUserName());
        return new ResponseEntity<>(taiKhoan, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> existsTKByEmail(@PathVariable(name = "email") String email) {
        boolean result = taiKhoanService.existsByEmail(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> dangKy(@RequestBody TaiKhoanCreateDto request) {
        TaiKhoan taiKhoan;
        taiKhoan = modelMapper.map(request, TaiKhoan.class);
        taiKhoanService.createTaiKhoan(taiKhoan);
        return new ResponseEntity<>("Tao tai khoản thành công", HttpStatus.OK);
    }

    @GetMapping("/userRegistrationConfirmRequest")
    public ResponseEntity<?> sendConfirmRegistrationViaEmail(@RequestParam String email) {
        taiKhoanService.sendConfirmAccountRegistrationViaEmail(email);
        return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
    }

    @GetMapping("/activeUser")
    // validate: check exists, check not expired
    public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {
        // active user
        taiKhoanService.activeAccount(token);
        return new ResponseEntity<>("Active success!", HttpStatus.OK);
    }
}
