package com.adesso.generics.repository;

import com.adesso.generics.jooq.JooqRepository;
import com.adesso.generics.jooq.generated.public_.tables.records.ExpensesRecord;
import com.adesso.generics.model.Expenses;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class ExpensesJooqRepository extends JooqRepository<Expenses, ExpensesRecord, Long> {

    public ExpensesJooqRepository(DSLContext dsl) {
        super(dsl, Expenses.class, com.adesso.generics.jooq.generated.public_.tables.Expenses.EXPENSES, ExpensesRecord.class);
    }
}
