package org.academico.springcloud.msvc.norma.application.services;

import org.academico.springcloud.msvc.norma.domain.models.entities.Norma;
import org.academico.springcloud.msvc.norma.domain.ports.in.CreateNormaUseCase;
import org.academico.springcloud.msvc.norma.domain.ports.in.DeleteNormaUseCase;
import org.academico.springcloud.msvc.norma.domain.ports.in.GetNormaUseCase;
import org.academico.springcloud.msvc.norma.domain.ports.in.UpdateNormaUseCase;

import java.util.List;
import java.util.Optional;

public class NormaService implements CreateNormaUseCase, DeleteNormaUseCase, GetNormaUseCase, UpdateNormaUseCase {


    private  final CreateNormaUseCase createNormaUseCase;
    private final DeleteNormaUseCase deleteNormaUseCase;
    private final GetNormaUseCase getNormaUseCase;
    private final UpdateNormaUseCase updateNormaUseCase;

    public NormaService(CreateNormaUseCase createNormaUseCase, DeleteNormaUseCase deleteNormaUseCase, GetNormaUseCase getNormaUseCase, UpdateNormaUseCase updateNormaUseCase) {
        this.createNormaUseCase = createNormaUseCase;
        this.deleteNormaUseCase = deleteNormaUseCase;
        this.getNormaUseCase = getNormaUseCase;
        this.updateNormaUseCase = updateNormaUseCase;
    }

    @Override
    public Norma createNorma(Norma norma) {
        return createNormaUseCase.createNorma(norma);
    }

    @Override
    public boolean deleteNorma(Long id) {
        return deleteNormaUseCase.deleteNorma(id);
    }

    @Override
    public Optional<Norma> getNorma(Long id) {
        return getNormaUseCase.getNorma(id);
    }

    @Override
    public List<Norma> getAllNormas() {
        return getNormaUseCase.getAllNormas();
    }

    @Override
    public Optional<Norma> updateNorma(Long id, Norma norma) {
        return updateNormaUseCase.updateNorma(id,norma);
    }
}
