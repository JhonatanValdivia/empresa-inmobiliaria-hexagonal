package org.academico.springcloud.msvc.comision.application.usescases;

import org.academico.springcloud.msvc.comision.domain.ports.in.EliminarComisionCasoUso;
import org.academico.springcloud.msvc.comision.domain.ports.out.ComisionRepositorioPort;

public class EliminarComisionCasoUsoImpl  implements EliminarComisionCasoUso {

    private final ComisionRepositorioPort comisionRepositorioPort;

    public EliminarComisionCasoUsoImpl(ComisionRepositorioPort comisionRepositorioPort) {
        this.comisionRepositorioPort = comisionRepositorioPort;
    }

    @Override
    public boolean eliminarComision(Long id) {
        return comisionRepositorioPort.eliminarPorId(id);
    }
}
