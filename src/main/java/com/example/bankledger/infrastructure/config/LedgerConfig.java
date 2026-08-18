package com.example.bankledger.infrastructure.config;

import com.example.bankledger.application.usecase.ConsultarSaldoUseCase;
import com.example.bankledger.application.usecase.ObtenerHistorialUseCase;
import com.example.bankledger.application.usecase.RegistrarTransaccionUseCase;
import com.example.bankledger.domain.port.TransaccionPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LedgerConfig {
    @Bean
    public RegistrarTransaccionUseCase registrarTransaccionUseCase(TransaccionPort transaccionPort) {
        return new RegistrarTransaccionUseCase(transaccionPort);
    }
    @Bean
    public ObtenerHistorialUseCase obtenerHistorialUseCase(TransaccionPort transaccionPort){
        return new ObtenerHistorialUseCase(transaccionPort);
    }
    @Bean
    ConsultarSaldoUseCase consultarSaldoUseCase(TransaccionPort transaccionPort)
    {
        return new ConsultarSaldoUseCase(transaccionPort);
    }
}
