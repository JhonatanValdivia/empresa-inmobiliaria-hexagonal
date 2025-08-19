package org.academico.springcloud.msvc.venta.domain.ports.out;

import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;
import java.util.List;
import java.util.Optional;

public interface VentaRepositoryPort {
    Venta save(Venta venta);
    Optional<Venta> findById(Long id);
    List<Venta> findAll();
    List<Venta> findAllById(List<Long> ids);
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}