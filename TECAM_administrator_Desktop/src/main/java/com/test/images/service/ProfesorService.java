package com.test.images.service;

import com.test.images.controllers.entity.Profesor;
import com.test.images.extras.Alerts;
import com.test.images.repository.ProfesorRepository;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;
    public long profID;

    public List<Profesor> obtenerProfesores() {
        return profesorRepository.findAll();
    }
    public boolean guardarProfesor(Profesor profesor)throws Exception {
        if(profesor.getEdad() < 15) {
            throw new Exception("Edad invalida");
        } else if(profesor.getNombre().isEmpty()) {
            throw new Exception("Datos vacios");
        }

        profesorRepository.save(profesor);
        return true;
    }
    public void eliminarProfesor(Long profID) {
        Optional<Profesor> profesorBBDD = profesorRepository.findById(profID);
        if(profesorBBDD.isPresent()) {
            if(Alerts.summon("Estas seguro que deseas eliminar al profesor "+profesorBBDD.get().getNombre()+"?",
                    Optional.empty())) {
                profesorRepository.delete(profesorBBDD.get());
                Alerts.summon(Alert.AlertType.INFORMATION,"El profesor a sido eliminado correctamente!",Optional.empty());
            }
        } else {
            Alerts.summon(Alert.AlertType.INFORMATION,"Error al intentar eliminar el profesor! Verifique los datos.",
                    Optional.of("Profesor no encontrado!"));
        }
    }
    public void editarProfesor(Profesor profesor) {
        Optional<Profesor> profesorBBDD = profesorRepository.findById(profID);
        if(profesorBBDD.isEmpty()) {
            Alerts.summon(Alert.AlertType.ERROR,"Profesor con ID: "+profID+" no encontrado",
                    Optional.of("Profesor no encontrado"));
            return;
        }
        Profesor profesorSave = profesorBBDD.get();
        profesorSave.setNombre(profesor.getNombre());
        profesorSave.setEdad(profesor.getEdad());
        profesorSave.setTelefono(profesor.getTelefono());
        profesorRepository.save(profesorSave);
        Alerts.summon(Alert.AlertType.INFORMATION,"El profesor "+profesorSave.getNombre()+" ha sido guardado correctamente",
                Optional.of("Profesor guardado"));
        profID = 0;
    }
}
