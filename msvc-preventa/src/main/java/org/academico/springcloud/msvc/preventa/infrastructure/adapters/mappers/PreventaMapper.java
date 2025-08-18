package org.academico.springcloud.msvc.preventa.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.ContratoVenta;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.ContratoVentaEntidad;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.PreventaEntidad;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.PropuestaPagoEntidad;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.VisitaProgramadaEntidad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Este mapper es central y maneja las relaciones anidadas
public class PreventaMapper {

    public PreventaMapper() {}

    // ------------------ ENTITY <-> DOMAIN ------------------

    public static PreventaEntidad toEntity(Preventa domain) {
        if (domain == null) return null;

        PreventaEntidad entity = new PreventaEntidad();
        entity.setId(domain.getId());
        entity.setFechaInicio(domain.getFechaInicio());
        entity.setEstado(domain.getEstado());

        // Asignar IDs de usuario
        entity.setUsuarioAgenteId(domain.getUsuarioAgenteId());
        entity.setUsuarioClienteId(domain.getUsuarioClienteId());

        // Mapeo de ContratoVenta
        if (domain.getContratoVenta() != null) {
            ContratoVentaEntidad contratoEntity = ContratoVentaMapper.toEntity(domain.getContratoVenta());
            if (contratoEntity != null) {
                contratoEntity.setPreventa(entity);
            }
            entity.setContratoVenta(contratoEntity);
        } else {
            entity.setContratoVenta(null);
        }

        // Mapeo de PropuestasPago
        if (domain.getPropuestasPago() != null && !domain.getPropuestasPago().isEmpty()) {
            List<PropuestaPagoEntidad> propuestaEntities = new ArrayList<>();
            for (var pp : domain.getPropuestasPago()) {
                var ppEntity = PropuestaPagoMapper.toEntity(pp);
                if (ppEntity != null) {
                    ppEntity.setPreventa(entity);
                    propuestaEntities.add(ppEntity);
                }
            }
            entity.setPropuestasPago(propuestaEntities);
        } else {
            entity.setPropuestasPago(new ArrayList<>());
        }

        // Mapeo de VisitasProgramadas
        if (domain.getVisitasProgramadas() != null && !domain.getVisitasProgramadas().isEmpty()) {
            List<VisitaProgramadaEntidad> visitaEntities = new ArrayList<>();
            for (var vp : domain.getVisitasProgramadas()) {
                var vpEntity = VisitaProgramadaMapper.toEntity(vp);
                if (vpEntity != null) {
                    vpEntity.setPreventa(entity);
                    visitaEntities.add(vpEntity);
                }
            }
            entity.setVisitasProgramadas(visitaEntities);
        } else {
            entity.setVisitasProgramadas(new ArrayList<>());
        }

        return entity;
    }

    public static Preventa toDomain(PreventaEntidad entity) {
        if (entity == null) return null;

        // Mapeo de ContratoVenta
        ContratoVenta domainContrato = null;
        if (entity.getContratoVenta() != null) {
            domainContrato = ContratoVentaMapper.toDomain(entity.getContratoVenta());
        }

        // Mapeo de PropuestasPago
        List<org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago> domainPropuestas =
                entity.getPropuestasPago() != null
                        ? entity.getPropuestasPago().stream()
                        .map(PropuestaPagoMapper::toDomain)
                        .collect(Collectors.toList())
                        : new ArrayList<>();

        // Mapeo de VisitasProgramadas
        List<org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada> domainVisitas =
                entity.getVisitasProgramadas() != null
                        ? entity.getVisitasProgramadas().stream()
                        .map(VisitaProgramadaMapper::toDomain)
                        .collect(Collectors.toList())
                        : new ArrayList<>();

        // Construcción del dominio
        Preventa domain = new Preventa(
                entity.getId(),
                entity.getFechaInicio(),
                entity.getEstado(),
                domainContrato,
                domainPropuestas,
                domainVisitas
        );

        // Asignar IDs de usuario
        domain.setUsuarioAgenteId(entity.getUsuarioAgenteId());
        domain.setUsuarioClienteId(entity.getUsuarioClienteId());

        return domain;
    }
}