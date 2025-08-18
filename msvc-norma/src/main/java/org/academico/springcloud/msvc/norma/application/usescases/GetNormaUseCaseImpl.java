package org.academico.springcloud.msvc.norma.application.usescases;

import org.academico.springcloud.msvc.norma.domain.models.entities.Norma;
import org.academico.springcloud.msvc.norma.domain.ports.in.GetNormaUseCase;
import org.academico.springcloud.msvc.norma.domain.ports.out.NormaRepositoryPort;

import java.util.List;
import java.util.Optional;

public class GetNormaUseCaseImpl implements GetNormaUseCase {
    private final NormaRepositoryPort normaRepositoryPort;

    public GetNormaUseCaseImpl(NormaRepositoryPort normaRepositoryPort) {
        this.normaRepositoryPort = normaRepositoryPort;
    }

    @Override
    public Optional<Norma> getNorma(Long id) {
        return normaRepositoryPort.findById(id);
    }

    @Override
    public List<Norma> getAllNormas() {
        return normaRepositoryPort.findAll();
    }
}
