package com.xbrain.testapp.Models;

public class Entrega {

    private Long id;
    private Pedido pedido;
    private String estado;
    private String cidade;
    private String Bairro;
    private String endereco;
    private  String numero;
    private  String complemento;

    public Entrega () { }

    public Entrega(Long id, Pedido pedido, String estado, String cidade, String bairro, String endereco, String numero, String complemento) {
        this.id = id;
        this.pedido = pedido;
        this.estado = estado;
        this.cidade = cidade;
        Bairro = bairro;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
