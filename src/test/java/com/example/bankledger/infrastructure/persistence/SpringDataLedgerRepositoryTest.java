package com.example.bankledger.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class SpringDataLedgerRepositoryTest {

    @Autowired
    private SpringDataLedgerRepository repository;

    @Test
    void deberiaCalcularElSaldoTotalCorrectamente(){

        LedgerEntity deposito = new LedgerEntity();
        deposito.setId(UUID.randomUUID());
        deposito.setCuentaId("CTA-001");
        deposito.setMonto(new BigDecimal("1000.00"));
        deposito.setTipo("DEPOSITO");
        deposito.setFechaCreacion(LocalDateTime.now());

        LedgerEntity retiro = new LedgerEntity();
        retiro.setId(UUID.randomUUID());
        retiro.setCuentaId("CTA-001");
        retiro.setMonto(new BigDecimal("-200.00"));
        retiro.setTipo("RETIRO");
        retiro.setFechaCreacion(LocalDateTime.now());

        repository.save(deposito);
        repository.save(retiro);

        BigDecimal saldoTotal = repository.calcularSaldoTotal("CTA-001");

        assertEquals(0, new BigDecimal("800.00").compareTo(saldoTotal));

    }
}
