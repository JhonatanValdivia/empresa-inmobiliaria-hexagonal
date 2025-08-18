package org.academico.springcloud.msvc.preventa.application.usescases.propuesta;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import org.academico.springcloud.msvc.preventa.domain.ports.in.propuesta.AceptarPropuestaPagoCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class AceptarPropuestaPagoCasoUsoImpl implements AceptarPropuestaPagoCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public AceptarPropuestaPagoCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<PropuestaPago> aceptarPropuestaPagoPreventa(Long preventaId, Long propuestaId) {
        return preventaRepositorioPort.porId(preventaId).flatMap(preventa -> {
            Optional<PropuestaPago> opPropuesta = preventa.findPropuestaPagoById(propuestaId);
            if (opPropuesta.isPresent()) {
                PropuestaPago propuesta = opPropuesta.get();
                try {
                    propuesta.aceptarPropuesta(); // Lógica de dominio para aceptar
                } catch (IllegalStateException e) {
                    throw new RuntimeException("Error al aceptar propuesta: " + e.getMessage(), e);
                }
                preventaRepositorioPort.guardar(preventa); // Guarda el agregado
                return Optional.of(propuesta);
            }
            return Optional.empty(); // Propuesta no encontrada en la preventa
        });
    }
}