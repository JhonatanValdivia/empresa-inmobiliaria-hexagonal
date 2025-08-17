package org.academico.springcloud.msvc.campania.infrastructure.models.entities;

import jakarta.persistence.*;
import org.academico.springcloud.msvc.campania.domain.models.enums.EstadoCampania;
import org.academico.springcloud.msvc.campania.domain.models.enums.MedioPago;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Campañas")
public class CampaniaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCampania;

    private Long idPropiedad;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idCampania")
    private List<ProveedorPublicidadEntity> proveedores = new ArrayList<>();

    private String nombre;
    @Enumerated(EnumType.STRING)
    private EstadoCampania estado;
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;
    private BigDecimal monto;
    @Enumerated(EnumType.STRING)
    private MedioPago medioPago;

    // Getters y setters
    public Long getIdCampania() { return idCampania; }
    public void setIdCampania(Long idCampania) { this.idCampania = idCampania; }
    public Long getIdPropiedad() { return idPropiedad; }
    public void setIdPropiedad(Long idPropiedad) { this.idPropiedad = idPropiedad; }
    public List<ProveedorPublicidadEntity> getProveedores() { return proveedores; }
    public void setProveedores(List<ProveedorPublicidadEntity> proveedores) { this.proveedores = proveedores; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public EstadoCampania getEstado() { return estado; }
    public void setEstado(EstadoCampania estado) { this.estado = estado; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public MedioPago getMedioPago() { return medioPago; }
    public void setMedioPago(MedioPago medioPago) { this.medioPago = medioPago; }
}