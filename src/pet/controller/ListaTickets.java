package pet.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import pet.acciones.TicketsAction;
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.vo.PeticionesVO;
import pet.vo.TicketsVO;

public class ListaTickets implements Initializable {

    @FXML
    private TableColumn<TicketsVO, String> colIdPet;
    
    @FXML
    private TableColumn<TicketsVO, String> colCodDemanda;
    
    @FXML
    private TableColumn<TicketsVO, String> idObservacion;

    @FXML
    private Button bActualizar;

    @FXML
    private TextField tBusqueda;

    @FXML
    private TableView<TicketsVO> tableTickets;

    @FXML
    private Pane principal;

    @FXML
    private TableColumn<TicketsVO, String> colDescTicket;

    @FXML
    private Button bDetalle;

    @FXML
    private Button bBorrar;

    @FXML
    private Button bBuscar;

    @FXML
    void buscar() {
    	if(null != tBusqueda && null != tBusqueda.getText() && !tBusqueda.getText().equals(Constantes.S_EMPTY)){
    		TicketsAction.buscarRegistro(tableTickets, tBusqueda);
    	}else{
    		TicketsAction.iniciar(tableTickets, codDemanda);
    	}
    	
    }

    @FXML
    void verDetalle(ActionEvent event) {

    }

    @FXML
    void actualizar(ActionEvent event) {
    	if(null != tableTickets && null != tableTickets.getSelectionModel() &&  null != tableTickets.getSelectionModel().getSelectedItem()){
    		TicketsAction action = new TicketsAction();
    		action.modificaTicket(tableTickets.getSelectionModel().getSelectedItem(), tableTickets);
    	}else{
    		MessagesBox.createAlert("Error", "Debe seleccionar un registro para modificar.");
    	}
    }

    @FXML
    void eliminar(ActionEvent event) {
    	if(null != tableTickets && null != tableTickets.getSelectionModel() &&  null != tableTickets.getSelectionModel().getSelectedItem()){
    		TicketsAction.eliminaReg(tableTickets.getSelectionModel().getSelectedItem().getIdObservacion());
    		TicketsAction.iniciar(tableTickets, codDemanda);
    	}else{
    		MessagesBox.createAlert("Error", "Debe seleccionar un registro para borrar.");
    	}
    }

    @FXML
    void buscaEnter(KeyEvent ev){
    	if(ev.getCode() == KeyCode.ENTER){
    		buscar();
    	}
    }
    
    String codDemanda;
    
    public void setDataIni(TableView<PeticionesVO> tablePeticiones){
    	codDemanda = tablePeticiones.getSelectionModel().getSelectedItem().getCodDemanda();
    	TicketsAction.iniciar(tableTickets, codDemanda);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		idObservacion.setStyle("-fx-alignment: CENTER;");
		idObservacion.setCellValueFactory(new PropertyValueFactory<TicketsVO,String>("idObservacion"));

		colIdPet.setStyle("-fx-alignment: CENTER;");
		colIdPet.setCellValueFactory(new PropertyValueFactory<TicketsVO,String>("idPeticion"));
		
		//colDescTicket.setStyle("-fx-alignment: CENTER;");
		colDescTicket.setCellValueFactory(new PropertyValueFactory<TicketsVO,String>("descTicket"));
		
		colCodDemanda.setStyle("-fx-alignment: CENTER;");
		colCodDemanda.setCellValueFactory(new PropertyValueFactory<TicketsVO,String>("codDemanda"));
				
	}

}
