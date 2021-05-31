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
import javafx.scene.control.CheckBox;
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
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.util.PrintLog;
import pet.vo.CompFXEjecucionVO;
import pet.vo.EstadosVO;
import pet.vo.EtapaPPMVO;
import pet.vo.PeticionesVO;
import pet.vo.RecuentoVO;
import pet.vo.TipoPeticionVO;

public class Ejecucion implements Initializable{

   @FXML
    private CheckBox cModelo2;
	
	@FXML
    private TextField descripcion;
	
    @FXML
    private ComboBox<EstadosVO> estado;
    
    @FXML
    private ComboBox<EtapaPPMVO> etapaPeticion;

    @FXML
    private TableColumn<PeticionesVO, Integer> colIdPet;

    @FXML
    private Button bPathRTC;

    @FXML
    private TableColumn<PeticionesVO, String> colEstado;

    @FXML
    private Button bEntregar;

    @FXML
    private TableColumn<PeticionesVO, String> colDSolution;
    
    @FXML
    private TableColumn<PeticionesVO, String> colIncidencias;

    @FXML
    private TextField responsableStgo;

    @FXML
    private TableColumn<PeticionesVO, String> colDescripcion;

    @FXML
    private Button bGuardar;

    @FXML
    private TableColumn<PeticionesVO, String> colDCentro;
    
    @FXML
    private TableColumn<PeticionesVO, String> colDRtcOsi;

    @FXML
    private TableColumn<PeticionesVO, String> colDRtcPap;
    
    @FXML
    private TextField responsableCentro;

    @FXML
    private TextField tAplazadas;

    @FXML
    private Button bActualizar;

    @FXML
    private TableColumn<PeticionesVO, String> colTipPet;

    @FXML
    private DatePicker fechaFin;

    @FXML
    private TextField tPeticiones3;

    @FXML
    private TableColumn<PeticionesVO, String> colRespStgo;

    @FXML
    private TextField tPeticiones5;

    @FXML
    private TextField repRtcOsi;

    @FXML
    private Button bBuscar;

    @FXML
    private TextField repCentro;

    @FXML
    private TextField tPeticionesHoy;

    @FXML
    private TableColumn<PeticionesVO, String> colCodDemanda;

    @FXML
    private TextField incidencias;

    @FXML
    private DatePicker fechaKiuwan;

    @FXML
    private TextField repRtcPap;

    @FXML
    private Pane principal;

    @FXML
    private TextField dudas;

    @FXML
    private TableColumn<PeticionesVO, String> colResponsableCentro;

    @FXML
    private TableView<PeticionesVO> tablePeticiones;

    @FXML
    private Button bBorrar;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private TableColumn<PeticionesVO, String> colPPMEtapa;

    @FXML
    private TableColumn<PeticionesVO, String> colEtapaPeticion;
    
    @FXML
    private DatePicker ppmFecha;

    @FXML
    private TableColumn<PeticionesVO, String> colPPMFecha;
    
    @FXML
    private TableColumn<PeticionesVO, String> colKiuwan;

    @FXML
    private TableColumn<PeticionesVO, String> colRespCentro;

    @FXML
    private TextField tEjecucion;

    @FXML
    private TextField idPeticion;

    @FXML
    private TextField ncs;

    @FXML
    private TableColumn<PeticionesVO, String> colNCs;

    @FXML
    private TextField tBusqueda;

    @FXML
    private TextField tEntregadas;

    @FXML
    private TextField idDemanda;

    @FXML
    private TextField esfuerzo;

    @FXML
    private ComboBox<EtapaPPMVO> etapaPPM;

    @FXML
    private TableColumn<PeticionesVO, String> colDudas;

    @FXML
    private Button bPathSolution;

    @FXML
    private Button bDetalle;

    @FXML
    private ComboBox<TipoPeticionVO> tipoPeticion;

    @FXML
    private Button bPathCentro;

	    @FXML
	    void onlyNumbers(KeyEvent ke) {
	        String caracter = ke.getCharacter();
	        if (!"0123456789".contains(caracter)) {
	            ke.consume();
	        }
	    }

