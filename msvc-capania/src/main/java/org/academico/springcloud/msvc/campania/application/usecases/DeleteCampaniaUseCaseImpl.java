package org.academico.springcloud.msvc.campania.application.usecases;

import org.academico.springcloud.msvc.campania.domain.ports.in.DeleteCampaniaUseCase;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.springframework.stereotype.Service;

@Service("deleteCampaniaUseCaseImpl")
public class DeleteCampaniaUseCaseImpl implements DeleteCampaniaUseCase {
    private final CampaniaRepositoryPort repositoryPort;

    public DeleteCampaniaUseCaseImpl(CampaniaRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public void delete(Long id) {
        repositoryPort.deleteById(id);
    }
}
