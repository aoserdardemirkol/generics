package com.adesso.generics.controller;

import com.adesso.generics.model.Products;
import com.adesso.generics.model.dto.ProductsDTO;
import com.adesso.generics.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public Products createProduct(@RequestBody ProductsDTO productsDTO) {
        Products products = new Products(productsDTO.name(), productsDTO.price());
        return productsService.create(products);
    }

    @GetMapping
    public List<Products> getAllProducts() {
        return productsService.getAll();
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable("id") Long id) {
        if (productsService.getById(id).isPresent()) {
            return productsService.getById(id).get();
        } else {
            throw new IllegalArgumentException("No product found with given id: " + id);
        }
    }

    @PutMapping("/{id}")
    public Products updateProduct(@PathVariable("id") Long id, @RequestBody ProductsDTO productsDTO) {
        Products products = new Products(productsDTO.name(), productsDTO.price());
        return productsService.update(id, products);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) {
        productsService.deleteById(id);
    }

    @GetMapping("/calculate")
    public Double calculateAveragePrice() {
        return productsService.calculate();
    }
}
