package com.example.foodify.controller;

import com.example.foodify.model.User;
import com.example.foodify.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email already registered");
        }

        // 🔐 HASH PASSWORD
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody User loginRequest) {
        User user=userRepository.findByEmail(loginRequest.getEmail()).orElse(null);
        if(user==null){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
           boolean passwordMatches = passwordEncoder.matches(
            loginRequest.getPassword(),
            user.getPassword()
             );
        if(!passwordMatches){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");

        }
        return ResponseEntity.ok("Login Successful");

    }
    
}
