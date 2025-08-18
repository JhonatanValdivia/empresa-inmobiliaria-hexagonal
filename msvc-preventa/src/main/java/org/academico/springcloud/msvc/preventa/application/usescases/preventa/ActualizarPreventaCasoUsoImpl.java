package org.academico.springcloud.msvc.preventa.application.usescases.preventa;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.preventa.ActualizarPreventaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class ActualizarPreventaCasoUsoImpl implements ActualizarPreventaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public ActualizarPreventaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<Preventa> actualizarPreventa(Long id, Preventa preventaActualizada) {
        return preventaRepositorioPort.porId(id).map(existingPreventa -> {
            // Actualiza los campos del agregado Preventa
            if (preventaActualizada.getFechaInicio() != null) {
                existingPreventa.setFechaInicio(preventaActualizada.getFechaInicio());
            }
            if (preventaActualizada.getEstado() != null) {
                existingPreventa.setEstado(preventaActualizada.getEstado());
            }
            // Las colecciones (ContratoVenta, PropuestasPago, VisitasProgramadas)
            // deben manejarse a través de sus propios casos de uso para mantener la consistencia del agregado.
            // Si se pasa un contrato o listas aquí, es un problema de diseño,
            // ya que se estarían sobreescribiendo las relaciones sin usar los métodos de agregado.

            return preventaRepositorioPort.guardar(existingPreventa);
        });
    }
}