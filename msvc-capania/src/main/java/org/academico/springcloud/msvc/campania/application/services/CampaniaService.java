package org.academico.springcloud.msvc.campania.application.services;

import org.academico.springcloud.msvc.campania.infrastructure.clients.PropiedadClientRest;
import org.academico.springcloud.msvc.campania.domain.model.Campania;

import java.util.List;
import java.util.Optional;

public interface CampaniaService {
    List<Campania> listar();
    Optional<Campania> porId(Long id);
    Campania guardar(Campania campania);
    void eliminar(Long id);
    void eliminarProveedor(Long idCampania, Long idProveedor);
    PropiedadClientRest getPropiedadClient();
}