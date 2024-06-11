package com.test.images.graphics;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
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
public class CourseElement {
    @Getter
    @Setter
    private long cursoID;
    private final ImageView imagenCurso = new ImageView();
    private final Label tituloCurso = new Label();
    private final Label descripcionCurso = new Label();
    private final Label nombreProfesor = new Label();
    protected Font fuenteLetra;
    @Getter
    protected final JFXButton btnEliminar = new JFXButton("Eliminar");
    @Getter
    protected final JFXButton btnEditar = new JFXButton("Editar");
    public CourseElement(long cursoID,String url_imagen,String tituloCurso,String descripcionCurso,String nombreProfesor) {
        this.cursoID = cursoID;
        imagenCurso.setImage(new Image(url_imagen));
        //Poner el titulo al elemento grafico del curso
        fuenteLetra = new Font("Britannic Bold",20);
        this.tituloCurso.setFont(fuenteLetra);
        this.tituloCurso.setText(tituloCurso);

        fuenteLetra = new Font("System",12);
        this.descripcionCurso.setFont(fuenteLetra);
        this.descripcionCurso.setText(descripcionCurso);

        this.nombreProfesor.setFont(fuenteLetra);
        this.nombreProfesor.setText("Imparte el curso: Prof. "+nombreProfesor);
    }
    public HBox create() {
        HBox elementoPadre = new HBox();
        VBox contenedorTexto = new VBox();
        HBox contenedorBotones = new HBox(10);

        elementoPadre.getChildren().add(this.imagenCurso);
        contenedorTexto.getChildren().add(this.tituloCurso);
        contenedorTexto.getChildren().add(this.descripcionCurso);
        contenedorTexto.getChildren().add(this.nombreProfesor);
        contenedorBotones.getChildren().add(this.btnEditar);
        contenedorBotones.getChildren().add(this.btnEliminar);

        setearEstilos(elementoPadre,contenedorBotones,contenedorTexto);
        elementoPadre.getChildren().add(contenedorTexto);
        elementoPadre.getChildren().add(contenedorBotones);
        return elementoPadre;
    }
    protected void setearEstilos(HBox elementoPadre, HBox contenedorBotones, VBox contenedorTexto) {
        byte labelHeight = 60;
        //Estilos del elemento padre (HBox root)
        elementoPadre.setPrefHeight(185);
        elementoPadre.setMaxHeight(185);
        elementoPadre.setStyle(
                "-fx-border-color: black;"+
                        "-fx-border-width: 6px;"+
                        "-fx-background-color: #3c82ab"
        );
        //Tamano del contenedor de los labels o texto
        contenedorTexto.setPrefWidth(300);
        contenedorTexto.setPadding(new Insets(0,10,0,10));
        //Estilos para el ImageView o imagen del curso
        this.imagenCurso.setFitHeight(elementoPadre.getPrefHeight());
        this.imagenCurso.setFitWidth(220);
        //Estilos para los label (descripcion y nombre profesor)
        this.descripcionCurso.setStyle("-fx-font-weight: bold");
        this.nombreProfesor.setStyle("-fx-font-weight: bold");
        this.descripcionCurso.setWrapText(true);

        //Estilos y tamano de los labels o textos
        this.tituloCurso.setPrefWidth(contenedorTexto.getPrefWidth());
        this.descripcionCurso.setPrefWidth(contenedorTexto.getPrefWidth());
        this.nombreProfesor.setPrefWidth(contenedorTexto.getPrefWidth());
        //Alto de cada label
        this.tituloCurso.setPrefHeight(labelHeight-10);
        this.descripcionCurso.setPrefHeight(labelHeight*2);
        this.nombreProfesor.setPrefHeight(labelHeight*0.42);

        contenedorBotones.setAlignment(Pos.CENTER);
       //Estilo del boton eliminar
        btnEliminar.setStyle("-fx-background-color: #d9111f; "+
                "-fx-background-radius: 10px; "+
                "-fx-text-fill: white");
        btnEliminar.setButtonType(JFXButton.ButtonType.RAISED);
        btnEliminar.setPrefWidth(140);
        btnEliminar.setAlignment(Pos.CENTER);
        btnEliminar.setOnMouseEntered(e -> btnEliminar.setCursor(Cursor.HAND));

        btnEditar.setStyle("-fx-background-color: #edbf40; "+
                "-fx-background-radius: 10px; "+
                "-fx-text-fill: black");
        btnEditar.setButtonType(JFXButton.ButtonType.RAISED);
        btnEditar.setPrefWidth(140);
        btnEditar.setAlignment(Pos.CENTER);
        btnEditar.setOnMouseEntered(e -> btnEditar.setCursor(Cursor.HAND));
    }
}