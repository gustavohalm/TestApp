package com.xbrain.testapp.Models;

public class Produto {

    private Long id;
    private String name;
    private Double value;
    private Integer quantity;

    public Produto() { }

    public Produto(Long id, String name, Double value, Integer quantity) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.quantity = quantity;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
