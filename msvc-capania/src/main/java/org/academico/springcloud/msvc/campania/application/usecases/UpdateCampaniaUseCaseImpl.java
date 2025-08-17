package org.academico.springcloud.msvc.campania.application.usecases;

import org.academico.springcloud.msvc.campania.domain.model.Campania;
import org.academico.springcloud.msvc.campania.domain.ports.in.UpdateCampaniaUseCase;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCampaniaUseCaseImpl implements UpdateCampaniaUseCase {
    private final CampaniaRepositoryPort repositoryPort;

    public UpdateCampaniaUseCaseImpl(CampaniaRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Campania update(Campania campania) {
        Optional<Campania> existingCampania = repositoryPort.findById(campania.getIdCampania());
        if (existingCampania.isEmpty()) {
            throw new IllegalArgumentException("La campaña con ID " + campania.getIdCampania() + " no existe.");
        }
        return repositoryPort.save(campania);
    }
}