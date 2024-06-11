package com.test.images.enums;

public enum Artes_Humanidades {
    DISENO("Diseño grafico"),
    VISUAL("Artes Visuales"),
    IDIOMAS("Aprendizaje de Lengua Extranjera"),
    HISTORIA("Historia Humana"),
    LETRAS("Letras y derivados de la literatura"),
    MUSICA("Aprendizaje musical"),
    TEATRO("Artes teatrales");

    private final String displayName;
    Artes_Humanidades(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return this.displayName;
    }
}
