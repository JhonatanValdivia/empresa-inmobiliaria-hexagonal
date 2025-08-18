package org.academico.springcloud.msvc.preventa.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.VisitaProgramadaEntidad;

public class VisitaProgramadaMapper {

    public VisitaProgramadaMapper() {}

    public static VisitaProgramadaEntidad toEntity(VisitaProgramada domain) {
        if (domain == null) {
            return null;
        }
        VisitaProgramadaEntidad entity = new VisitaProgramadaEntidad();
        entity.setId(domain.getId());
        entity.setFecha(domain.getFecha());
        entity.setEstadoVisita(domain.getEstadoVisita());
        // La relación con Preventa (PreventaEntidad) no se setea aquí para evitar ciclos
        return entity;
    }

    public static VisitaProgramada toDomain(VisitaProgramadaEntidad entity) {
        if (entity == null) {
            return null;
        }
        return new VisitaProgramada(
                entity.getId(),
                entity.getFecha(),
                entity.getEstadoVisita()
        );
    }
}