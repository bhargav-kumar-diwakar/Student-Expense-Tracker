package com.Student_Expense_Tracker.repository;

import com.Student_Expense_Tracker.model.User;
import com.Student_Expense_Tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //fetch all expenses by category(e.g. snacks)
    List<Expense> findByCategory(String category);

    //fetch expenses between two dates
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    //fetch expenses greater than a certain amount
    List<Expense> findByAmountGreaterThan(Double amount);

    //Find expense by id AND user - prevents user A accessing user B's expense
    Optional<Expense> findByIdAndUser(Long id, User user);

    //Get all expenses belonging to a specific user
    List<Expense> findByUser(User user);

    // Get expenses by category for a specific user
    List<Expense> findByUserAndCategory(User user, String category);

    // Get expenses by date range for a specific user
    List<Expense> findByUserAndDateBetween(User user,
                                           LocalDate startDate,
                                           LocalDate endDate);

}
