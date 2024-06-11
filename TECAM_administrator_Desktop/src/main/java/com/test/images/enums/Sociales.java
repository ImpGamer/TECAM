package com.test.images.enums;

public enum Sociales {
    ADMINISTRACION("Administracion empresarial o personal"),
    POLITICA("Leyes y control social"),
    CONTADURIA("Contaduria Administrativa"),
    ECONOMIA("Economia general"),
    GEOGRAFIA("Geografia social"),
    INFORMATICA("Ofimatica"),
    NEGOCIOS("Negocios y Crecimiento"),
    SOCIAL("Desarollo Social sostenible");


    private final String displayName;
    Sociales(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return this.displayName;
    }
}
