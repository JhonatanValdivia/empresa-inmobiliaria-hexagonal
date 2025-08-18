package org.academico.springcloud.msvc.preventa.domain.ports.in.propuesta;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import java.util.Optional;

public interface AceptarPropuestaPagoCasoUso {
    Optional<PropuestaPago> aceptarPropuestaPagoPreventa(Long preventaId, Long propuestaId);
}