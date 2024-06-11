package com.test.images.enums;

public enum AreaConocimiento {
    MATEMATICAS_E_INGENIERIA("Fisico Matematicas e Ingenierias"),
    CIENCIAS_BIOLOGICAS_SALUD("Ciencias Biologicas y de la Salud"),
    CIENCIAS_SOCIALES("Ciencias Sociales"),
    ARTES_HUMANIDADES("Artes y Humanidades");

    private final String displayName;

    AreaConocimiento(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return this.displayName;
    }
}
