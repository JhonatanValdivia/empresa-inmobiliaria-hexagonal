package org.academico.springcloud.msvc.preventa.domain.ports.in.contrato;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.ContratoVenta;
import java.util.Optional;

public interface ActualizarContratoVentaCasoUso {
    Optional<ContratoVenta> actualizarContratoVenta(Long preventaId, ContratoVenta contratoActualizado);
}