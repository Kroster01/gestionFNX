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
import javafx.stage.Stage;
import javafx.util.Callback;
import pet.acciones.DatosAction;
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.util.PrintLog;
import pet.vo.PeticionesVO;
import pet.vo.RecuentoVO;

public class Entregadas implements Initializable{

	   @FXML
	    private TableColumn<PeticionesVO, String> colNCs;

	    @FXML
	    private TableColumn<PeticionesVO, Integer> colIdPet;

	    @FXML
	    private TableColumn<PeticionesVO, String> colEstado;

	    @FXML
	    private Button bBuscarEnt;

	    @FXML
	    private TableColumn<PeticionesVO, String> colCodDemanda;

	    @FXML
	    private Button bDetalleEnt;

	    @FXML
	    private TableColumn<PeticionesVO, String> colTipPet;

	    @FXML
	    private TableColumn<PeticionesVO, String> colDudas;

	    @FXML
	    private TextField tBusquedaEnt;

	    @FXML
	    private TableColumn<PeticionesVO, String> colDescripcion;

	    @FXML
	    private TableColumn<PeticionesVO, String> colResponsable;

	    @FXML
	    private Button bBorrarEnt;

	    @FXML
	    private TableView<PeticionesVO> tablePeticiones;

	    @FXML
	    private TableColumn<PeticionesVO, String> colRespStgo;

	    @FXML
	    private TableColumn<PeticionesVO, String> colPPMEtapa;

	    @FXML
	    private TableColumn<PeticionesVO, String> colPPMFecha;

	    @FXML
	    private TableColumn<PeticionesVO, String> colDCentro;
	    
	    @FXML
	    private TableColumn<PeticionesVO, String> colDRtcOsi;

	    @FXML
	    private TableColumn<PeticionesVO, String> colDRtcPap;

	    @FXML
	    private TextField tEntregadasEnt;

	    @FXML
	    private TableColumn<PeticionesVO, String> colRespCentro;

	    @FXML
	    private TableColumn<PeticionesVO, String> colIncidencias;

	    @FXML
	    private TableColumn<PeticionesVO, String> colKiuwan;

	    @FXML
	    private Button bActualizarEnt;
	
    @FXML
    void abreDirectorio(MouseEvent event){
    	if(null != tablePeticiones && null != tablePeticiones.getSelectionModel() && null != tablePeticiones.getSelectionModel().getSelectedItem()){
    		if(event.getButton() == MouseButton.PRIMARY){
    			boolean resultado = DatosAction.abrirDirectorioEjec(tablePeticiones, colDCentro, colDRtcOsi, colDRtcPap);
        		if(!resultado){
        			MessagesBox.createAlert("Error", "Ruta no existe.");
        		}
    		}
    	}
    }
	
    @FXML
    void buscarEnt() {
    	if(null != tBusquedaEnt && null != tBusquedaEnt.getText() && !tBusquedaEnt.getText().equals(Constantes.S_EMPTY)){
    		DatosAction.buscarRegistroEnt(tablePeticiones, tBusquedaEnt);
    	}else{
    		DatosAction.iniciarEntregadas(tablePeticiones, recuentos);
    	}
    }
	
	@FXML
	void verDetalleEnt(ActionEvent event) {
    	if(null != tablePeticiones && null != tablePeticiones.getSelectionModel() && null != tablePeticiones.getSelectionModel().getSelectedItem()){
    		DatosAction datosAction = new DatosAction();
    		datosAction.abrirDetalle(tablePeticiones);
    	}else{
    		MessagesBox.createAlert("Error", "Debe seleccionar registro para ver el detalle.");
    	}
	}
	
	@FXML
	void actualizarEnt(ActionEvent event) {
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
	void eliminarEnt(ActionEvent event) {
		if(MessagesBox.createConfirm("Consulta", "¿Desea eliminar la petición?")){
	    	if(null != tablePeticiones && null != tablePeticiones.getSelectionModel() && null != tablePeticiones.getSelectionModel().getSelectedItem()){
	    		DatosAction.prepDataBorrar(tablePeticiones, colIdPet);
	    		DatosAction.iniciarEntregadas(tablePeticiones, recuentos);
	    	}else{
	    		MessagesBox.createAlert("Error", "Debe seleccionar registro para borrar.");
	    	}
		}
    }
	
    @FXML
    void buscaEnter(KeyEvent ev){
    	if(ev.getCode() == KeyCode.ENTER){
    		buscarEnt();
    	}
    }
	
    RecuentoVO recuentos;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		recuentos = new RecuentoVO();
		recuentos.settEntregadas(tEntregadasEnt);

		colIdPet.setStyle("-fx-alignment: CENTER;");
		colIdPet.setCellValueFactory(new PropertyValueFactory<PeticionesVO,Integer>("idPeticion"));
		colCodDemanda.setStyle("-fx-alignment: CENTER;");
		
		colCodDemanda.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("codDemanda"));
				
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
		
//		colDRTC.setStyle("-fx-alignment: CENTER;");
//		colDRTC.setCellValueFactory(new PropertyValueFactory<PeticionesVO,String>("dirRTC"));
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
										 getTableRow().setStyle("-fx-alignment: CENTER;");
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
										 getTableRow().setStyle("-fx-alignment: CENTER;");
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
//							PeticionesVO peticion = (PeticionesVO) getTableRow().getItem();
//							if(null != peticion && peticion.getTipoPeticion().equals(Constantes.S_TIP_PET_CO) || null != peticion && peticion.getTipoPeticion().equals(Constantes.S_TIP_PET_DTCO)){
//								if(null != peticion.getFechaKiuwan() && !peticion.getFechaKiuwan().equals(Constantes.S_EMPTY)){
//									 setStyle("-fx-alignment: CENTER; -fx-background-color:  B9D305");
//								}else{
//									 setStyle("-fx-alignment: CENTER; -fx-background-color: FA8C0F");
//								}
//							}
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
		
		DatosAction.iniciarEntregadas(tablePeticiones, recuentos);
		
	}

}
