package org.academico.springcloud.msvc.cobro.domain.ports.in;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;

import java.util.List;
import java.util.Optional;

public interface GetCobroUseCase {
    Optional<Cobro> getCobroById(Long id);
    List<Cobro> getAllCobros();
}