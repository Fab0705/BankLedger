package com.example.bankledger.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransaccionLedger {
    private final UUID id;
    private final String cuentaId;
    private final BigDecimal monto;
    private final TipoTransaccion tipo;
    private final LocalDateTime fechaCreacion;

    public enum TipoTransaccion { DEPOSITO, RETIRO, PAGO_SERVICIO }

    public TransaccionLedger(UUID id, String cuentaId, BigDecimal monto, TipoTransaccion tipo, LocalDateTime fechaCreacion) {
        if(monto.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("El monto de la transacción no puede ser cero");
        }
        if(tipo == TipoTransaccion.RETIRO && monto.compareTo(BigDecimal.ZERO) > 0){
            throw new IllegalArgumentException("Los retiros deben registrarse con montos negativos");
        }

        this.id = id;
        this.cuentaId = cuentaId;
        this.monto = monto;
        this.tipo = tipo;
        this.fechaCreacion = fechaCreacion;
    }

    public UUID getId() { return id; }
    public String getCuentaId() { return cuentaId; }
    public BigDecimal getMonto() { return monto; }
    public TipoTransaccion getTipo() { return tipo; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
}
