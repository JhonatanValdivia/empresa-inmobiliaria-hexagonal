package org.academico.springcloud.msvc.comision.application.usescases;

import org.academico.springcloud.msvc.comision.domain.models.entities.Comision;
import org.academico.springcloud.msvc.comision.domain.ports.in.RecuperarComisionCasoUso;
import org.academico.springcloud.msvc.comision.domain.ports.out.ComisionRepositorioPort;

import java.util.List;
import java.util.Optional;

public class RecuperarComisionCasoUsoImpl implements RecuperarComisionCasoUso {
    private final ComisionRepositorioPort comisionRepositorioPort;

    public RecuperarComisionCasoUsoImpl(ComisionRepositorioPort comisionRepositorioPort) {
        this.comisionRepositorioPort = comisionRepositorioPort;
    }

    @Override
    public Optional<Comision> comisionPorId(Long id) {
        return comisionRepositorioPort.porId(id);
    }

    @Override
    public List<Comision> obtenerTodasComisiones() {
        return comisionRepositorioPort.listar();
    }
}
