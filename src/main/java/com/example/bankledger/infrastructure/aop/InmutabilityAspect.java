package com.example.bankledger.infrastructure.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InmutabilityAspect {
    @Before("execution(* org.springframework.data.jpa.repository.JpaRepository.delete*(..)) || " +
            "execution(* org.springframework.data.jpa.repository.JpaRepository.remove*(..)) || " +
            "execution(* org.springframework.data.jpa.repository.JpaRepository.deleteAll*(..))")
    public void prevenirBorrado() {
        throw new SecurityException("¡ALERTA DE SEGURIDAD! El Ledger es inmutable. No se permite eliminar transacciones.");
    }
}
