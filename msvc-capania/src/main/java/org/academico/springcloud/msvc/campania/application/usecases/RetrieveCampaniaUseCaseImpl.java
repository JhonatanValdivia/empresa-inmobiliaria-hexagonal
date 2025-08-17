package org.academico.springcloud.msvc.campania.application.usecases;

import org.academico.springcloud.msvc.campania.domain.model.Campania;
import org.academico.springcloud.msvc.campania.domain.ports.in.RetrieveCampaniaUseCase;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetrieveCampaniaUseCaseImpl implements RetrieveCampaniaUseCase {
    private final CampaniaRepositoryPort repositoryPort;

    public RetrieveCampaniaUseCaseImpl(CampaniaRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<Campania> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Optional<Campania> findById(Long id) {
        return repositoryPort.findById(id);
    }
}