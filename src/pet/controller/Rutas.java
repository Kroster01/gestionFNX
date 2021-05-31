package pet.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import pet.acciones.DatosAction;
import pet.acciones.RutasAction;
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.util.PrintLog;
import pet.vo.RutasVO;

public class Rutas implements Initializable{

	 @FXML
	    private Button bActualizarPath;

	    @FXML
	    private Button bBorrarPath;

	    @FXML
	    private TableView<RutasVO> tablePath;

	    @FXML
	    private TableColumn<RutasVO, String> colDescripcionPath;

	    @FXML
	    private Button bDetallePath;

	    @FXML
	    private Pane principal1;

	    @FXML
	    private Button bGuardarPath;

	    @FXML
	    private Button bPath;

	    @FXML
	    private TableColumn<RutasVO, String> colPath;

	    @FXML
	    private TextField path;

	    @FXML
	    private TextField tBusquedaPath;

	    @FXML
	    private TextField descripcionPath;

	    @FXML
	    private TableColumn<RutasVO, Integer> colIdRuta;

	    @FXML
	    private Button bBuscarPath;

	    @FXML
	    void guardarPath(ActionEvent event) {
	    	boolean resultado = RutasAction.guardarRegistro(Constantes.iCodZero, descripcionPath, path);
	    	if(resultado){
	    		RutasAction.iniciar(tablePath);
	    		RutasAction.resetDatos(descripcionPath, path);
	    	}else{
	    		MessagesBox.createAlert("Error", "Error al crear directorio.");
	    	}
	    }

	    @FXML
	    void abrirPath(ActionEvent event) {
	    	DatosAction.selectDirectorio(path);
	    }

	    @FXML
	    void abrePath(MouseEvent event){
	    	if(null != tablePath && null != tablePath.getSelectionModel() && null != tablePath.getSelectionModel().getSelectedItem()){
	    		if(event.getButton() == MouseButton.PRIMARY){
	    			boolean resultado = RutasAction.abrirDirectorio(tablePath, colDescripcionPath, colPath);
	        		if(!resultado){
	        			MessagesBox.createAlert("Error", "Ruta no existe.");
	        		}
	    		}
	    		

	    	}
	    }

	    @FXML
	    void buscarPath() {
	    	if(null != tBusquedaPath && null != tBusquedaPath.getText() && !tBusquedaPath.getText().equals(Constantes.S_EMPTY)){
	    		RutasAction.buscarRegistro(tablePath, tBusquedaPath);
	    	}else{
	    		RutasAction.iniciar(tablePath);
	    	}
	    }

	    @FXML
	    void verDetallePath(ActionEvent event) {

	    }

	    @FXML
	    void actualizarPath(ActionEvent event) {
	       	if(null != tablePath && null != tablePath.getSelectionModel() && null != tablePath.getSelectionModel().getSelectedItem()){
	    		try {
					// Nueva Pantalla detalle de compra
					final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/updateRuta.fxml");
					final FXMLLoader fxmlLoader = new FXMLLoader(clase);
					Parent root;
					root = (Parent)fxmlLoader.load();
					
					UpdateRuta controller = fxmlLoader.<UpdateRuta>getController();
					controller.setDataIni(tablePath);
		
					final Scene scene = new Scene(root);
					Stage stageDetalle = new Stage();
					stageDetalle.setScene(scene);
					stageDetalle.setTitle("Detalle Ruta");					
					stageDetalle.show();
				} catch (IOException e1) {
					Logger log = PrintLog.getLogger();
					FileHandler fh = PrintLog.getHandler();
					log.addHandler(fh);
					log.info(e1.getMessage());
					PrintLog.closeHandler(fh);
					e1.printStackTrace();
				}
	    	}else{
	    		MessagesBox.createAlert("Error", "Debe seleccionar registro para actualizar.");
	    	}
	    }

	    @FXML
	    void eliminarPath(ActionEvent event) {
	    	if(null != tablePath && null != tablePath.getSelectionModel() && null != tablePath.getSelectionModel().getSelectedItem()){
	    		RutasAction.prepDataBorrar(tablePath, colIdRuta);
	    		RutasAction.iniciar(tablePath);
	    	}else{
	    		MessagesBox.createAlert("Error", "Debe seleccionar registro para borrar.");
	    	}
	    }

	    @FXML
	    void buscaEnterPath(KeyEvent ev){
	    	if(ev.getCode() == KeyCode.ENTER){
	    		buscarPath();
	    	}
	    }
	
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		// Columnas de tabla de rutas
		
		colIdRuta.setStyle("-fx-alignment: CENTER;");
		colIdRuta.setCellValueFactory(new PropertyValueFactory<RutasVO,Integer>("idRuta"));
		//colPath.setStyle("-fx-alignment: CENTER;");
		colPath.setCellValueFactory(new PropertyValueFactory<RutasVO,String>("directorio"));
		
		colPath.setCellFactory(new Callback<TableColumn<RutasVO, String>, TableCell<RutasVO, String>>() {
            @Override
            public TableCell<RutasVO, String> call(TableColumn<RutasVO, String> p) {
                TableCell<RutasVO, String> cell = new TableCell<RutasVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null) {
                            setTooltip(null);
                            setText(Constantes.S_EMPTY);
                        } else {
                        	setText(item);
                            tooltip.setText(item);
                            setTooltip(tooltip);
                        }
                    }
                };
                return cell;
            }
        });
		
		colDescripcionPath.setStyle("-fx-alignment: CENTER;");
		colDescripcionPath.setCellValueFactory(new PropertyValueFactory<RutasVO,String>("descripcion"));
		colDescripcionPath.setCellFactory(new Callback<TableColumn<RutasVO, String>, TableCell<RutasVO, String>>() {
            @Override
            public TableCell<RutasVO, String> call(TableColumn<RutasVO, String> p) {
                TableCell<RutasVO, String> cell = new TableCell<RutasVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null) {
                            setTooltip(null);
                            setText(Constantes.S_EMPTY);
                        } else {
                        	setText(item);
                            tooltip.setText(item);
                            setTooltip(tooltip);
                        }
                    }
                };
                return cell;
            }
        });
		// Carga inicial de datos
		RutasAction.iniciar(tablePath);

	
		
	}

}
