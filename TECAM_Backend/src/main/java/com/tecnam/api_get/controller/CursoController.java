package com.tecnam.api_get.controller;

import com.tecnam.api_get.entity.Curso;
import com.tecnam.api_get.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = {"http://localhost:3606", "http://127.0.0.1:3606"})
public class CursoController {
    @Autowired
    private CursoService cursoService;
    @GetMapping
    ResponseEntity<List<Curso>> mostrarCursos() {
        return ResponseEntity.ok(cursoService.mostrarCursos());
    }
    @GetMapping("/search")
    ResponseEntity<List<Curso>> cursosEncontrados(@RequestParam(name = "course")String titleCourse) {
        return ResponseEntity.ok(cursoService.buscarCurso_Nombre(titleCourse));
    }
    @GetMapping("/{cursoID}")
    ResponseEntity<Curso> buscarCurso_ID(@PathVariable Long cursoID) {
        return ResponseEntity.ok(cursoService.buscarCurso_ID(cursoID));
    }
}