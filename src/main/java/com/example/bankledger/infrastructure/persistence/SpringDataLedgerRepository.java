package com.example.bankledger.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface SpringDataLedgerRepository extends JpaRepository<LedgerEntity, UUID> {
    List<LedgerEntity> findByCuentaIdOrderByFechaCreacionAsc(String cuentaId);

    // JPQL para sumar todas las transacciones y obtener el saldo real
    @Query("SELECT COALESCE(SUM(l.monto), 0) FROM LedgerEntity l WHERE l.cuentaId = :cuentaId")
    BigDecimal calcularSaldoTotal(@Param("cuentaId") String cuentaId);
}
