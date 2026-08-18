package com.example.bankledger.application.usecase;

import com.example.bankledger.domain.model.TransaccionLedger;
import com.example.bankledger.domain.port.TransaccionPort;

import java.util.List;

public class ObtenerHistorialUseCase {
    private final TransaccionPort transaccionPort;
    public ObtenerHistorialUseCase(TransaccionPort transaccionPort)
    {
        this.transaccionPort = transaccionPort;
    }

    public List<TransaccionLedger> ejecutar(String cuentaId)
    {
        return transaccionPort.obtenerHistorial(cuentaId);
    }
}
