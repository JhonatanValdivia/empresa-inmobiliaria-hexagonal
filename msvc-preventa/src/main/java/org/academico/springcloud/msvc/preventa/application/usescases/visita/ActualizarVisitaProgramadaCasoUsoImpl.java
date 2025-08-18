package org.academico.springcloud.msvc.preventa.application.usescases.visita;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import org.academico.springcloud.msvc.preventa.domain.ports.in.visita.ActualizarVisitaProgramadaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class ActualizarVisitaProgramadaCasoUsoImpl implements ActualizarVisitaProgramadaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public ActualizarVisitaProgramadaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<VisitaProgramada> actualizarVisitaProgramada(Long preventaId, Long visitaId, VisitaProgramada visitaActualizada) {
        return preventaRepositorioPort.porId(preventaId).flatMap(preventa -> {
            Optional<VisitaProgramada> opVisitaDB = preventa.findVisitaProgramadaById(visitaId);
            if (opVisitaDB.isPresent()) {
                VisitaProgramada visitaDB = opVisitaDB.get();
                // Actualiza los campos de la visita encontrada
                if (visitaActualizada.getFecha() != null) {
                    visitaDB.setFecha(visitaActualizada.getFecha());
                }
                if (visitaActualizada.getEstadoVisita() != null) {
                    visitaDB.setEstadoVisita(visitaActualizada.getEstadoVisita());
                }

                preventaRepositorioPort.guardar(preventa); // Guarda el agregado
                return Optional.of(visitaDB);
            }
            return Optional.empty(); // Visita no encontrada en la preventa
        });
    }
}