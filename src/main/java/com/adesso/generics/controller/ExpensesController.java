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
        Expenses expenses = new Expenses(expensesDTO.getName(), expensesDTO.getCost());
        return expensesService.create(expenses);
    }

    @GetMapping
    public List<Expenses> getAllExpenses() {
        return expensesService.getAll();
    }

    @GetMapping("/{id}")
    public Expenses getExpenseById(@PathVariable("id") Long id) {
        if (expensesService.getById(id).isPresent()) {
            return expensesService.getById(id).get();
        } else {
            throw new IllegalArgumentException("No expense found with given id: " + id);
        }
    }

    @PutMapping("/{id}")
    public Expenses updateExpense(@PathVariable("id") Long id, @RequestBody ExpensesDTO expensesDTO) {
        Expenses expenses = new Expenses(expensesDTO.getName(), expensesDTO.getCost());
        return expensesService.update(id, expenses);
    }

    @DeleteMapping
    public void deleteExpense(@RequestBody Expenses expenses) {
        expensesService.delete(expenses);
    }

    @DeleteMapping("/{id}")
    public void deleteExpenseById(@PathVariable("id") Long id) {
        expensesService.deleteById(id);
    }

    @GetMapping("/calculate")
    public Double calculateAverageExpense() {
        return expensesService.calculate();
    }
}
