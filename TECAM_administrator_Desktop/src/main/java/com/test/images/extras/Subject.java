package com.test.images.extras;

import com.test.images.enums.Artes_Humanidades;
import com.test.images.enums.Mat_Ingenierias;
import com.test.images.enums.Salud_Biologicas;
import com.test.images.enums.Sociales;

import java.util.HashSet;

public class Subject {
    public static HashSet<String> getAll() {
        HashSet<String> materiasEnfocadas = new HashSet<>();

        for (Artes_Humanidades value : Artes_Humanidades.values()) {
            materiasEnfocadas.add(value.getDisplayName());
        }
        for (Mat_Ingenierias value : Mat_Ingenierias.values()) {
            materiasEnfocadas.add(value.getDisplayName());
        }
        for (Salud_Biologicas value : Salud_Biologicas.values()) {
            materiasEnfocadas.add(value.getDisplayName());
        }
        for (Sociales value : Sociales.values()) {
            materiasEnfocadas.add(value.getDisplayName());
        }
        return materiasEnfocadas;
    }
}
