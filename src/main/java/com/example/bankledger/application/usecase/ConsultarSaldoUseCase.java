package com.example.bankledger.application.usecase;

import com.example.bankledger.domain.port.TransaccionPort;

import java.math.BigDecimal;

public class ConsultarSaldoUseCase {
    private final TransaccionPort transaccionPort;
    public ConsultarSaldoUseCase(TransaccionPort transaccionPort)
    {
        this.transaccionPort = transaccionPort;
    }
    public BigDecimal ejecutar(String cuentaId)
    {
        return transaccionPort.calcularSaldoActual(cuentaId);
    }
}
