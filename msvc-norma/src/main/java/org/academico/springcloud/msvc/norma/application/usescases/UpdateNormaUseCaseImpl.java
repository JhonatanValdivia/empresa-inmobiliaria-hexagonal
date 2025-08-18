package org.academico.springcloud.msvc.norma.application.usescases;

import org.academico.springcloud.msvc.norma.domain.models.entities.Norma;
import org.academico.springcloud.msvc.norma.domain.ports.in.UpdateNormaUseCase;
import org.academico.springcloud.msvc.norma.domain.ports.out.NormaRepositoryPort;

import java.util.Optional;

public class UpdateNormaUseCaseImpl implements UpdateNormaUseCase {
    private final NormaRepositoryPort normaRepositoryPort;

    public UpdateNormaUseCaseImpl(NormaRepositoryPort normaRepositoryPort) {
        this.normaRepositoryPort = normaRepositoryPort;
    }

    @Override
    public Optional<Norma> updateNorma(Long id, Norma norma) {
        return normaRepositoryPort.update(norma);
    }
}
