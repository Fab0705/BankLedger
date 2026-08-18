package com.example.bankledger.infrastructure.persistence;

import com.example.bankledger.domain.model.TransaccionLedger;
import com.example.bankledger.domain.port.TransaccionPort;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class TransaccionPortAdapter implements TransaccionPort {
    private final SpringDataLedgerRepository jpaRepository;
    public TransaccionPortAdapter(SpringDataLedgerRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void registrar(TransaccionLedger transaccion)
    {
        LedgerEntity entity = new LedgerEntity();
        entity.setId(transaccion.getId());
        entity.setCuentaId(transaccion.getCuentaId());
        entity.setMonto(transaccion.getMonto());
        entity.setTipo(transaccion.getTipo().name());
        entity.setFechaCreacion(transaccion.getFechaCreacion());

        jpaRepository.save(entity);
    }

    @Override
    public List<TransaccionLedger> obtenerHistorial(String cuentaId) {
        List<LedgerEntity> entidades = jpaRepository.findByCuentaIdOrderByFechaCreacionAsc(cuentaId);

        return entidades.stream()
                .map(entity -> new TransaccionLedger(
                        entity.getId(),
                        entity.getCuentaId(),
                        entity.getMonto(),
                        TransaccionLedger.TipoTransaccion.valueOf(entity.getTipo()),
                        entity.getFechaCreacion()
                )).toList();
    }

    @Override
    public BigDecimal calcularSaldoActual(String cuentaId) {
        return jpaRepository.calcularSaldoTotal(cuentaId);
    }
}
