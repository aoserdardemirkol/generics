package com.adesso.generics.model;

import com.adesso.generics.annotations.JQField;
import com.adesso.generics.annotations.JQId;

public class Products {

    @JQId
    private Long id;

    @JQField("name")
    private String name;

    @JQField("price")
    private Double price;

    public Products() {
    }

    public Products(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Products(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
