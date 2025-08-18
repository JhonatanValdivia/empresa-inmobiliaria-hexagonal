package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.mappers;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.DocumentoLegal;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.DocumentoLegalEntity;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.PropiedadInmobiliariaEntity;

public class DocumentoLegalMapper {
    public DocumentoLegalMapper(){}
    public static DocumentoLegalEntity toEntity(DocumentoLegal d) {

        if (d == null) return null;
        DocumentoLegalEntity e = new DocumentoLegalEntity();
        e.setEstadoDocumento(d.getEstadoDocumento());
        e.setIdDocumentoLegal(d.getIdDocumentoLegal());
        e.setNombreNotaria(d.getNombreNotaria());
        e.setTipoDocumento(d.getTipoDocumento());
        e.setNumeroInscripcionSunarp(d.getNumeroInscripcionSunarp());


        return e;
    }
    public static DocumentoLegalEntity toEntity(DocumentoLegal d, PropiedadInmobiliariaEntity parent) {
        DocumentoLegalEntity e = toEntity(d);
        if (e != null) {
            e.setPropiedad(parent); // AQUÍ sí pasamos la entidad JPA
        }
        return e;
    }

    // Infra -> Dominio
    public static DocumentoLegal toDomain(DocumentoLegalEntity e) {
        if (e == null) return null;
        DocumentoLegal d = new DocumentoLegal();
        d.setIdDocumentoLegal(e.getIdDocumentoLegal());
        d.setTipoDocumento(e.getTipoDocumento());
        d.setEstadoDocumento(e.getEstadoDocumento());
        d.setNombreNotaria(e.getNombreNotaria());
        d.setNumeroInscripcionSunarp(e.getNumeroInscripcionSunarp());


        // Normalmente no seteamos la propiedad para evitar ciclos;

        return d;
    }
}
