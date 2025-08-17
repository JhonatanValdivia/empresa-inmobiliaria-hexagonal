package org.academico.springcloud.msvc.campania.domain.ports.in;

import org.academico.springcloud.msvc.campania.domain.model.Campania;

import java.math.BigDecimal;
import java.util.Optional;

public interface ApproveMontoUseCase {
    Campania approve(Long id, BigDecimal nuevoMonto);
}