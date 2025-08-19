package org.academico.springcloud.msvc.venta.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.venta.domain.models.valueobjects.PrecioVenta;
import org.academico.springcloud.msvc.venta.infrastructure.entities.infravalueobjects.PrecioVentaInfr;

public class PrecioVentaMapper {

    private PrecioVentaMapper() {}

    public static PrecioVentaInfr toEntity(PrecioVenta domain) {
        if (domain == null) {
            return null;
        }
        return new PrecioVentaInfr(domain.getPrecioVenta(), domain.getMoneda());
    }

    public static PrecioVenta toDomain(PrecioVentaInfr entity) {
        if (entity == null) {
            return null;
        }
        return new PrecioVenta(entity.getPrecioVenta(), entity.getMoneda());
    }
}