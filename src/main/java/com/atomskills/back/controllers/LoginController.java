package com.atomskills.back.controllers;

import com.atomskills.back.dto.LoginDto;
import com.atomskills.back.dto.SignupDto;
import com.atomskills.back.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(loginService.login(dto.getLogin()));
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody SignupDto dto) {
        return ResponseEntity.ok(loginService.signup(dto.getLogin(), dto.getUserRole()));
    }
}
