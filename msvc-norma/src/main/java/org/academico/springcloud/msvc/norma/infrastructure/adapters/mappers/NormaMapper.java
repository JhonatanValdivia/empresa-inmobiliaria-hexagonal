package org.academico.springcloud.msvc.norma.infrastructure.adapters.mappers;

import org.academico.springcloud.msvc.norma.domain.models.entities.Norma;
import org.academico.springcloud.msvc.norma.infrastructure.entities.NormaEntity;

public class NormaMapper {
    private NormaMapper(){}

    //dominio a infraesstructura
    public static NormaEntity toEntity(Norma d){
        if(d==null) return null;
        NormaEntity e = new NormaEntity();
        e.setDescripcion(d.getDescripcion());
        e.setFecha(ValueObjectsAdapter.toEntity(d.getFecha()));
        e.setEstadoNorma(d.getEstadoNorma());
        e.setIdNorma(d.getIdNorma());
        e.setTipo(d.getTipo());

        return e;


    }

    //infraestructura a dominio
    public static Norma toDomain(NormaEntity e){
        if(e==null) return null;
        Norma d = new Norma();
        d.setDescripcion(e.getDescripcion());
        d.setIdNorma(e.getIdNorma());
        d.setEstadoNorma(e.getEstadoNorma());
        d.setFecha(ValueObjectsAdapter.toDomain(e.getFecha()));
        d.setTipo(e.getTipo());

        return d;

    }

}
