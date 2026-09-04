package com.Student_Expense_Tracker.service;

import com.Student_Expense_Tracker.Security.JwtUtil;
import com.Student_Expense_Tracker.dto.JwtResponseDTO;
import com.Student_Expense_Tracker.dto.LoginDTO;
import com.Student_Expense_Tracker.dto.RegisterDTO;
import com.Student_Expense_Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterDTO registerDTO){
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public JwtResponseDTO login(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePassowordAuthenticatiionToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(()->
                        new RuntimeException("User not fouund")
                );
        String token = jwtUtil.generateToken(user.getEmail());
        return new JwtResponseDTO(token,user.getEmail(),user.getName());
    }

}
