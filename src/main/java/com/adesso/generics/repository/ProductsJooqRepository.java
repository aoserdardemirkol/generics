package com.adesso.generics.repository;

import com.adesso.generics.jooq.JooqRepository;
import com.adesso.generics.jooq.generated.public_.tables.records.ProductsRecord;
import com.adesso.generics.model.Products;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProductsJooqRepository extends JooqRepository<Products, ProductsRecord, Long> {

    public ProductsJooqRepository(DSLContext dsl) {
        super(dsl, Products.class, com.adesso.generics.jooq.generated.public_.tables.Products.PRODUCTS, ProductsRecord.class);
    }
}