	    @FXML
	    void guardar(ActionEvent event) {
	    	CompFXEjecucionVO componentes = new CompFXEjecucionVO();
	    	componentes.setIdPeticion(idPeticion);
	    	componentes.setIdDemanda(idDemanda);
	    	componentes.setTipoPeticion(tipoPeticion);
	    	componentes.setDescripcion(descripcion);
	    	componentes.setRepCentro(repCentro);
	    	componentes.setRepSolution(repRtcOsi);
	    	componentes.setRepRTC(repRtcPap);
	    	componentes.setResponsableCentro(responsableCentro);
	    	componentes.setEsfuerzo(esfuerzo);
	    	componentes.setFechaInicio(fechaInicio);
	    	componentes.setFechaFin(fechaFin);
	    	componentes.setEstado(estado);
	    	
	    	componentes.setEtapaPPM(etapaPPM);
	    	componentes.setResponsableStgo(responsableStgo);
	    	componentes.setIncidencias(incidencias);
	    	componentes.setFechaKiuwan(fechaKiuwan);
	    	componentes.setPpmFecha(ppmFecha);
	    	componentes.setNcs(ncs);
	    	componentes.setDudas(dudas);
	    	componentes.setEtapaPeticion(etapaPeticion);
	    	componentes.setcModelo2(cModelo2);
	    	
	    	boolean resultado = DatosAction.guardarRegistro(componentes);
	    	if(resultado){
	    		MessagesBox.createAlert("Exito", "Petición ingresada correctamente.");
	    		DatosAction.iniciar(tablePeticiones, recuentos);	    		
	    		if(cModelo2.isSelected()){
	        		// Confirma si se quiere línea base
	        		if (MessagesBox.createConfirm("Consulta", "¿Desear crear línea base de solution de la petición?")) {
	        			boolean result = DatosAction.creaLineaBaseSolution(idDemanda, repCentro);
	        			if(result){
	        				MessagesBox.createAlert("Exito", "Directorio creado correctamente.");
	        			}else{
	        				MessagesBox.createAlert("Error", "Error al crear directorio.");
	        			}
	        		}
	    		}else{
		    		// Confirma si se quiere crear línea base
		    		if (MessagesBox.createConfirm("Consulta", "¿Desear crear línea base de centro de la petición?")) {
		    			boolean result = DatosAction.creaLineaBaseCentro(idPeticion, tipoPeticion.getSelectionModel().getSelectedItem(), repCentro);
		    			if(result){
		    				MessagesBox.createAlert("Exito", "Directorio creado correctamente.");
		    			}else{
		    				MessagesBox.createAlert("Error", "Error al crear directorio.");
		    			}
		    		}
	    		}
	    		DatosAction.resetDatos(componentes);
	    	}else{
	    		MessagesBox.createAlert("Error", "Error al ingresar petición.");
	    	}
	    }

	    @FXML
	    void abrirPathCentro(ActionEvent event) {
	    	DatosAction.selectDirectorio(repCentro);
	    }

	    @FXML
	    void abrirPathSolution(ActionEvent event) {
	    	DatosAction.selectDirectorio(repRtcOsi);
	    }

	    @FXML
	    void abrirPathRTC(ActionEvent event) {
	    	DatosAction.selectDirectorio(repRtcPap);
	    }

