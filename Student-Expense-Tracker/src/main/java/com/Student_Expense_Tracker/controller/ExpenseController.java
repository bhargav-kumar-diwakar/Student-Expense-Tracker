package com.Student_Expense_Tracker.controller;

import com.Student_Expense_Tracker.dto.ExpenseDTO;
import com.Student_Expense_Tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO){
        ExpenseDTO created = expenseService.createExpense(expenseDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses(){
        List<ExpenseDTO>expenses = expenseService.getAllExpense();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id){
        ExpenseDTO expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id
    ,@RequestBody ExpenseDTO expenseDTO){
        ExpenseDTO updated = expenseService.updateExpense(id,expenseDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense deleted successfully");
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExpenseDTO>> getByCategory(@PathVariable String category){
        List<ExpenseDTO> expenses = expenseService.getExpensesByCategory(category);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/daterange")
    public ResponseEntity<List<ExpenseDTO>> getByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        List<ExpenseDTO> expenses = expenseService.getExpensesByDateRange(startDate,endDate);
        return ResponseEntity.ok(expenses);
    }


}
