package com.dulceyson.juezVirtual.domain.models;

public enum Dificultad {
    FACIL("Fácil"),
    MEDIO("Medio"),
    DIFICIL("Difícil"),
    EXPERTO("Experto");

    private final String nombre;

    Dificultad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}