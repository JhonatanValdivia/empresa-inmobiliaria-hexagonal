package org.academico.springcloud.msvc.preventa.application.usescases.visita;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import org.academico.springcloud.msvc.preventa.domain.ports.in.visita.ReprogramarVisitaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;

public class ReprogramarVisitaCasoUsoImpl implements ReprogramarVisitaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public ReprogramarVisitaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<VisitaProgramada> reprogramarVisitaPreventa(Long preventaId, Long visitaId, LocalDate fecha) {
        return preventaRepositorioPort.porId(preventaId).flatMap(preventa -> {
            Optional<VisitaProgramada> opVisita = preventa.findVisitaProgramadaById(visitaId);
            if (opVisita.isPresent()) {
                VisitaProgramada visita = opVisita.get();
                try {
                    visita.reprogramarVisita(fecha); // Lógica de dominio para reprogramar
                } catch (IllegalStateException | IllegalArgumentException e) {
                    throw new RuntimeException("Error al reprogramar visita: " + e.getMessage(), e);
                }
                preventaRepositorioPort.guardar(preventa); // Guarda el agregado
                return Optional.of(visita);
            }
            return Optional.empty(); // Visita no encontrada en la preventa
        });
    }
}