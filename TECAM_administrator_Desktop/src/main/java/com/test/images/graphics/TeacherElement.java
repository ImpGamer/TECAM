package com.test.images.graphics;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TeacherElement extends CourseElement {
    @Getter
    @Setter
    private long profesorID;
    private final Image imgProfesor = new Image("https://static.vecteezy.com/system/resources/previews/014/554/760/original/man-profile-negative-photo-anonymous-silhouette-human-head-businessman-worker-support-illustration-vector.jpg");
    private final Label nombreProfesor = new Label();
    private final Label edadProfesor = new Label();
    private final Label telefonoProfesor = new Label();
    public TeacherElement(long profesorID,String nombreProf,byte edadProf,String telefono) {
        this.profesorID = profesorID;
        fuenteLetra = new Font("Britannic Bold",20);
        this.nombreProfesor.setText("Nombre Profes@r: " +nombreProf);
        this.nombreProfesor.setFont(fuenteLetra);

        fuenteLetra = new Font("System",15);
        this.edadProfesor.setFont(fuenteLetra);
        this.edadProfesor.setText("Edad del profesor: "+edadProf);
        this.telefonoProfesor.setText("Telefono: "+telefono);
    }

    @Override
    public HBox create() {
        HBox elementoPadre = new HBox();
        VBox contenedorLabel = new VBox();
        HBox contenedorBtn = new HBox(10);

        contenedorLabel.getChildren().add(this.nombreProfesor);
        contenedorLabel.getChildren().add(this.telefonoProfesor);
        contenedorLabel.getChildren().add(this.edadProfesor);
        contenedorBtn.getChildren().add(this.getBtnEditar());
        contenedorBtn.getChildren().add(this.btnEliminar);
        setearEstilos(elementoPadre,contenedorBtn,contenedorLabel);
        elementoPadre.getChildren().add(contenedorLabel);
        elementoPadre.getChildren().add(contenedorBtn);
        return elementoPadre;
    }

    @Override
    protected void setearEstilos(HBox elementoPadre, HBox contenedorBotones, VBox contenedorLabel) {
        //Estilos del contenedor padre y la imagen dentro de este contenedor
        elementoPadre.setPrefHeight(170);
        ImageView imgProf = new ImageView();
        imgProf.setImage(this.imgProfesor);
        imgProf.setFitWidth(170);
        imgProf.setFitHeight(elementoPadre.getPrefHeight());

        elementoPadre.setStyle("-fx-background-color: #c2c7cf; "+
                "-fx-background-radius: 10px; "+
                "-fx-border-color: black; "+
                "-fx-border-radius: 10px; "+
                "-fx-border-width: 2px");
        elementoPadre.getChildren().add(imgProf);
        //Estilos de los Label (nombre y edad) del profesor
        byte labelHeight = 100;
        this.nombreProfesor.setPrefHeight(labelHeight+50);
        this.edadProfesor.setPrefHeight(labelHeight-50);
        this.telefonoProfesor.setPrefHeight(labelHeight-50);
        this.nombreProfesor.setWrapText(true);
        //Estilos del contenedor del nombre y edad del profesor
        contenedorLabel.setPrefWidth(345);
        contenedorLabel.setPrefHeight(elementoPadre.getPrefHeight());
        contenedorLabel.setPadding(new Insets(0,10,0,10));

        //Estilo del boton
        btnEliminar.setStyle("-fx-background-color: #d9111f; "+
                "-fx-background-radius: 10px; "+
                "-fx-text-fill: white");
        btnEliminar.setButtonType(JFXButton.ButtonType.RAISED);
        btnEliminar.setPrefWidth(140);
        btnEliminar.setAlignment(Pos.CENTER);
        btnEliminar.setOnMouseEntered(e -> btnEliminar.setCursor(Cursor.HAND));
        btnEditar.setStyle("-fx-background-color: #f2e530;"+
                "-fx-background-radius: 10px; "+
                "-fx-text-fill: black");
        btnEditar.setButtonType(JFXButton.ButtonType.RAISED);
        btnEditar.setPrefWidth(140);
        btnEditar.setAlignment(Pos.CENTER);
        btnEditar.setOnMouseEntered(e -> btnEditar.setCursor(Cursor.HAND));

        contenedorBotones.setAlignment(Pos.CENTER);
    }
}
