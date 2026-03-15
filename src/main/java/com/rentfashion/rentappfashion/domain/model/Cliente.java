package com.rentfashion.rentappfashion.domain.model;

public class Cliente extends Persona{

    private String mail;

    public Cliente() {}

    public Cliente(String id, String nombre, String direccion, String telefono, String mail) {
        super(id, nombre, direccion, telefono);
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
}
