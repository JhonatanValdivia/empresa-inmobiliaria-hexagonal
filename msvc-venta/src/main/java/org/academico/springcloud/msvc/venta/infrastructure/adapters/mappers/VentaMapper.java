package org.academico.springcloud.msvc.venta.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.venta.domain.models.domainentities.DetalleVenta;
import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;
import org.academico.springcloud.msvc.venta.infrastructure.entities.DetalleVentaEntity;
import org.academico.springcloud.msvc.venta.infrastructure.entities.VentaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VentaMapper {

    private VentaMapper() {}

    public static VentaEntity toEntity(Venta domain) {
        if (domain == null) {
            return null;
        }
        VentaEntity entity = new VentaEntity();
        entity.setId(domain.getId());
        entity.setTipoVenta(domain.getTipoVenta());
        entity.setEstado(domain.getEstado());
        entity.setFecha(FechaVentaMapper.toEntity(domain.getFecha()));
        entity.setPrecioVenta(PrecioVentaMapper.toEntity(domain.getPrecioVenta()));
        entity.setIdPreventa(domain.getIdPreventa()); // NUEVO: Mapear el nuevo campo

        if (domain.getDetalleVentaLista() != null && !domain.getDetalleVentaLista().isEmpty()) {
            List<DetalleVentaEntity> detalleEntities = new ArrayList<>();
            for (DetalleVenta dv : domain.getDetalleVentaLista()) {
                DetalleVentaEntity dvEntity = DetalleVentaMapper.toEntity(dv);
                if (dvEntity != null) {
                    dvEntity.setVenta(entity); // Establece la referencia bidireccional
                    detalleEntities.add(dvEntity);
                }
            }
            entity.setDetalleVentaLista(detalleEntities);
        } else {
            entity.setDetalleVentaLista(new ArrayList<>());
        }
        return entity;
    }

    public static Venta toDomain(VentaEntity entity) {
        if (entity == null) {
            return null;
        }

        List<DetalleVenta> detalleDomainList = entity.getDetalleVentaLista() != null
                ? entity.getDetalleVentaLista().stream()
                .map(DetalleVentaMapper::toDomain)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new Venta(
                entity.getId(),
                entity.getTipoVenta(),
                entity.getEstado(),
                FechaVentaMapper.toDomain(entity.getFecha()),
                PrecioVentaMapper.toDomain(entity.getPrecioVenta()),
                detalleDomainList,
                entity.getIdPreventa() // NUEVO: Mapear el nuevo campo
        );
    }
}