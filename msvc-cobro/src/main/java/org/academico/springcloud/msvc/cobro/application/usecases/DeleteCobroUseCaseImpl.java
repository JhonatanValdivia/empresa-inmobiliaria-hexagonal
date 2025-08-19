package org.academico.springcloud.msvc.cobro.application.usecases;

import org.academico.springcloud.msvc.cobro.domain.ports.in.DeleteCobroUseCase;
import org.academico.springcloud.msvc.cobro.domain.ports.out.CobroRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

public class DeleteCobroUseCaseImpl implements DeleteCobroUseCase {

    private final CobroRepositoryPort cobroRepositoryPort;

    public DeleteCobroUseCaseImpl(CobroRepositoryPort cobroRepositoryPort) {
        this.cobroRepositoryPort = cobroRepositoryPort;
    }

    @Override
    @Transactional
    public boolean deleteCobro(Long id) {
        if (cobroRepositoryPort.existsById(id)) {
            cobroRepositoryPort.deleteById(id);
            return true;
        }
        return false;
    }
}