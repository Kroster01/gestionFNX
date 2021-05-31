package pet.controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import pet.acciones.IncurridosAction;
import pet.datos.ObtieneIncurridos;
import pet.util.CnvString;
import pet.util.TableColumnUtil;
import pet.vo.DiaVO;
import pet.vo.EmpleadoVO;
import pet.vo.IncurridoVO;

public class Incurridos implements Initializable{
	
   @FXML
    private TableColumn<EmpleadoVO, String> col18;

    @FXML
    private TableColumn<EmpleadoVO, String> col17;

    @FXML
    private TableColumn<EmpleadoVO, String> col16;

    @FXML
    private TableColumn<EmpleadoVO, String> col15;

    @FXML
    private TableColumn<EmpleadoVO, String> col14;

    @FXML
    private TableColumn<EmpleadoVO, String> col13;

    @FXML
    private TableColumn<EmpleadoVO, String> col129;

    @FXML
    private TableColumn<EmpleadoVO, String> col12;

    @FXML
    private TableColumn<EmpleadoVO, String> col11;

    @FXML
    private TableColumn<EmpleadoVO, String> col126;

    @FXML
    private TableColumn<EmpleadoVO, String> col125;

    @FXML
    private TableColumn<EmpleadoVO, String> col128;

    @FXML
    private TableColumn<EmpleadoVO, String> col127;

    @FXML
    private TableColumn<EmpleadoVO, String> col122;

    @FXML
    private TableColumn<EmpleadoVO, String> col121;

    @FXML
    private TableColumn<EmpleadoVO, String> col124;

    @FXML
    private TableColumn<EmpleadoVO, String> col19;

    @FXML
    private TableColumn<EmpleadoVO, String> col123;

    @FXML
    private TableColumn<EmpleadoVO, String> col120;

    @FXML
    private TableColumn<EmpleadoVO, String> col1;

    @FXML
    private TableColumn<EmpleadoVO, String> col119;

    @FXML
    private TableColumn<EmpleadoVO, String> col118;

    @FXML
    private TableColumn<EmpleadoVO, String> col115;

    @FXML
    private TableColumn<EmpleadoVO, String> col114;

    @FXML
    private TableColumn<EmpleadoVO, String> col117;

    @FXML
    private TableColumn<EmpleadoVO, String> col116;

    @FXML
    private TableColumn<EmpleadoVO, String> col111;

    @FXML
    private TableColumn<EmpleadoVO, String> col110;

    @FXML
    private TableColumn<EmpleadoVO, String> col113;

    @FXML
    private TableColumn<EmpleadoVO, String> col112;

    @FXML
    private TableColumn<EmpleadoVO, String> col130;

    @FXML
    private TableColumn<EmpleadoVO, String> colNomEmpl;

    @FXML
    private TableView<EmpleadoVO> tableIncurrido;

    @FXML
    private TableColumn<EmpleadoVO, String> colNumEmpl;

    @FXML
    private DatePicker dFecha;

    @FXML
    private ComboBox<?> cCelda;

    @FXML
    private Button bBuscar;

