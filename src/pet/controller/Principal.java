package pet.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
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
import pet.acciones.MenusAction;
import pet.acciones.RutasAction;
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.util.PrintLog;
import pet.vo.CompFXEjecucionVO;
import pet.vo.EstadosVO;
import pet.vo.PeticionesVO;
import pet.vo.RecuentoVO;
import pet.vo.RutasVO;
import pet.vo.TipoPeticionVO;

public class Principal implements Initializable{

	@FXML
	private Button bPath;
	
	@FXML
	private Button bPathCentro;
	
	@FXML
	private Button bPathSolution;
	
	@FXML
	private Button bPathRTC;
	
    @FXML
    private TextField descripcion;
    
    @FXML
    private TextField descripcionPath;

    @FXML
    private TableColumn<PeticionesVO, Integer> colIdPet;

    @FXML
    private TextField repCentro;
    
    @FXML
    private TextField path;
    
    @FXML
    private TextField tPeticionesHoy;
    
    @FXML
    private TextField tPeticiones3;
    
    @FXML
    private TextField tPeticiones5;
    
    @FXML
    private TextField tAplazadas;

    @FXML
    private TextField tEntregadas;
    
    @FXML
    private TextField tEjecucion;
    
    @FXML
    private TableColumn<PeticionesVO, String> colCodDemanda;


    @FXML
    private TextField repRTC;

    @FXML
    private Pane principal;

    @FXML
    private TableColumn<PeticionesVO, String> colDSolution;
    
    @FXML
    private TableColumn<PeticionesVO, String> colEstado;

    @FXML
    private TableColumn<PeticionesVO, String> colDescripcion;
    
    @FXML
    private TableColumn<RutasVO, String> colDescripcionPath;
    
    @FXML
    private TableColumn<RutasVO, String> colPath;

    @FXML
    private TableColumn<PeticionesVO, String> colResponsable;
    
    @FXML
    private TableColumn<RutasVO, Integer> colIdRuta;

    @FXML
    private Button bGuardar;
    
    @FXML
    private Button bGuardarPath;

    @FXML
    private TableView<PeticionesVO> tablePeticiones;
    
    @FXML
    private TableView<RutasVO> tablePath;

    @FXML
    private Button bBorrar;
    
    @FXML
    private Button bBorrarPath;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private TableColumn<PeticionesVO, String> colDCentro;

    @FXML
    private TextField idPeticion;

    @FXML
    private TextField responsable;

    @FXML
    private Button bActualizar;
    
    @FXML
    private Button bActualizarPath;

    @FXML
    private TextField tBusqueda;
    
    @FXML
    private TextField tBusquedaPath;

    @FXML
    private TableColumn<PeticionesVO, String> colTipPet;

    @FXML
    private TextField idDemanda;

    @FXML
    private DatePicker fechaFin;

    @FXML
    private TextField esfuerzo;

    @FXML
    private Button bDetalle;
    
    @FXML
    private Button bDetallePath;

    @FXML
    private ComboBox<TipoPeticionVO> tipoPeticion;
    
    @FXML
    private ComboBox<EstadosVO> estado;

    @FXML
    private TableColumn<PeticionesVO, String> colDRTC;

    @FXML
    private TextField repSolution;

    @FXML
    private Button bBuscar;
    
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
    void guardar(ActionEvent event) {
    	CompFXEjecucionVO componentes = new CompFXEjecucionVO();
    	boolean resultado = DatosAction.guardarRegistro(componentes);
    	if(resultado){
    		DatosAction.iniciar(tablePeticiones, recuentos);
    		// Confirma si se quiere crear línea base
    		if (MessagesBox.createConfirm("Consulta", "¿Desear crear línea base de centro de la petición?")) {
    			boolean result = DatosAction.creaLineaBaseCentro(idPeticion, tipoPeticion.getSelectionModel().getSelectedItem(), repCentro);
    			if(result){
    				MessagesBox.createAlert("Exito", "Directorio creado correctamente.");
    			}else{
    				MessagesBox.createAlert("Error", "Error al crear directorio.");
    			}
    		}
    		
    		if(null != repSolution && null != repSolution.getText() && !repSolution.getText().equals(Constantes.S_EMPTY)){
        		// Confirma si se quiere línea base
        		if (MessagesBox.createConfirm("Consulta", "¿Desear crear línea base de solution de la petición?")) {
        			boolean result = DatosAction.creaLineaBaseSolution(idDemanda, repSolution);
        			if(result){
        				MessagesBox.createAlert("Exito", "Directorio creado correctamente.");
        			}else{
        				MessagesBox.createAlert("Error", "Error al crear directorio.");
        			}
        		}
    		}
    		//DatosAction.resetDatos(idPeticion, idDemanda, tipoPeticion, descripcion, repCentro, repSolution, repRTC, responsable, esfuerzo, fechaInicio, fechaFin);
    	}
    }
    
