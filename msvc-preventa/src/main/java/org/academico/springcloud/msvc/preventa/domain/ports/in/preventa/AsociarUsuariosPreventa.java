package org.academico.springcloud.msvc.preventa.domain.ports.in.preventa;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;

public interface AsociarUsuariosPreventa {
    Preventa asociarUsuariosPreventa(Long idPreventa, Long idAgente, Long idCliente);
}
