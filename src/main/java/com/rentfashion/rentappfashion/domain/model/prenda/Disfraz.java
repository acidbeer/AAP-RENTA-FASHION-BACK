package com.rentfashion.rentappfashion.domain.model.prenda;

import java.math.BigDecimal;

public class Disfraz extends Prenda{

    private String nombreDisfraz;

    public Disfraz() {}

    public Disfraz(Long id, String ref, String color, String marca, String talla, BigDecimal valorAlquiler,
                   Estado estado, String nombreDisfraz) {
        super(id, ref, color, marca, talla, valorAlquiler, estado);
        this.nombreDisfraz = nombreDisfraz;
    }

    @Override
    public Tipo getTipo() {
        return Tipo.DISFRAZ;
    }

    public String getNombreDisfraz() {
        return nombreDisfraz;
    }
    public void setNombreDisfraz(String nombreDisfraz) {
        this.nombreDisfraz = nombreDisfraz;
    }
}
