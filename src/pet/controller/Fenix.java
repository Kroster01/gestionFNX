package pet.controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import pet.acciones.DatosAction;
import pet.datos.ObtieneReportes;
import pet.util.Constantes;
import pet.vo.AplicacionVO;
import pet.vo.EstadosVO;
import pet.vo.FenixReportVO;

public class Fenix implements Initializable{

    @FXML
    private TableColumn<FenixReportVO, String> colNCs;

    @FXML
    private TableColumn<FenixReportVO, String> colIdPet;

    @FXML
    private TableColumn<FenixReportVO, String> colEstado;

    @FXML
    private TableColumn<FenixReportVO, String> colTipPet;
    
    @FXML
    private TableColumn<FenixReportVO, String> colHAcuerdo;

    @FXML
    private TableColumn<FenixReportVO, String> colIncidenciasInt;

    @FXML
    private TableColumn<FenixReportVO, String> colETC;

    @FXML
    private DatePicker dFechaSolicitud;

    @FXML
    private TableColumn<FenixReportVO, String> colDudas;

    @FXML
    private Button bDetalle;

    @FXML
    private TableColumn<FenixReportVO, String> colDescripcion;

    @FXML
    private TableColumn<FenixReportVO, String> colResponsable;

    @FXML
    private TableView<FenixReportVO> tablePeticiones;

    @FXML
    private TableColumn<FenixReportVO, String> colDCentro;

    @FXML
    private TableColumn<FenixReportVO, String> colIncidenciasExt;
    
    @FXML
    private TableColumn<FenixReportVO, String> colIncurridoInt;
    
    @FXML
    private TableColumn<FenixReportVO, String> colRatio;
    
    @FXML
    private TableColumn<FenixReportVO, String> colRetrabajo;

    @FXML
    private TableColumn<FenixReportVO, String> colHPlanif;

    @FXML
    private TableColumn<FenixReportVO, String> colETCAut;
    
    @FXML
    private TableColumn<FenixReportVO, String> colFechaFin;
    
    @FXML
    private TableColumn<FenixReportVO, String> colFechaInicio;

    @FXML
    private ComboBox<EstadosVO> cEstado;
    
    @FXML
    private ChoiceBox<EstadosVO> chEstado;

    @FXML
    private ComboBox<AplicacionVO> cAplicacion;
    
    @FXML
    private ChoiceBox<AplicacionVO> chAplicacion;

    @FXML
    private Button bBuscar;

