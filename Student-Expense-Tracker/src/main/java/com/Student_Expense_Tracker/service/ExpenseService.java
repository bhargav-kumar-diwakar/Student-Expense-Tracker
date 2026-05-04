package com.Student_Expense_Tracker.service;

import com.Student_Expense_Tracker.dto.ExpenseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    ExpenseDTO createExpense(ExpenseDTO expenseDTO);
    List<ExpenseDTO> getAllExpense();
    ExpenseDTO getExpenseById(Long id);
    ExpenseDTO updateExpense(Long id,ExpenseDTO expenseDTO);
    void deleteExpense(Long id);
    List<ExpenseDTO> getExpensesByCategory(String category);
    List<ExpenseDTO> getExpensesByDateRange(LocalDate startDate, LocalDate endDate);
}
