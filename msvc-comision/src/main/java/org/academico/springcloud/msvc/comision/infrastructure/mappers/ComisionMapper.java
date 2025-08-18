package org.academico.springcloud.msvc.comision.infrastructure.mappers;

import org.academico.springcloud.msvc.comision.domain.models.entities.Comision;
import org.academico.springcloud.msvc.comision.domain.models.valueObjects.FechaPagoComision;
import org.academico.springcloud.msvc.comision.domain.models.valueObjects.MontoComision;
import org.academico.springcloud.msvc.comision.infrastructure.models.entities.ComisionEntidad;
import org.academico.springcloud.msvc.comision.infrastructure.models.valueObjects.FechaPagoComisionVO;
import org.academico.springcloud.msvc.comision.infrastructure.models.valueObjects.MontoComisionVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComisionMapper {

    Comision toDomain(ComisionEntidad entity);
    ComisionEntidad toEntity(Comision domain);
}