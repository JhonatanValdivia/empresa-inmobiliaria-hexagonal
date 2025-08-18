package org.academico.springcloud.msvc.preventa.application.usescases.preventa;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.preventa.RecuperarPreventaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public class RecuperarPreventaCasoUsoImpl implements RecuperarPreventaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public RecuperarPreventaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Preventa> preventaPorId(Long id) {
        return preventaRepositorioPort.porId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Preventa> obtenerTodasPreventas() {
        return preventaRepositorioPort.listar();
    }
}