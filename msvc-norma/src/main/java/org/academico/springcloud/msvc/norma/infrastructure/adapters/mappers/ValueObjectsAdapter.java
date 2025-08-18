package org.academico.springcloud.msvc.norma.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.norma.domain.models.valueobjects.Fecha;
import org.academico.springcloud.msvc.norma.infrastructure.entities.valueobjects.FechaInfr;

public class ValueObjectsAdapter {
    private ValueObjectsAdapter(){}
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
