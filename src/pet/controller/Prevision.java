package pet.controller;

import java.net.URL;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import pet.acciones.DatosAction;
import pet.acciones.IncurridosAction;
import pet.util.CnvString;
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.util.TableColumnUtil;
import pet.vo.CelulaVO;
import pet.vo.EmpleadoPrevVO;
import pet.vo.PrevisionVO;

public class Prevision implements Initializable{
	
    @FXML
    private ComboBox<CelulaVO> cCelula;
	
	@FXML
	private Button bGuardarPrevision;
	
	@FXML
	private Button bEliminar;
	
	@FXML
	private TextField tMesPrevision;
	
	@FXML
	private TextField tTotalProyecto;
	
	@FXML
	private TextField tNombreEmpl;
	
	@FXML
	private TextField tNroEmpl;
	
	@FXML
	private TextField tCelulaEmp;
	
   @FXML
    private TableColumn<EmpleadoPrevVO, String> colTotal;
	
   @FXML
    private TableColumn<EmpleadoPrevVO, String> col18;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col17;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col16;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col15;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col14;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col13;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col129;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col12;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col11;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col126;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col125;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col128;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col127;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col122;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col121;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col124;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col19;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col123;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col120;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col1;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col119;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col118;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col115;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col114;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col117;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col116;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col111;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col110;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col113;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col112;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> col130;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> colNomEmpl;

    @FXML
    private TableView<EmpleadoPrevVO> tableIncurrido;

    @FXML
    private TableColumn<EmpleadoPrevVO, String> colNumEmpl;

    @FXML
    private DatePicker dFecha;

    @FXML
    private Button bBuscar;
    
    @FXML
    private Button bAgregar;
    
    @FXML
    void eliminar(ActionEvent event){
    	if(null != tableIncurrido && null != tableIncurrido.getSelectionModel() && null != tableIncurrido.getSelectionModel().getSelectedItem()){
    		EmpleadoPrevVO empleado = tableIncurrido.getSelectionModel().getSelectedItem();
    		DatosAction.eliminaRegEmp(empleado.getNroEmpleado());
    		MessagesBox.createAlert("Exito", "Registro eliminado correctamente.");
    		buscar(event);
    	}else{
    		MessagesBox.createAlert("Error", "Debe seleccionar un registro para eliminar.");
    	}   	
    }
    
    @FXML
    void agregarEmp(ActionEvent event){
    	String nroEmpleado = tNroEmpl.getText();
    	String nombreEmpleado = tNombreEmpl.getText();
    	String celula = cCelula.getValue().getCelula();
    	
    	String registro = nroEmpleado + Constantes.S_PUNTO_COMA + nombreEmpleado + Constantes.S_PUNTO_COMA + celula;	
    	DatosAction.escribeFicheroEmpleados(registro);
    	MessagesBox.createAlert("Exito", "Registro agregado correctamente.");
    	buscar(event);

    }
    
