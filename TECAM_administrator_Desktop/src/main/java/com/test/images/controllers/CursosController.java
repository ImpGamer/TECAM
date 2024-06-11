package com.test.images.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.test.images.controllers.entity.Curso;
import com.test.images.controllers.entity.Profesor;
import com.test.images.extras.Alerts;
import com.test.images.extras.Subject;
import com.test.images.extras.Validation;
import com.test.images.graphics.CourseElement;
import com.test.images.graphics.TeacherElement;
import com.test.images.service.CursoService;
import com.test.images.service.ProfesorService;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class CursosController {
    private HostServices hostServices;
    //Apartado grafico de los cursos
    @FXML
    private VBox coursesConteiner = new VBox();
    @FXML
    private Hyperlink LinkWeb = new Hyperlink();
    @FXML
    private TextField imgURL_txt,titulo_txt,precio_txt;
    @FXML
    private Label errrorImageLbl;
    @FXML
    private Label errorTitle,errorPrice;
    @FXML
    private ImageView imagenURL;
    @FXML
    private JFXComboBox<String> materiasEnfocadas = new JFXComboBox<>();
    @FXML
    private JFXComboBox<Profesor> profesoresCB = new JFXComboBox<>();
    @FXML
    private JFXTextArea descripcion_txt;
    @FXML
    private DatePicker fechaInicio = new DatePicker();
    @FXML
    private JFXButton btnLimpiar = new JFXButton();
    //Aparatado grafico de los profesores
    @FXML
    private TextField nombreProf,edadProf,telefonoProf;
    @FXML
    private Label errorName,errorAge;
    @FXML
    private VBox teachersContainer = new VBox();
    private final String className = this.getClass().getName();
    private final Logger LOGGER = Logger.getLogger(className);
    //Tabuladores
    @FXML
    private TabPane parentTabPane;
    @FXML
    private Tab cursoTab,profesorTab;

    //Inyeccion de dependencias
    @Autowired
    private ProfesorService profesorService;
    @Autowired
    private CursoService cursoService;

    public void initialize() {
        materiasEnfocadas.getItems().addAll(Subject.getAll());
        actualizarProf();
        btnLimpiar.setOnAction(event -> {
            if(Alerts.summon("Estas seguro que deseas borrar todo?",Optional.empty())) {
                limpiarTodo();
            }
        });
        LinkWeb.setOnAction(event -> {
            String url = "http://localhost:3606"; //URL web (cambiar luego)
            try {
                hostServices.showDocument(url);
            }catch (Exception e) {
                Alerts.summon(Alert.AlertType.ERROR,"Error al tratar de abrir el navegador.\n"+e.getMessage(),
                        Optional.empty());
            }
        });
        setGraphicCourses();
        setGraphicTeachers();
    }
    @FXML
    private void guardarCurso() {
        float precio;

        if(validateInputs()) {
            try {
                precio = Float.parseFloat(precio_txt.getText());
            }catch (NumberFormatException e) {
                Alerts.summon(Alert.AlertType.ERROR,"El precio ingresado no es valido.",
                        Optional.of("Error de precio"));
                return;
            }
            //Creacion de un nuevo objeto curso que se almacenara
            Curso nuevoCurso = new Curso(
                    titulo_txt.getText(),
                    descripcion_txt.getText().isEmpty()?"Sin descripcion":descripcion_txt.getText(),
                    precio,
                    imgURL_txt.getText(),
                    fechaInicio.getValue(),
                    profesoresCB.getValue(),
                    materiasEnfocadas.getValue()
            );
            if(cursoService.cursoID != 0) {
                cursoService.editarCurso(nuevoCurso);
                limpiarTodo();
                setGraphicCourses();
            } else if(cursoService.agregarCurso(nuevoCurso)) {
                Alerts.summon(Alert.AlertType.INFORMATION,"El curso '"+titulo_txt.getText()+"' a sido guardado correctamente!"
                        ,Optional.of("Curso guardado"));
                CourseElement cursoGrafico = cursoService.getCourseElement();
                HBox elementoCurso = cursoGrafico.create();
                if(Validation.containsLabel(coursesConteiner)) {
                    coursesConteiner.getChildren().clear();
                    coursesConteiner.getChildren().add(elementoCurso);
                    limpiarTodo();
                    return;
                }
                coursesConteiner.getChildren().add(elementoCurso);
                limpiarTodo();
            } else {
                Alerts.summon(Alert.AlertType.ERROR,"Ha ocurrido un error al tratar de guardar el curso\nVerifique la informacion y vuelva a intentar"
                        ,Optional.of("Error al guardar curso"));
            }
        }

    }
    @FXML
    private void guardarProfesor() {
        String errorMessage = "Este campo no puede ser vacio";
        errorName.setText(nombreProf.getText().isEmpty()?errorMessage:"");
        errorAge.setText(edadProf.getText().isEmpty()?errorMessage:"");
        byte edad;

        try {
            edad = Byte.parseByte(edadProf.getText());
            String telefono = telefonoProf.getText();
            if(telefono.length() != 10) {
                Alerts.summon(Alert.AlertType.ERROR,"El telefono proporcionado no es valid (10 caracteres)",Optional.of("Telefono invalido"));
                return;
            }
            Profesor profesor = new Profesor(nombreProf.getText(),edad,telefono);
            if(profesorService.profID != 0) {
                profesorService.editarProfesor(profesor);
                setGraphicTeachers();
                limpiarTodo();
            } else if(profesorService.guardarProfesor(profesor)) {
                actualizarProf();
                Alerts.summon(Alert.AlertType.INFORMATION,"El profesor "+profesor.getNombre()+" ha sido guardado correctamente!",
                        Optional.empty());
                TeacherElement teacherElement = new TeacherElement(
                        profesor.getId(),
                        profesor.getNombre(),
                        profesor.getEdad(),
                        profesor.getTelefono()
                );
                HBox graphicTeacher = teacherElement.create();
                teachersContainer.getChildren().add(graphicTeacher);
                limpiarTodo();
            }

        }catch (Exception e) {
            errorMessage = e.getMessage();
            Alerts.summon(Alert.AlertType.ERROR,"Oops ha ocurrido un error.\nVerifique sus datos o intentelo de nuevo."
            ,Optional.of(errorMessage));
        }
    }
    private void actualizarProf() {
        profesoresCB.getItems().clear();
        profesoresCB.getItems().addAll(profesorService.obtenerProfesores());
        profesoresCB.setConverter(new StringConverter<>() {
            @Override
            public String toString(Profesor profesor) {
                return profesor != null?"Prof. "+profesor.getNombre():null;
            }
            @Override
            public Profesor fromString(String s) {
                return null;
            }
        });
    }
    @FXML
    private void limpiarTodo() {
        titulo_txt.setText("");
        precio_txt.setText("");
        imgURL_txt.setText("");
        errrorImageLbl.setText("");
        errorTitle.setText("");
        errorPrice.setText("");
        imagenURL.setImage(null);
        descripcion_txt.setText("");
        fechaInicio.setValue(null);
        materiasEnfocadas.setValue(null);
        profesoresCB.setValue(null);
        errorName.setText("");
        errorAge.setText("");
        nombreProf.setText("");
        edadProf.setText("");
        telefonoProf.setText("");
    }
    @FXML
    private void salir() {
        if(Alerts.summon("Estas seguro que deseas salir de la aplicacion?", Optional.of("Salir"))) {
            LOGGER.info("Hasta luego!");
            Platform.exit();
        }
    }
    @FXML
    private void verImagenURL() {
        this.errrorImageLbl.setText("");
        if(imgURL_txt.getText().isEmpty()) {
            this.errrorImageLbl.setText("No se a proporcionado ninguna URL");
            return;
        }
        try {
            String URL = this.imgURL_txt.getText();
            Image image = new Image(URL);
            this.imagenURL.setImage(image);
        }catch (Exception e) {
            this.errrorImageLbl.setText("La URL es incompleta o no valida");
        }
    }
    private boolean validateInputs() {
        boolean validate = true;
        String errrorMessage = "Este campo no puede estar vacio";
        if(titulo_txt.getText().isEmpty()) {
            errorTitle.setText(errrorMessage);
            validate=false;
        }
        errorPrice.setText(precio_txt.getText().isEmpty()?errrorMessage:"");
        if(fechaInicio.getValue().isBefore(LocalDate.now())) {
            Alerts.summon(Alert.AlertType.WARNING,"La fecha proporcionada debe ser futura.",Optional.of("Error de fecha"));
            validate = false;
        } else if(materiasEnfocadas.getValue().isEmpty()) {
            Alerts.summon(Alert.AlertType.WARNING,"Se debe proporcionar la materia enfocada"
                    ,Optional.of("Materia no especificada"));
            validate = false;
        }

        return validate;
    }
    @FXML
    private void setGraphicCourses() {
        List<Curso> lista_de_cursos = cursoService.listaCursos();
        coursesConteiner.getChildren().clear();
        if(lista_de_cursos.isEmpty()) {
            Label sinCursos = new Label("No se encuentran cursos registrados");
            sinCursos.setFont(new Font(25));
            sinCursos.setTextFill(Color.YELLOW);
            coursesConteiner.getChildren().add(sinCursos);
            return;
        }
        for(Curso curso: lista_de_cursos) {
            CourseElement cursoGrafico = new CourseElement(
              curso.getId(),
              curso.getURL_imagen(),
              curso.getTitulo(),
              curso.getDescripcion(),
              curso.getProfesor().getNombre()
            );
            cursoGrafico.getBtnEliminar().setOnAction(event -> {
                cursoService.eliminarCurso(cursoGrafico.getCursoID());
                setGraphicCourses();
            });
            cursoGrafico.getBtnEditar().setOnAction(event -> {
                parentTabPane.getSelectionModel().select(cursoTab);
                cursoService.cursoID = curso.getId();
                titulo_txt.setText(curso.getTitulo());
                precio_txt.setText(String.valueOf(curso.getPrecio()));
                imgURL_txt.setText(curso.getURL_imagen());
                imagenURL.setImage(new Image(curso.getURL_imagen()));
                materiasEnfocadas.setValue(curso.getRama_Estudio());
                profesoresCB.setValue(curso.getProfesor());
                fechaInicio.setValue(curso.getFechaInicio());
                descripcion_txt.setText(curso.getDescripcion());
            });
            HBox elementoCurso = cursoGrafico.create();
            coursesConteiner.getChildren().add(elementoCurso);
        }
    }
    @FXML
    private void setGraphicTeachers() {
        List<Profesor> lista_profesores = profesorService.obtenerProfesores();
        teachersContainer.getChildren().clear();
        for(Profesor profesor: lista_profesores) {
            TeacherElement profeGrafico = new TeacherElement(
              profesor.getId(),
              profesor.getNombre(),
                    profesor.getEdad(),
                    profesor.getTelefono()
            );
            profeGrafico.getBtnEliminar().setOnAction(event -> {
                profesorService.eliminarProfesor(profesor.getId());
                setGraphicTeachers();
            });
            profeGrafico.getBtnEditar().setOnAction(event -> {
                parentTabPane.getSelectionModel().select(profesorTab);
                nombreProf.setText(profesor.getNombre());
                edadProf.setText(String.valueOf(profesor.getEdad()));
                telefonoProf.setText(profesor.getTelefono());
                profesorService.profID = profesor.getId();
            });
            HBox graphicElement = profeGrafico.create();
            teachersContainer.getChildren().add(graphicElement);
        }
    }
    public void setHostService(HostServices hostServices) {
        this.hostServices = hostServices;
    }
}