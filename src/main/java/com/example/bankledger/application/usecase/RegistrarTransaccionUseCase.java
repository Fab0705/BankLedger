package com.example.bankledger.application.usecase;

import com.example.bankledger.application.dto.TransaccionCommand;
import com.example.bankledger.domain.model.TransaccionLedger;
import com.example.bankledger.domain.port.TransaccionPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class RegistrarTransaccionUseCase {

    private final TransaccionPort transaccionPort;
    public RegistrarTransaccionUseCase(TransaccionPort transaccionPort)
    {
        this.transaccionPort = transaccionPort;
    }

    public void ejecutar(TransaccionCommand command)
    {
        if (command.tipo() == TransaccionLedger.TipoTransaccion.RETIRO) {
            var saldoActual = transaccionPort.calcularSaldoActual(command.cuentaId());
            if (saldoActual.add(command.monto()).compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Fondos insuficientes para el retiro.");
            }
        }

        TransaccionLedger nuevaTransaccion = new TransaccionLedger(
                UUID.randomUUID(),
                command.cuentaId(),
                command.monto(),
                command.tipo(),
                LocalDateTime.now()
        );

        transaccionPort.registrar(nuevaTransaccion);
    }
}