    @FXML
    void guardarPrevision(ActionEvent event){
    	ObservableList<EmpleadoPrevVO> empleados = tableIncurrido.getItems();
    	try {
			ObservableList<PrevisionVO> previsiones = IncurridosAction.updatePrevisiones(empleados, dFecha);
			DatosAction.limpiaFicheroPrevision();
			
			for (PrevisionVO previsionVO : previsiones) {
				if(null != previsionVO.getHoras() && !previsionVO.getHoras().equals(Constantes.S_EMPTY)){
					DatosAction.guardarRegistro(previsionVO);
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	MessagesBox.createAlert("Exito", "Actualización exitosa.");
    	buscar(event);
    	
    }

    @FXML
    void buscar(ActionEvent event) {
		try {
			Date firstFecha = CnvString.getFirstDateOfMonth(dFecha);
			Date lastFecha = CnvString.getLastDateOfMonth(dFecha);
			
			tMesPrevision.setText(CnvString.getBaseMesRuta(dFecha));
			
			ObservableList<EmpleadoPrevVO> empleados = DatosAction.getEmpleadosPrev();
			
			ObservableList<PrevisionVO> previsiones = DatosAction.iniciarPrevision();
			ObservableList<EmpleadoPrevVO> empleadosFull = FXCollections.observableArrayList();
			
			for (EmpleadoPrevVO empleado : empleados) {
				HashMap<Date, String> incuAgrup = new HashMap<Date, String>();
				for (PrevisionVO prevision : previsiones) {
					if(empleado.getNroEmpleado().equals(String.valueOf(prevision.getNroEmpleado())) 
							&& CnvString.afterOrEqual(prevision.getFecha(), firstFecha) && CnvString.beforeOrEqual(prevision.getFecha(), lastFecha)){
				    	if(null != incuAgrup.get(prevision.getFecha())){
				    		String horas = incuAgrup.get(prevision.getFecha());
				    		horas = horas + prevision.getHoras();
				    		incuAgrup.remove(prevision.getFecha());
				    		incuAgrup.put(prevision.getFecha(), horas);
				    	}else{
				    		incuAgrup.put(prevision.getFecha(), prevision.getHoras());
				    	}
					}
					
				}
				IncurridosAction.setHorasPrevision(empleado, incuAgrup);
				empleadosFull.add(empleado);
			}
			IncurridosAction.delHorasWeekendFeriado(empleadosFull, dFecha, tTotalProyecto);

			FXCollections.sort(empleadosFull, comparador);
			tableIncurrido.getItems().clear();
			tableIncurrido.setItems(empleadosFull);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} 
    }
    
    //Comparator for int, by Number
    static Comparator<? super EmpleadoPrevVO> comparador = new Comparator<EmpleadoPrevVO>() {
        public int compare(EmpleadoPrevVO o1, EmpleadoPrevVO o2) {
            return o1.getNomEmpleado().compareTo(o2.getNomEmpleado());
        }
    };
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

//		colNomEmpl.setStyle("-fx-alignment: CENTER;");
		colNomEmpl.setCellValueFactory(new PropertyValueFactory<EmpleadoPrevVO, String>("nomEmpleado"));
		colNomEmpl.setCellFactory(new Callback<TableColumn<EmpleadoPrevVO, String>, TableCell<EmpleadoPrevVO, String>>() {
            @Override
            public TableCell<EmpleadoPrevVO, String> call(TableColumn<EmpleadoPrevVO, String> p) {
                TableCell<EmpleadoPrevVO, String> cell = new TableCell<EmpleadoPrevVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                    	setText(item);
                        tooltip.setText(item);
                        setTooltip(tooltip);
//                        setStyle("-fx-alignment: CENTER;");  
                    }
                };
                return cell;
            }
        });
		
		colNumEmpl.setStyle("-fx-alignment: CENTER;");
		colNumEmpl.setCellValueFactory(new PropertyValueFactory<EmpleadoPrevVO, String>("celula"));
		colNumEmpl.setCellFactory(new Callback<TableColumn<EmpleadoPrevVO, String>, TableCell<EmpleadoPrevVO, String>>() {
            @Override
            public TableCell<EmpleadoPrevVO, String> call(TableColumn<EmpleadoPrevVO, String> p) {
                TableCell<EmpleadoPrevVO, String> cell = new TableCell<EmpleadoPrevVO, String>() {
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

		TableColumnUtil.setIniColumnPrev(col1, dFecha, 1, "day1");
		TableColumnUtil.setIniColumnPrev(col11, dFecha, 2, "day2");
		TableColumnUtil.setIniColumnPrev(col12, dFecha, 3, "day3");
		TableColumnUtil.setIniColumnPrev(col13, dFecha, 4, "day4");
		TableColumnUtil.setIniColumnPrev(col14, dFecha, 5, "day5");
		TableColumnUtil.setIniColumnPrev(col15, dFecha, 6, "day6");
		TableColumnUtil.setIniColumnPrev(col16, dFecha, 7, "day7");
		TableColumnUtil.setIniColumnPrev(col17, dFecha, 8, "day8");
		TableColumnUtil.setIniColumnPrev(col18, dFecha, 9, "day9");
		TableColumnUtil.setIniColumnPrev(col19, dFecha, 10, "day10");
		TableColumnUtil.setIniColumnPrev(col110, dFecha, 11, "day11");
		TableColumnUtil.setIniColumnPrev(col111, dFecha, 12, "day12");
		TableColumnUtil.setIniColumnPrev(col112, dFecha, 13, "day13");
		TableColumnUtil.setIniColumnPrev(col113, dFecha, 14, "day14");
		TableColumnUtil.setIniColumnPrev(col114, dFecha, 15, "day15");
		TableColumnUtil.setIniColumnPrev(col115, dFecha, 16, "day16");
		TableColumnUtil.setIniColumnPrev(col116, dFecha, 17, "day17");
		TableColumnUtil.setIniColumnPrev(col117, dFecha, 18, "day18");
		TableColumnUtil.setIniColumnPrev(col118, dFecha, 19, "day19");
		TableColumnUtil.setIniColumnPrev(col119, dFecha, 20, "day20");
		TableColumnUtil.setIniColumnPrev(col120, dFecha, 21, "day21");
		TableColumnUtil.setIniColumnPrev(col121, dFecha, 22, "day22");
		TableColumnUtil.setIniColumnPrev(col122, dFecha, 23, "day23");
		TableColumnUtil.setIniColumnPrev(col123, dFecha, 24, "day24");
		TableColumnUtil.setIniColumnPrev(col124, dFecha, 25, "day25");
		TableColumnUtil.setIniColumnPrev(col125, dFecha, 26, "day26");
		TableColumnUtil.setIniColumnPrev(col126, dFecha, 27, "day27");
		TableColumnUtil.setIniColumnPrev(col127, dFecha, 28, "day28");
		TableColumnUtil.setIniColumnPrev(col128, dFecha, 29, "day29");
		TableColumnUtil.setIniColumnPrev(col129, dFecha, 30, "day30");
		TableColumnUtil.setIniColumnPrev(col130, dFecha, 31, "day31");	
		
		colTotal.setStyle("-fx-alignment: CENTER;");
		colTotal.setCellValueFactory(new PropertyValueFactory<EmpleadoPrevVO, String>("total"));
				
		tableIncurrido.setEditable(true);
		
		DatosAction.cargaComboCelulas(cCelula);

//		colPUniVent.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
	}


}
