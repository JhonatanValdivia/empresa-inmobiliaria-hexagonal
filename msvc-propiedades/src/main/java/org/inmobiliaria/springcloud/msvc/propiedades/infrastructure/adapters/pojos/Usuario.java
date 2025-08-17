package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.pojos;

import jakarta.persistence.*;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.TipoUsuario;


public class Usuario {
    private Long id;
    private NombreCompleto nombreCompleto;
    private TipoUsuario tipoUsuario;
    private Telefono telefono;
    private CorreoElectronico correoElectronico;
    private Direccion direccion;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NombreCompleto getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(NombreCompleto nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public CorreoElectronico getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(CorreoElectronico correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void asignarRol(TipoUsuario rol) {
        this.tipoUsuario = rol;
    }

    public void actualizarDatos(NombreCompleto nombre, Telefono telefono, CorreoElectronico correo, Direccion direccion) {
        this.nombreCompleto = nombre;
        this.telefono = telefono;
        this.correoElectronico = correo;
        this.direccion = direccion;
    }
}