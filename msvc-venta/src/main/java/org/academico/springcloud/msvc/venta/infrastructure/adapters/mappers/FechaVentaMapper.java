package org.academico.springcloud.msvc.venta.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.venta.domain.models.valueobjects.FechaVenta;
import org.academico.springcloud.msvc.venta.infrastructure.entities.infravalueobjects.FechaVentaInfr;

public class FechaVentaMapper {

    private FechaVentaMapper() {}

    public static FechaVentaInfr toEntity(FechaVenta domain) {
        if (domain == null) {
            return null;
        }
        return new FechaVentaInfr(domain.getDia(), domain.getMes(), domain.getAnio());
    }

    public static FechaVenta toDomain(FechaVentaInfr entity) {
        if (entity == null) {
            return null;
        }
        return new FechaVenta(entity.getDia(), entity.getMes(), entity.getAnio());
    }
}