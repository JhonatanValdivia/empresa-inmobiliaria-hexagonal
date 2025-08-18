package org.academico.springcloud.msvc.norma.application.usescases;

import org.academico.springcloud.msvc.norma.domain.models.entities.Norma;
import org.academico.springcloud.msvc.norma.domain.ports.in.CreateNormaUseCase;
import org.academico.springcloud.msvc.norma.domain.ports.out.NormaRepositoryPort;

public class CreateNormaUseCaseImpl implements CreateNormaUseCase {
    private final NormaRepositoryPort normaRepositoryPort;

    public CreateNormaUseCaseImpl(NormaRepositoryPort normaRepositoryPort) {
        this.normaRepositoryPort = normaRepositoryPort;
    }

    @Override
    public Norma createNorma(Norma norma) {
        return normaRepositoryPort.save(norma);
    }
}
