package org.academico.springcloud.msvc.cobro.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;
import org.academico.springcloud.msvc.cobro.infrastructure.entities.CobroEntity;

public class CobroMapper {

    private CobroMapper() {} // Clase de utilidad

    // Mapeo de Dominio a Infraestructura (para guardar)
    public static CobroEntity toEntity(Cobro domain) {
        if (domain == null) {
            return null;
        }
        CobroEntity entity = new CobroEntity();
        entity.setIdCobro(domain.getIdCobro());
        entity.setIdVenta(domain.getIdVenta());
        entity.setFechaCobro(FechaCobroMapper.toEntity(domain.getFechaCobro()));
        entity.setMontoCobro(MontoCobroMapper.toEntity(domain.getMontoCobro()));
        entity.setEstadoCobro(domain.getEstadoCobro());
        entity.setMedioPago(domain.getMedioPago());
        return entity;
    }

    // Mapeo de Infraestructura a Dominio (para leer)
    public static Cobro toDomain(CobroEntity entity) {
        if (entity == null) {
            return null;
        }
        // El constructor de Cobro del dominio tiene validaciones. Asegúrate de que los datos de la DB
        // sean válidos o maneja las excepciones aquí.
        return new Cobro(
                entity.getIdCobro(),
                entity.getIdVenta(),
                FechaCobroMapper.toDomain(entity.getFechaCobro()),
                MontoCobroMapper.toDomain(entity.getMontoCobro()),
                entity.getEstadoCobro(),
                entity.getMedioPago()
        );
    }
}