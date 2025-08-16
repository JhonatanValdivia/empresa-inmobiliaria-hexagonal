package org.academico.springcloud.msvc.campania.application.services;

import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.academico.springcloud.msvc.campania.infrastructure.clients.PropiedadClientRest;
import org.academico.springcloud.msvc.campania.domain.model.Propiedad;
import org.academico.springcloud.msvc.campania.domain.model.Campania;
import org.academico.springcloud.msvc.campania.domain.model.ProveedorPublicidad;
import org.academico.springcloud.msvc.campania.infrastructure.repositories.CampaniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
    public class CampaniaServiceImpl implements CampaniaService {
    @Autowired
    private CampaniaRepositoryPort repository;

    @Autowired
    private PropiedadClientRest propiedadClient; // Inyectar el cliente

    @Override
    @Transactional(readOnly = true)
    public List<Campania> listar() {
        List<Campania> campanias = (List<Campania>) repository.findAll();
        campanias.forEach(this::cargarPropiedad); // Cargar propiedades para todas las campañas
        return campanias;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Campania> porId(Long id) {
        Optional<Campania> campaniaOpt = repository.findById(id);
        campaniaOpt.ifPresent(this::cargarPropiedad); // Cargar propiedad para la campaña específica
        return campaniaOpt;
    }

    @Override
    @Transactional
    public Campania guardar(Campania campania) {
        campania = cargarPropiedad(campania); // Cargar propiedad antes de guardar
        return repository.save(campania);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id); // Elimina físicamente el registro
    }

    @Transactional
    public void eliminarProveedor(Long idCampania, Long idProveedor) {
        Optional<Campania> campaniaOpt = porId(idCampania);
        if (campaniaOpt.isPresent()) {
            Campania campania = campaniaOpt.get();
            ProveedorPublicidad proveedorAEliminar = campania.getProveedores().stream()
                    .filter(p -> p.getIdProveedor().equals(idProveedor))
                    .findFirst()
                    .orElse(null);
            if (proveedorAEliminar != null) {
                proveedorAEliminar.eliminarProveedor();
                campania.getProveedores().remove(proveedorAEliminar);
                repository.save(campania);
            } else {
                throw new IllegalArgumentException("Proveedor no encontrado en la campaña");
            }
        } else {
            throw new IllegalArgumentException("Campaña no encontrada");
        }
    }

    @Override
    public PropiedadClientRest getPropiedadClient() {
        return propiedadClient;
    }

    private Campania cargarPropiedad(Campania campania) {
        if (campania != null && campania.getIdPropiedad() != null) {
            try {
                Propiedad propiedad = propiedadClient.detalle(campania.getIdPropiedad());
                campania.setPropiedad(propiedad);
            } catch (Exception e) {
                campania.setPropiedad(null);
                System.err.println("Error al cargar propiedad: " + e.getMessage());
            }
        }
        return campania;
    }
}