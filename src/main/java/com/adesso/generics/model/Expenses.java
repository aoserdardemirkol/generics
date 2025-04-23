package com.adesso.generics.model;

import com.adesso.generics.annotations.JQField;
import com.adesso.generics.annotations.JQId;

public class Expenses {

    @JQId
    private Long id;

    @JQField("name")
    private String name;

    private Float cost;

    public Expenses() {
    }

    public Expenses(String name, Float cost) {
        this.name = name;
        this.cost = cost;
    }

    public Expenses(Long id, String name, Float cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
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

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }
}
