package org.academico.springcloud.msvc.cobro.infrastructure.adapters;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;
import org.academico.springcloud.msvc.cobro.domain.ports.out.CobroRepositoryPort;
import org.academico.springcloud.msvc.cobro.infrastructure.adapters.mappers.CobroMapper;
import org.academico.springcloud.msvc.cobro.infrastructure.entities.CobroEntity;
import org.academico.springcloud.msvc.cobro.infrastructure.repositories.JpaCobroRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class JpaCobroRepositoryAdapter implements CobroRepositoryPort {

    private final JpaCobroRepository jpaCobroRepository;

    public JpaCobroRepositoryAdapter(JpaCobroRepository jpaCobroRepository) {
        this.jpaCobroRepository = jpaCobroRepository;
    }

    @Override
    public Cobro save(Cobro cobro) {
        CobroEntity cobroEntity = CobroMapper.toEntity(cobro);
        CobroEntity cobroSaved = jpaCobroRepository.save(cobroEntity);
        return CobroMapper.toDomain(cobroSaved);
    }

    @Override
    public Optional<Cobro> findById(Long id) {
        return jpaCobroRepository.findById(id)
                .map(CobroMapper::toDomain);
    }

    @Override
    public List<Cobro> findAll() {
        return StreamSupport.stream(jpaCobroRepository.findAll().spliterator(), false)
                .map(CobroMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaCobroRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaCobroRepository.existsById(id);
    }
}