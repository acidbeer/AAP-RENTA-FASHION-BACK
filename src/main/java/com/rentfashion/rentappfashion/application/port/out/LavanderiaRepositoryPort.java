package com.rentfashion.rentappfashion.application.port.out;

import com.rentfashion.rentappfashion.domain.model.LavanderiaItem;

import java.util.List;

public interface LavanderiaRepositoryPort {

    LavanderiaItem save(LavanderiaItem item);
    List<LavanderiaItem> findPendientes();
    List<LavanderiaItem> takePendientesOrdenados(int cantidad); // prioridad desc, fecha asc
    LavanderiaItem markEnviada(LavanderiaItem item);

}
