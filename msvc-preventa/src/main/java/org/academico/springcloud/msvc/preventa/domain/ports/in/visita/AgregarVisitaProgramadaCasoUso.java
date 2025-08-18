package org.academico.springcloud.msvc.preventa.domain.ports.in.visita;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa; // Agregado raíz
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import java.util.Optional;

public interface AgregarVisitaProgramadaCasoUso {
    Optional<Preventa> agregarVisitaProgramada(Long preventaId, VisitaProgramada visita);
}