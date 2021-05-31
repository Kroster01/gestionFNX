package pet.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MessagesBox {
	
	public static void createAlert(String title, String message){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public static boolean createConfirm(String title, String message){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		//alert.setHeaderText("Look, a Confirmation Dialog");
		alert.setContentText(message);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return true;
		} else {
		    return false;
		}
	}
}
