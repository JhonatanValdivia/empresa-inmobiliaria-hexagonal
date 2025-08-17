package org.academico.springcloud.msvc.campania.application.usecases;

import org.academico.springcloud.msvc.campania.domain.ports.in.EliminarCampaniaUseCase;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("eliminarCampaniaUseCaseImpl")
public class EliminarCampaniaUseCaseImpl implements EliminarCampaniaUseCase {
    private final CampaniaRepositoryPort repositoryPort;

    public EliminarCampaniaUseCaseImpl(CampaniaRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public void delete(Long id) {
        repositoryPort.deleteById(id);
    }
}
