package com.rentfashion.rentappfashion.domain.model.prenda;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Prenda {

    public enum Tipo { VESTIDO_DAMA, TRAJE_CABALLERO, DISFRAZ }
    public enum Estado { DISPONIBLE, EN_LAVANDERIA }

    private Long id;
    private String ref;
    private String color;
    private String marca;
    private String talla;
    private BigDecimal valorAlquiler;
    private Estado estado = Estado.DISPONIBLE;

    protected Prenda() {}

    protected Prenda(Long id, String ref, String color, String marca, String talla, BigDecimal valorAlquiler, Estado estado) {
        this.id = id;
        this.ref = ref;
        this.color = color;
        this.marca = marca;
        this.talla = talla;
        this.valorAlquiler = valorAlquiler;
        this.estado = estado == null ? Estado.DISPONIBLE : estado;
    }

    public abstract Tipo getTipo();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRef() { return ref; }
    public void setRef(String ref) { this.ref = ref; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }

    public BigDecimal getValorAlquiler() { return valorAlquiler; }
    public void setValorAlquiler(BigDecimal valorAlquiler) { this.valorAlquiler = valorAlquiler; }

    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prenda prenda)) return false;
        return Objects.equals(ref, prenda.ref);
    }
    @Override
    public int hashCode() {
        return Objects.hash(ref);
    }
}
