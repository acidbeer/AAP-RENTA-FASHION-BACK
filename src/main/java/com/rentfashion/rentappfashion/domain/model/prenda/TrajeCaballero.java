package com.rentfashion.rentappfashion.domain.model.prenda;

import java.math.BigDecimal;

public class TrajeCaballero extends Prenda{

    private String tipoTraje;
    private String aderezo;

    public TrajeCaballero() {}

    public TrajeCaballero(Long id, String ref, String color, String marca, String talla, BigDecimal valorAlquiler,
                          Estado estado, String tipoTraje, String aderezo) {
        super(id, ref, color, marca, talla, valorAlquiler, estado);
        this.tipoTraje = tipoTraje;
        this.aderezo = aderezo;
    }

    @Override
    public Tipo getTipo() {
        return Tipo.TRAJE_CABALLERO;
    }

    public String getTipoTraje() {
        return tipoTraje; }
    public void setTipoTraje(String tipoTraje) {
        this.tipoTraje = tipoTraje; }

    public String getAderezo() {
        return aderezo; }
    public void setAderezo(String aderezo) {
        this.aderezo = aderezo; }
}
