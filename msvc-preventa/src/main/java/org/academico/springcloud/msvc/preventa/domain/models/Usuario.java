package org.academico.springcloud.msvc.preventa.domain.models;

//pojo
public class Usuario {
    private Long id;

    // Nombre completo
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;

    // Teléfono
    private String numeroTelefono;
    private String codigoPaisTelefono;

    // Correo
    private String correoElectronico;
    private String dominioCorreo;

    // Dirección
    private String ubigeo;
    private String ciudad;
    private String direccion;

    // Tipo de usuario
    private TipoUsuario tipoUsuario;

    public Usuario(Long id,
                   String primerNombre,
                   String segundoNombre,
                   String primerApellido,
                   String segundoApellido,
                   String numeroTelefono,
                   String codigoPaisTelefono,
                   String correoElectronico,
                   String dominioCorreo,
                   String ubigeo,
                   String ciudad,
                   String direccion,
                   TipoUsuario tipoUsuario) {
        this.id = id;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.numeroTelefono = numeroTelefono;
        this.codigoPaisTelefono = codigoPaisTelefono;
        this.correoElectronico = correoElectronico;
        this.dominioCorreo = dominioCorreo;
        this.ubigeo = ubigeo;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters (puedes agregar setters si tu dominio lo permite)
    public Long getId() {
        return id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getCodigoPaisTelefono() {
        return codigoPaisTelefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getDominioCorreo() {
        return dominioCorreo;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public enum TipoUsuario {
        CLIENTE,
        AGENTE,
        PROPIETARIO
    }
}
