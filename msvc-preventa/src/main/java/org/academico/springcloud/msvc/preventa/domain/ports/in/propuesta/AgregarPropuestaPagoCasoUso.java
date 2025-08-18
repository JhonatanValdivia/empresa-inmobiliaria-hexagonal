package org.academico.springcloud.msvc.preventa.domain.ports.in.propuesta;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa; // Agregado raíz
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import java.util.Optional;

public interface AgregarPropuestaPagoCasoUso {
    Optional<Preventa> agregarPropuestaPago(Long preventaId, PropuestaPago propuesta);
}