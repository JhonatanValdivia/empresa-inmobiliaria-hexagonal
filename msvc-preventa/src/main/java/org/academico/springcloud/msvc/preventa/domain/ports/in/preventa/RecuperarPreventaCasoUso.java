package org.academico.springcloud.msvc.preventa.domain.ports.in.preventa;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import java.util.List;
import java.util.Optional;

public interface RecuperarPreventaCasoUso {
    Optional<Preventa> preventaPorId(Long id);
    List<Preventa> obtenerTodasPreventas();
}