	    @FXML
	    void abreDirectorio(MouseEvent event){
	    	if(null != tablePeticiones && null != tablePeticiones.getSelectionModel() && null != tablePeticiones.getSelectionModel().getSelectedItem()){
	    		if(event.getButton() == MouseButton.PRIMARY){
	    			boolean resultado = DatosAction.abrirDirectorioEjec(tablePeticiones, colDCentro, colDRtcOsi, colDRtcPap);
	    			//DatosAction.iniciar(tablePeticiones, recuentos);
	        		if(!resultado){
	        			MessagesBox.createAlert("Error", "Ruta no existe.");
	        		}
	    		}
	    		

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
	    void eliminar(ActionEvent event) {
	    	if(MessagesBox.createConfirm("Consulta", "¿Desea eliminar la petición?")){
		    	if(null != tablePeticiones && null != tablePeticiones.getSelectionModel() && null != tablePeticiones.getSelectionModel().getSelectedItem()){
		    		DatosAction.prepDataBorrar(tablePeticiones, colIdPet);
		    		DatosAction.iniciar(tablePeticiones, recuentos);
		    	}else{
		    		MessagesBox.createAlert("Error", "Debe seleccionar registro para borrar.");
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
	    void entregar(ActionEvent event) {
	    	if(MessagesBox.createConfirm("Consulta", "¿Desea entregar la petición?")){
		    	if(null != tablePeticiones && null != tablePeticiones.getSelectionModel() && null != tablePeticiones.getSelectionModel().getSelectedItem()){
		    		DatosAction.prepDataEntrega(tablePeticiones, colIdPet);
		    		DatosAction.iniciar(tablePeticiones, recuentos);
		    	}else{
		    		MessagesBox.createAlert("Error", "Debe seleccionar registro para borrar.");
		    	}
	    	}
	    }
	
	    ContextMenu menu;
	    MenuItem itemCopiar;
	    MenuItem itemAsociarTicket;
	    MenuItem itemVerTickets;
	    RecuentoVO recuentos;
	@Override
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
		colIdPet.setCellFactory(new Callback<TableColumn<PeticionesVO, Integer>, TableCell<PeticionesVO, Integer>>() {
            @Override
            public TableCell<PeticionesVO, Integer> call(TableColumn<PeticionesVO, Integer> p) {
                TableCell<PeticionesVO, Integer> cell = new TableCell<PeticionesVO, Integer>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null) {
                            setTooltip(null);
                            setText(Constantes.S_EMPTY);
                            setStyle("-fx-alignment: CENTER;");
                        } else {
                        	setText(String.valueOf(item));
                            tooltip.setText(String.valueOf(item));
                            setTooltip(tooltip);
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
	                   		 Date fechaActual = new Date();
	                   		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	                   		 String fechaSistema = formateador.format(fechaActual);
	                   		 if(null != peticion && null != peticion.getFechaFin() && null != peticion.getEstado() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)){
	                   			 String fechaPet = peticion.getFechaFin();
	                   			 String estadoPet = peticion.getEstado();
	                   			 
	                   			 try {
									if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
										setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  gold");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  FF9933");
									 }else if(estadoPet.equals(Constantes.sAplazada)){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  A9A9A9");		 
									 }else{
										setStyle("-fx-alignment: CENTER;");
									 }
								} catch (ParseException e) {
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									setStyle("-fx-alignment: CENTER;");
								}
	                   		 }
                        }
                    }
                };
                return cell;
            }
        });
		
		
		colCodDemanda.setStyle("-fx-alignment: CENTER;");
		colCodDemanda.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("codDemanda"));
		colCodDemanda.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
	                   		 Date fechaActual = new Date();
	                   		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	                   		 String fechaSistema = formateador.format(fechaActual);
	                   		 if(null != peticion && null != peticion.getFechaFin() && null != peticion.getEstado() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)){
	                   			 String fechaPet = peticion.getFechaFin();
	                   			 String estadoPet = peticion.getEstado();
	                   			 
	                   			 try {
									if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
										setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  gold");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  FF9933");
									 }else if(estadoPet.equals(Constantes.sAplazada)){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  A9A9A9");		 
									 }else{
										setStyle("-fx-alignment: CENTER;");
									 }
								} catch (ParseException e) {
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									setStyle("-fx-alignment: CENTER;");
								}
	                   		 }
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
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
	                   		 Date fechaActual = new Date();
	                   		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	                   		 String fechaSistema = formateador.format(fechaActual);
	                   		 if(null != peticion && null != peticion.getFechaFin() && null != peticion.getEstado() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)){
	                   			 String fechaPet = peticion.getFechaFin();
	                   			 String estadoPet = peticion.getEstado();
	                   			 
	                   			 try {
									if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
										setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  gold");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  FF9933");
									 }else if(estadoPet.equals(Constantes.sAplazada)){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  A9A9A9");		 
									 }else{
										setStyle("-fx-alignment: CENTER;");
									 }
								} catch (ParseException e) {
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									setStyle("-fx-alignment: CENTER;");
								}
	                   		 }
                        }
                    }
                };
                return cell;
            }
        });
		
		colRespCentro.setStyle("-fx-alignment: CENTER;");
		colRespCentro.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("responsable"));
		colRespCentro.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
	                   		 Date fechaActual = new Date();
	                   		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	                   		 String fechaSistema = formateador.format(fechaActual);
	                   		 if(null != peticion && null != peticion.getFechaFin() && null != peticion.getEstado() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)){
	                   			 String fechaPet = peticion.getFechaFin();
	                   			 String estadoPet = peticion.getEstado();
	                   			 
	                   			 try {
									if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
										setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  gold");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  FF9933");
									 }else if(estadoPet.equals(Constantes.sAplazada)){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  A9A9A9");		 
									 }else{
										setStyle("-fx-alignment: CENTER;");
									 }
								} catch (ParseException e) {
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									setStyle("-fx-alignment: CENTER;");
								}
	                   		 }
                        }
                    }
                };
                return cell;
            }
        });
		
		colDCentro.setStyle("-fx-alignment: CENTER;");
		colDCentro.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("dirCentro"));
		colDCentro.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
	                   		 Date fechaActual = new Date();
	                   		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	                   		 String fechaSistema = formateador.format(fechaActual);
	                   		 if(null != peticion && null != peticion.getFechaFin() && null != peticion.getEstado() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)){
	                   			 String fechaPet = peticion.getFechaFin();
	                   			 String estadoPet = peticion.getEstado();
	                   			 
	                   			 try {
									if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
										setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  gold");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  FF9933");
									 }else if(estadoPet.equals(Constantes.sAplazada)){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  A9A9A9");		 
									 }else{
										setStyle("-fx-alignment: CENTER;");
									 }
								} catch (ParseException e) {
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									setStyle("-fx-alignment: CENTER;");
								}
	                   		 }
                        }
                    }
                };
                return cell;
            }
        });
		
		colDRtcPap.setStyle("-fx-alignment: CENTER;");
		colDRtcPap.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("dirRtcPap"));
		colDRtcPap.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
	                   		 Date fechaActual = new Date();
	                   		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	                   		 String fechaSistema = formateador.format(fechaActual);
	                   		 if(null != peticion && null != peticion.getFechaFin() && null != peticion.getEstado() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)){
	                   			 String fechaPet = peticion.getFechaFin();
	                   			 String estadoPet = peticion.getEstado();
	                   			 
	                   			 try {
									if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
										setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  gold");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  FF9933");
									 }else if(estadoPet.equals(Constantes.sAplazada)){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  A9A9A9");		 
									 }else{
										setStyle("-fx-alignment: CENTER;");
									 }
								} catch (ParseException e) {
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									setStyle("-fx-alignment: CENTER;");
								}
	                   		 }
                        }
                    }
                };
                return cell;
            }
        });
		
		colDRtcOsi.setStyle("-fx-alignment: CENTER;");
		colDRtcOsi.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("dirRtcOsi"));
		colDRtcOsi.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
	                   		 Date fechaActual = new Date();
	                   		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	                   		 String fechaSistema = formateador.format(fechaActual);
	                   		 if(null != peticion && null != peticion.getFechaFin() && null != peticion.getEstado() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)){
	                   			 String fechaPet = peticion.getFechaFin();
	                   			 String estadoPet = peticion.getEstado();
	                   			 
	                   			 try {
									if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
										setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  gold");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  FF9933");
									 }else if(estadoPet.equals(Constantes.sAplazada)){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  A9A9A9");		 
									 }else{
										setStyle("-fx-alignment: CENTER;");
									 }
								} catch (ParseException e) {
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									setStyle("-fx-alignment: CENTER;");
								}
	                   		 }
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
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                        	if(DatosAction.tipoPeticion(item)){
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
				
		tipoPeticion.setCellFactory(new Callback<ListView<TipoPeticionVO>, ListCell<TipoPeticionVO>>() {
	                @Override public ListCell<TipoPeticionVO> call(ListView<TipoPeticionVO> param) {
	                    final ListCell<TipoPeticionVO> cell = new ListCell<TipoPeticionVO>() {
    
	                        @Override public void updateItem(TipoPeticionVO item, boolean empty) {
	                                super.updateItem(item, empty);
	                                if (null != item) {
	                                    setText(item.getTipo());
	                                }else {
	                                	setText(Constantes.S_EMPTY);
	                                }
	                            }
	                };
	                return cell;
	            }
	        });
//		
		colEstado.setStyle("-fx-alignment: CENTER;");
		colEstado.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("estado"));
		colEstado.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
	                   		 Date fechaActual = new Date();
	                   		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	                   		 String fechaSistema = formateador.format(fechaActual);
	                   		 if(null != peticion && null != peticion.getFechaFin() && null != peticion.getEstado() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)){
	                   			 String fechaPet = peticion.getFechaFin();
	                   			 String estadoPet = peticion.getEstado();
	                   			 
	                   			 try {
									if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
										setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  gold");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  FF9933");
									 }else if(estadoPet.equals(Constantes.sAplazada)){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  A9A9A9");		 
									 }else{
										setStyle("-fx-alignment: CENTER;");
									 }
								} catch (ParseException e) {
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									setStyle("-fx-alignment: CENTER;");
								}
	                   		 }
                        }
                    }
                };
                return cell;
            }
        });
		
		colEtapaPeticion.setStyle("-fx-alignment: CENTER;");
		colEtapaPeticion.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("etapaPeticion"));
		colEtapaPeticion.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
	                   		 Date fechaActual = new Date();
	                   		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	                   		 String fechaSistema = formateador.format(fechaActual);
	                   		 if(null != peticion && null != peticion.getFechaFin() && null != peticion.getEstado() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)){
	                   			 String fechaPet = peticion.getFechaFin();
	                   			 String estadoPet = peticion.getEstado();
	                   			 
	                   			 try {
									if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
										setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  gold");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  FF9933");
									 }else if(estadoPet.equals(Constantes.sAplazada)){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  A9A9A9");		 
									 }else{
										setStyle("-fx-alignment: CENTER;");
									 }
								} catch (ParseException e) {
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									setStyle("-fx-alignment: CENTER;");
								}
	                   		 }
                        }
                    }
                };
                return cell;
            }
        });
		
		colPPMEtapa.setStyle("-fx-alignment: CENTER;");
		colPPMEtapa.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("etapaPPM"));	
		colPPMEtapa.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();

							if(DatosAction.validaCriterioPPM(peticion)){
								Date fechaActual = new Date(); 
								try {
									if(DatosAction.cumplePPM(peticion, fechaActual)){
										// Color Verde
										setStyle("-fx-alignment: CENTER; -fx-background-color:  B9D305");
									}else{
										// Color rojo
										setStyle("-fx-alignment: CENTER; -fx-background-color: d31f05");
									}
								} catch (ParseException e) {
									e.printStackTrace();
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									getTableRow().setStyle("-fx-alignment: CENTER;");
								}
							}
                        }
                    }
                };
                return cell;
            }
        });
		
		colPPMFecha.setStyle("-fx-alignment: CENTER;");
		colPPMFecha.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("ppmFecha"));		
		colPPMFecha.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();

							if(DatosAction.validaCriterioPPM(peticion)){
								Date fechaActual = new Date(); 
								try {
									if(DatosAction.cumplePPM(peticion, fechaActual)){
										// Color Verde
										setStyle("-fx-alignment: CENTER; -fx-background-color:  B9D305");
									}else{
										// Color rojo
										setStyle("-fx-alignment: CENTER; -fx-background-color: d31f05");
									}
								} catch (ParseException e) {
									e.printStackTrace();
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									getTableRow().setStyle("-fx-alignment: CENTER;");
								}

							}
                        }
                    }
                };
                return cell;
            }
        });
		
		
		colIncidencias.setStyle("-fx-alignment: CENTER;");
		colIncidencias.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("incidencia"));
		colIncidencias.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();

							if(null != peticion && null != peticion.getEsfuerzo() && !peticion.getEsfuerzo().equals(Constantes.S_EMPTY)){
								if(DatosAction.aplicaIncidencias(peticion)){
									 if(null != peticion.getIncidencia() && !peticion.getIncidencia().equals(Constantes.S_EMPTY) && Integer.valueOf(peticion.getIncidencia()) > Constantes.iCodZero){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  B9D305");
									 }else{
										 setStyle("-fx-alignment: CENTER; -fx-background-color: FA8C0F");
									 }
								}
							}
                        }
                    }
                };
                return cell;
            }
        });
		
		colDudas.setStyle("-fx-alignment: CENTER;");
		colDudas.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("dudas"));
		colDudas.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
							//System.out.println(peticion.getTipoPeticion());
							if(null != peticion && null != peticion.getEsfuerzo() && !peticion.getEsfuerzo().equals(Constantes.S_EMPTY)){
								if(DatosAction.aplicaDudas(peticion)){
									 if(null != peticion.getDudas() && !peticion.getDudas().equals(Constantes.S_EMPTY) && Integer.valueOf(peticion.getDudas()) > Constantes.iCodZero){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  B9D305");
									 }else{
										 setStyle("-fx-alignment: CENTER; -fx-background-color: FA8C0F");
									 }
								}
							}
                        }
                    }
                };
                return cell;
            }
        });
		
		colNCs.setStyle("-fx-alignment: CENTER;");
		colNCs.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("ncs"));
		colNCs.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
							if(null != peticion && !peticion.getTipoPeticion().equals(Constantes.S_TIP_PET_SP) && !peticion.getEstado().equals(Constantes.sEntregada)){
								if(DatosAction.validaNCs(peticion)){
									 setStyle("-fx-alignment: CENTER; -fx-background-color:  FA8C0F");
								}else{
									 setStyle("-fx-alignment: CENTER; -fx-background-color: B9D305");
								}
							}
                        }
                    }
                };
                return cell;
            }
        });
		
		colKiuwan.setStyle("-fx-alignment: CENTER;");
		colKiuwan.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("fechaKiuwan"));
		colKiuwan.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
							if(null != peticion && !peticion.getEstado().equals(Constantes.sEntregada) && (peticion.getTipoPeticion().equals(Constantes.S_TIP_PET_CO) || peticion.getTipoPeticion().equals(Constantes.S_TIP_PET_DTCO))){
								if(null != peticion.getFechaKiuwan() && !peticion.getFechaKiuwan().equals(Constantes.S_EMPTY)){
									 setStyle("-fx-alignment: CENTER; -fx-background-color:  B9D305");
								}else{
									 setStyle("-fx-alignment: CENTER; -fx-background-color: FA8C0F");
								}
							}
                        }
                    }
                };
                return cell;
            }
        });		

		colRespStgo.setStyle("-fx-alignment: CENTER;");
		colRespStgo.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("responsableStgo"));
		colRespStgo.setCellFactory(new Callback<TableColumn<PeticionesVO, String>, TableCell<PeticionesVO, String>>() {
            @Override
            public TableCell<PeticionesVO, String> call(TableColumn<PeticionesVO, String> p) {
                TableCell<PeticionesVO, String> cell = new TableCell<PeticionesVO, String>() {
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
                            setStyle("-fx-alignment: CENTER;");
							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
	                   		 Date fechaActual = new Date();
	                   		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	                   		 String fechaSistema = formateador.format(fechaActual);
	                   		 if(null != peticion && null != peticion.getFechaFin() && null != peticion.getEstado() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)){
	                   			 String fechaPet = peticion.getFechaFin();
	                   			 String estadoPet = peticion.getEstado();
	                   			 
	                   			 try {
									if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
										setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  gold");
									 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  FF9933");
									 }else if(estadoPet.equals(Constantes.sAplazada)){
										 setStyle("-fx-alignment: CENTER; -fx-background-color:  A9A9A9");		 
									 }else{
										setStyle("-fx-alignment: CENTER;");
									 }
								} catch (ParseException e) {
									Logger log = PrintLog.getLogger();
									FileHandler fh = PrintLog.getHandler();
									log.addHandler(fh);
									log.info(e.getMessage());
									PrintLog.closeHandler(fh);
									setStyle("-fx-alignment: CENTER;");
								}
	                   		 }
                        }
                    }
                };
                return cell;
            }
        });
		
		
//		// Carga inicial de datos
		DatosAction.iniciar(tablePeticiones, recuentos);
		DatosAction.cargaComboTipos(tipoPeticion);
		DatosAction.cargaComboEstado(estado);
		DatosAction.cargaComboEtapas(etapaPPM);
		DatosAction.cargaComboEtapas(etapaPeticion);
		menu = new ContextMenu();
		MenusAction.creaMenuCopiar(tablePeticiones, itemCopiar, menu);
		MenusAction.creaMenuAsociarTicket(tablePeticiones, itemAsociarTicket, menu);
		MenusAction.creaMenuVerTicket(tablePeticiones, itemVerTickets, menu);
	}

}
