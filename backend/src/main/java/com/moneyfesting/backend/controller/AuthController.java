package com.moneyfesting.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneyfesting.backend.model.User;
import com.moneyfesting.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // SIGNUP
    @PostMapping("/signup")
    public User signup(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {

        return authService.signup(name, email, password);
    }

    // LOGIN
    @PostMapping("/login")
    public User login(
            @RequestParam String email,
            @RequestParam String password) {

        return authService.login(email, password);
    }
}