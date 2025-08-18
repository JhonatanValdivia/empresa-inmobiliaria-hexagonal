package org.academico.springcloud.msvc.preventa.application.usescases.contrato;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.ContratoVenta;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.contrato.ActualizarContratoVentaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class ActualizarContratoVentaCasoUsoImpl implements ActualizarContratoVentaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public ActualizarContratoVentaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<ContratoVenta> actualizarContratoVenta(Long preventaId, ContratoVenta contratoActualizado) {
        return preventaRepositorioPort.porId(preventaId).map(preventa -> {
            ContratoVenta contratoDB = preventa.getContratoVenta();
            if (contratoDB == null) {
                throw new IllegalStateException("La Preventa " + preventaId + " no tiene un contrato para actualizar.");
            }
            // Actualiza los campos del contrato existente
            contratoDB.setTipoContrato(contratoActualizado.getTipoContrato());
            contratoDB.setFechaFirma(contratoActualizado.getFechaFirma());
            contratoDB.setEstado(contratoActualizado.getEstado());

            preventaRepositorioPort.guardar(preventa); // Guarda el agregado
            return contratoDB;
        });
    }
}