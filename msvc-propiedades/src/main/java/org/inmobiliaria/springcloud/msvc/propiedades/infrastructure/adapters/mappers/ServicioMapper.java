package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.mappers;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.DocumentoLegal;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.Servicio;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.DocumentoLegalEntity;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.PropiedadInmobiliariaEntity;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.ServicioEntity;

public class ServicioMapper {
    public ServicioMapper(){}
    public static ServicioEntity toEntity(Servicio d) {
        if (d == null) return null;
        ServicioEntity e = new ServicioEntity();
        e.setEstadoServicio(d.getEstadoServicio());
        e.setIdServicio(d.getIdServicio());
        e.setNumeroSuministro(d.getNumeroSuministro());
        e.setProveedor(d.getProveedor());
        return e;
    }
    public static ServicioEntity toEntity(Servicio d, PropiedadInmobiliariaEntity parent) {
        ServicioEntity e = toEntity(d);
        if (e != null) {
            e.setPropiedad(parent); // AQUÍ sí pasamos la entidad JPA
        }
        return e;
    }

    // Infra -> Dominio
    public static Servicio toDomain(ServicioEntity e) {
        if (e == null) return null;
       Servicio d = new Servicio();
       d.setEstadoServicio(e.getEstadoServicio());
       d.setIdServicio(e.getIdServicio());
       d.setProveedor(e.getProveedor());
       d.setNumeroSuministro(e.getNumeroSuministro());
       d.setTipoServicio(e.getTipoServicio());



        return d;
    }
}
