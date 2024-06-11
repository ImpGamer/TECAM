package com.tecnam.api_get.service;

import com.tecnam.api_get.entity.Curso;
import com.tecnam.api_get.exceptions.CoursesNotFoundException;
import com.tecnam.api_get.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> mostrarCursos() {
        return cursoRepository.findAll();
    }
    public List<Curso> buscarCurso_Nombre(String nameCurse)throws CoursesNotFoundException {
        List<Curso> nombre_cursos = cursoRepository.findCursoByName(nameCurse);
        if(nombre_cursos.isEmpty()) {
            throw new CoursesNotFoundException("No se han encontrado cursos de '"+nameCurse+"'");
        }
        return nombre_cursos;
    }
    public Curso buscarCurso_ID(Long id)throws CoursesNotFoundException {
        Optional<Curso> cursoBBDD = cursoRepository.findById(id);
        if(cursoBBDD.isEmpty()) {
            throw new CoursesNotFoundException("El curso con ID: "+id+" no fue encontrado");
        }
        return cursoBBDD.get();
    }
}
