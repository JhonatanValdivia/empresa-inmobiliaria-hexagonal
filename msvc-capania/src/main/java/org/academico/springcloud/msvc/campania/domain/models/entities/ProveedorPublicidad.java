package org.academico.springcloud.msvc.campania.domain.models.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ProveedoresPublicidad")
public class ProveedorPublicidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String cuentaPublicitaria;

    public ProveedorPublicidad() {} // Constructor vacío para JPA

    public ProveedorPublicidad(Long idProveedor, String nombre, String cuentaPublicitaria) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es obligatorio");
        }
        if (cuentaPublicitaria == null || cuentaPublicitaria.trim().isEmpty()) {
            throw new IllegalArgumentException("La cuenta publicitaria es obligatoria");
        }
        this.idProveedor = idProveedor;
        this.nombre = nombre.trim();
        this.cuentaPublicitaria = cuentaPublicitaria.trim();
    }

    // Getters y setters
    public Long getIdProveedor() { return idProveedor; }
    public void setIdProveedor(Long idProveedor) { this.idProveedor = idProveedor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCuentaPublicitaria() { return cuentaPublicitaria; }
    public void setCuentaPublicitaria(String cuentaPublicitaria) { this.cuentaPublicitaria = cuentaPublicitaria; }

    // Método para editar proveedor
    public void editarProveedor(String nuevoNombre, String nuevaCuenta) {
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nuevo nombre no puede estar vacío.");
        }
        if (nuevaCuenta == null || nuevaCuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("La nueva cuenta publicitaria no puede estar vacía.");
        }
        this.nombre = nuevoNombre.trim();
        this.cuentaPublicitaria = nuevaCuenta.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProveedorPublicidad)) return false;
        ProveedorPublicidad that = (ProveedorPublicidad) o;
        return Objects.equals(idProveedor, that.idProveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProveedor);
    }
}
