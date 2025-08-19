package org.academico.springcloud.msvc.cobro.domain.ports.in;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;

import java.util.Optional;

public interface UpdateCobroUseCase {
    Optional<Cobro> updateCobro(Long id, Cobro cobro);
}