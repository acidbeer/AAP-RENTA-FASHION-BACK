package com.rentfashion.rentappfashion.application.port.in;

import com.rentfashion.rentappfashion.domain.model.LavanderiaItem;

import java.util.List;

public interface VerListaLavanderiaUseCase {
    List<LavanderiaItem> verPendientes();
}
