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
import pet.vo.TicketsVO;

public class UpdateTicket implements Initializable {

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

	    String idObservacion;
	    @FXML
	    void guardar(ActionEvent event) {
	    	boolean result = TicketsAction.actualizarRegistro(idObservacion, idPeticion, codDemanda, descripcionTicket);
	    	if(result){
	    		TicketsAction.iniciar(tableTicket, codDemanda.getText());
	    		MessagesBox.createAlert("Exito", "Registro actualizado correctamente.");
		  	    Stage stage = (Stage) idPeticion.getScene().getWindow();
		  	    stage.close();
	    	}
	    }
	    
	    TableView<TicketsVO> tableTicket;
	    public void setDataIni(TicketsVO ticket, TableView<TicketsVO> tableTicket){
	    	this.tableTicket = tableTicket;
	    	idObservacion = ticket.getIdObservacion();
	    	idPeticion.setText(String.valueOf(ticket.getIdPeticion()));
	    	codDemanda.setText(ticket.getCodDemanda());

	    	descripcionTicket.setText(ticket.getDescTicket());

	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

			
		}
}
