package com.adesso.generics.service;

import com.adesso.generics.model.Expenses;
import com.adesso.generics.repository.ExpensesJooqRepository;
import com.adesso.generics.util.Calculate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpensesService {

    private final Calculate calculate;

    private final ExpensesJooqRepository expensesRepository;

    public ExpensesService(Calculate calculate, ExpensesJooqRepository expensesRepository) {
        this.calculate = calculate;
        this.expensesRepository = expensesRepository;
    }

    public Expenses createExpense(Expenses expenses) {
        return expensesRepository.save(expenses);
    }

    public Optional<Expenses> getExpenseById(Long id) {
        return expensesRepository.findById(id);
    }

    public List<Expenses> getExpenses() {
        return expensesRepository.findAll();
    }

    public Expenses updateExpense(Long id, Expenses expenses) {
        return expensesRepository.update(id, expenses);
    }

    public void delete(Expenses expenses) {
        expensesRepository.delete(expenses);
    }

    public void deleteExpenseById(Long id) {
        expensesRepository.deleteById(id);
    }

    public Double calculateAverageExpenses() {
        List<Float> expenseValues = new ArrayList<>();
        List<Expenses> expensesList = getExpenses();

        expensesList.forEach(expense -> expenseValues.add(expense.getCost()));

        return calculate.calculateAverage(expenseValues);
    }
}
