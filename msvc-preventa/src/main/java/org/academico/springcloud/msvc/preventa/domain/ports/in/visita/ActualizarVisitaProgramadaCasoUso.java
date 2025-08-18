package org.academico.springcloud.msvc.preventa.domain.ports.in.visita;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import java.util.Optional;

public interface ActualizarVisitaProgramadaCasoUso {
    Optional<VisitaProgramada> actualizarVisitaProgramada(Long preventaId, Long visitaId, VisitaProgramada visitaActualizada);
}