package org.academico.springcloud.msvc.preventa.domain.ports.out;

import org.academico.springcloud.msvc.preventa.domain.models.PropiedadInmobiliaria;
import java.util.Optional;

public interface PropiedadPort {
    Optional<PropiedadInmobiliaria> obtenerPorId(Long id);
}