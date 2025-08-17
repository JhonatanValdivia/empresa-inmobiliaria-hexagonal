package org.academico.springcloud.msvc.norma.application.usescases;

import org.academico.springcloud.msvc.norma.domain.ports.in.DeleteNormaUseCase;
import org.academico.springcloud.msvc.norma.domain.ports.out.NormaRepositoryPort;

public class DeleteNormaUseCaseImpl implements DeleteNormaUseCase {
    private final NormaRepositoryPort normaRepositoryPort;

    public DeleteNormaUseCaseImpl(NormaRepositoryPort normaRepositoryPort) {
        this.normaRepositoryPort = normaRepositoryPort;
    }

    @Override
    public boolean deleteNorma(Long id) {
        return normaRepositoryPort.deleteById(id);
    }
}
