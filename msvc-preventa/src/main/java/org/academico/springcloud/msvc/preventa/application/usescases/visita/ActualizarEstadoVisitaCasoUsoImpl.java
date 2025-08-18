package org.academico.springcloud.msvc.preventa.application.usescases.visita;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoVisita;
import org.academico.springcloud.msvc.preventa.domain.ports.in.visita.ActualizarEstadoVisitaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class ActualizarEstadoVisitaCasoUsoImpl implements ActualizarEstadoVisitaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public ActualizarEstadoVisitaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<VisitaProgramada> actualizarEstadoVisitaPreventa(Long preventaId, Long visitaId, EstadoVisita estadoVisita) {
        return preventaRepositorioPort.porId(preventaId).flatMap(preventa -> {
            Optional<VisitaProgramada> opVisita = preventa.findVisitaProgramadaById(visitaId);
            if (opVisita.isPresent()) {
                VisitaProgramada visita = opVisita.get();
                try {
                    visita.actualizarEstado(estadoVisita); // Lógica de dominio para actualizar estado
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Error al actualizar estado de visita: " + e.getMessage(), e);
                }
                preventaRepositorioPort.guardar(preventa); // Guarda el agregado
                return Optional.of(visita);
            }
            return Optional.empty(); // Visita no encontrada en la preventa
        });
    }
}