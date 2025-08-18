package org.academico.springcloud.msvc.preventa.application.usescases.contrato;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.ContratoVenta;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.contrato.ObtenerContratoPorPreventaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class ObtenerContratoPorPreventaCasoUsoImpl implements ObtenerContratoPorPreventaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public ObtenerContratoPorPreventaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContratoVenta> obtenerContratoPorPreventa(Long preventaId) {
        return preventaRepositorioPort.porId(preventaId)
                .map(Preventa::getContratoVenta); // Obtiene el contrato del agregado
    }
}