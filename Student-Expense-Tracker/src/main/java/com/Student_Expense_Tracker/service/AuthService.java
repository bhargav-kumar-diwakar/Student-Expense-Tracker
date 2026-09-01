package com.Student_Expense_Tracker.service;

import com.Student_Expense_Tracker.dto.JwtResponseDTO;
import com.Student_Expense_Tracker.dto.LoginDTO;
import com.Student_Expense_Tracker.dto.RegisterDTO;

public interface AuthService {
    String register(RegisterDTO registerDTO);
    JwtResponseDTO login(LoginDTO loginDTO);
}
