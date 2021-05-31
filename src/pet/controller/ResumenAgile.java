package pet.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pet.acciones.DatosAction;
import pet.util.Constantes;
import pet.util.LineCharts;
import pet.util.MessagesBox;
import pet.vo.CelulaVO;
import pet.vo.EstadosVO;
import pet.vo.HistoriasVO;
import pet.vo.RiesgoVO;
import pet.vo.SprintVO;

public class ResumenAgile implements Initializable{

    @FXML
    private TableColumn<RiesgoVO,String> colImpactoRiesgo;

    @FXML
    private TextField tImpactRiesgo;

    @FXML
    private TextField tIdHistoria;

    @FXML
    private TableColumn<HistoriasVO,String> colIdHistoria;

    @FXML
    private Pane principal;

    @FXML
    private TableColumn<RiesgoVO,String> colFechaRiesgo;

    @FXML
    private DatePicker dFechaIni;

    @FXML
    private Button bGuardar;

    @FXML
    private TextField tRiesgo;

    @FXML
    private Button bAgregaRiesgo;

    @FXML
    private TextField tObjetivo;

    @FXML
    private Button bAgregaHistoria;

    @FXML
    private TextField tResponsableRiesgo;

    @FXML
    private DatePicker dFechaRiesgo;

    @FXML
    private TextField tHistoCompletadas;

    @FXML
    private TextField tPuntCompletados;

    @FXML
    private TextField tNSprint;

    @FXML
    private DatePicker dFechaFin;

    @FXML
    private Pane paneTendencias;

    @FXML
    private TableView<HistoriasVO> tableHistorias;

    @FXML
    private TableColumn<HistoriasVO,String> colDescHistoria;

    @FXML
    private TextField tNHistorias;

    @FXML
    private TableColumn<RiesgoVO,String> colAccionRiesgo;

    @FXML
    private ComboBox<EstadosVO> cEstadoHistoria;

    @FXML
    private TableView<RiesgoVO> tableRiesgos;

    @FXML
    private TableColumn<RiesgoVO,String> colRiesgo;

    @FXML
    private TextField tDescHistoria;

    @FXML
    private TableColumn<RiesgoVO,String> colResponsableRiesgo;

    @FXML
    private ComboBox<CelulaVO> cCelula;

    @FXML
    private TableColumn<HistoriasVO,String> colEstadoHistoria;

    @FXML
    private TextField tHighlight;

    @FXML
    private TextField tNPuntos;

    @FXML
    private TextField tAccionRiesgo;

    @FXML
    void eliminaFactHist(MouseEvent event){
    	if(null != tableHistorias && null != tableHistorias.getSelectionModel() && null != tableHistorias.getSelectionModel().getSelectedItem()){
    		if(event.getButton() == MouseButton.PRIMARY){
    			if(MessagesBox.createConfirm("Error", "¿Esta seguro que desea borrar este registro?")){
    				tableHistorias.getItems().remove(tableHistorias.getSelectionModel().getSelectedIndex());
    			}
    		}
    	}
    }

    @FXML
    void agregaHistoria(ActionEvent event) {
    	HistoriasVO historia = new HistoriasVO();
    	
    	historia.setIdHistoria(tIdHistoria.getText());
    	historia.setDescHistoria(tDescHistoria.getText());
    	historia.setEstado(cEstadoHistoria.getValue().getEstado());
    	ObservableList<HistoriasVO> historias = tableHistorias.getItems();

		historias.add(historia);
		tableHistorias.getSelectionModel().clearSelection();
		tableHistorias.setItems(historias);
		
		tIdHistoria.setText(Constantes.S_EMPTY);
		tDescHistoria.setText(Constantes.S_EMPTY);
		cEstadoHistoria.setValue(null);

    }

    @FXML
    void agregaRiesgo(ActionEvent event) {
    	RiesgoVO riesgo = new RiesgoVO();
    	riesgo.setAccion(tAccionRiesgo.getText());
    	riesgo.setFecha(dFechaRiesgo.getValue().toString());
    	riesgo.setImpacto(tImpactRiesgo.getText());
    	riesgo.setResponsable(tResponsableRiesgo.getText());
    	riesgo.setRiesgo(tRiesgo.getText());
    	ObservableList<RiesgoVO> riesgos = tableRiesgos.getItems();

		riesgos.add(riesgo);
		tableRiesgos.getSelectionModel().clearSelection();
		tableRiesgos.setItems(riesgos);
		
		tAccionRiesgo.setText(Constantes.S_EMPTY);
		dFechaRiesgo.setValue(null);
		tImpactRiesgo.setText(Constantes.S_EMPTY);
		tResponsableRiesgo.setText(Constantes.S_EMPTY);
		tRiesgo.setText(Constantes.S_EMPTY);

    }

    @FXML
    void eliminaFactRiesgo(MouseEvent event){
    	if(null != tableRiesgos && null != tableRiesgos.getSelectionModel() && null != tableRiesgos.getSelectionModel().getSelectedItem()){
    		if(event.getButton() == MouseButton.PRIMARY){
    			if(MessagesBox.createConfirm("Error", "¿Esta seguro que desea borrar este registro?")){
    				tableRiesgos.getItems().remove(tableRiesgos.getSelectionModel().getSelectedIndex());
    			}
    		}
    	}
    }

