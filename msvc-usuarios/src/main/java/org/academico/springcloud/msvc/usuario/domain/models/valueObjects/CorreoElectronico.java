package org.academico.springcloud.msvc.usuario.domain.models.valueObjects;

import java.util.Objects;

public class CorreoElectronico {
    private String dominio;
    private String valorCorreo;

    protected CorreoElectronico() {}

    public CorreoElectronico(String dominio, String valorCorreo) {
        if (dominio == null || dominio.trim().isEmpty()) {
            throw new IllegalArgumentException("El dominio no puede ser vacío.");
        }
        if (valorCorreo == null || valorCorreo.trim().isEmpty()) {
            throw new IllegalArgumentException("El valor del correo no puede ser vacío.");
        }

        this.dominio = dominio.trim().toLowerCase();
        this.valorCorreo = valorCorreo.trim().toLowerCase();

        String correoCompleto = valorCorreo + "@" + dominio;
        if (correoCompleto.length() > 254) {
            throw new IllegalArgumentException("El correo excede la longitud máxima permitida.");
        }
        if (!correoCompleto.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Formato de correo inválido.");
        }
    }

    public String getDominio() { return dominio; }
    public String getValorCorreo() { return valorCorreo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CorreoElectronico)) return false;
        CorreoElectronico that = (CorreoElectronico) o;
        return dominio.equals(that.dominio) && valorCorreo.equals(that.valorCorreo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dominio, valorCorreo);
    }

    @Override
    public String toString() {
        return valorCorreo + "@" + dominio;
    }
}