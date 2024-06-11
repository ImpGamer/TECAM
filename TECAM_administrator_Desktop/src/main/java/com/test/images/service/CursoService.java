package com.test.images.service;

import com.test.images.controllers.entity.Curso;
import com.test.images.enums.*;
import com.test.images.extras.Alerts;
import com.test.images.graphics.CourseElement;
import com.test.images.repository.CursosRepository;
import javafx.scene.control.Alert;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CursoService {
    @Autowired
    private CursosRepository cursosRepository;
    @Getter
    private CourseElement courseElement;
    public long cursoID;

    public boolean agregarCurso(Curso curso) {
        if(curso.getTitulo().length() > 30 || curso.getPrecio() < 1) {
            return false;
        }
        String rama_estudo = curso.getRama_Estudio();
        curso.setAreaConocimiento(setAreaConocimiento(rama_estudo));

        Curso cursoSave = cursosRepository.save(curso);
        this.courseElement = new CourseElement(
          cursoSave.getId(),
          cursoSave.getURL_imagen(),
          cursoSave.getTitulo(),
          cursoSave.getDescripcion(),
          cursoSave.getProfesor().getNombre()
        );

        return true;
    }
    public List<Curso> listaCursos() {return cursosRepository.findAll();}

    private Enum<?> buscarArea_porDisplay(String display) {
        for(Mat_Ingenierias value: Mat_Ingenierias.values()) {
            if(value.getDisplayName().equals(display)) {
                return Mat_Ingenierias.ARQUITECTURA;
            }
        }
        for(Salud_Biologicas value: Salud_Biologicas.values()) {
            if(value.getDisplayName().equals(display)) {
                return Salud_Biologicas.BIOLOGIA;
            }
        }
        for(Sociales value: Sociales.values()) {
            if(value.getDisplayName().equals(display)) {
                return Sociales.SOCIAL;
            }
        }
        for(Artes_Humanidades value: Artes_Humanidades.values()) {
            if(value.getDisplayName().equals(display)) {
                return Artes_Humanidades.DISENO;
            }
        }
        return null;
    }
    public void eliminarCurso(Long id) {
        Optional<Curso> cursoBBDD = cursosRepository.findById(id);
        if(cursoBBDD.isPresent()) {
            if(Alerts.summon("Estas seguro que deseas eliminar el curso: '"+cursoBBDD.get().getTitulo()+"'?"
            ,Optional.of("Eliminar curso"))) {
                cursosRepository.delete(cursoBBDD.get());
                Alerts.summon(Alert.AlertType.INFORMATION,"El curso a sido eliminado correctamente",Optional.empty());
            }
        } else {
            Alerts.summon(Alert.AlertType.WARNING,"No se a podido encontrar el curso con ID: "+id,
                    Optional.of("Curso no encontrado!"));
        }
    }
    public void editarCurso(Curso curso) {
        Optional<Curso> cursoBBDD = cursosRepository.findById(cursoID);
        if(cursoBBDD.isEmpty()) {
            Alerts.summon(Alert.AlertType.ERROR,"Curso con ID: "+cursoID+" no encontrado",
                    Optional.of("Curso no encontrado"));
            return;
        }
        Curso cursoSave = cursoBBDD.get();
        cursoSave.setTitulo(curso.getTitulo());
        cursoSave.setPrecio(curso.getPrecio());
        cursoSave.setDescripcion(curso.getDescripcion());
        cursoSave.setFechaInicio(curso.getFechaInicio());
        cursoSave.setRama_Estudio(curso.getRama_Estudio());
        cursoSave.setURL_imagen(curso.getURL_imagen());
        cursoSave.setAreaConocimiento(setAreaConocimiento(curso.getRama_Estudio()));
        cursoSave.setProfesor(curso.getProfesor());
        cursosRepository.save(cursoSave);
        Alerts.summon(Alert.AlertType.INFORMATION,"Curso '"+cursoSave.getTitulo()+"' guardado correctamente",
                Optional.of("Curso guardado"));
        cursoID = 0;
    }
    private String setAreaConocimiento(String rama_estudio) {
        Enum<?> enumArea = buscarArea_porDisplay(rama_estudio);

        return switch (enumArea) {
            case Mat_Ingenierias ignored1 -> AreaConocimiento.MATEMATICAS_E_INGENIERIA.getDisplayName();
            case Salud_Biologicas saludBiologicas -> AreaConocimiento.CIENCIAS_BIOLOGICAS_SALUD.getDisplayName();
            case Sociales sociales -> AreaConocimiento.CIENCIAS_SOCIALES.getDisplayName();
            case Artes_Humanidades ignored -> AreaConocimiento.ARTES_HUMANIDADES.getDisplayName();
            case null, default -> "No posee area";
        };
    }
}
