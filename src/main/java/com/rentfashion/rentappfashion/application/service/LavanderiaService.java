package com.rentfashion.rentappfashion.application.service;

import com.rentfashion.rentappfashion.application.port.in.EnviarLavanderiaUseCase;
import com.rentfashion.rentappfashion.application.port.in.RegistrarPrendaLavanderiaUseCase;
import com.rentfashion.rentappfashion.application.port.in.VerListaLavanderiaUseCase;
import com.rentfashion.rentappfashion.application.port.out.LavanderiaRepositoryPort;
import com.rentfashion.rentappfashion.application.port.out.PrendaRepositoryPort;
import com.rentfashion.rentappfashion.domain.exception.BusinessRuleException;
import com.rentfashion.rentappfashion.domain.exception.NotFoundException;
import com.rentfashion.rentappfashion.domain.exception.ValidationException;
import com.rentfashion.rentappfashion.domain.model.LavanderiaItem;
import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;

import java.time.LocalDateTime;
import java.util.List;

public class LavanderiaService implements RegistrarPrendaLavanderiaUseCase,
        VerListaLavanderiaUseCase,
        EnviarLavanderiaUseCase {

    private final LavanderiaRepositoryPort lavanderiaRepo;
    private final PrendaRepositoryPort prendaRepo;

    public LavanderiaService(LavanderiaRepositoryPort lavanderiaRepo, PrendaRepositoryPort prendaRepo) {
        this.lavanderiaRepo = lavanderiaRepo;
        this.prendaRepo = prendaRepo;
    }

    @Override
    public void registrar(String refPrenda, boolean prioridad) {
        if (refPrenda == null || refPrenda.isBlank()) throw new ValidationException("refPrenda es obligatoria");

        Prenda prenda = prendaRepo.findByRef(refPrenda.trim())
                .orElseThrow(() -> new NotFoundException("Prenda no existe: " + refPrenda));

        if (prenda.getEstado() == Prenda.Estado.EN_LAVANDERIA) {
            throw new BusinessRuleException("La prenda ya está en lavandería: " + refPrenda);
        }

        // Bloquea prenda para alquiler
        prenda.setEstado(Prenda.Estado.EN_LAVANDERIA);
        prendaRepo.save(prenda);

        LavanderiaItem item = new LavanderiaItem();
        item.setPrenda(prenda);
        item.setPrioridad(prioridad);
        item.setEstado(LavanderiaItem.Estado.PENDIENTE);
        item.setFechaRegistro(LocalDateTime.now());

        lavanderiaRepo.save(item);
    }

    @Override
    public List<LavanderiaItem> verPendientes() {
        return lavanderiaRepo.findPendientes();
    }

    @Override
    public List<LavanderiaItem> enviar(int cantidad) {
        if (cantidad <= 0) throw new ValidationException("cantidad debe ser > 0");
        List<LavanderiaItem> items = lavanderiaRepo.takePendientesOrdenados(cantidad);
        for (LavanderiaItem i : items) {
            lavanderiaRepo.markEnviada(i);
        }
        return items;
    }
}
