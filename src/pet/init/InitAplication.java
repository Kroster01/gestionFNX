package pet.init;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class InitAplication extends Application{

	public static void main(String[] arg0){
		launch(arg0);
	}
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		//final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/principal.fxml");
		final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/principal_menu.fxml");

		final FXMLLoader fxmlLoader = new FXMLLoader(clase);
		final Parent root =(Parent)fxmlLoader.load();
		
		final Scene scene = new Scene(root);
		arg0.setScene(scene);
		//arg0.initStyle(StageStyle.TRANSPARENT);
		arg0.setTitle("PetScope");
		arg0.getIcons().add(new Image("pet/images/128.png"));
		arg0.setResizable(false);
		arg0.show();
	}
}
