package pet.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import pet.acciones.DatosAction;
import pet.acciones.MenusAction;
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.vo.CelulaVO;
import pet.vo.EstadosVO;
import pet.vo.HistoriasVO;
import pet.vo.RiesgoVO;
import pet.vo.SprintVO;

public class Agile implements Initializable{

    @FXML
    private TableColumn<RiesgoVO, String> colImpactoRiesgo;

    @FXML
    private TextField tImpactRiesgo;

    @FXML
    private TableColumn<SprintVO, String> colSprint;

    @FXML
    private TextField tIdHistoria;

    @FXML
    private TableColumn<SprintVO, String> colNPuntComplSprint;

    @FXML
    private TableColumn<HistoriasVO, String> colIdHistoria;

    @FXML
    private TextField tSprintSearch;

    @FXML
    private TableColumn<SprintVO, String> colObjetivoSprint;

    @FXML
    private TableColumn<SprintVO, String> colFIniSprint;

    @FXML
    private Pane principal;

    @FXML
    private TableColumn<SprintVO, String> colHighlightSprint;

    @FXML
    private TableColumn<RiesgoVO, String> colFechaRiesgo;

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
    private Button bDetalleSprint;

    @FXML
    private Button bAgregaHistoria;

    @FXML
    private TableColumn<SprintVO, String> colFFinSprint;

    @FXML
    private TextField tResponsableRiesgo;

    @FXML
    private DatePicker dFechaRiesgo;

    @FXML
    private TextField tHistoCompletadas;

    @FXML
    private TextField tPuntCompletados;

    @FXML
    private TableColumn<SprintVO, String> colNPuntosSprint;

    @FXML
    private TextField tNSprint;

    @FXML
    private TableColumn<SprintVO, String> colNHuComplSprint;

    @FXML
    private ComboBox<CelulaVO> cCelulaSearch;

    @FXML
    private DatePicker dFechaFin;

    @FXML
    private TableView<HistoriasVO> tableHistorias;

    @FXML
    private TableColumn<HistoriasVO, String> colDescHistoria;

    @FXML
    private TableColumn<SprintVO, String> colNHUSprint;

    @FXML
    private TextField tNHistorias;

    @FXML
    private TableColumn<RiesgoVO, String> colAccionRiesgo;

    @FXML
    private ComboBox<EstadosVO> cEstadoHistoria;

    @FXML
    private TableView<RiesgoVO> tableRiesgos;

    @FXML
    private TableColumn<RiesgoVO, String> colRiesgo;

    @FXML
    private TableView<SprintVO> tableSprint;

    @FXML
    private DatePicker dFechaSearch;

    @FXML
    private TextField tDescHistoria;

    @FXML
    private TableColumn<SprintVO, String> colCelulaSprint;

    @FXML
    private TableColumn<RiesgoVO, String> colResponsableRiesgo;

    @FXML
    private ComboBox<CelulaVO> cCelula;

    @FXML
    private TableColumn<HistoriasVO, String> colEstadoHistoria;

    @FXML
    private TextField tHighlight;

    @FXML
    private TextField tNPuntos;

    @FXML
    private TextField tAccionRiesgo;

    @FXML
    private Button bBuscar;

    @FXML
    void onlyNumbers(KeyEvent ke) {
        String caracter = ke.getCharacter();
        if (!"0123456789".contains(caracter)) {
            ke.consume();
        }
    }
    
    @FXML
    void eliminaFactHist(MouseEvent event){
    	if(null != tableHistorias && null != tableHistorias.getSelectionModel() && null != tableHistorias.getSelectionModel().getSelectedItem()){
    		if(event.getButton() == MouseButton.PRIMARY){
//    			if(MessagesBox.createConfirm("Error", "¿Esta seguro que desea borrar este registro?")){
    				tableHistorias.getItems().remove(tableHistorias.getSelectionModel().getSelectedIndex());
//    			}
    		}
    	}
    }
    
