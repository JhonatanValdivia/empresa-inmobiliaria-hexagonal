package org.academico.springcloud.msvc.campania.infrastructure.mappers;

import org.academico.springcloud.msvc.campania.domain.model.Campania;
import org.academico.springcloud.msvc.campania.domain.model.ProveedorPublicidad;
import org.academico.springcloud.msvc.campania.infrastructure.entities.CampaniaEntity;
import org.academico.springcloud.msvc.campania.infrastructure.entities.ProveedorPublicidadEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampaniaMapper {

    public Campania toDomain(CampaniaEntity entity) {
        if (entity == null) return null;

        Campania campania = new Campania();
        campania.setIdCampania(entity.getIdCampania());
        campania.setIdPropiedad(entity.getIdPropiedad());
        campania.setProveedores(
                entity.getProveedores().stream()
                        .map(this::toDomainProveedor)
                        .collect(Collectors.toList())
        );
        campania.setNombre(entity.getNombre());
        campania.setEstado(entity.getEstado());
        campania.setFechaInicio(entity.getFechaInicio());
        campania.setMonto(entity.getMonto());
        campania.setMedioPago(entity.getMedioPago());
        // campania.setPropiedad(null); // No se mapea, es @Transient
        return campania;
    }

    public CampaniaEntity toEntity(Campania domain) {
        if (domain == null) return null;

        CampaniaEntity entity = new CampaniaEntity();
        entity.setIdCampania(domain.getIdCampania());
        entity.setIdPropiedad(domain.getIdPropiedad());
        entity.setProveedores(
                domain.getProveedores().stream()
                        .map(this::toEntityProveedor)
                        .collect(Collectors.toList())
        );
        entity.setNombre(domain.getNombre());
        entity.setEstado(domain.getEstado());
        entity.setFechaInicio(domain.getFechaInicio());
        entity.setMonto(domain.getMonto());
        entity.setMedioPago(domain.getMedioPago());
        return entity;
    }

    // ---- Conversión Proveedores ----
    private ProveedorPublicidad toDomainProveedor(ProveedorPublicidadEntity entity) {
        if (entity == null) return null;
        ProveedorPublicidad proveedor = new ProveedorPublicidad();
        proveedor.setIdProveedor(entity.getIdProveedor());
        proveedor.setNombre(entity.getNombre());
        proveedor.setCuentaPublicitaria(entity.getCuentaPublicitaria());
        return proveedor;
    }

    private ProveedorPublicidadEntity toEntityProveedor(ProveedorPublicidad proveedor) {
        if (proveedor == null) return null;
        ProveedorPublicidadEntity entity = new ProveedorPublicidadEntity();
        entity.setIdProveedor(proveedor.getIdProveedor());
        entity.setNombre(proveedor.getNombre());
        entity.setCuentaPublicitaria(proveedor.getCuentaPublicitaria());
        return entity;
    }
}
