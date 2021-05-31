package pet.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pet.acciones.DatosAction;
import pet.acciones.RutasAction;
import pet.util.MessagesBox;
import pet.vo.RutasVO;

public class UpdateRuta implements Initializable{
	
    @FXML
    private TextField descripcion;

    @FXML
    private TextField path;

    @FXML
    private Button bGuardar;

     @FXML
    private Button bPath;


    @FXML
    void onlyNumbers(KeyEvent ke) {
        String caracter = ke.getCharacter();
        if (!"0123456789".contains(caracter)) {
            ke.consume();
        }
    }
    
    int idRuta = 0;
    TableView<RutasVO> tableRutas;
    
    public void setDataIni(TableView<RutasVO> tableRutas){
    	
    	Tooltip tooltipCen = new Tooltip();
    	Tooltip tooltipDesc = new Tooltip();
    	
    	this.tableRutas = tableRutas;
    	RutasVO ruta = tableRutas.getSelectionModel().getSelectedItem();
    	
    	idRuta = ruta.getIdRuta();
    	descripcion.setText(ruta.getDescripcion().trim());
    	tooltipDesc.setText(ruta.getDescripcion().trim());
    	descripcion.setTooltip(tooltipDesc);
    	
    	path.setText(ruta.getDirectorio().trim());
    	tooltipCen.setText(ruta.getDirectorio().trim());
    	path.setTooltip(tooltipCen);
    	
     }

    @FXML
    void guardar(ActionEvent event) {
    	boolean resultado = RutasAction.actualizarRegistro(idRuta, descripcion, path);
    	if(resultado){
    		MessagesBox.createAlert("Exito", "Registro actualizado correctamente.");
    		RutasAction.iniciar(tableRutas);
	  	    Stage stage = (Stage) descripcion.getScene().getWindow();
	  	    stage.close();
    	}else{
    		MessagesBox.createAlert("Error", "Error al actualizar registro.");
    	}
    }
    
    @FXML
    void abrirPath(ActionEvent event) {
    	DatosAction.selectDirectorio(path);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
