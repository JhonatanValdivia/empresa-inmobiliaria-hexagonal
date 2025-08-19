package org.academico.springcloud.msvc.cobro.application.usecases;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;
import org.academico.springcloud.msvc.cobro.domain.ports.in.GetCobroUseCase;
import org.academico.springcloud.msvc.cobro.domain.ports.out.CobroRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class GetCobroUseCaseImpl implements GetCobroUseCase {

    private final CobroRepositoryPort cobroRepositoryPort;

    public GetCobroUseCaseImpl(CobroRepositoryPort cobroRepositoryPort) {
        this.cobroRepositoryPort = cobroRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cobro> getCobroById(Long id) {
        return cobroRepositoryPort.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cobro> getAllCobros() {
        return cobroRepositoryPort.findAll();
    }
}