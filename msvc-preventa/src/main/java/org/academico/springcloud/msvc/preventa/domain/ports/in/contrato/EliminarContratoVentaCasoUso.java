package org.academico.springcloud.msvc.preventa.domain.ports.in.contrato;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa; // En tu código original eliminaba Preventa
import java.util.Optional;

public interface EliminarContratoVentaCasoUso {
    // Devuelve Optional<Preventa> para indicar si la preventa fue actualizada o no encontrada
    Optional<Preventa> eliminarContratoVenta(Long preventaId);
}