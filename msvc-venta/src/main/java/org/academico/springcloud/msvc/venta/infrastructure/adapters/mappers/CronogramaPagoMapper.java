package org.academico.springcloud.msvc.venta.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.venta.domain.models.valueobjects.CronogramaPago;
import org.academico.springcloud.msvc.venta.infrastructure.entities.infravalueobjects.CronogramaPagoInfr;

public class CronogramaPagoMapper {

    private CronogramaPagoMapper() {}

    public static CronogramaPagoInfr toEntity(CronogramaPago domain) {
        if (domain == null) {
            return null;
        }
        return new CronogramaPagoInfr(domain.getFechaInicio(), domain.getNumeroCuotas(), domain.getFrecuencia());
    }

    public static CronogramaPago toDomain(CronogramaPagoInfr entity) {
        if (entity == null) {
            return null;
        }
        return new CronogramaPago(entity.getFechaInicio(), entity.getNumeroCuotas(), entity.getFrecuencia());
    }
}