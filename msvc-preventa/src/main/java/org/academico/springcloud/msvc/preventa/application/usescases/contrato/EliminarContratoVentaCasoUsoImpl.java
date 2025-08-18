package org.academico.springcloud.msvc.preventa.application.usescases.contrato;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.contrato.EliminarContratoVentaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class EliminarContratoVentaCasoUsoImpl implements EliminarContratoVentaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public EliminarContratoVentaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<Preventa> eliminarContratoVenta(Long preventaId) {
        return preventaRepositorioPort.porId(preventaId).map(preventa -> {
            if (preventa.getContratoVenta() == null) {
                throw new IllegalStateException("La Preventa " + preventaId + " no tiene un contrato para eliminar.");
            }
            preventa.setContratoVenta(null); // Desasocia el contrato (JPA con orphanRemoval=true lo borrará)
            preventaRepositorioPort.guardar(preventa); // Guarda el agregado
            return preventa;
        });
    }
}
