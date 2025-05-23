package com.adesso.generics.model.dto;

public class ProductsDTO {

    private String name;

    private Double price;

    public ProductsDTO() {
    }

    public ProductsDTO(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}