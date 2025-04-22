package com.adesso.generics.service;

import com.adesso.generics.model.Products;
import com.adesso.generics.repository.ProductsJooqRepository;
import com.adesso.generics.util.Calculate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    private final Calculate calculate;

    private final ProductsJooqRepository productsRepository;

    public ProductsService(Calculate calculate, ProductsJooqRepository productsRepository) {
        this.calculate = calculate;
        this.productsRepository = productsRepository;
    }

    public Products createProduct(Products products) {
        return productsRepository.save(products);
    }

    public Optional<Products> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    public List<Products> getProducts() {
        return productsRepository.findAll();
    }

    public Products updateProduct(Long id, Products products) {
        return productsRepository.update(id, products);
    }

    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }

    public Double calculateAveragePrice() {
        List<Products> productsList = getProducts();

        return calculate.calculateAverage(productsList.stream().map(Products::getPrice).collect(Collectors.toList()));
    }
}
