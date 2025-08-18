package org.academico.springcloud.msvc.comision.application.usescases;

import org.academico.springcloud.msvc.comision.domain.models.entities.Comision;
import org.academico.springcloud.msvc.comision.domain.ports.in.ActualizarComisionCasoUso;
import org.academico.springcloud.msvc.comision.domain.ports.out.ComisionRepositorioPort;

import java.util.Optional;

public class ActualizarComisionCasoUsoImpl  implements ActualizarComisionCasoUso {

    private final ComisionRepositorioPort comisionRepositorioPort;

    public ActualizarComisionCasoUsoImpl(ComisionRepositorioPort comisionRepositorioPort) {
        this.comisionRepositorioPort = comisionRepositorioPort;
    }

    @Override
    public Optional<Comision> actualizarComision(Long id, Comision comisionActualizada) {
        return comisionRepositorioPort.actualizar(comisionActualizada);
    }
}