    @FXML
    void onlyNumbers(KeyEvent ke) {
        String caracter = ke.getCharacter();
        if (!"0123456789".contains(caracter)) {
            ke.consume();
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
    void buscar() {
    	if(null != tBusqueda && null != tBusqueda.getText() && !tBusqueda.getText().equals(Constantes.S_EMPTY)){
    		DatosAction.buscarRegistro(tablePeticiones, tBusqueda);
    	}else{
    		DatosAction.iniciar(tablePeticiones, recuentos);
    	}
    }

    @FXML
    void verDetalle(ActionEvent event) {
    	if(null != tablePeticiones && null != tablePeticiones.getSelectionModel() && null != tablePeticiones.getSelectionModel().getSelectedItem()){
    		DatosAction datosAction = new DatosAction();
    		datosAction.abrirDetalle(tablePeticiones);
    	}else{
    		MessagesBox.createAlert("Error", "Debe seleccionar registro para ver el detalle.");
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
    void actualizar(ActionEvent event) {
    	if(null != tablePeticiones && null != tablePeticiones.getSelectionModel() && null != tablePeticiones.getSelectionModel().getSelectedItem()){
    		try {
				// Nueva Pantalla detalle de compra
				final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/updatePeticion.fxml");
				final FXMLLoader fxmlLoader = new FXMLLoader(clase);
				Parent root;
				root = (Parent)fxmlLoader.load();
				
				UpdatePeticion controller = fxmlLoader.<UpdatePeticion>getController();
				controller.setDataIni(tablePeticiones, recuentos);
	
				final Scene scene = new Scene(root);
				Stage stageDetalle = new Stage();
				stageDetalle.setScene(scene);
				stageDetalle.setTitle("Detalle Petición");					
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
    void eliminar(ActionEvent event) {
    	if(null != tablePeticiones && null != tablePeticiones.getSelectionModel() && null != tablePeticiones.getSelectionModel().getSelectedItem()){
    		DatosAction.prepDataBorrar(tablePeticiones, colIdPet);
    		DatosAction.iniciar(tablePeticiones, recuentos);
    	}else{
    		MessagesBox.createAlert("Error", "Debe seleccionar registro para borrar.");
    	}
    }
    
    @FXML
    void entregar(ActionEvent event) {
    	if(null != tablePeticiones && null != tablePeticiones.getSelectionModel() && null != tablePeticiones.getSelectionModel().getSelectedItem()){
    		DatosAction.prepDataEntrega(tablePeticiones, colIdPet);
    		DatosAction.iniciar(tablePeticiones, recuentos);
    	}else{
    		MessagesBox.createAlert("Error", "Debe seleccionar registro para borrar.");
    	}
    }
    
    @FXML
    void abrirPath(ActionEvent event) {
    	DatosAction.selectDirectorio(path);
    }
    
    @FXML
    void abrirPathCentro(ActionEvent event) {
    	DatosAction.selectDirectorio(repCentro);
    }
    
    @FXML
    void abrirPathSolution(ActionEvent event) {
    	DatosAction.selectDirectorio(repSolution);
    }
    
    @FXML
    void abrirPathRTC(ActionEvent event) {
    	DatosAction.selectDirectorio(repRTC);
    }
    
    @FXML
    void abreDirectorio(MouseEvent event){
    	if(null != tablePeticiones && null != tablePeticiones.getSelectionModel() && null != tablePeticiones.getSelectionModel().getSelectedItem()){
    		if(event.getButton() == MouseButton.PRIMARY){
    			boolean resultado = DatosAction.abrirDirectorio(tablePeticiones, colDCentro, colDSolution, colDRTC);
        		if(!resultado){
        			MessagesBox.createAlert("Error", "Ruta no existe.");
        		}
    		}
    		

    	}
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
    void buscaEnter(KeyEvent ev){
    	if(ev.getCode() == KeyCode.ENTER){
    		buscar();
    	}
    }
    
    @FXML
    void buscaEnterPath(KeyEvent ev){
    	if(ev.getCode() == KeyCode.ENTER){
    		buscarPath();
    	}
    }

    ContextMenu menu;
    MenuItem itemCopiar;
    MenuItem itemAsociarTicket;
    MenuItem itemVerTickets;
    RecuentoVO recuentos;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		recuentos = new RecuentoVO();
		recuentos.settAplazadas(tAplazadas);
		recuentos.settEntregadas(tEntregadas);
		recuentos.settPeticiones3(tPeticiones3);
		recuentos.settPeticiones5(tPeticiones5);
		recuentos.settPeticionesHoy(tPeticionesHoy);
		recuentos.settEjecucion(tEjecucion);
		
		
//		tAplazadas.setStyle("-fx-alignment: CENTER;");
//		tEntregadas.setStyle("-fx-alignment: CENTER;");
//		tPeticiones3.setStyle("-fx-alignment: CENTER;");
//		tPeticiones5.setStyle("-fx-alignment: CENTER;");
//		tPeticionesHoy.setStyle("-fx-alignment: CENTER;");
		
		colIdPet.setStyle("-fx-alignment: CENTER;");
		colIdPet.setCellValueFactory(new PropertyValueFactory<PeticionesVO,Integer>("idPeticion"));
		colCodDemanda.setStyle("-fx-alignment: CENTER;");
		colCodDemanda.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("codDemanda"));
		colDSolution.setStyle("-fx-alignment: CENTER;");
		colDSolution.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("dirSolution"));
		colDSolution.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell cell = new TableCell<PeticionesVO, String>() {
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
		
		colDescripcion.setStyle("-fx-alignment: CENTER;");
		colDescripcion.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("descripcion"));
		colDescripcion.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell cell = new TableCell<PeticionesVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null) {
                            setTooltip(null);
                            setText(Constantes.S_EMPTY);
                            getTableRow().setStyle("-fx-alignment: CENTER;");
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
		
		colResponsable.setStyle("-fx-alignment: CENTER;");
		colResponsable.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("responsable"));
		colDCentro.setStyle("-fx-alignment: CENTER;");
		colDCentro.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("dirCentro"));
		
		colDCentro.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell cell = new TableCell<PeticionesVO, String>() {
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
		
		colTipPet.setStyle("-fx-alignment: CENTER;");
		// -fx-background-color: GREEN;
		colTipPet.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell cell = new TableCell<PeticionesVO, String>() {
                	//private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null) {
                            setTooltip(null);
                            setText(Constantes.S_EMPTY);
                            setStyle("-fx-alignment: CENTER;");
                        } else {
                        	setText(item);
//                            tooltip.setText(item);
//                            setTooltip(tooltip);
                        	if(item.equals(Constantes.S_TIP_PET_ME)){
                        		//getTableRow().setStyle("-fx-alignment: CENTER; -fx-background-color:  B9D305");
                        		setStyle("-fx-alignment: CENTER; -fx-background-color:  B9D305");
                        	}else if(item.equals(Constantes.S_TIP_PET_SO)){
                        		//getTableRow().setStyle("-fx-alignment: CENTER; -fx-background-color: FA8C0F");
                        		setStyle("-fx-alignment: CENTER; -fx-background-color: FA8C0F");
                        	}else{
                        		//getTableRow().setStyle("-fx-alignment: CENTER;");
                        		setStyle("-fx-alignment: CENTER;");
                        	}
                        }
                    }
                };
                
                return cell;
            }
        });
		colTipPet.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("tipoPeticion"));
		
		colDRTC.setStyle("-fx-alignment: CENTER;");
		colDRTC.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("dirRTC"));
		
		// Columnas de tabla de rutas
		
		colIdRuta.setStyle("-fx-alignment: CENTER;");
		colIdRuta.setCellValueFactory(new PropertyValueFactory<RutasVO,Integer>("idRuta"));
		//colPath.setStyle("-fx-alignment: CENTER;");
		colPath.setCellValueFactory(new PropertyValueFactory<RutasVO,String>("directorio"));
		
		colPath.setCellFactory(new Callback<TableColumn<RutasVO, String>, TableCell<RutasVO, String>>() {
            @Override
            public TableCell<RutasVO, String> call(TableColumn<RutasVO, String> p) {
                TableCell cell = new TableCell<PeticionesVO, String>() {
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
                TableCell cell = new TableCell<RutasVO, String>() {
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
		
		tipoPeticion.setCellFactory(new Callback<ListView<TipoPeticionVO>, ListCell<TipoPeticionVO>>() {
	                @Override public ListCell<TipoPeticionVO> call(ListView<TipoPeticionVO> param) {
	                    final ListCell<TipoPeticionVO> cell = new ListCell<TipoPeticionVO>() {
    
	                        @Override public void updateItem(TipoPeticionVO item, boolean empty) {
	                                super.updateItem(item, empty);
	                                if (null != item) {
	                                    setText(item.getTipo());    
	                                    if (item.getTipo().equals(Constantes.S_TIP_PET_ME)) {
	                                    	//setStyle("-fx-alignment: CENTER; -fx-background-color:  B9D305");
	                                        //setTextFill(Color.RED);
	                                    }
	                                    else if (item.getTipo().equals(Constantes.S_TIP_PET_SO)){
	                                    	//setStyle("-fx-alignment: CENTER; -fx-background-color: FA8C0F");
	                                        //setTextFill(Color.GREEN);
	                                    }
	                                }
	                                else {
	                                	setText(Constantes.S_EMPTY);
	                                }
	                            }
	                };
	                return cell;
	            }
	        });
		
		colEstado.setStyle("-fx-alignment: CENTER;");
		colEstado.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("estado"));
		colEstado.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell cell = new TableCell<RutasVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null) {
                            setTooltip(null);
                            setText(Constantes.S_EMPTY);
                            setStyle("-fx-alignment: CENTER;");
                        } else {
                        	setText(item);
                            tooltip.setText(item);
                            setTooltip(tooltip);
                           
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
	                   		 Date fechaActual = new Date();
	                   		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	                   		 String fechaSistema = formateador.format(fechaActual);
	                   		 if(null != peticion && null != peticion.getFechaFin() && null != peticion.getEstado() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)){
	                   			 String fechaPet = peticion.getFechaFin();
	                   			 String estadoPet = peticion.getEstado();
	                   			 
	                   			 try {
									if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
										getTableRow().setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
										 getTableRow().setStyle("-fx-alignment: CENTER; -fx-background-color:  gold");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
										 getTableRow().setStyle("-fx-alignment: CENTER; -fx-background-color:  FF9933");
									 }else if(estadoPet.equals(Constantes.sAplazada)){
										 getTableRow().setStyle("-fx-alignment: CENTER; -fx-background-color:  A9A9A9");		 
									 }else{
										getTableRow().setStyle("-fx-alignment: CENTER;");
									 }
								} catch (ParseException e) {
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									getTableRow().setStyle("-fx-alignment: CENTER;");
								}
	                   		 }else{
	                   			getTableRow().setStyle("-fx-alignment: CENTER;");
	                   		 }
                            
                            
                            
                        }
                    }
                };
                return cell;
            }
        });
		// Carga inicial de datos
		DatosAction.iniciar(tablePeticiones, recuentos);
		DatosAction.cargaComboTipos(tipoPeticion);
		DatosAction.cargaComboEstado(estado);
		RutasAction.iniciar(tablePath);
		menu = new ContextMenu();
		MenusAction.creaMenuCopiar(tablePeticiones, itemCopiar, menu);
		MenusAction.creaMenuAsociarTicket(tablePeticiones, itemAsociarTicket, menu);
		MenusAction.creaMenuVerTicket(tablePeticiones, itemVerTickets, menu);
	}

}
