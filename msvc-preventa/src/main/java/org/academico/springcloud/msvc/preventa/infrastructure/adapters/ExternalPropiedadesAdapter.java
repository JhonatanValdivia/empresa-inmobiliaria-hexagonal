package org.academico.springcloud.msvc.preventa.infrastructure.adapters;

import feign.FeignException;
import org.academico.springcloud.msvc.preventa.domain.models.PropiedadInmobiliaria;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PropiedadPort;
import org.academico.springcloud.msvc.preventa.infrastructure.adapters.mappers.PreventaMapper;
import org.academico.springcloud.msvc.preventa.infrastructure.clients.PropiedadClientRest;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.PropiedadInmobiliariaPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExternalPropiedadesAdapter implements PropiedadPort {

    private static final Logger log = LoggerFactory.getLogger(ExternalPropiedadesAdapter.class);
    private final PropiedadClientRest propiedadClientRest;

    public ExternalPropiedadesAdapter(PropiedadClientRest propiedadClientRest) {
        this.propiedadClientRest = propiedadClientRest;
    }

    @Override
    public Optional<PropiedadInmobiliaria> obtenerPorId(Long id) {
        try {
            PropiedadInmobiliariaPojo pojo = propiedadClientRest.obtenerPropiedad(id);
            return Optional.ofNullable(PreventaMapper.toDomain(pojo));

        } catch (FeignException.NotFound e) {
            log.info("No se encontró la propiedad con id: {}. El servicio externo devolvió 404.", id);
            return Optional.empty();

        } catch (Exception e) {
            log.error("Error inesperado al intentar obtener la propiedad con id: {}. Causa: {}", id, e.getMessage(), e);
            return Optional.empty();
        }
    }
}