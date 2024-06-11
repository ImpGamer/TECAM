package com.tecnam.api_get.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "CURSOS")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Curso {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(nullable = false,length = 30)
    private String titulo;
    @Column(nullable = false,length = 200)
    private String descripcion;

    private String areaConocimiento;

    private float precio;
    @Column(nullable = false,updatable = false,unique = true)
    private String URL_imagen;

    @Column(nullable = false,name = "fecha_de_inicio")
    private LocalDate fechaInicio;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    private String rama_Estudio;
}
