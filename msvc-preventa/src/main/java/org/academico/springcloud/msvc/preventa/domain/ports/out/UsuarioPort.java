package org.academico.springcloud.msvc.preventa.domain.ports.out;

import org.academico.springcloud.msvc.preventa.domain.models.Usuario;

public interface UsuarioPort {
    Usuario obtenerUsuario(Long idUsuario);
}
