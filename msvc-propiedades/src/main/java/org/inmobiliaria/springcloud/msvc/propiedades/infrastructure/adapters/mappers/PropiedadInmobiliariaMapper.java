package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.mappers;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.PropiedadInmobiliariaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PropiedadInmobiliariaMapper {

    private PropiedadInmobiliariaMapper() {}

    // =======================
    // Dominio -> Infraestructura
    // =======================
    public static PropiedadInmobiliariaEntity toEntity(PropiedadInmobiliaria d) {
        if (d == null) return null;

        PropiedadInmobiliariaEntity e = new PropiedadInmobiliariaEntity();

        //  Solo setear el id si viene no nulo (caso update)
        if (d.getIdPropiedad() != null) {
            e.setIdPropiedad(d.getIdPropiedad());
        }

        e.setTipoPropiedad(d.getTipoPropiedad());
        e.setEstado(d.getEstado());
        e.setPrecio(ValueObjectMappers.toEntity(d.getPrecio()));
        e.setUbicacion(ValueObjectMappers.toEntity(d.getUbicacion()));
        e.setZonificacion(ValueObjectMappers.toEntity(d.getZonificacion()));

        // Documentos legales
        if (d.getDocumentosLegales() != null) {
            List varDocs = d.getDocumentosLegales().stream()
                    .map(doc -> DocumentoLegalMapper.toEntity(doc, e)) // con back-ref al padre
                    .collect(Collectors.toList());
            e.setDocumentosLegales(varDocs);
        }

        // Servicios
        if (d.getServicios() != null) {
            List varServicios = d.getServicios().stream()
                    .map(serv -> ServicioMapper.toEntity(serv, e)) // con back-ref al padre
                    .collect(Collectors.toList());
            e.setServicios(varServicios);
        }

        // Expediente
        if (d.getExpediente() != null) {
            e.setExpediente(ExpedienteMapper.toEntity(d.getExpediente(), e));
        }

        return e;
    }

    // =======================
    // Infraestructura -> Dominio
    // =======================
    public static PropiedadInmobiliaria toDomain(PropiedadInmobiliariaEntity e) {
        if (e == null) return null;

        PropiedadInmobiliaria d = new PropiedadInmobiliaria();
        d.setIdPropiedad(e.getIdPropiedad());
        d.setTipoPropiedad(e.getTipoPropiedad());
        d.setEstado(e.getEstado());
        d.setPrecio(ValueObjectMappers.toDomain(e.getPrecio()));
        d.setUbicacion(ValueObjectMappers.toDomain(e.getUbicacion()));
        d.setZonificacion(ValueObjectMappers.toDomain(e.getZonificacion()));

        // Documentos legales
        if (e.getDocumentosLegales() != null) {
            d.setDocumentosLegales(
                    e.getDocumentosLegales().stream()
                            .map(DocumentoLegalMapper::toDomain)
                            .collect(Collectors.toList())
            );
        }

        // Servicios
        if (e.getServicios() != null) {
            d.setServicios(
                    e.getServicios().stream()
                            .map(ServicioMapper::toDomain)
                            .collect(Collectors.toList())
            );
        }

        // Expediente
        if (e.getExpediente() != null) {
            d.setExpediente(ExpedienteMapper.toDomain(e.getExpediente()));
        }

        return d;
    }
}
