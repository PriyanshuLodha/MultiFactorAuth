package com.example.MFA3.controllers;

import com.example.MFA3.model.UserSecretKey;
import com.example.MFA3.repo.UserSecretKeyRepo;
import com.example.MFA3.service.TwoFactorAuthService;
import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/qrGenerate")
public class QrCodeController {
    @Autowired
    TwoFactorAuthService twoFactorAuthService;
    @Autowired
    UserSecretKeyRepo userSecretKeyRepo;

    @GetMapping("/image")
    public String getQR(@RequestParam("username") String name) {

        UserSecretKey userSecretKey = userSecretKeyRepo.findByUsername(name);

        String s = twoFactorAuthService.generateQrCodeImageUri(userSecretKey.getKey());
        return s;
    }

    @GetMapping("/verify")
    public Boolean verifyCode(@RequestParam("otp") String otp, @RequestParam("name") String name) {
        UserSecretKey userSecretKey = userSecretKeyRepo.findByUsername(name);

        String s = twoFactorAuthService.generateQrCodeImageUri(userSecretKey.getKey());
        return twoFactorAuthService.isOtpValid(userSecretKey.getKey(),otp);
    }
}
