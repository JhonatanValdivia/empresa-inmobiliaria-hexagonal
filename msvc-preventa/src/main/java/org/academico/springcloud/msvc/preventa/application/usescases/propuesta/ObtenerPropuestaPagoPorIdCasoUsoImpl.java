package org.academico.springcloud.msvc.preventa.application.usescases.propuesta;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import org.academico.springcloud.msvc.preventa.domain.ports.in.propuesta.ObtenerPropuestaPagoPorIdCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class ObtenerPropuestaPagoPorIdCasoUsoImpl implements ObtenerPropuestaPagoPorIdCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public ObtenerPropuestaPagoPorIdCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PropuestaPago> porIdPropuestaPago(Long preventaId, Long propuestaId) {
        return preventaRepositorioPort.porId(preventaId)
                .flatMap(preventa -> preventa.findPropuestaPagoById(propuestaId)); // Encuentra la propuesta dentro del agregado
    }
}