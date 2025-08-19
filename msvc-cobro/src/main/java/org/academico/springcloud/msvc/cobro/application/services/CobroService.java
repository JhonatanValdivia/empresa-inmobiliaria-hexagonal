package org.academico.springcloud.msvc.cobro.application.services;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;
import org.academico.springcloud.msvc.cobro.domain.ports.in.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Este servicio actúa como una fachada que expone los diferentes casos de uso.
// Los controladores interactuarán con esta clase.
public class CobroService implements
        CreateCobroUseCase,
        DeleteCobroUseCase,
        GenerateComprobanteUseCase,
        GetCobroUseCase,
        RegistrarPagoUseCase,
        UpdateCobroUseCase {

    private final CreateCobroUseCase createCobroUseCase;
    private final DeleteCobroUseCase deleteCobroUseCase;
    private final GenerateComprobanteUseCase generateComprobanteUseCase;
    private final GetCobroUseCase getCobroUseCase;
    private final RegistrarPagoUseCase registrarPagoUseCase;
    private final UpdateCobroUseCase updateCobroUseCase;

    public CobroService(
            CreateCobroUseCase createCobroUseCase,
            DeleteCobroUseCase deleteCobroUseCase,
            GenerateComprobanteUseCase generateComprobanteUseCase,
            GetCobroUseCase getCobroUseCase,
            RegistrarPagoUseCase registrarPagoUseCase,
            UpdateCobroUseCase updateCobroUseCase) {
        this.createCobroUseCase = createCobroUseCase;
        this.deleteCobroUseCase = deleteCobroUseCase;
        this.generateComprobanteUseCase = generateComprobanteUseCase;
        this.getCobroUseCase = getCobroUseCase;
        this.registrarPagoUseCase = registrarPagoUseCase;
        this.updateCobroUseCase = updateCobroUseCase;
    }

    @Override
    public Cobro createCobro(Cobro cobro) {
        return createCobroUseCase.createCobro(cobro);
    }

    @Override
    public boolean deleteCobro(Long id) {
        return deleteCobroUseCase.deleteCobro(id);
    }

    @Override
    public Optional<String> generateComprobante(Long idCobro) {
        return generateComprobanteUseCase.generateComprobante(idCobro);
    }

    @Override
    public Optional<Cobro> getCobroById(Long id) {
        return getCobroUseCase.getCobroById(id);
    }

    @Override
    public List<Cobro> getAllCobros() {
        return getCobroUseCase.getAllCobros();
    }

    @Override
    public Optional<Cobro> registrarPago(Long idCobro, BigDecimal montoPago) {
        return registrarPagoUseCase.registrarPago(idCobro, montoPago);
    }

    @Override
    public Optional<Cobro> updateCobro(Long id, Cobro cobro) {
        return updateCobroUseCase.updateCobro(id, cobro);
    }
}