package org.academico.springcloud.msvc.campania.application.usecases;

import org.academico.springcloud.msvc.campania.domain.models.entities.Campania;
import org.academico.springcloud.msvc.campania.domain.ports.in.ActualizarCampaniaUseCase;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("actualizarCampaniaUseCaseImpl")
public class ActualizarCampaniaUseCaseImpl implements ActualizarCampaniaUseCase {
    private final CampaniaRepositoryPort repositoryPort;

    public ActualizarCampaniaUseCaseImpl(CampaniaRepositoryPort repositoryPort) {
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