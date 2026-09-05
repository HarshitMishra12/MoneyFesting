package com.moneyfesting.backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.moneyfesting.backend.model.User;
import com.moneyfesting.backend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User signup(String name, String email, String password) {

        // Check whether email is already registered
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }

        // Create new user
        User user = new User();

        user.setName(name);
        user.setEmail(email);

        // Never store the plain-text password
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public User login(String email, String password) {

        // Find user using email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        // Verify password against BCrypt hash
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }
}