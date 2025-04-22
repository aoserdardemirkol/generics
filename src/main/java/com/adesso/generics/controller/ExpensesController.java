package com.adesso.generics.controller;

import com.adesso.generics.model.Expenses;
import com.adesso.generics.model.dto.ExpensesDTO;
import com.adesso.generics.service.ExpensesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {

    private final ExpensesService expensesService;

    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @PostMapping
    public Expenses createExpense(@RequestBody ExpensesDTO expensesDTO) {
        Expenses expenses = new Expenses(expensesDTO.name(), expensesDTO.cost());
        return expensesService.createExpense(expenses);
    }

    @GetMapping
    public List<Expenses> getAllProducts() {
        return expensesService.getExpenses();
    }

    @GetMapping("/{id}")
    public Expenses getExpenseById(@PathVariable Long id) {
        return expensesService.getExpenseById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Expenses updateExpense(@PathVariable Long id, @RequestBody ExpensesDTO expensesDTO) {
        Expenses expenses = new Expenses(expensesDTO.name(), expensesDTO.cost());
        return expensesService.updateExpense(id, expenses);
    }

    @DeleteMapping
    public void deleteExpense(@RequestBody Expenses expenses) {
        expensesService.delete(expenses);
    }

    @DeleteMapping("/{id}")
    public void deleteExpenseById(@PathVariable Long id) {
        expensesService.deleteExpenseById(id);
    }

    @GetMapping("/calculate")
    public Double calculateAverageExpense() {
        return expensesService.calculateAverageExpenses();
    }
}
