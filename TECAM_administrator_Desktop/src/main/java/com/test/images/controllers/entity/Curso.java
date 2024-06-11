package com.test.images.controllers.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "CURSOS")
@NoArgsConstructor
@Getter
@Setter

public class Curso{
    public Curso(String titulo, String descripcion, float precio,
                 String URL_imagen, LocalDate fechaInicio, Profesor profesor,String rama_Estudio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.URL_imagen = URL_imagen;
        this.fechaInicio = fechaInicio;
        this.profesor = profesor;
        this.rama_Estudio = rama_Estudio;
    }
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(nullable = false,length = 30)
    private String titulo;
    @Column(nullable = false,length = 200)
    private String descripcion;

    private String areaConocimiento;

    private float precio;
    @Column(nullable = false,unique = true)
    private String URL_imagen;

    @Column(nullable = false,name = "fecha_de_inicio")
    private LocalDate fechaInicio;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    private String rama_Estudio;

}
