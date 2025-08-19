package org.academico.springcloud.msvc.cobro.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.cobro.domain.models.valueobjects.MontoCobro;
import org.academico.springcloud.msvc.cobro.infrastructure.entities.infravalueobjects.MontoCobroInfr;

public class MontoCobroMapper {

    private MontoCobroMapper() {} // Clase de utilidad

    public static MontoCobroInfr toEntity(MontoCobro domain) {
        if (domain == null) {
            return null;
        }
        return new MontoCobroInfr(domain.getMonto(), domain.getMoneda());
    }

    public static MontoCobro toDomain(MontoCobroInfr entity) {
        if (entity == null) {
            return null;
        }
        // Se asume que MontoCobro (dominio) valida el monto y la moneda al construirse
        return new MontoCobro(entity.getMonto(), entity.getMoneda());
    }
}