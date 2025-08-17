package org.academico.springcloud.msvc.campania.infrastructure.adapters;

import org.academico.springcloud.msvc.campania.domain.model.Campania;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.academico.springcloud.msvc.campania.infrastructure.entities.CampaniaEntity;
import org.academico.springcloud.msvc.campania.infrastructure.mappers.CampaniaMapper;
import org.academico.springcloud.msvc.campania.infrastructure.repositories.JpaCampaniaRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Primary
public class CampaniaRepositoryAdapter implements CampaniaRepositoryPort {

    private final JpaCampaniaRepository repository;
    private final CampaniaMapper mapper;

    public CampaniaRepositoryAdapter(JpaCampaniaRepository repository, CampaniaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Campania> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Campania> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Campania save(Campania campania) {
        CampaniaEntity entity = mapper.toEntity(campania);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}