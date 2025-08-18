package org.academico.springcloud.msvc.comision.infrastructure.repositories;

import org.academico.springcloud.msvc.comision.domain.models.entities.Comision;
import org.academico.springcloud.msvc.comision.domain.ports.out.ComisionRepositorioPort;
import org.academico.springcloud.msvc.comision.infrastructure.mappers.ComisionMapper;
import org.academico.springcloud.msvc.comision.infrastructure.models.entities.ComisionEntidad;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ComisionRepositorioAdapter implements ComisionRepositorioPort {
    private final JpaComisionRepositorio jpaComisionRepositorio;
    private final ComisionMapper comisionMapper;

    public ComisionRepositorioAdapter(JpaComisionRepositorio jpaComisionRepositorio, ComisionMapper comisionMapper) {
        this.jpaComisionRepositorio = jpaComisionRepositorio;
        this.comisionMapper = comisionMapper;
    }

    @Override
    public List<Comision> listar() {
        return jpaComisionRepositorio.findAll().stream()
                .map(comisionMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Comision> porId(Long id) {
        return jpaComisionRepositorio.findById(id).map(comisionMapper::toDomain);
    }

    @Override
    public Comision guardar(Comision comision) {
        ComisionEntidad entidad = comisionMapper.toEntity(comision);
        ComisionEntidad guardada = jpaComisionRepositorio.save(entidad);
        return comisionMapper.toDomain(guardada);
    }

    @Override
    public boolean eliminarPorId(Long id) {
        if (!jpaComisionRepositorio.existsById(id)) {
            return false;
        }
        jpaComisionRepositorio.deleteById(id);
        return true;
    }

    @Override
    public Optional<Comision> actualizar(Comision comision) {
        if (!jpaComisionRepositorio.existsById(comision.getId())) {
            return Optional.empty();
        }
        ComisionEntidad entidad = comisionMapper.toEntity(comision);
        ComisionEntidad actualizada = jpaComisionRepositorio.save(entidad);
        return Optional.of(comisionMapper.toDomain(actualizada));
    }

    @Override
    public boolean existePorId(Long id) {
        return jpaComisionRepositorio.existsById(id);
    }

    @Override
    public long contarComisiones() {
        return jpaComisionRepositorio.count();
    }
}
