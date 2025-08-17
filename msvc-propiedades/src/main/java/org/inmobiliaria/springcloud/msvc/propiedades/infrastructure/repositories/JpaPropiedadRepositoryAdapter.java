package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.repositories;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.PropiedadRepositoryPort;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.mappers.*;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.DocumentoLegalEntity;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.ExpedienteEntity;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.PropiedadInmobiliariaEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Component
public class JpaPropiedadRepositoryAdapter implements PropiedadRepositoryPort {

    private final JpaPropiedadRepository jpaPropiedadRepository;

    public JpaPropiedadRepositoryAdapter(JpaPropiedadRepository jpaPropiedadRepository) {
        this.jpaPropiedadRepository = jpaPropiedadRepository;
    }
    @Transactional
    @Override
    public PropiedadInmobiliaria save(PropiedadInmobiliaria propiedadInmobiliaria) {
        PropiedadInmobiliariaEntity propiedadEntity = PropiedadInmobiliariaMapper.toEntity(propiedadInmobiliaria);
        PropiedadInmobiliariaEntity propiedadSaved = jpaPropiedadRepository.save(propiedadEntity);
        return PropiedadInmobiliariaMapper.toDomain(propiedadSaved);
    }

    // READ by id
    @Override
    public Optional<PropiedadInmobiliaria> findById(Long id) {
        return jpaPropiedadRepository.findById(id)
                .map(PropiedadInmobiliariaMapper::toDomain);
    }

    // READ all
    @Override
    public List<PropiedadInmobiliaria> findAll() {
        return StreamSupport.stream(jpaPropiedadRepository.findAll().spliterator(), false)
                .map(PropiedadInmobiliariaMapper::toDomain)
                .collect(Collectors.toList());
    }



    // UPDATE
    @Override
    @Transactional
    public Optional<PropiedadInmobiliaria> update(PropiedadInmobiliaria source) {
        if (!jpaPropiedadRepository.existsById(source.getIdPropiedad())) {
            return Optional.empty();
        }
        PropiedadInmobiliariaEntity entityToSave = PropiedadInmobiliariaMapper.toEntity(source);
        PropiedadInmobiliariaEntity saved = jpaPropiedadRepository.save(entityToSave);
        return Optional.of(PropiedadInmobiliariaMapper.toDomain(saved));
    }

    // DELETE
    @Override
    @Transactional
    public boolean deleteById(Long id) {
        if (!jpaPropiedadRepository.existsById(id)) return false;
        jpaPropiedadRepository.deleteById(id);
        return true;
    }
}
