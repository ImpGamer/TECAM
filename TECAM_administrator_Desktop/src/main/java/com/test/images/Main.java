package com.test.images;

import com.test.images.controllers.CursosController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {
	public static ConfigurableApplicationContext context;
	public static FXMLLoader fxmlLoader;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Image icon = new Image("https://i.pinimg.com/474x/0f/e9/2b/0fe92b21e51714f933f1d58a5fe66ccc.jpg");
		context = SpringApplication.run(Main.class);
		fxmlLoader = new FXMLLoader(Main.class.getResource("/Cursos.fxml"));
		fxmlLoader.setControllerFactory(context::getBean);
		Scene scene = new Scene(fxmlLoader.load());
		stage.getIcons().add(icon);
		stage.setTitle("Administracion Cursos TECNAM");
		CursosController controller = fxmlLoader.getController();
		controller.setHostService(getHostServices());
		stage.setScene(scene);
		stage.show();
	}
}