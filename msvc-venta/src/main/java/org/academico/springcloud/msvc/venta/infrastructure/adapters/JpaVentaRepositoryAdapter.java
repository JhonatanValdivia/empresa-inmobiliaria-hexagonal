package org.academico.springcloud.msvc.venta.infrastructure.adapters;

import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;
import org.academico.springcloud.msvc.venta.domain.ports.out.VentaRepositoryPort;
import org.academico.springcloud.msvc.venta.infrastructure.adapters.mappers.VentaMapper;
import org.academico.springcloud.msvc.venta.infrastructure.entities.VentaEntity;
import org.academico.springcloud.msvc.venta.infrastructure.repositories.JpaVentaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class JpaVentaRepositoryAdapter implements VentaRepositoryPort {

    private final JpaVentaRepository jpaVentaRepository;

    public JpaVentaRepositoryAdapter(JpaVentaRepository jpaVentaRepository) {
        this.jpaVentaRepository = jpaVentaRepository;
    }

    @Override
    public Venta save(Venta venta) {
        VentaEntity ventaEntity = VentaMapper.toEntity(venta);
        VentaEntity savedEntity = jpaVentaRepository.save(ventaEntity);
        return VentaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return jpaVentaRepository.findById(id)
                .map(VentaMapper::toDomain);
    }

    @Override
    public List<Venta> findAll() {
        return StreamSupport.stream(jpaVentaRepository.findAll().spliterator(), false)
                .map(VentaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> findAllById(List<Long> ids) {
        return StreamSupport.stream(jpaVentaRepository.findAllById(ids).spliterator(), false)
                .map(VentaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaVentaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaVentaRepository.existsById(id);
    }

    @Override
    public long count() {
        return jpaVentaRepository.count();
    }
}