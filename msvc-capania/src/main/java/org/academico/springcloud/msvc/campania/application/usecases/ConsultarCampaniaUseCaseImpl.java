package org.academico.springcloud.msvc.campania.application.usecases;

import org.academico.springcloud.msvc.campania.domain.models.entities.Campania;
import org.academico.springcloud.msvc.campania.domain.ports.in.ConsultarCampaniaUseCase;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultarCampaniaUseCaseImpl implements ConsultarCampaniaUseCase {

    private final CampaniaRepositoryPort repositoryPort;

    public ConsultarCampaniaUseCaseImpl(CampaniaRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<Campania> listar() {
        return (List<Campania>) repositoryPort.findAll();
    }

    @Override
    public Optional<Campania> obtenerPorId(Long idCampania) {
        return repositoryPort.findById(idCampania);
    }
}
