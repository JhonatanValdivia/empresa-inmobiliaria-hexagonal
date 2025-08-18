package org.academico.springcloud.msvc.preventa.infrastructure.adapters;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.academico.springcloud.msvc.preventa.infrastructure.adapters.mappers.PreventaMapper; // <--- Sigue siendo necesario para llamar a sus métodos estáticos
import org.academico.springcloud.msvc.preventa.infrastructure.entities.PreventaEntidad;
import org.academico.springcloud.msvc.preventa.infrastructure.repositories.JpaPreventaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PreventaRepositorioAdapter implements PreventaRepositorioPort {

    private final JpaPreventaRepositorio jpaPreventaRepositorio;
    // private final PreventaMapper preventaMapper; // <--- ¡ELIMINA ESTA VARIABLE DE INSTANCIA!

    @Autowired
    public PreventaRepositorioAdapter(JpaPreventaRepositorio jpaPreventaRepositorio) { // <--- ELIMINA 'PreventaMapper preventaMapper' DEL CONSTRUCTOR
        this.jpaPreventaRepositorio = jpaPreventaRepositorio;
        // this.preventaMapper = preventaMapper; // <--- ¡ELIMINA ESTA ASIGNACIÓN!
    }

    @Override
    public Preventa guardar(Preventa preventa) {
        PreventaEntidad entidad = PreventaMapper.toEntity(preventa); // <--- CAMBIADO: PreventaMapper.toEntity
        PreventaEntidad guardada = jpaPreventaRepositorio.save(entidad);
        return PreventaMapper.toDomain(guardada); // <--- CAMBIADO: PreventaMapper.toDomain
    }

    @Override
    public Optional<Preventa> porId(Long id) {
        // CORRECCIÓN DE LA LÍNEA DEL ERROR
        return jpaPreventaRepositorio.findById(id).map(PreventaMapper::toDomain); // <--- CAMBIADO: PreventaMapper::toDomain
    }

    @Override
    public List<Preventa> listar() {
        Iterable<PreventaEntidad> entidades = jpaPreventaRepositorio.findAll();
        return StreamSupport.stream(entidades.spliterator(), false)
                .map(PreventaMapper::toDomain) // <--- CAMBIADO: PreventaMapper::toDomain
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Preventa> actualizar(Preventa preventa) {
        if (!jpaPreventaRepositorio.existsById(preventa.getId())) {
            return Optional.empty();
        }
        PreventaEntidad entidad = PreventaMapper.toEntity(preventa); // <--- CAMBIADO: PreventaMapper.toEntity
        PreventaEntidad actualizada = jpaPreventaRepositorio.save(entidad);
        return Optional.of(PreventaMapper.toDomain(actualizada)); // <--- CAMBIADO: PreventaMapper.toDomain
    }

    @Override
    public void eliminarPorId(Long id) {
        if (jpaPreventaRepositorio.existsById(id)) {
            jpaPreventaRepositorio.deleteById(id);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return jpaPreventaRepositorio.existsById(id);
    }
}