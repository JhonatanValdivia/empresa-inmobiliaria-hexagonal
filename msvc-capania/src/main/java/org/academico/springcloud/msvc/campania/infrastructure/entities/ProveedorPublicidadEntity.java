package org.academico.springcloud.msvc.campania.infrastructure.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ProveedoresPublicidad")
public class ProveedorPublicidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    private String nombre;
    private String cuentaPublicitaria;

    // Getters y setters
    public Long getIdProveedor() { return idProveedor; }
    public void setIdProveedor(Long idProveedor) { this.idProveedor = idProveedor; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCuentaPublicitaria() { return cuentaPublicitaria; }
    public void setCuentaPublicitaria(String cuentaPublicitaria) { this.cuentaPublicitaria = cuentaPublicitaria; }
}