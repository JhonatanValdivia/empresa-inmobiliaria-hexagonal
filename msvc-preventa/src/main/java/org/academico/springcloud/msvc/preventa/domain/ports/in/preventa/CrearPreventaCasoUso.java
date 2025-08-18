package org.academico.springcloud.msvc.preventa.domain.ports.in.preventa;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;

public interface CrearPreventaCasoUso {
    Preventa crearPreventa(Preventa preventa);
}