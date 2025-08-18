package org.academico.springcloud.msvc.preventa.domain.ports.out;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.AdicionalPreventaInfo;

// Este es un puerto de salida para interactuar con un servicio externo
public interface ExternalServicePort {
    AdicionalPreventaInfo getAdicionalPreventaInfo(Long id);
}