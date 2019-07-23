package com.xbrain.testapp.Models;


public class Cliente {

    private Long id;
    private String name;
    private String celphone;
    private String email;

    public Cliente(Long id, String name, String celphone, String email) {
        this.id = id;
        this.name = name;
        this.celphone = celphone;
        this.email = email;
    }

    public Cliente(){}

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

    public String getCelphone() {
        return celphone;
    }

    public void setCelphone(String celphone) {
        this.celphone = celphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
