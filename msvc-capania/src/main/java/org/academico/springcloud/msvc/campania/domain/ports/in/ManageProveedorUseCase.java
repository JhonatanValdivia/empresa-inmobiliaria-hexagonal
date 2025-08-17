package org.academico.springcloud.msvc.campania.domain.ports.in;

import org.academico.springcloud.msvc.campania.domain.model.Campania;

import java.util.Map;

public interface ManageProveedorUseCase {
    Campania editProveedor(Long idCampania, Long idProveedor, String nombre, String cuentaPublicitaria);
    void deleteProveedor(Long idCampania, Long idProveedor);
}