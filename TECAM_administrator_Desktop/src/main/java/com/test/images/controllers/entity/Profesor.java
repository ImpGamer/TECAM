package com.test.images.controllers.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROFESORES")
@Getter
@Setter
@NoArgsConstructor

public class Profesor{

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String nombre;
    private Byte edad;
    private String telefono;

    public Profesor(String nombre, byte edad,String telefono) {
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
    }
}
