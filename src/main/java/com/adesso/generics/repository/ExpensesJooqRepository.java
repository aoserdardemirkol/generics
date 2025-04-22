package com.adesso.generics.repository;

import com.adesso.generics.jooq.JooqRepositoryImpl;
import com.adesso.generics.jooq.generated.tables.records.ExpensesRecord;
import com.adesso.generics.model.Expenses;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpensesJooqRepository extends JooqRepositoryImpl<Expenses, ExpensesRecord, Long> {
    Optional<Expenses> findById(Long id);

    List<Expenses> findAll();

    Expenses save(Expenses expenses);

    Expenses update(Long id, Expenses expenses);

    void deleteById(Long id);

    void delete(Expenses expenses);
}
