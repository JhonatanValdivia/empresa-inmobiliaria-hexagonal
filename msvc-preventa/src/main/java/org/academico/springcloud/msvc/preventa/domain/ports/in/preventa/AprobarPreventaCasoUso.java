package org.academico.springcloud.msvc.preventa.domain.ports.in.preventa;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import java.util.Optional;

public interface AprobarPreventaCasoUso {
    Optional<Preventa> aprobarPreventa(Long preventaId);
}