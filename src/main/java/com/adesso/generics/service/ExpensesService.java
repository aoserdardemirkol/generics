package com.adesso.generics.service;

import com.adesso.generics.model.Expenses;
import com.adesso.generics.repository.ExpensesJooqRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpensesService implements BaseService<Expenses, Long> {

    private final ExpensesJooqRepository expensesRepository;

    public ExpensesService(ExpensesJooqRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    @Override
    public Expenses create(Expenses entity) {
        return expensesRepository.save(entity);
    }

    @Override
    public Optional<Expenses> getById(Long id) {
        return expensesRepository.findById(id);
    }

    @Override
    public List<Expenses> getAll() {
        return expensesRepository.findAll();
    }

    @Override
    public Expenses update(Long id, Expenses entity) {
        return expensesRepository.update(id, entity);
    }

    public void delete(Expenses expenses) {
        expensesRepository.delete(expenses);
    }

    @Override
    public void deleteById(Long id) {
        expensesRepository.deleteById(id);
    }

    @Override
    public Double calculate() {
        return expensesRepository.calculateAverage(getAll().stream().map(Expenses::getCost).collect(Collectors.toList()));
    }
}
