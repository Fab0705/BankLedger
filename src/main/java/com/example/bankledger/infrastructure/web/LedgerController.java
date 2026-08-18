package com.example.bankledger.infrastructure.web;

import com.example.bankledger.application.dto.TransaccionCommand;
import com.example.bankledger.application.usecase.ConsultarSaldoUseCase;
import com.example.bankledger.application.usecase.ObtenerHistorialUseCase;
import com.example.bankledger.application.usecase.RegistrarTransaccionUseCase;
import com.example.bankledger.domain.model.TransaccionLedger;
import com.example.bankledger.infrastructure.persistence.SpringDataLedgerRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/ledger")
public class LedgerController {
    private final RegistrarTransaccionUseCase registrarUseCase;
    private final ObtenerHistorialUseCase historialUseCase;
    private final ConsultarSaldoUseCase saldoUseCase;

    //ENDPOINT PRUEBA DE INMUTABILIDAD
    private final SpringDataLedgerRepository repoTest;

    public LedgerController(RegistrarTransaccionUseCase registrarUseCase, ObtenerHistorialUseCase historialUseCase, ConsultarSaldoUseCase saldoUseCase, SpringDataLedgerRepository repoTest)
    {
        this.registrarUseCase = registrarUseCase;
        this.historialUseCase = historialUseCase;
        this.saldoUseCase = saldoUseCase;
        this.repoTest = repoTest;
    }

    @PostMapping("/transaccion")
    public String registrarTransaccion(@RequestBody TransaccionCommand command)
    {
        registrarUseCase.ejecutar(command);
        return  "¡Éxito! Transacción de " + command.tipo() + " por $" + command.monto() + " registrada.";
    }

    @GetMapping("/{cuentaId}/historial")
    public List<TransaccionLedger> obtenerHistorial(@PathVariable String cuentaId)
    {
        return historialUseCase.ejecutar(cuentaId);
    }

    @GetMapping("/{cuentaId}/saldo")
    public String consultarSaldo(@PathVariable String cuentaId)
    {
        BigDecimal saldo = saldoUseCase.ejecutar(cuentaId);
        return "El saldo actual de la cuenta " + cuentaId + " es: $" + saldo;
    }

    // 🔴 ENDPOINT MALICIOSO TEMPORAL
    @DeleteMapping("/hack/borrar-todo")
    public String intentarBorrarTodo() {
        // Un desarrollador junior intenta limpiar la tabla...
        repoTest.deleteAll();
        return "Si ves esto, el AOP falló y la base de datos fue borrada.";
    }
}
