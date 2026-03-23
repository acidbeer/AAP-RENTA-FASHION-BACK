package com.rentfashion.rentappfashion.domain.model.iterator;

import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.util.List;

public class PrendaCollection {

    private final List<Prenda> prendas;

    public PrendaCollection(List<Prenda> prendas) {
        this.prendas = prendas;
    }

    public CustomIterator<Prenda> createIterator() {
        return new PrendaIterator(prendas);
    }
}
