package org.academico.springcloud.msvc.venta.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.venta.domain.models.domainentities.DetalleVenta;
import org.academico.springcloud.msvc.venta.infrastructure.entities.DetalleVentaEntity;
import org.academico.springcloud.msvc.venta.infrastructure.entities.VentaEntity;

public class DetalleVentaMapper {

    private DetalleVentaMapper() {}

    public static DetalleVentaEntity toEntity(DetalleVenta domain) {
        if (domain == null) {
            return null;
        }
        DetalleVentaEntity entity = new DetalleVentaEntity();
        entity.setId(domain.getId());
        entity.setCronogramaPago(CronogramaPagoMapper.toEntity(domain.getCronogramaPago()));
        entity.setMetodoPago(domain.getMetodoPago());
        entity.setEstadoDetalle(domain.getEstadoDetalle());
        return entity;
    }

    public static DetalleVentaEntity toEntity(DetalleVenta domain, VentaEntity parentVentaEntity) {
        DetalleVentaEntity entity = toEntity(domain);
        if (entity != null && parentVentaEntity != null) {
            entity.setVenta(parentVentaEntity);
        }
        return entity;
    }

    public static DetalleVenta toDomain(DetalleVentaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new DetalleVenta(
                entity.getId(),
                CronogramaPagoMapper.toDomain(entity.getCronogramaPago()),
                entity.getMetodoPago(),
                entity.getEstadoDetalle()
        );
    }
}