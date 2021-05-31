package pet.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pet.acciones.TicketsAction;
import pet.util.MessagesBox;
import pet.vo.PeticionesVO;

public class IngresaTicket implements Initializable {

	   @FXML
	    private Pane principal;
	    
	    @FXML
	    private TextField codDemanda;

	    @FXML
	    private Button bGuardar;

	    @FXML
	    private TextField idPeticion;

	    @FXML
	    private TextField descripcionTicket;

	    @FXML
	    void onlyNumbers(KeyEvent ke) {
	        String caracter = ke.getCharacter();
	        if (!"0123456789".contains(caracter)) {
	            ke.consume();
	        }
	    }

	    @FXML
	    void guardar(ActionEvent event) {
	    	boolean result = TicketsAction.guardarRegistro(idPeticion,codDemanda, descripcionTicket);
	    	if(result){
	    		MessagesBox.createAlert("Exito", "Registro ingresado correctamente.");
		  	    Stage stage = (Stage) idPeticion.getScene().getWindow();
		  	    stage.close();
	    	}
	    }
	    
	    
	    public void setDataIni(TableView<PeticionesVO> tablePeticiones){
	    	PeticionesVO peticion = tablePeticiones.getSelectionModel().getSelectedItem();
	    	idPeticion.setText(String.valueOf(peticion.getIdPeticion()));
	    	codDemanda.setText(peticion.getCodDemanda());
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

			
		}
}
