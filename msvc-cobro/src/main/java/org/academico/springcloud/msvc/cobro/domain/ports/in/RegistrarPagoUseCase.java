package org.academico.springcloud.msvc.cobro.domain.ports.in;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;

import java.math.BigDecimal;
import java.util.Optional;

public interface RegistrarPagoUseCase {
    Optional<Cobro> registrarPago(Long idCobro, BigDecimal montoPago);
}