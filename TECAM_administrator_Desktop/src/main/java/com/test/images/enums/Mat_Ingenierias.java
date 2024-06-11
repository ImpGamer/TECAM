package com.test.images.enums;

public enum Mat_Ingenierias {
    //Area Ingenieria y Matematicas
    DESARROLLO_WEB("Desarrollo Web"),
    VIDEOJUEGOS("Desarrollo de VideoJuegos"),
    INTELIGENCIA_ARTIFICIAL("Inteligencia Artificial"),
    CIENCIA_DATOS("Ciencia de datos"),
    REALIDAD_AUM("Realidad Virtual y Aumentada"),
    LENGUAJE_PROG("Lenguaje de Programacion"),
    SEGURIDAD("Seguridad Informatica"),
    MOBILE("Desarrollo Movil"),
    FISICA("Fisica y sus derivados"),
    SOFTWARE("Desarrollo de Software"),
    MATEMATICAS("Temas selectos de Matematicas"),
    FRAMEWORKS("Herramientas y Frameworks"),
    CLOUD("Computo en la Nube"),
    BASES_DATOS("Bases de datos"),
    ELECTRONICA("Electronica y sus derivados"),
    MICROCONTROLADORES("Electronica y MicroControladores"),
    REDES("Redes de Computo"),
    ARQUITECTURA("Principios de Arquitectura y Construccion"),
    MECANICA("Mecanismos Industriales"),
    TELECOMUNICACIONES("Telecomunicaciones");

    private final String displayName;
    Mat_Ingenierias(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return this.displayName;
    }
}
