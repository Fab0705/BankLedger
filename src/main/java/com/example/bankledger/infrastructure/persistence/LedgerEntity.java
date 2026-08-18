package com.example.bankledger.infrastructure.persistence;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ledger_transacciones")
public class LedgerEntity {
    @Id
    private UUID id;

    @Column(name = "cuenta_id", nullable = false)
    private String cuentaId;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private String tipo; // Guardaremos el enum como String ("DEPOSITO", "RETIRO")

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    public LedgerEntity() {
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCuentaId() { return cuentaId; }
    public void setCuentaId(String cuentaId) { this.cuentaId = cuentaId; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    @PreUpdate
    public void prevenirActualizacion() {
        throw new SecurityException("¡ALERTA DE SEGURIDAD! El Ledger es inmutable. No se permite modificar transacciones existentes.");
    }
}
