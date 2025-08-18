package org.academico.springcloud.msvc.preventa.application.usescases.propuesta;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import org.academico.springcloud.msvc.preventa.domain.ports.in.propuesta.ActualizarPropuestaPagoCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class ActualizarPropuestaPagoCasoUsoImpl implements ActualizarPropuestaPagoCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public ActualizarPropuestaPagoCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<PropuestaPago> actualizarPropuestaPago(Long preventaId, Long propuestaId, PropuestaPago propuestaActualizada) {
        return preventaRepositorioPort.porId(preventaId).flatMap(preventa -> {
            Optional<PropuestaPago> opPropuestaDB = preventa.findPropuestaPagoById(propuestaId);
            if (opPropuestaDB.isPresent()) {
                PropuestaPago propuestaDB = opPropuestaDB.get();
                // Actualiza los campos de la propuesta de pago encontrada
                if (propuestaActualizada.getMonto() != null) {
                    propuestaDB.setMonto(propuestaActualizada.getMonto());
                }
                if (propuestaActualizada.getFecha() != null) {
                    propuestaDB.setFecha(propuestaActualizada.getFecha());
                }
                if (propuestaActualizada.getCuotas() != null) {
                    propuestaDB.setCuotas(propuestaActualizada.getCuotas());
                }
                if (propuestaActualizada.getMetodoPago() != null) {
                    propuestaDB.setMetodoPago(propuestaActualizada.getMetodoPago());
                }

                preventaRepositorioPort.guardar(preventa); // Guarda el agregado
                return Optional.of(propuestaDB);
            }
            return Optional.empty(); // Propuesta no encontrada en la preventa
        });
    }
}