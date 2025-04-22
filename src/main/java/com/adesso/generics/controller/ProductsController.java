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
        return productsService.createProduct(products);
    }

    @GetMapping
    public List<Products> getAllProducts() {
        return productsService.getProducts();
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Long id) {
        return productsService.getProductById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Products updateProduct(@PathVariable Long id, @RequestBody ProductsDTO productsDTO) {
        Products products = new Products(productsDTO.name(), productsDTO.price());
        return productsService.updateProduct(id, products);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productsService.deleteProductById(id);
    }

    @GetMapping("/calculate")
    public Double calculateAveragePrice() {
        return productsService.calculateAveragePrice();
    }
}
