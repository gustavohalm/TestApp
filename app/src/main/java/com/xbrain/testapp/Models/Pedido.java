package com.xbrain.testapp.Models;

import java.util.List;

public class Pedido {
    private Long id;
    private Cliente cliente;
    private List<Produto> produtos;
    private Double totalValue;

    public Pedido() { }

    public Pedido(Long id, Cliente cliente, List<Produto> produtos, Double totalValue) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
        this.totalValue = totalValue;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

}
