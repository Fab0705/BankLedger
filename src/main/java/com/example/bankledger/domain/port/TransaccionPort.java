package com.example.bankledger.domain.port;

import com.example.bankledger.domain.model.TransaccionLedger;

import java.math.BigDecimal;
import java.util.List;

public interface TransaccionPort {
    void registrar(TransaccionLedger transaccion);
    List<TransaccionLedger> obtenerHistorial(String cuentaId);
    BigDecimal calcularSaldoActual(String cuentaId);
}
