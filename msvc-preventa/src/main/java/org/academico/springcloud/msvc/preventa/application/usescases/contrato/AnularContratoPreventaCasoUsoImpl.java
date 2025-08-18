package org.academico.springcloud.msvc.preventa.application.usescases.contrato;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.ContratoVenta;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.contrato.AnularContratoPreventaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class AnularContratoPreventaCasoUsoImpl implements AnularContratoPreventaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public AnularContratoPreventaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<ContratoVenta> anularContratoPreventa(Long preventaId) {
        return preventaRepositorioPort.porId(preventaId).map(preventa -> {
            ContratoVenta contrato = preventa.getContratoVenta();
            if (contrato == null) {
                throw new IllegalStateException("No hay contrato para anular en la Preventa " + preventaId);
            }
            contrato.anularContrato(); // Lógica de dominio para anular
            preventaRepositorioPort.guardar(preventa); // Guarda el agregado
            return contrato;
        });
    }
}