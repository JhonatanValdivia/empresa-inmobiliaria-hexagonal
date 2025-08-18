package org.academico.springcloud.msvc.comision.application.usescases;

import org.academico.springcloud.msvc.comision.domain.models.entities.Comision;
import org.academico.springcloud.msvc.comision.domain.ports.in.CrearComisionCasoUso;
import org.academico.springcloud.msvc.comision.domain.ports.out.ComisionRepositorioPort;

public class CrearComisionCasoUsoImpl implements CrearComisionCasoUso {
    private final ComisionRepositorioPort comisionRepositorioPort;

    public CrearComisionCasoUsoImpl(ComisionRepositorioPort comisionRepositorioPort) {
        this.comisionRepositorioPort = comisionRepositorioPort;
    }

    @Override
    public Comision crearComision(Comision comision) {
        return comisionRepositorioPort.guardar(comision);
    }
}
