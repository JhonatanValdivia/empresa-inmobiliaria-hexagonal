package org.academico.springcloud.msvc.preventa.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.PropuestaPagoEntidad;

public class PropuestaPagoMapper {

    public PropuestaPagoMapper() {}

    public static PropuestaPagoEntidad toEntity(PropuestaPago domain) {
        if (domain == null) {
            return null;
        }
        PropuestaPagoEntidad entity = new PropuestaPagoEntidad();
        entity.setId(domain.getId());
        entity.setMonto(domain.getMonto());
        entity.setFecha(domain.getFecha());
        entity.setCuotas(domain.getCuotas());
        entity.setMetodoPago(domain.getMetodoPago());
        // La relación con Preventa (PreventaEntidad) no se setea aquí para evitar ciclos
        return entity;
    }

    public static PropuestaPago toDomain(PropuestaPagoEntidad entity) {
        if (entity == null) {
            return null;
        }
        return new PropuestaPago(
                entity.getId(),
                entity.getMonto(),
                entity.getFecha(),
                entity.getCuotas(),
                entity.getMetodoPago()
        );
    }
}