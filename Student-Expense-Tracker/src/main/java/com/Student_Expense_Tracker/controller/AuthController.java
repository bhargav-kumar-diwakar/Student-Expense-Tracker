package com.Student_Expense_Tracker.controller;

import com.Student_Expense_Tracker.dto.JwtResponseDTO;
import com.Student_Expense_Tracker.dto.LoginDTO;
import com.Student_Expense_Tracker.dto.RegisterDTO;
import com.Student_Expense_Tracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        String message = authService.register(registerDTO);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginDTO loginDTO){
        JwtResponseDTO response = authService.login(loginDTO);
        return ResponseEntity.ok(response);
    }
}
