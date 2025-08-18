package org.academico.springcloud.msvc.preventa.domain.ports.in.visita;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoVisita;
import java.util.Optional;

public interface ActualizarEstadoVisitaCasoUso {
    Optional<VisitaProgramada> actualizarEstadoVisitaPreventa(Long preventaId, Long visitaId, EstadoVisita estadoVisita);
}