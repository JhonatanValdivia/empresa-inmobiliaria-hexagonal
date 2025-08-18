package org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in;

import java.util.Optional;

public interface AsignarUsuarioUseCase {
    void asignarUsuario(Long propiedadId, Long usuarioId);
}
