package com.adesso.generics.model.dto;

public class ExpensesDTO {

    private String name;

    private Float cost;

    public ExpensesDTO() {

    }

    public ExpensesDTO(String name, Float cost) {

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