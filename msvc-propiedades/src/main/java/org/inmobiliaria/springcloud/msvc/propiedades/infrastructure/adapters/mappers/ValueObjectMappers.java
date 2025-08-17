package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.mappers;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.valueobjects.Fecha;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.valueobjects.Precio;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.valueobjects.Ubicacion;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.valueobjects.Zonificacion;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.infravalueobjects.FechaInfr;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.infravalueobjects.PrecioInfr;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.infravalueobjects.UbicacionInfr;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.infravalueobjects.ZonificacionInfr;

public class ValueObjectMappers {
    private ValueObjectMappers() {}

    // Precio
    public static PrecioInfr toEntity(Precio d) {
        if (d == null) return null;
        PrecioInfr e = new PrecioInfr();
        e.setMonto(d.getMonto());
        e.setMoneda(d.getMoneda());
        return e;
    }
    public static Precio toDomain(PrecioInfr e) {
        if (e == null) return null;
        return new Precio(e.getMonto(), e.getMoneda());
    }
    // Ubicacion
    public static UbicacionInfr toEntity(Ubicacion d) {
        if (d == null) return null;
        UbicacionInfr e = new UbicacionInfr();
        e.setDireccion(d.getDireccion());
        e.setCiudad(d.getCiudad());
        e.setUbigeo(d.getUbigeo());
        e.setDireccion(d.getDireccion());
        return e;
    }
    public static Ubicacion toDomain(UbicacionInfr e) {
        if (e == null) return null;
        return new Ubicacion(e.getUbigeo(), e.getCiudad(), e.getDireccion());
    }

    // Zonificacion
    public static ZonificacionInfr toEntity(Zonificacion d) {
        if (d == null) return null;
        ZonificacionInfr e = new ZonificacionInfr();
        e.setTipoZona(d.getTipoZona());
        e.setUsoPermitido(d.getUsoPermitido());
        e.setDescripcionNormativa(d.getDescripcionNormativa());

        return e;

    }
    public static Zonificacion toDomain(ZonificacionInfr e) {
        if (e == null) return null;
        return new Zonificacion(e.getTipoZona(),e.getDescripcionNormativa(),e.getUsoPermitido());
    }

    // Fecha si es VO
    public static FechaInfr toEntity(Fecha d) {
        if(d == null) return null;
        FechaInfr e = new FechaInfr();
        e.setAnio(d.getAnio());
        e.setDia(d.getDia());
        e.setMes(d.getMes());
        return e;
    }
    public static Fecha toDomain(FechaInfr e) {
        if (e == null) return null;
        return new Fecha(e.getDia(), e.getMes(), e.getAnio());
    }
}
