package com.Student_Expense_Tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {

    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be greate than zero")
    private Double amount;

    @NotBlank(message = "Category cannot be empty")
    private String category;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;
    private String description;

}
