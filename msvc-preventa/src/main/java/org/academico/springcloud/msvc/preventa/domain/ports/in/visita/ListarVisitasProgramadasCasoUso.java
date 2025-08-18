package org.academico.springcloud.msvc.preventa.domain.ports.in.visita;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import java.util.List;
import java.util.Optional;

public interface ListarVisitasProgramadasCasoUso {
    Optional<List<VisitaProgramada>> listarVisitasProgramadasPorPreventa(Long preventaId);
}