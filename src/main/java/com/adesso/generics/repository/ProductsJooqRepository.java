package com.adesso.generics.repository;

import com.adesso.generics.jooq.JooqRepositoryImpl;
import com.adesso.generics.jooq.generated.tables.records.ProductsRecord;
import com.adesso.generics.model.Products;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsJooqRepository extends JooqRepositoryImpl<Products, ProductsRecord, Long> {
    Optional<Products> findById(Long id);

    List<Products> findAll();

    Products save(Products products);

    Products update(Long id, Products products);

    void deleteById(Long id);

    void delete(Products products);
}
