package pet.controller;

import java.net.URL;
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
import javafx.scene.layout.Pane;
import pet.acciones.DatosAction;
import pet.acciones.DatosRallyAction;
import pet.util.CnvString;
import pet.util.BarChartsRally;
import pet.util.Constantes;
import pet.util.LineChartsRally;
import pet.util.MessagesBox;
import pet.vo.CelulaVO;
import pet.vo.HistoriasVO;
import pet.vo.ResumenByCells;
import pet.vo.SprintUmaneVO;
import pet.vo.SprintVO;

public class Rally implements Initializable{

    @FXML
    private Pane principal;

    @FXML
    private ComboBox<?> cSprint;

    @FXML
    private TableColumn<HistoriasVO,String> colIdHistoria;

    @FXML
    private Pane paneTendencias;

    @FXML
    private ComboBox<CelulaVO> cCelula;

    @FXML
    private TableColumn<HistoriasVO,String> colEstadoHistoria;

    @FXML
    private TableView<HistoriasVO> tableHistorias;

    @FXML
    private TableColumn<HistoriasVO,String> colDescHistoria;
    
    @FXML
    private DatePicker fechaInforme;

    @FXML
    private TextField tObjetivo;
    
    @FXML
    private TextField tEntregasPlazo;
    
    @FXML
    private TextField tCalidadEntregas;
    
    @FXML
    private TextField tProductividad;
    
    @FXML
    private Button tExportar;
    
    @FXML
    private Button tExportarUMANE;
    
    @FXML
    void exportarUmane(ActionEvent event) {
    	if(null != fechaInforme && null != fechaInforme.getValue()){
    		SprintUmaneVO titulos = new SprintUmaneVO();
    		titulos.setCelula("Célula");	
    		titulos.setSprint("Sprint");
    		titulos.setFechaInicio("Fecha inicio Sprint");	
    		titulos.setFechaFin("Fecha Fin Sprint");	
    		titulos.setHuPlanif("HU Planificada");	
    		titulos.setPuntosPlanif("Puntos H Planificada");	
    		titulos.setEstadoHu("Estado HU");	
    		titulos.setFechaHUDef("Fecha HU Definida");	
    		titulos.setFechaHUInProgres("Fecha HU en Progreso");	
    		titulos.setFechaHUCompletada("Fecha HU Completada");	
    		titulos.setFechaHUAceptada("Fecha HU Aceptada");	
    		titulos.setFechaHURelease("Fecha HU Release");	
    		titulos.setCantDefectos("Cantidad de Defectos");
    		
    		DatosRallyAction.guardarRegistroUmane(titulos);

    		ObservableList<CelulaVO> celulas = DatosRallyAction.cargaCelulas();
    		
    		for (CelulaVO celula : celulas) {
    			System.out.println("celula: " + celula);
    			ObservableList<SprintVO> sprints = DatosRallyAction.cargaInfoRally(celula.getCelula(), fechaInforme);
    			for (SprintVO sprint : sprints) {
    				if(null != sprint.getHistorias() && sprint.getHistorias().size() > 0){
        	    		for (HistoriasVO historia : sprint.getHistorias()) {
            	    		SprintUmaneVO sprintUmane = new SprintUmaneVO();
            	    		sprintUmane.setCelula(sprint.getCelula());	
            	    		sprintUmane.setSprint(sprint.getNombreSprint());
            	    		sprintUmane.setFechaInicio(sprint.getFechaInicio());	
            	    		sprintUmane.setFechaFin(sprint.getFechaFin());	
            	    		sprintUmane.setHuPlanif(sprint.getNHUSprint());	
            	    		sprintUmane.setPuntosPlanif(sprint.getNPuntos());
            	    		
            	    		sprintUmane.setEstadoHu(historia.getEstado());
            	    		if(null != historia.getFechaCreacion() && !historia.getFechaCreacion().equals(Constantes.S_EMPTY)){
        	    				sprintUmane.setFechaHUDef(CnvString.formatDateStrange(historia.getFechaCreacion()));	
            	    		}else{
            	    			sprintUmane.setFechaHUDef(sprint.getFechaInicio());	
            	    		}
            	    		
            	    		if(null != historia.getInicioCiclo() && !historia.getInicioCiclo().equals(Constantes.S_EMPTY)){
            	    			sprintUmane.setFechaHUInProgres(CnvString.convertFecha(historia.getInicioCiclo()));	
            	    		}else{
            	    			sprintUmane.setFechaHUInProgres(sprint.getFechaInicio());	
            	    		}
            	    		
            	    		if(null != historia.getFinCiclo() && !historia.getFinCiclo().equals(Constantes.S_EMPTY)){
            	    			sprintUmane.setFechaHUCompletada(CnvString.convertFecha(historia.getFinCiclo()));
            	    		}else{
            	    			sprintUmane.setFechaHUCompletada(Constantes.S_EMPTY);
            	    		}
            	    		
            	    		if(null != historia.getFechaAceptacion() && !historia.getFechaAceptacion().equals(Constantes.S_EMPTY)){
            	    			sprintUmane.setFechaHUAceptada(CnvString.formatDateStrange(historia.getFechaAceptacion()));	
            	    		}else{
            	    			sprintUmane.setFechaHUAceptada(Constantes.S_EMPTY);
            	    		}
            	    		
            	    		sprintUmane.setFechaHURelease(Constantes.S_EMPTY);	
            	    		sprintUmane.setCantDefectos(historia.getDefectos());
            	    		
            	    		DatosRallyAction.guardarRegistroUmane(sprintUmane);
    					}
    				}
				}
			}
    		MessagesBox.createAlert("Exito", "Archivo generado correctamente.");
    	}else{
    		MessagesBox.createAlert("Error", "Debe indicar fecha de informe");
    	}
    }
    
