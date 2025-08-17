package org.academico.springcloud.msvc.campania.domain.ports.in;

import org.academico.springcloud.msvc.campania.domain.models.entities.Campania;

import java.math.BigDecimal;

public interface AprobarMontoUseCase {
    Campania approve(Long id, BigDecimal nuevoMonto);
}