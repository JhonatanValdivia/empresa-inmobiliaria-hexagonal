package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.mappers;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.Expediente;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.Servicio;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.ExpedienteEntity;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.PlanoEntity;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.PropiedadInmobiliariaEntity;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.ServicioEntity;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

public class ExpedienteMapper {
    private ExpedienteMapper() {}
    public static ExpedienteEntity toEntity(Expediente d) {
        if (d == null) return null;
        ExpedienteEntity e = new ExpedienteEntity();
        e.setEstadoExpediente(d.getEstadoExpediente());
        e.setIdExpediente(d.getIdExpediente());
        e.setFecha(ValueObjectMappers.toEntity(d.getFecha()));
        e.setObservaciones(d.getObservaciones());
        e.setTipoExpediente(d.getTipoExpediente());
        if (d.getPlanos() != null) {
            // mapeamos cada Plano (dominio) a PlanoEntity (infra)
            List<PlanoEntity> planos = d.getPlanos().stream()
                    .map(PlanoMapper::toEntity)  // sin back-ref aquí
                    .collect(Collectors.toList());
            e.setPlanos(planos);
        }
        return e;

    }
    public static ExpedienteEntity toEntity(Expediente d, PropiedadInmobiliariaEntity parent) {
        ExpedienteEntity e = toEntity(d);
        if (e != null) {
            e.setPropiedad(parent); // referencia JPA correcta
            // ahora sí seteamos back-ref de planos -> expediente
            if (e.getPlanos() != null) {
                e.getPlanos().forEach(p -> p.setExpediente(e));
            }
        }
        return e;
    }

    // Infra -> Dominio
    public static Expediente toDomain(ExpedienteEntity e) {
        if (e == null) return null;
        Expediente d = new Expediente();
        d.setIdExpediente(e.getIdExpediente());
        d.setEstadoExpediente(e.getEstadoExpediente());
        d.setTipoExpediente(e.getTipoExpediente());
        d.setFecha(ValueObjectMappers.toDomain(e.getFecha()));
        d.setObservaciones(e.getObservaciones());

        if (e.getPlanos() != null) {
            d.setPlanos(
                    e.getPlanos().stream()
                            .map(PlanoMapper::toDomain)
                            .collect(Collectors.toList())
            );
        }

        return d;
    }
}
