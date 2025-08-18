package org.academico.springcloud.msvc.preventa.domain.ports.in.contrato;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.ContratoVenta;
import java.util.Optional;

public interface AgregarContratoCasoUso {
    Optional<ContratoVenta> agregarContratoVenta(Long preventaId, ContratoVenta contrato);
}