    @FXML
    void eliminaFactRiesgo(MouseEvent event){
    	if(null != tableRiesgos && null != tableRiesgos.getSelectionModel() && null != tableRiesgos.getSelectionModel().getSelectedItem()){
    		if(event.getButton() == MouseButton.PRIMARY){
//    			if(MessagesBox.createConfirm("Error", "¿Esta seguro que desea borrar este registro?")){
    				tableRiesgos.getItems().remove(tableRiesgos.getSelectionModel().getSelectedIndex());
//    			}
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
    	
    	boolean resultado = DatosAction.guardarRegistro(sprint);
    	
    	if(resultado){
    		MessagesBox.createAlert("Exito", "Registro ingresado correctamente.");
    		DatosAction.iniciarAgile(tableSprint);
        	cCelula.setValue(null);
        	dFechaFin.setValue(null);
        	dFechaIni.setValue(null);
        	tHighlight.setText(Constantes.S_EMPTY);   	
        	tHistoCompletadas.setText(Constantes.S_EMPTY);
        	tNHistorias.setText(Constantes.S_EMPTY);
        	tNPuntos.setText(Constantes.S_EMPTY);
        	tNSprint.setText(Constantes.S_EMPTY);
        	tPuntCompletados.setText(Constantes.S_EMPTY);
        	tObjetivo.setText(Constantes.S_EMPTY);
//        	tableHistorias.setItems(null);
//        	tableRiesgos.setItems(null);
    	}else{
    		MessagesBox.createAlert("Error", "Error al ingresar registro, intente nuevamente.");
    	}
    }

    @FXML
    void buscaInfo(ActionEvent event) {
		DatosAction.iniciarAgile(tableSprint);
    }
    
    ContextMenu menu;
    MenuItem itemResumenSprint;
 
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colObjetivoSprint.setStyle("-fx-alignment: CENTER;");
		colObjetivoSprint.setCellValueFactory(new PropertyValueFactory<SprintVO,String>("objetivo"));
		colNHUSprint.setStyle("-fx-alignment: CENTER;");
		colNHUSprint.setCellValueFactory(new PropertyValueFactory<SprintVO,String>("nHUSprint"));			
		colNHuComplSprint.setStyle("-fx-alignment: CENTER;");
		colNHuComplSprint.setCellValueFactory(new PropertyValueFactory<SprintVO,String>("nHUComplete"));			
		colNPuntosSprint.setStyle("-fx-alignment: CENTER;");
		colNPuntosSprint.setCellValueFactory(new PropertyValueFactory<SprintVO,String>("nPuntos"));			
		colFFinSprint.setStyle("-fx-alignment: CENTER;");
		colFFinSprint.setCellValueFactory(new PropertyValueFactory<SprintVO,String>("fechaFin"));			
		colCelulaSprint.setStyle("-fx-alignment: CENTER;");
		colCelulaSprint.setCellValueFactory(new PropertyValueFactory<SprintVO,String>("celula"));			
		colHighlightSprint.setStyle("-fx-alignment: CENTER;");
		colHighlightSprint.setCellValueFactory(new PropertyValueFactory<SprintVO,String>("highlight"));			
		colFIniSprint.setStyle("-fx-alignment: CENTER;");
		colFIniSprint.setCellValueFactory(new PropertyValueFactory<SprintVO,String>("fechaInicio"));			
		colNPuntComplSprint.setStyle("-fx-alignment: CENTER;");
		colNPuntComplSprint.setCellValueFactory(new PropertyValueFactory<SprintVO,String>("nPuntosComplete"));	
		colSprint.setStyle("-fx-alignment: CENTER;");
		colSprint.setCellValueFactory(new PropertyValueFactory<SprintVO,String>("nSprint"));	
		
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
		
		DatosAction.cargaComboEstadoHU(cEstadoHistoria);
		DatosAction.cargaComboCelulas(cCelula);
		DatosAction.cargaComboCelulas(cCelulaSearch);
		DatosAction.iniciarAgile(tableSprint);
		
		menu = new ContextMenu();
		MenusAction.creaMenuAbreResumen(tableSprint, itemResumenSprint, menu);
	}

}
