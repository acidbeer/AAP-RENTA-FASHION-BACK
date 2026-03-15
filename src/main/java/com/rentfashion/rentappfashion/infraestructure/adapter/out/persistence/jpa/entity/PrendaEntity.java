package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "prenda")
public class PrendaEntity {

    public enum Tipo { VESTIDO_DAMA, TRAJE_CABALLERO, DISFRAZ }
    public enum Estado { DISPONIBLE, EN_LAVANDERIA }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref", length = 60, nullable = false, unique = true)
    private String ref;

    @Column(name = "color", length = 60, nullable = false)
    private String color;

    @Column(name = "marca", length = 60, nullable = false)
    private String marca;

    @Column(name = "talla", length = 10, nullable = false)
    private String talla;

    @Column(name = "valor_alquiler", precision = 12, scale = 2, nullable = false)
    private BigDecimal valorAlquiler;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado = Estado.DISPONIBLE;

    // VestidoDama
    @Column(name = "pedreria")
    private Boolean pedreria;

    @Column(name = "altura", length = 30)
    private String altura;

    @Column(name = "cant_piezas")
    private Short cantPiezas;

    // TrajeCaballero
    @Column(name = "tipo_traje", length = 60)
    private String tipoTraje;

    @Column(name = "aderezo", length = 80)
    private String aderezo;

    // Disfraz
    @Column(name = "nombre_disfraz", length = 80)
    private String nombreDisfraz;

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

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Boolean getPedreria() { return pedreria; }
    public void setPedreria(Boolean pedreria) { this.pedreria = pedreria; }

    public String getAltura() { return altura; }
    public void setAltura(String altura) { this.altura = altura; }

    public Short getCantPiezas() { return cantPiezas; }
    public void setCantPiezas(Short cantPiezas) { this.cantPiezas = cantPiezas; }

    public String getTipoTraje() { return tipoTraje; }
    public void setTipoTraje(String tipoTraje) { this.tipoTraje = tipoTraje; }

    public String getAderezo() { return aderezo; }
    public void setAderezo(String aderezo) { this.aderezo = aderezo; }

    public String getNombreDisfraz() { return nombreDisfraz; }
    public void setNombreDisfraz(String nombreDisfraz) { this.nombreDisfraz = nombreDisfraz; }
}