    @FXML
    void buscarFenix(ActionEvent event) {
    	
		SimpleDateFormat sdfBack = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaDesde = null;
		String fechaDesdeStg = Constantes.S_EMPTY;
		String estado = Constantes.S_EMPTY;
		String aplicacion = Constantes.S_EMPTY;
		
		if(null != dFechaSolicitud && null != dFechaSolicitud.getValue()){
			try {
				fechaDesde = sdfBack.parse(dFechaSolicitud.getValue().toString());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			fechaDesdeStg = sdf.format(fechaDesde);
		}

		if(null != cEstado && null != cEstado.getValue()){
			estado = "'" + cEstado.getValue() + "'";
		}
    	
		if(null != cAplicacion && null != cAplicacion.getValue()){
			aplicacion = "'" + cAplicacion.getValue() + "'";
		}

		try {
			ObservableList<FenixReportVO> petisList = ObtieneReportes.obtienePeticionesFenix(estado, aplicacion, fechaDesdeStg);
			tablePeticiones.setItems(petisList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void verDetalleFenix(ActionEvent event) {

    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colNCs.setStyle("-fx-alignment: CENTER;");
		colNCs.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("ncs"));
		colNCs.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();

                        if(null != peticion && !peticion.getTipoPeticion().contains("CESION") && !peticion.getEstado().contains("DESESTIMADA")){
    						if(null != peticion && null != peticion.getNcs() && !peticion.getNcs().equals(Constantes.S_EMPTY) && Integer.valueOf(peticion.getNcs()) > Constantes.iCodZero){
	   							 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
	   						}else{
	   							 setStyle("-fx-alignment: CENTER;");
	   						}
                        }else{
                        	 setStyle("-fx-alignment: CENTER;");
                        }

                    }
                };
                return cell;
            }
        });
		
		colIdPet.setStyle("-fx-alignment: CENTER;");
		colIdPet.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("idPeticion"));
		
		colEstado.setStyle("-fx-alignment: CENTER;");
		colEstado.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("estado"));
		colEstado.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                    	setText(item);
                        tooltip.setText(item);
                        setTooltip(tooltip);
                        setStyle("-fx-alignment: CENTER;");
                    }
                };
                return cell;
            }
        });
		
		colTipPet.setStyle("-fx-alignment: CENTER;");
		colTipPet.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("tipoPeticion"));
		colTipPet.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                    	setText(item);
                        tooltip.setText(item);
                        setTooltip(tooltip);
                        setStyle("-fx-alignment: CENTER;");
                    }
                };
                return cell;
            }
        });
		
		colIncidenciasInt.setStyle("-fx-alignment: CENTER;");
		colIncidenciasInt.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("incInt"));
		colIncidenciasInt.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();
                        
                        if(null != peticion && !peticion.getTipoPeticion().contains("CESION") && !peticion.getEstado().contains("DESESTIMADA")){
    						if(null != peticion && null != peticion.getIncInt() && !peticion.getIncInt().equals(Constantes.S_EMPTY) && Integer.valueOf(peticion.getIncInt()) == Constantes.iCodZero){
	   							 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
	   						}else{
	   							 setStyle("-fx-alignment: CENTER;");
	   						}
                        }else{
                        	 setStyle("-fx-alignment: CENTER;");
                        }
                    }
                };
                return cell;
            }
        });
		
		colETC.setStyle("-fx-alignment: CENTER;");
		colETC.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("etc"));
		colETC.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();
                        if(null != peticion && !peticion.getTipoPeticion().contains("CESION") && !peticion.getEstado().contains("DESESTIMADA")){
    						if(null != peticion && null != peticion.getEtc() && !peticion.getEtc().equals(Constantes.S_EMPTY) 
    								&& null != peticion.getEtcAut() && !peticion.getEtcAut().equals(Constantes.S_EMPTY) && !peticion.getEtc().equals(peticion.getEtcAut())){
    							 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
    						}else{
    							 setStyle("-fx-alignment: CENTER;");
    						}
                        }else{
                        	 setStyle("-fx-alignment: CENTER;");
                        }
                    }
                };
                return cell;
            }
        });
		
		colDudas.setStyle("-fx-alignment: CENTER;");
		colDudas.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("dudas"));
		colDudas.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();
                        if(null != peticion && !peticion.getTipoPeticion().contains("CESION") && !peticion.getEstado().contains("DESESTIMADA")){
    						if(null != peticion && null != peticion.getDudas() && !peticion.getDudas().equals(Constantes.S_EMPTY) && Integer.valueOf(peticion.getDudas()) == Constantes.iCodZero){
	   							 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
	   						}else{
	   							 setStyle("-fx-alignment: CENTER;");
	   						}
                        }else{
                        	 setStyle("-fx-alignment: CENTER;");
                        }
                    }
                };
                return cell;
            }
        });
		
		colDescripcion.setStyle("-fx-alignment: CENTER;");
		colDescripcion.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("descripcion"));
		colDescripcion.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                    	setText(item);
                        tooltip.setText(item);
                        setTooltip(tooltip);
                        setStyle("-fx-alignment: CENTER;");
                    }
                };
                return cell;
            }
        });
		
		colResponsable.setStyle("-fx-alignment: CENTER;");
		colResponsable.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("responsable"));
		
		colDCentro.setStyle("-fx-alignment: CENTER;");
		colDCentro.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("dirCentro"));
		colDCentro.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                    	setText(item);
                        tooltip.setText(item);
                        setTooltip(tooltip);
                        setStyle("-fx-alignment: CENTER;");
                    }
                };
                return cell;
            }
        });
		
		colIncidenciasExt.setStyle("-fx-alignment: CENTER;");
		colIncidenciasExt.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("incExt"));
		colIncidenciasExt.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();
                        if(null != peticion && !peticion.getTipoPeticion().contains("CESION") && !peticion.getEstado().contains("DESESTIMADA")){
    						if(null != peticion && null != peticion.getIncExt() && !peticion.getIncExt().equals(Constantes.S_EMPTY) && Integer.valueOf(peticion.getIncExt()) > Constantes.iCodZero){
	   							 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
	   						}else{
	   							 setStyle("-fx-alignment: CENTER;");
	   						}
                        }else{
                        	 setStyle("-fx-alignment: CENTER;");
                        }
                    }
                };
                return cell;
            }
        });
		
		colHPlanif.setStyle("-fx-alignment: CENTER;");
		colHPlanif.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("horPlanif"));
		colHPlanif.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();
                        if(null != peticion && !peticion.getTipoPeticion().contains("CESION") && !peticion.getEstado().contains("DESESTIMADA")){
    						if(null != peticion && null != peticion.getHorasAcuerdo() && null != peticion.getHorPlanif() 
    								&& peticion.getHorasAcuerdo().equals(peticion.getHorPlanif())){
    							 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
    						}else{
    							 setStyle("-fx-alignment: CENTER;");
    						}
                        }else{
                        	 setStyle("-fx-alignment: CENTER;");
                        }
                    }
                };
                return cell;
            }
        });
		
		
		colETCAut.setStyle("-fx-alignment: CENTER;");
		colETCAut.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("etcAut"));
		colETCAut.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();
                        if(null != peticion && !peticion.getTipoPeticion().contains("CESION") && !peticion.getEstado().contains("DESESTIMADA")){
    						if(null != peticion && null != peticion.getEtc() && !peticion.getEtc().equals(Constantes.S_EMPTY) 
    								&& null != peticion.getEtcAut() && !peticion.getEtcAut().equals(Constantes.S_EMPTY) && !peticion.getEtc().equals(peticion.getEtcAut())){
    							 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
    						}else{
    							 setStyle("-fx-alignment: CENTER;");
    						}
                        }else{
                        	 setStyle("-fx-alignment: CENTER;");
                        }
                    }
                };
                return cell;
            }
        });
		
		colIncurridoInt.setStyle("-fx-alignment: CENTER;");
		colIncurridoInt.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("incurridoInt"));
		colIncurridoInt.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                    	FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();
                        super.updateItem(item, empty);
                        setText(item);
                        if(null != peticion && !peticion.getTipoPeticion().contains("CESION") && !peticion.getEstado().contains("DESESTIMADA")){
    						if(null != peticion && null != peticion.getIncurridoInt() && !peticion.getIncurridoInt().equals(Constantes.S_EMPTY) 
    								&& DatosAction.incurridoIntMas(peticion.getHorPlanif(), peticion.getIncurridoInt())){
    							 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
    						}else{
    							 setStyle("-fx-alignment: CENTER;");
    						}
                        }else{
                        	 setStyle("-fx-alignment: CENTER;");
                        }

                    }
                };
                return cell;
            }
        });
		
		colRatio.setStyle("-fx-alignment: CENTER;");
		colRatio.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("incurridoInt"));
		colRatio.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                    	FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();
                        super.updateItem(item, empty);
                        if(null != peticion && !peticion.getTipoPeticion().contains("CESION") && !peticion.getEstado().contains("DESESTIMADA")){
    						if(null != peticion && null != peticion.getIncInt() && !peticion.getIncInt().equals(Constantes.S_EMPTY) 
    								&& !peticion.getIncInt().equals(Constantes.sCodZero) && null != peticion.getHorPlanif() 
    								&& !peticion.getHorPlanif().equals(Constantes.S_EMPTY) ){
    							setText(DatosAction.getRatio(peticion.getHorPlanif(), peticion.getIncInt()));
    						}else{
    							setText(Constantes.S_EMPTY);
    						}
    						if(null != peticion && null != peticion.getIncInt() && !peticion.getIncInt().equals(Constantes.S_EMPTY) 
    								&& !peticion.getIncInt().equals(Constantes.sCodZero) && DatosAction.ratioIncMas(peticion.getHorPlanif(), peticion.getIncInt())){
    							 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
    						}else{
    							 setStyle("-fx-alignment: CENTER;");
    						}
                        }else{
                        	 setStyle("-fx-alignment: CENTER;");
                        }
                    }
                };
                return cell;
            }
        });
		
		colRetrabajo.setStyle("-fx-alignment: CENTER;");
		colRetrabajo.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("incurridoInt"));
		colRetrabajo.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                    	FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();
                        super.updateItem(item, empty);
                        if(null != peticion && !peticion.getTipoPeticion().contains("CESION") && !peticion.getEstado().contains("DESESTIMADA")){
    						if(null != peticion && null != peticion.getIncurridoInt() && !peticion.getIncurridoInt().equals(Constantes.S_EMPTY)){
   							 setText(DatosAction.getRetrabajo(peticion.getHorPlanif(), peticion.getIncurridoInt()));
   						}else{
   							setText(Constantes.S_EMPTY);
   						}
   						
   						if(null != peticion && null != peticion.getIncurridoInt() && !peticion.getIncurridoInt().equals(Constantes.S_EMPTY) 
   								&& DatosAction.incurridoIntMas(peticion.getHorPlanif(), peticion.getIncurridoInt())){
   							 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
   						}else{
   							 setStyle("-fx-alignment: CENTER;");
   						}
                        }else{
                        	 setStyle("-fx-alignment: CENTER;");
                        }

                    }
                };
                return cell;
            }
        });
		
		colHAcuerdo.setStyle("-fx-alignment: CENTER;");
		colHAcuerdo.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("horasAcuerdo"));
		colHAcuerdo.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();
                        if(null != peticion && !peticion.getTipoPeticion().contains("CESION") && !peticion.getEstado().contains("DESESTIMADA")){
    						if(null != peticion && null != peticion.getHorasAcuerdo() && null != peticion.getHorPlanif() 
    								&& peticion.getHorasAcuerdo().equals(peticion.getHorPlanif())){
    							 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
    						}else{
    							 setStyle("-fx-alignment: CENTER;");
    						}
                        }else{
                        	 setStyle("-fx-alignment: CENTER;");
                        }
                    }
                };
                return cell;
            }
        });
		
		colFechaFin.setStyle("-fx-alignment: CENTER;");
		colFechaFin.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("fechaFin"));
		colFechaFin.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(null != item && null != item.substring(0, 10)){
                            setText(item.substring(0, 10));
                            tooltip.setText(item);
                            setTooltip(tooltip);
                        }else{
                        	setText(item);
                        }

                        FenixReportVO peticion = (FenixReportVO) getTableRow().getItem();
                        
                        SimpleDateFormat sdfBack = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaFin;
						try {
							if(null != peticion && null != peticion.getFechaFin() && !peticion.getFechaFin().equals(Constantes.S_EMPTY)
									&& null != peticion.getEstado() && !peticion.getEstado().equals(Constantes.S_EMPTY)){
								fechaFin = sdfBack.parse(peticion.getFechaFin());
		                        boolean antes = fechaFin.before(new Date());

								if(antes && peticion.getEstado().equals(Constantes.sEjecucionFenix)){
									 setStyle("-fx-alignment: CENTER; -fx-background-color:  e3a225");
								}else{
									 setStyle("-fx-alignment: CENTER;");
								}
							}else{
								 setStyle("-fx-alignment: CENTER;");
							}

						} catch (ParseException e) {
							e.printStackTrace();
						}
                    }
                };
                return cell;
            }
        });
		
		colFechaInicio.setStyle("-fx-alignment: CENTER;");
		colFechaInicio.setCellValueFactory(new PropertyValueFactory<FenixReportVO,String>("fechaInicio"));
		colFechaInicio.setCellFactory(new Callback<TableColumn<FenixReportVO, String>, TableCell<FenixReportVO, String>>() {
            @Override
            public TableCell<FenixReportVO, String> call(TableColumn<FenixReportVO, String> p) {
                TableCell<FenixReportVO, String> cell = new TableCell<FenixReportVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(null != item && null != item.substring(0, 10)){
                            setText(item.substring(0, 10));
                            tooltip.setText(item);
                            setTooltip(tooltip);
                        }else{
                        	setText(item);
                        }
                    }
                };
                return cell;
            }
        });
		
		DatosAction.cargaComboAplicaciones(cAplicacion);
		DatosAction.cargaComboEstadoFenix(cEstado);
		
		
		
	}

}
