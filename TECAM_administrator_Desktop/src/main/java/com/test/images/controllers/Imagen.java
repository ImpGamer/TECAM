package com.test.images.controllers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "IMAGENES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(name = "imagen", columnDefinition = "MEDIUMBLOB",nullable = false)
    private byte[] bytes_Img;
    @Column(length = 200,nullable = false)
    private String title;
}