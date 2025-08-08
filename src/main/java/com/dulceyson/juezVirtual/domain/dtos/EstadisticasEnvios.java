package com.dulceyson.juezVirtual.domain.dtos;

public record EstadisticasEnvios(
        long totalEnvios,
        long enviosAceptados,
        long primerosIntentosAceptados
) {
    public double porcentajeAceptados() {
        return totalEnvios > 0 ? (enviosAceptados * 100.0) / totalEnvios : 0;
    }
}