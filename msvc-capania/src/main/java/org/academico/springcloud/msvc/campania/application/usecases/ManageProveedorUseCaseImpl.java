package org.academico.springcloud.msvc.campania.application.usecases;

import org.academico.springcloud.msvc.campania.domain.model.Campania;
import org.academico.springcloud.msvc.campania.domain.model.ProveedorPublicidad;
import org.academico.springcloud.msvc.campania.domain.ports.in.ManageProveedorUseCase;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManageProveedorUseCaseImpl implements ManageProveedorUseCase {
    private final CampaniaRepositoryPort repositoryPort;

    public ManageProveedorUseCaseImpl(CampaniaRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Campania editProveedor(Long idCampania, Long idProveedor, String nombre, String cuentaPublicitaria) {
        Optional<Campania> campaniaOpt = repositoryPort.findById(idCampania);
        if (campaniaOpt.isEmpty()) {
            throw new IllegalArgumentException("Campaña no encontrada");
        }
        Campania campania = campaniaOpt.get();
        ProveedorPublicidad proveedor = campania.getProveedores().stream()
                .filter(p -> p.getIdProveedor().equals(idProveedor))
                .findFirst()
                .orElse(null);
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado en la campaña");
        }
        proveedor.editarProveedor(nombre != null ? nombre : proveedor.getNombre(),
                cuentaPublicitaria != null ? cuentaPublicitaria : proveedor.getCuentaPublicitaria());
        return repositoryPort.save(campania);
    }

    @Override
    public void deleteProveedor(Long idCampania, Long idProveedor) {
        Optional<Campania> campaniaOpt = repositoryPort.findById(idCampania);
        if (campaniaOpt.isEmpty()) {
            throw new IllegalArgumentException("Campaña no encontrada");
        }
        Campania campania = campaniaOpt.get();
        if (campania.getProveedores().size() <= 1) {
            throw new IllegalStateException("No se puede eliminar el último proveedor de la campaña");
        }
        ProveedorPublicidad proveedor = campania.getProveedores().stream()
                .filter(p -> p.getIdProveedor().equals(idProveedor))
                .findFirst()
                .orElse(null);
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado en la campaña");
        }
        proveedor.eliminarProveedor();
        campania.getProveedores().remove(proveedor);
        repositoryPort.save(campania);
    }
}