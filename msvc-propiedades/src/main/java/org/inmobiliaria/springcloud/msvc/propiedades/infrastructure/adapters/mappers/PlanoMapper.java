package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.mappers;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.Plano;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.ExpedienteEntity;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.PlanoEntity;

public class PlanoMapper {
    private PlanoMapper() {}

    // Dominio -> Infra
    public static PlanoEntity toEntity(Plano d) {
        if (d == null) return null;
        PlanoEntity e = new PlanoEntity();
        e.setIdPlano(d.getIdPlano());
        e.setEstadoPlano(d.getEstadoPlano());
        e.setArchivoRuta(d.getArchivoRuta());
        e.setAutor(d.getAutor());
        e.setObservaciones(d.getObservaciones());
        e.setTipoPlano(d.getTipoPlano());
        e.setVersion(d.getVersion());
        e.setNombreArchivo(d.getNombreArchivo());
        return e;
    }

    // Dominio -> Infra (con back-reference al expediente)
    public static PlanoEntity toEntity(Plano d, ExpedienteEntity parent) {
        PlanoEntity e = toEntity(d);
        if (e != null) e.setExpediente(parent);
        return e;
    }

    // Infra -> Dominio
    public static Plano toDomain(PlanoEntity e) {
        if (e == null) return null;
        Plano d = new Plano();
        d.setIdPlano(e.getIdPlano());
        d.setAutor(e.getAutor());
        d.setEstadoPlano(e.getEstadoPlano());
        d.setTipoPlano(e.getTipoPlano());
        d.setVersion(e.getVersion());
        d.setArchivoRuta(e.getArchivoRuta());
        d.setObservaciones(e.getObservaciones());
        d.setNombreArchivo(e.getNombreArchivo());

        return d;
    }
}
