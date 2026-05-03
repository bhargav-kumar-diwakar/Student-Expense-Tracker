package com.Student_Expense_Tracker.repository;

import com.Student_Expense_Tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //fetch all expenses by category(e.g. snacks)
    List<Expense> findByCategory(String category);

    //fetch expenses between two dates
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    //fetch expenses greater than a certain amount
    List<Expense> findByAmountGreaterThan(Double amount);
}