    @FXML
    void exportar(ActionEvent event) {
    	if(null != fechaInforme && null != fechaInforme.getValue()){
    		SprintVO titulos = new SprintVO();
    		titulos.setCelula("Celula");
    		titulos.setCycleTime("Cycle Time");
    		titulos.setFechaFin("Fecha fin");
    		titulos.setFechaInicio("Fecha inicio");
    		titulos.setHighlight("Highlight");
    		titulos.setNHUComplete("Historias completadas");
    		titulos.setNHUSprint("Historias totales");
    		titulos.setNPuntos("Puntos totales");
    		titulos.setNPuntosComplete("Puntos completados");
    		titulos.setNSprint("Sprint");
    		titulos.setObjetivo("Objetivo");
    		
    		DatosRallyAction.guardarRegistro(titulos);

    		
    		ObservableList<CelulaVO> celulas = DatosRallyAction.cargaCelulas();
    		
    		for (CelulaVO celula : celulas) {
    			ObservableList<SprintVO> sprints = DatosRallyAction.cargaInfoRally(celula.getCelula(), fechaInforme);
    			for (SprintVO sprint : sprints) {
					DatosRallyAction.guardarRegistro(sprint);
				}
			}
    		MessagesBox.createAlert("Exito", "Archivo generado correctamente.");
    	}else{
    		MessagesBox.createAlert("Error", "Debe indicar fecha de informe");
    	}
    }
    
    @FXML
    void cargaInfo(ActionEvent event) {
    	
//    	if(null != fechaInforme && null != fechaInforme.getValue()){
        	ObservableList<SprintVO> sprints = DatosRallyAction.cargaInfoRally(cCelula.getValue().getCelula(), fechaInforme);
        	tableSprint.getItems().clear();
        	tableSprint.setItems(sprints);
        	tableSprint.getSelectionModel().selectFirst();
    		LineChartsRally.createLineChartVel(paneTendencias, tableSprint);
    		LineChartsRally.createLineChartTrh(paneTendencias, tableSprint);
    		LineChartsRally.createLineChartCycle(paneTendencias, tableSprint);
    		
//    		IndicadoresVO indicadores = DatosRallyAction.cargaIndicTelef(sprints, fechaInforme);
    		
//    		tEntregasPlazo.setText(String.valueOf(indicadores.getPlazos()));
//    		tProductividad.setText(String.valueOf(indicadores.getProductividad())); 
//    	}else{
//    		MessagesBox.createAlert("Error", "Debe indicar fecha de informe");
//    	}
    }
 
	TableView<SprintVO> tableSprint;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tableSprint = new TableView<SprintVO>();		

		DatosAction.cargaComboCelulas(cCelula);
		
		ObservableList<ResumenByCells> sprintsCel = DatosRallyAction.cargaInfoRallyAll();
    	BarChartsRally.createBarChartVel(paneTendencias, sprintsCel);
    	BarChartsRally.createBarChartTrh(paneTendencias, sprintsCel);
//    	BarChartsRally.createBarChartCycle(paneTendencias, sprintsCel);
		
	}

}
