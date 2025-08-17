package org.academico.springcloud.msvc.campania.application.usecases;

import org.academico.springcloud.msvc.campania.domain.models.entities.Campania;
import org.academico.springcloud.msvc.campania.domain.models.Propiedad;
import org.academico.springcloud.msvc.campania.domain.models.entities.ProveedorPublicidad;
import org.academico.springcloud.msvc.campania.domain.ports.in.CrearCampaniaUseCase;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.academico.springcloud.msvc.campania.domain.ports.out.PropiedadExternalServicePort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Qualifier("crearCampaniaUseCaselmpl")
public class CrearCampaniaUseCaselmpl implements CrearCampaniaUseCase {
    private final CampaniaRepositoryPort repositoryPort;
    private final PropiedadExternalServicePort externalServicePort;

    public CrearCampaniaUseCaselmpl(CampaniaRepositoryPort repositoryPort, PropiedadExternalServicePort externalServicePort) {
        this.repositoryPort = repositoryPort;
        this.externalServicePort = externalServicePort;
    }

    @Override
    public Campania create(Campania campania) {
        // Validar proveedores
        if (campania.getProveedores() == null || campania.getProveedores().isEmpty()) {
            throw new IllegalArgumentException("Debe proporcionar al menos un proveedor.");
        }
        for (ProveedorPublicidad proveedor : campania.getProveedores()) {
            if (proveedor.getIdProveedor() == null) {
                throw new IllegalArgumentException("El proveedor debe tener un ID válido.");
            }
            // Validar existencia de proveedor en otras campañas
            List<Campania> allCampanias = repositoryPort.findAll();
            boolean proveedorExiste = allCampanias.stream()
                    .flatMap(c -> c.getProveedores().stream())
                    .anyMatch(p -> p.getIdProveedor().equals(proveedor.getIdProveedor()));
            if (!proveedorExiste) {
                throw new IllegalArgumentException("El proveedor con ID " + proveedor.getIdProveedor() + " no existe.");
            }
        }

        // Validar monto
        if (campania.getMonto() == null || campania.getMonto().compareTo(BigDecimal.valueOf(5)) < 0) {
            throw new IllegalArgumentException("El monto debe ser al menos 5.");
        }

        // Validar propiedad
        if (campania.getIdPropiedad() != null) {
            Propiedad propiedad = externalServicePort.findById(campania.getIdPropiedad());
            if (propiedad == null) {
                throw new IllegalArgumentException("La propiedad con ID " + campania.getIdPropiedad() + " no existe.");
            }
            campania.setPropiedad(propiedad);
        }

        campania.crearCampania();
        return repositoryPort.save(campania);
    }

}