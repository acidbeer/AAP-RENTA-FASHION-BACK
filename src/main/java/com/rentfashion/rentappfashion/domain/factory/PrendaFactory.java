package com.rentfashion.rentappfashion.domain.factory;

import com.rentfashion.rentappfashion.domain.exception.ValidationException;
import com.rentfashion.rentappfashion.domain.model.prenda.Disfraz;
import com.rentfashion.rentappfashion.domain.model.prenda.Prenda;
import com.rentfashion.rentappfashion.domain.model.prenda.TrajeCaballero;
import com.rentfashion.rentappfashion.domain.model.prenda.VestidoDama;

import java.math.BigDecimal;
import java.util.Map;

public class PrendaFactory {

    public Prenda crearPrenda(String tipo, Map<String, Object> datos) {
        if (tipo == null) throw new ValidationException("tipo es obligatorio");

        Prenda.Tipo t;
        try {
            t = Prenda.Tipo.valueOf(tipo.trim().toUpperCase());
        } catch (Exception e) {
            throw new ValidationException("tipo inválido. Use: VESTIDO_DAMA, TRAJE_CABALLERO, DISFRAZ");
        }

        String ref = str(datos, "ref");
        String color = str(datos, "color");
        String marca = str(datos, "marca");
        String talla = str(datos, "talla");
        BigDecimal valor = dec(datos, "valorAlquiler");

        return switch (t) {
            case VESTIDO_DAMA -> new VestidoDama(
                    null, ref, color, marca, talla, valor, Prenda.Estado.DISPONIBLE,
                    bool(datos, "pedreria"), str(datos, "altura"), shrt(datos, "cantPiezas")
            );
            case TRAJE_CABALLERO -> new TrajeCaballero(
                    null, ref, color, marca, talla, valor, Prenda.Estado.DISPONIBLE,
                    str(datos, "tipoTraje"), str(datos, "aderezo")
            );
            case DISFRAZ -> new Disfraz(
                    null, ref, color, marca, talla, valor, Prenda.Estado.DISPONIBLE,
                    str(datos, "nombreDisfraz")
            );
        };
    }

    private String str(Map<String, Object> m, String k) {
        Object v = m.get(k);
        if (v == null || v.toString().isBlank()) throw new ValidationException(k + " es obligatorio");
        return v.toString().trim();
    }

    private boolean bool(Map<String, Object> m, String k) {
        Object v = m.get(k);
        if (v == null) return false;
        return Boolean.parseBoolean(v.toString());
    }

    private short shrt(Map<String, Object> m, String k) {
        Object v = m.get(k);
        if (v == null) return 0;
        return Short.parseShort(v.toString());
    }

    private BigDecimal dec(Map<String, Object> m, String k) {
        Object v = m.get(k);
        if (v == null) throw new ValidationException(k + " es obligatorio");
        return new BigDecimal(v.toString());
    }
}
