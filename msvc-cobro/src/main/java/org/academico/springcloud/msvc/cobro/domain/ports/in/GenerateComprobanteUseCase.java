package org.academico.springcloud.msvc.cobro.domain.ports.in;

import java.util.Optional;

public interface GenerateComprobanteUseCase {
    Optional<String> generateComprobante(Long idCobro);
}