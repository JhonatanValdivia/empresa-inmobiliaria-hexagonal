package org.academico.springcloud.msvc.preventa.application.usescases.contrato;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.ContratoVenta;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.contrato.AgregarContratoCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class AgregarContratoCasoUsoImpl implements AgregarContratoCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public AgregarContratoCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<ContratoVenta> agregarContratoVenta(Long preventaId, ContratoVenta contrato) {
        return preventaRepositorioPort.porId(preventaId).map(preventa -> {
            if (preventa.getContratoVenta() != null) {
                throw new IllegalStateException("La Preventa " + preventaId + " ya tiene un contrato asociado.");
            }
            contrato.setId(null); // Asegura que sea una inserción
            preventa.setContratoVenta(contrato); // Asocia el contrato a la preventa
            preventaRepositorioPort.guardar(preventa); // Guarda el agregado
            return contrato;
        });
    }
}