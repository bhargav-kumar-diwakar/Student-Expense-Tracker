package com.Student_Expense_Tracker.service;

import com.Student_Expense_Tracker.dto.ExpenseDTO;
import com.Student_Expense_Tracker.entity.Expense;
import com.Student_Expense_Tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    private ExpenseDTO convertToDTO(Expense expense){
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setCateogry(expense.getCateogry());
        dto.setDate(expense.getDate());
        dto.setDescription(expense.getDescription());
        return dto;
    }

    private Expense convertToEntity(ExpenseDTO dto){
        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCateogry(dto.getCateogry());
        expense.setDate(dto.getDate());
        expense.setDescription(dto.getDescription());
        return expense;
    }

    @Override
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO){
        Expense expense  = convertToEntity(expenseDTO);
        Expense savedExpense = expenseRepository.save(expense);
        return convertToDTO(savedExpense);
    }

    @Override
    public List<ExpenseDTO> getAllExpense() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDTO getExpenseById(Long id){
        Expense expense = expenseRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Expense not found with id: "+id));
        return convertToDTO(expense);
    }

    @Override
    public ExpenseDTO updateExpense(Long id,ExpenseDTO expenseDTO){
        Expense existingExpense = expenseRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Expense not found with id: "+id));
        existingExpense.setTitle(expenseDTO.getTitle());
        existingExpense.setAmount(expenseDTO.getAmount());
        existingExpense.setCateogry(expenseDTO.getCateogry());
        existingExpense.setDate(expenseDTO.getDate());
        existingExpense.setDescription(expenseDTO.getDescription());
        Expense updatedExpense = expenseRepository.save(existingExpense);
        return convertToDTO(updatedExpense);
    }

    @Override
    public void deleteExpense(Long id){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Expense not found with id: "+id));
        expenseRepository.delete(expense);
    }

    @Override
    public List<ExpenseDTO> getExpensesByCategory(String category){
        List<Expense> expenses = expenseRepository.findByCategory(category);
        return expenses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDTO> getExpensesByDateRange(LocalDate startDate, LocalDate endDate){
        List<Expense> expenses = expenseRepository.findByDateBetween(startDate,endDate);
        return expenses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
