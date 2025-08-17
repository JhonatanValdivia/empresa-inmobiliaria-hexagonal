package org.academico.springcloud.msvc.campania.domain.ports.in;

import org.academico.springcloud.msvc.campania.domain.models.entities.Campania;

public interface GestionarProveedorUseCase {
    Campania editProveedor(Long idCampania, Long idProveedor, String nombre, String cuentaPublicitaria);
    void deleteProveedor(Long idCampania, Long idProveedor);
}