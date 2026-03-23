package com.rentfashion.rentappfashion.domain.model.iterator;

import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.util.List;

public class PrendaIterator implements CustomIterator{

    private final List<Prenda> prendas;
    private int position = 0;

    public PrendaIterator(List<Prenda> prendas) {
        this.prendas = prendas;
    }

    @Override
    public boolean hasNext() {
        return position < prendas.size();
    }

    @Override
    public Prenda next() {
        return prendas.get(position++);
    }
}
