package com.test.images.enums;

public enum Salud_Biologicas  {
    BIOLOGIA("Principios de Biologia"),
    NUTRICION("Salud y equilibrio humano"),
    ECOLOGIA("Bienestar Ecologico"),
    ENFERMERIA("Enfermeria y cuidado humano"),
    QUIMICA("Quimica general");

    private final String displayName;
    Salud_Biologicas(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return this.displayName;
    }
}
