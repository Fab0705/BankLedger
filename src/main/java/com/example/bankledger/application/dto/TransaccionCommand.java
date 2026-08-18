package com.example.bankledger.application.dto;

import com.example.bankledger.domain.model.TransaccionLedger;

import java.math.BigDecimal;

public record TransaccionCommand(String cuentaId, BigDecimal monto, TransaccionLedger.TipoTransaccion tipo) {
}
