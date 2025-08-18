package org.academico.springcloud.msvc.preventa.application.services;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.AdicionalPreventaInfo;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.preventa.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class PreventaService implements ActualizarPreventaCasoUso, AprobarPreventaCasoUso, CrearPreventaCasoUso, EliminarPreventaCasoUso, RecuperarPreventaCasoUso, AsociarUsuariosPreventa{

    private final ActualizarPreventaCasoUso actualizarPreventaCasoUso;
    private final AprobarPreventaCasoUso aprobarPreventaCasoUso;
    private final CrearPreventaCasoUso crearPreventaCasoUso;
    private final EliminarPreventaCasoUso eliminarPreventaCasoUso;
    private final RecuperarPreventaCasoUso recuperarPreventaCasoUso;
    private final AsociarUsuariosPreventa asociarUsuariosPreventa;

    @Autowired
    public PreventaService(
            ActualizarPreventaCasoUso actualizarPreventaCasoUso,
            AprobarPreventaCasoUso aprobarPreventaCasoUso,
            CrearPreventaCasoUso crearPreventaCasoUso,
            EliminarPreventaCasoUso eliminarPreventaCasoUso,
            RecuperarPreventaCasoUso recuperarPreventaCasoUso,
            AsociarUsuariosPreventa asociarUsuariosPreventa) {
        this.actualizarPreventaCasoUso = actualizarPreventaCasoUso;
        this.aprobarPreventaCasoUso = aprobarPreventaCasoUso;
        this.crearPreventaCasoUso = crearPreventaCasoUso;
        this.eliminarPreventaCasoUso = eliminarPreventaCasoUso;
        this.recuperarPreventaCasoUso = recuperarPreventaCasoUso;
        this.asociarUsuariosPreventa = asociarUsuariosPreventa;
    }

    @Override
    public Optional<Preventa> actualizarPreventa(Long id, Preventa preventaActualizada) {
        return actualizarPreventaCasoUso.actualizarPreventa(id, preventaActualizada);
    }

    @Override
    public Optional<Preventa> aprobarPreventa(Long preventaId) {
        return aprobarPreventaCasoUso.aprobarPreventa(preventaId);
    }

    @Override
    public Preventa crearPreventa(Preventa preventa) {
        return crearPreventaCasoUso.crearPreventa(preventa);
    }

    @Override
    public void eliminarPreventa(Long id) {
        eliminarPreventaCasoUso.eliminarPreventa(id);
    }

    @Override
    public Optional<Preventa> preventaPorId(Long id) {
        return recuperarPreventaCasoUso.preventaPorId(id);
    }

    @Override
    public List<Preventa> obtenerTodasPreventas() {
        return recuperarPreventaCasoUso.obtenerTodasPreventas();
    }

    @Override
    public Preventa asociarUsuariosPreventa(Long idPreventa, Long idAgente, Long idCliente) {
        return asociarUsuariosPreventa.asociarUsuariosPreventa(idPreventa, idAgente, idCliente);
    }
}