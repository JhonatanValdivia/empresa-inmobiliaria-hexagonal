package org.academico.springcloud.msvc.preventa.domain.ports.out;

import org.academico.springcloud.msvc.preventa.domain.models.Usuario;
import java.util.Optional;

public interface UsuarioPort {
    Optional<Usuario> obtenerUsuario(Long idUsuario); // <-- Nombre del método corregido
}