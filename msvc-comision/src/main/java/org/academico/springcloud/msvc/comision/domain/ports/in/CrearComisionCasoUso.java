package org.academico.springcloud.msvc.comision.domain.ports.in;

import org.academico.springcloud.msvc.comision.domain.models.entities.Comision;

public interface CrearComisionCasoUso {
   Comision crearComision(Comision comision);
}
