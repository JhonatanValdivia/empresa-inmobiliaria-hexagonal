package org.academico.springcloud.msvc.comision.application.services;

import org.academico.springcloud.msvc.comision.domain.models.entities.Comision;
import org.academico.springcloud.msvc.comision.domain.ports.in.ActualizarComisionCasoUso;
import org.academico.springcloud.msvc.comision.domain.ports.in.CrearComisionCasoUso;
import org.academico.springcloud.msvc.comision.domain.ports.in.EliminarComisionCasoUso;
import org.academico.springcloud.msvc.comision.domain.ports.in.RecuperarComisionCasoUso;

import java.util.List;
import java.util.Optional;

public class ComisionServicio implements CrearComisionCasoUso, RecuperarComisionCasoUso, ActualizarComisionCasoUso, EliminarComisionCasoUso {

    private final CrearComisionCasoUso crearComisionCasoUso;
    private final RecuperarComisionCasoUso recuperarComisionCasoUso;
    private final ActualizarComisionCasoUso actualizarComisionCasoUso;
    private final EliminarComisionCasoUso eliminarComisionCasoUso;

    public ComisionServicio(CrearComisionCasoUso crearComisionCasoUso, RecuperarComisionCasoUso recuperarComisionCasoUso, ActualizarComisionCasoUso actualizarComisionCasoUso, EliminarComisionCasoUso eliminarComisionCasoUso) {
        this.crearComisionCasoUso = crearComisionCasoUso;
        this.recuperarComisionCasoUso = recuperarComisionCasoUso;
        this.actualizarComisionCasoUso = actualizarComisionCasoUso;
        this.eliminarComisionCasoUso = eliminarComisionCasoUso;
    }


    @Override
    public Optional<Comision> actualizarComision(Long id, Comision comisionActualizada) {
        return actualizarComisionCasoUso.actualizarComision(id, comisionActualizada);
    }

    @Override
    public Comision crearComision(Comision comision) {
        return crearComisionCasoUso.crearComision(comision);
    }

    @Override
    public boolean eliminarComision(Long id) {
        return eliminarComisionCasoUso.eliminarComision(id);
    }

    @Override
    public Optional<Comision> comisionPorId(Long id) {
        return recuperarComisionCasoUso.comisionPorId(id);
    }

    @Override
    public List<Comision> obtenerTodasComisiones() {
        return recuperarComisionCasoUso.obtenerTodasComisiones();
    }
}
