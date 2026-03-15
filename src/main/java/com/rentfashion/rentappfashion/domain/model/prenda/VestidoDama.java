package com.rentfashion.rentappfashion.domain.model.prenda;

import java.math.BigDecimal;

public class VestidoDama extends Prenda{

    private boolean pedreria;
    private String altura;
    private short cantPiezas;

    public VestidoDama() {}

    public VestidoDama(Long id, String ref, String color, String marca, String talla, BigDecimal valorAlquiler,
                       Estado estado, boolean pedreria, String altura, short cantPiezas) {
        super(id, ref, color, marca, talla, valorAlquiler, estado);
        this.pedreria = pedreria;
        this.altura = altura;
        this.cantPiezas = cantPiezas;
    }

    @Override
    public Tipo getTipo() {
        return Tipo.VESTIDO_DAMA;
    }

    public boolean isPedreria() {
        return pedreria;
    }
    public void setPedreria(boolean pedreria) {
        this.pedreria = pedreria;
    }

    public String getAltura() {
        return altura;
    }
    public void setAltura(String altura) {
        this.altura = altura;
    }

    public short getCantPiezas() {
        return cantPiezas;
    }
    public void setCantPiezas(short cantPiezas) {
        this.cantPiezas = cantPiezas;
    }
}
