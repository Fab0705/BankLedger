package com.example.bankledger.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransaccionLedgerTest {
    @Test
    void deberiaCrearTransaccionExitosaSiDatasoSonValidos()
    {
        UUID id = UUID.randomUUID();
        String cuentaId = "CUENTA-123";
        BigDecimal monto = new BigDecimal("500.00");

        TransaccionLedger transaccion = new TransaccionLedger(
                id, cuentaId, monto, TransaccionLedger.TipoTransaccion.DEPOSITO, LocalDateTime.now()
        );

        assertNotNull(transaccion);
        assertEquals(monto, transaccion.getMonto());
    }

    @Test
    void deberiaLanzarExcepcionSiMontoEsCero(){
        Exception exception = assertThrows(IllegalAccessException.class, () -> {
            new TransaccionLedger(
                    UUID.randomUUID(),
                    "CUENTA-123",
                    BigDecimal.ZERO,
                    TransaccionLedger.TipoTransaccion.DEPOSITO,
                    LocalDateTime.now()
            );
        });

        assertEquals("El monto de la transacción no puede ser cero.", exception.getMessage());
    }
}
