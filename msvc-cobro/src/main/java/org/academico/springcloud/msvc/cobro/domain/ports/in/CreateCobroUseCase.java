package org.academico.springcloud.msvc.cobro.domain.ports.in;


import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;

public interface CreateCobroUseCase {
    Cobro createCobro(Cobro cobro);
}