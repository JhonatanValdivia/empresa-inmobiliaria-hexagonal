package org.academico.springcloud.msvc.campania.domain.ports.in;

import org.academico.springcloud.msvc.campania.domain.models.entities.Campania;

import java.util.List;
import java.util.Optional;

public interface ConsultarCampaniaUseCase {
    List<Campania> listar();
    Optional<Campania> obtenerPorId(Long idCampania);
}
