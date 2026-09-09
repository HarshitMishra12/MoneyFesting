package com.moneyfesting.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneyfesting.backend.dto.AuthResponse;
import com.moneyfesting.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public AuthResponse signup(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        return authService.signup(name, email, password);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        return authService.login(email, password);
    }
}