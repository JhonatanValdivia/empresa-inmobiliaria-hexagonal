package org.academico.springcloud.msvc.preventa.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.ContratoVenta;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.ContratoVentaEntidad;

public class ContratoVentaMapper {

    public ContratoVentaMapper() {}

    public static ContratoVentaEntidad toEntity(ContratoVenta domain) {
        if (domain == null) {
            return null;
        }
        ContratoVentaEntidad entity = new ContratoVentaEntidad();
        entity.setId(domain.getId());
        entity.setTipoContrato(domain.getTipoContrato());
        entity.setFechaFirma(domain.getFechaFirma());
        entity.setEstado(domain.getEstado());
        // La relación con Preventa (PreventaEntidad) no se setea aquí para evitar ciclos
        // Será seteada por el PreventaMapper si es necesario
        return entity;
    }

    // Mapeo sin la referencia a Preventa (para evitar ciclos)
    public static ContratoVentaEntidad toEntityWithoutPreventa(ContratoVenta domain) {
        ContratoVentaEntidad entity = toEntity(domain);
        if (entity != null) {
            entity.setPreventa(null); // Asegura que la referencia a Preventa sea null
        }
        return entity;
    }

    public static ContratoVenta toDomain(ContratoVentaEntidad entity) {
        if (entity == null) {
            return null;
        }
        ContratoVenta domain = new ContratoVenta(
                entity.getId(),
                entity.getTipoContrato(),
                entity.getFechaFirma(),
                entity.getEstado()
        );
        // La referencia a Preventa (dominio) no se setea aquí para evitar ciclos
        // Puede ser seteada por el PreventaMapper si es necesario
        return domain;
    }

    // Mapeo sin la referencia a Preventa (para evitar ciclos)
    public static ContratoVenta toDomainWithoutPreventa(ContratoVentaEntidad entity) {
        ContratoVenta domain = toDomain(entity);
        // Aquí no hay referencia a setear en el dominio porque ContratoVenta no tiene un setPreventa
        return domain;
    }
}