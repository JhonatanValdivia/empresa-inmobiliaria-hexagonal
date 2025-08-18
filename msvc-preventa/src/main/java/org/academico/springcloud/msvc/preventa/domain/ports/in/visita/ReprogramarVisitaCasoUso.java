package org.academico.springcloud.msvc.preventa.domain.ports.in.visita;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import java.time.LocalDate;
import java.util.Optional;

public interface ReprogramarVisitaCasoUso {
    Optional<VisitaProgramada> reprogramarVisitaPreventa(Long preventaId, Long visitaId, LocalDate fecha);
}