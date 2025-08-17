package org.academico.springcloud.msvc.comision.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {
    private Long id;
    private NombreCompleto nombreCompleto;
    private String tipoUsuario;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
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

    // Clases estáticas para objetos de valor

    public static class NombreCompleto {
        private String primerNombre;
        private String segundoNombre;
        private String primerApellido;
        private String segundoApellido;

        protected NombreCompleto() {}

        @JsonCreator
        public NombreCompleto(@JsonProperty("primerNombre") String primerNombre, @JsonProperty("segundoNombre") String segundoNombre,
                              @JsonProperty("primerApellido") String primerApellido, @JsonProperty("segundoApellido") String segundoApellido) {
            this.primerNombre = primerNombre;
            this.segundoNombre = segundoNombre;
            this.primerApellido = primerApellido;
            this.segundoApellido = segundoApellido;
        }

        public String getPrimerNombre() {
            return primerNombre;
        }

        public void setPrimerNombre(String primerNombre) {
            this.primerNombre = primerNombre;
        }

        public String getSegundoNombre() {
            return segundoNombre;
        }

        public void setSegundoNombre(String segundoNombre) {
            this.segundoNombre = segundoNombre;
        }

        public String getPrimerApellido() {
            return primerApellido;
        }

        public void setPrimerApellido(String primerApellido) {
            this.primerApellido = primerApellido;
        }

        public String getSegundoApellido() {
            return segundoApellido;
        }

        public void setSegundoApellido(String segundoApellido) {
            this.segundoApellido = segundoApellido;
        }
    }

    public static class Telefono {
        private String numero;
        private String codigoPais;

        protected Telefono() {}

        @JsonCreator
        public Telefono(@JsonProperty("numero") String numero, @JsonProperty("codigoPais") String codigoPais) {
            this.numero = numero;
            this.codigoPais = codigoPais;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getCodigoPais() {
            return codigoPais;
        }

        public void setCodigoPais(String codigoPais) {
            this.codigoPais = codigoPais;
        }
    }

    public static class CorreoElectronico {
        private String dominio;
        private String valorCorreo;

        protected CorreoElectronico() {}

        @JsonCreator
        public CorreoElectronico(@JsonProperty("dominio") String dominio, @JsonProperty("valorCorreo") String valorCorreo) {
            this.dominio = dominio;
            this.valorCorreo = valorCorreo;
        }

        public String getDominio() {
            return dominio;
        }

        public void setDominio(String dominio) {
            this.dominio = dominio;
        }

        public String getValorCorreo() {
            return valorCorreo;
        }

        public void setValorCorreo(String valorCorreo) {
            this.valorCorreo = valorCorreo;
        }
    }

    public static class Direccion {
        private String ubigeo;
        private String ciudad;
        private String direccion;

        protected Direccion() {}

        @JsonCreator
        public Direccion(@JsonProperty("ubigeo") String ubigeo, @JsonProperty("ciudad") String ciudad,
                         @JsonProperty("direccion") String direccion) {
            this.ubigeo = ubigeo;
            this.ciudad = ciudad;
            this.direccion = direccion;
        }

        public String getUbigeo() {
            return ubigeo;
        }

        public void setUbigeo(String ubigeo) {
            this.ubigeo = ubigeo;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }
    }
}