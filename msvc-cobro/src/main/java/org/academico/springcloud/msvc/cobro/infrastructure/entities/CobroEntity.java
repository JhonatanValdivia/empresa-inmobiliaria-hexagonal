package org.academico.springcloud.msvc.cobro.infrastructure.entities;

import jakarta.persistence.*;
import org.academico.springcloud.msvc.cobro.domain.models.enums.EstadoCobro;
import org.academico.springcloud.msvc.cobro.domain.models.enums.MedioPago;
import org.academico.springcloud.msvc.cobro.infrastructure.entities.infravalueobjects.FechaCobroInfr;
import org.academico.springcloud.msvc.cobro.infrastructure.entities.infravalueobjects.MontoCobroInfr;

@Entity
@Table(name = "Cobros")
public class CobroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCobro;

    @Column(name = "venta_id", nullable = false)
    private Long idVenta;

    @Embedded
    private FechaCobroInfr fechaCobro; // Usamos la versión Infra del Value Object

    @Embedded
    private MontoCobroInfr montoCobro; // Usamos la versión Infra del Value Object

    @Enumerated(EnumType.STRING)
    private EstadoCobro estadoCobro;

    @Enumerated(EnumType.STRING)
    private MedioPago medioPago;

    public CobroEntity() {
    }

    public CobroEntity(Long idCobro, Long idVenta, FechaCobroInfr fechaCobro, MontoCobroInfr montoCobro, EstadoCobro estadoCobro, MedioPago medioPago) {
        this.idCobro = idCobro;
        this.idVenta = idVenta;
        this.fechaCobro = fechaCobro;
        this.montoCobro = montoCobro;
        this.estadoCobro = estadoCobro;
        this.medioPago = medioPago;
    }

    // Getters y Setters
    public Long getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(Long idCobro) {
        this.idCobro = idCobro;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public FechaCobroInfr getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(FechaCobroInfr fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public MontoCobroInfr getMontoCobro() {
        return montoCobro;
    }

    public void setMontoCobro(MontoCobroInfr montoCobro) {
        this.montoCobro = montoCobro;
    }

    public EstadoCobro getEstadoCobro() {
        return estadoCobro;
    }

    public void setEstadoCobro(EstadoCobro estadoCobro) {
        this.estadoCobro = estadoCobro;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
}