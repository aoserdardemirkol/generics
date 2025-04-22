package com.adesso.generics.service;

import com.adesso.generics.model.Products;
import com.adesso.generics.repository.ProductsJooqRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsService implements BaseService<Products, Long> {

    private final ProductsJooqRepository productsRepository;

    public ProductsService(ProductsJooqRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Products create(Products entity) {
        return productsRepository.save(entity);
    }

    @Override
    public Optional<Products> getById(Long id) {
        return productsRepository.findById(id);
    }

    @Override
    public List<Products> getAll() {
        return productsRepository.findAll();
    }

    @Override
    public Products update(Long id, Products entity) {
        return productsRepository.update(id, entity);
    }

    @Override
    public void delete(Products entity) {
        productsRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public Double calculate() {
        return productsRepository.calculateAverage(getAll().stream().map(Products::getPrice).collect(Collectors.toList()));
    }
}
