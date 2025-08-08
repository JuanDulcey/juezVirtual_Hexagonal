package com.dulceyson.juezVirtual.domain.models;

public enum EstadoEnvio {
    PENDIENTE,
    PROCESANDO,
    ACEPTADO,
    ERROR_COMPILACION,
    ERROR_EJECUCION,
    TIEMPO_LIMITE_EXCEDIDO,
    RESPUESTA_INCORRECTA,
    MEMORIA_LIMITE_EXCEDIDA
}