    @FXML
    void guardarSprint(ActionEvent event) {
    	SprintVO sprint = new SprintVO();
    	sprint.setCelula(cCelula.getValue().getCelula());
    	sprint.setFechaFin(dFechaFin.getValue().toString());
    	sprint.setFechaInicio(dFechaIni.getValue().toString());
    	sprint.setHighlight(tHighlight.getText());   	
    	sprint.setNHUComplete(tHistoCompletadas.getText());
    	sprint.setNHUSprint(tNHistorias.getText());
    	sprint.setNPuntos(tNPuntos.getText());
    	sprint.setNSprint(tNSprint.getText());
    	sprint.setNPuntosComplete(tPuntCompletados.getText());
    	sprint.setObjetivo(tObjetivo.getText());
    	
    	if(null != tableHistorias.getItems() && tableHistorias.getItems().size() > 0){
    		sprint.setHistorias(tableHistorias.getItems());
    	}else{
    		sprint.setHistorias(null);
    	}
    	if(null != tableRiesgos.getItems() && tableRiesgos.getItems().size() > 0){
    		sprint.setRiesgos(tableRiesgos.getItems());
    	}else{
    		sprint.setRiesgos(null);
    	}
    	
    	boolean resultado = DatosAction.modificaRegistro(sprint);
    	
    	if(resultado){
    		MessagesBox.createAlert("Exito", "Registro modificado correctamente.");
    		DatosAction.iniciarAgile(tableSprint);
	  	    Stage stage = (Stage) principal.getScene().getWindow();
	  	    stage.close();
    	}else{
    		MessagesBox.createAlert("Error", "Error al modificar registro, intente nuevamente.");
    	}
    }

    @FXML
    void onlyNumbers(KeyEvent ke) {
        String caracter = ke.getCharacter();
        if (!"0123456789".contains(caracter)) {
            ke.consume();
        }
    }
    
    TableView<SprintVO> tableSprint;
    public void setDataIni(TableView<SprintVO> tableSprint){
    	this.tableSprint = tableSprint;
    	SprintVO sprint = tableSprint.getSelectionModel().getSelectedItem();
    	CelulaVO celula = new CelulaVO();
    	celula.setCelula(sprint.getCelula());
    	cCelula.setValue(celula);
    	
    	LocalDate inicio = LocalDate.parse(sprint.getFechaInicio());
    	dFechaIni.setValue(inicio);
    	
    	LocalDate fin = LocalDate.parse(sprint.getFechaFin());
    	dFechaFin.setValue(fin);
    	
    	tHighlight.setText(sprint.getHighlight());   	
    	tHistoCompletadas.setText(sprint.getNHUComplete());
    	tNHistorias.setText(sprint.getNHUSprint());
    	tNPuntos.setText(sprint.getNPuntos());
    	tNSprint.setText(sprint.getNSprint());
    	tPuntCompletados.setText(sprint.getNPuntosComplete());
    	tObjetivo.setText(sprint.getObjetivo());
    	tableHistorias.setItems(sprint.getHistorias());
    	tableRiesgos.setItems(sprint.getRiesgos());
    	
		LineCharts.createLineChartVel(paneTendencias, tableSprint);
		LineCharts.createLineChartTrh(paneTendencias, tableSprint);
    }
 
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colIdHistoria.setStyle("-fx-alignment: CENTER;");
		colIdHistoria.setCellValueFactory(new PropertyValueFactory<HistoriasVO,String>("idHistoria"));		
		colDescHistoria.setStyle("-fx-alignment: CENTER;");
		colDescHistoria.setCellValueFactory(new PropertyValueFactory<HistoriasVO,String>("descHistoria"));	
		colEstadoHistoria.setStyle("-fx-alignment: CENTER;");
		colEstadoHistoria.setCellValueFactory(new PropertyValueFactory<HistoriasVO,String>("estado"));	
		
		
		colRiesgo.setStyle("-fx-alignment: CENTER;");
		colRiesgo.setCellValueFactory(new PropertyValueFactory<RiesgoVO,String>("riesgo"));		
		colImpactoRiesgo.setStyle("-fx-alignment: CENTER;");
		colImpactoRiesgo.setCellValueFactory(new PropertyValueFactory<RiesgoVO,String>("impacto"));		
		colAccionRiesgo.setStyle("-fx-alignment: CENTER;");
		colAccionRiesgo.setCellValueFactory(new PropertyValueFactory<RiesgoVO,String>("accion"));		
		colResponsableRiesgo.setStyle("-fx-alignment: CENTER;");
		colResponsableRiesgo.setCellValueFactory(new PropertyValueFactory<RiesgoVO,String>("responsable"));
		colFechaRiesgo.setStyle("-fx-alignment: CENTER;");
		colFechaRiesgo.setCellValueFactory(new PropertyValueFactory<RiesgoVO,String>("fecha"));

		DatosAction.cargaComboCelulas(cCelula);
	}

}
