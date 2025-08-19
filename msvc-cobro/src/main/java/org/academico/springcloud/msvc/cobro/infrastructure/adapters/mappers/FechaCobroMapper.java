package org.academico.springcloud.msvc.cobro.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.cobro.domain.models.valueobjects.FechaCobro;
import org.academico.springcloud.msvc.cobro.infrastructure.entities.infravalueobjects.FechaCobroInfr;

public class FechaCobroMapper {

    private FechaCobroMapper() {} // Clase de utilidad

    public static FechaCobroInfr toEntity(FechaCobro domain) {
        if (domain == null) {
            return null;
        }
        return new FechaCobroInfr(domain.getDia(), domain.getMes(), domain.getAnio(), domain.getHora(), domain.getMinuto());
    }

    public static FechaCobro toDomain(FechaCobroInfr entity) {
        if (entity == null) {
            return null;
        }
        return new FechaCobro(entity.getDia(), entity.getMes(), entity.getAnio(), entity.getHora(), entity.getMinuto());
    }
}