    @FXML
    void buscar(ActionEvent event) {
		try {
			Date firstFecha = CnvString.getFirstDateOfMonth(dFecha);
			Date lastFecha = CnvString.getLastDateOfMonth(dFecha);
			
			HashMap<String, EmpleadoVO> incurridos = ObtieneIncurridos.obtieneIncurridosFenix(null, CnvString.convertFecha(firstFecha), CnvString.convertFecha(lastFecha));
			ObservableList<EmpleadoVO> empleadosList = FXCollections.observableArrayList();
			
			for(Map.Entry<String, EmpleadoVO> entry : incurridos.entrySet()) {
			    EmpleadoVO empleado = entry.getValue();
			    String nroEmpleado = entry.getKey();
			    ObservableList<IncurridoVO> incu = empleado.getIncurridos();
			    HashMap<Date, DiaVO> incuAgrup = new HashMap<Date, DiaVO>();

			    for (IncurridoVO incurridoVO : incu) {   	
			    	
			    	if(null != incuAgrup.get(incurridoVO.getFecha())){
			    		DiaVO dia = incuAgrup.get(incurridoVO.getFecha());
			    		dia.setHoras(dia.getHoras() + incurridoVO.getHoras());
			    		dia.setDescIncurrido(dia.getDescIncurrido() + "; " + incurridoVO.getDescIncurrido());
			    		incuAgrup.remove(incurridoVO.getFecha());
			    		incuAgrup.put(incurridoVO.getFecha(), dia);
			    	}else{
			    		DiaVO dia = new DiaVO();
			    		dia.setHoras(incurridoVO.getHoras());
			    		dia.setDescIncurrido(incurridoVO.getDescIncurrido());
			    		incuAgrup.put(incurridoVO.getFecha(), dia);
			    	}
				}
			    
			    IncurridosAction.setHorasInc(empleado, incuAgrup);
			    
			    empleadosList.add(empleado);
			}
			
			FXCollections.sort(empleadosList, comparador);
			
			for (EmpleadoVO empleado : empleadosList) {
				System.out.println(empleado.getNroEmpleado() + ";" + empleado.getNomEmpleado() + ";" + empleado.getCelula());
			}
			
			tableIncurrido.getItems().clear();
			tableIncurrido.setItems(empleadosList);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    //Comparator for int, by Number
    static Comparator<? super EmpleadoVO> comparador = new Comparator<EmpleadoVO>() {
        public int compare(EmpleadoVO o1, EmpleadoVO o2) {
            return o1.getNomEmpleado().compareTo(o2.getNomEmpleado());
        }
    };
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

//		colNomEmpl.setStyle("-fx-alignment: CENTER;");
		colNomEmpl.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("nomEmpleado"));
		colNomEmpl.setCellFactory(new Callback<TableColumn<EmpleadoVO, String>, TableCell<EmpleadoVO, String>>() {
            @Override
            public TableCell<EmpleadoVO, String> call(TableColumn<EmpleadoVO, String> p) {
                TableCell<EmpleadoVO, String> cell = new TableCell<EmpleadoVO, String>() {
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
		colNumEmpl.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>("nroEmpleado"));
		colNumEmpl.setCellFactory(new Callback<TableColumn<EmpleadoVO, String>, TableCell<EmpleadoVO, String>>() {
            @Override
            public TableCell<EmpleadoVO, String> call(TableColumn<EmpleadoVO, String> p) {
                TableCell<EmpleadoVO, String> cell = new TableCell<EmpleadoVO, String>() {
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
		
		TableColumnUtil.setIniColumnInc(col1, dFecha, 1, "day1");
		TableColumnUtil.setIniColumnInc(col11, dFecha, 2, "day2");
		TableColumnUtil.setIniColumnInc(col12, dFecha, 3, "day3");
		TableColumnUtil.setIniColumnInc(col13, dFecha, 4, "day4");
		TableColumnUtil.setIniColumnInc(col14, dFecha, 5, "day5");
		TableColumnUtil.setIniColumnInc(col15, dFecha, 6, "day6");
		TableColumnUtil.setIniColumnInc(col16, dFecha, 7, "day7");
		TableColumnUtil.setIniColumnInc(col17, dFecha, 8, "day8");
		TableColumnUtil.setIniColumnInc(col18, dFecha, 9, "day9");
		TableColumnUtil.setIniColumnInc(col19, dFecha, 10, "day10");
		TableColumnUtil.setIniColumnInc(col110, dFecha, 11, "day11");
		TableColumnUtil.setIniColumnInc(col111, dFecha, 12, "day12");
		TableColumnUtil.setIniColumnInc(col112, dFecha, 13, "day13");
		TableColumnUtil.setIniColumnInc(col113, dFecha, 14, "day14");
		TableColumnUtil.setIniColumnInc(col114, dFecha, 15, "day15");
		TableColumnUtil.setIniColumnInc(col115, dFecha, 16, "day16");
		TableColumnUtil.setIniColumnInc(col116, dFecha, 17, "day17");
		TableColumnUtil.setIniColumnInc(col117, dFecha, 18, "day18");
		TableColumnUtil.setIniColumnInc(col118, dFecha, 19, "day19");
		TableColumnUtil.setIniColumnInc(col119, dFecha, 20, "day20");
		TableColumnUtil.setIniColumnInc(col120, dFecha, 21, "day21");
		TableColumnUtil.setIniColumnInc(col121, dFecha, 22, "day22");
		TableColumnUtil.setIniColumnInc(col122, dFecha, 23, "day23");
		TableColumnUtil.setIniColumnInc(col123, dFecha, 24, "day24");
		TableColumnUtil.setIniColumnInc(col124, dFecha, 25, "day25");
		TableColumnUtil.setIniColumnInc(col125, dFecha, 26, "day26");
		TableColumnUtil.setIniColumnInc(col126, dFecha, 27, "day27");
		TableColumnUtil.setIniColumnInc(col127, dFecha, 28, "day28");
		TableColumnUtil.setIniColumnInc(col128, dFecha, 29, "day29");
		TableColumnUtil.setIniColumnInc(col129, dFecha, 30, "day30");
		TableColumnUtil.setIniColumnInc(col130, dFecha, 31, "day31");	
	}
}
