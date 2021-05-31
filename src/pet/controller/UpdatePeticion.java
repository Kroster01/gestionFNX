package pet.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pet.acciones.DatosAction;
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.util.PrintLog;
import pet.vo.CompFXEjecucionVO;
import pet.vo.EstadosVO;
import pet.vo.EtapaPPMVO;
import pet.vo.PeticionesVO;
import pet.vo.RecuentoVO;
import pet.vo.TipoPeticionVO;

public class UpdatePeticion implements Initializable{
	
	@FXML
    private TextField descripcion;

    @FXML
    private ComboBox<EstadosVO> estado;

    @FXML
    private TextField repCentro;

    @FXML
    private Button bPathRTC;

    @FXML
    private TextField incidencias;

    @FXML
    private DatePicker fechaKiuwan;

    @FXML
    private TextField idDemanda;

    @FXML
    private DatePicker fechaFin;

    @FXML
    private TextField esfuerzo;

    @FXML
    private ComboBox<EtapaPPMVO> etapaPPM;
    
    @FXML
    private ComboBox<EtapaPPMVO> etapaPeticion;

    @FXML
    private TextField repRTC;

    @FXML
    private Button bPathSolution;

    @FXML
    private Pane principal;

    @FXML
    private TextField responsableStgo;

    @FXML
    private TextField dudas;

    @FXML
    private Button bGuardar;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private ComboBox<TipoPeticionVO> tipoPeticion;

    @FXML
    private DatePicker ppmFecha;

    @FXML
    private Button bPathCentro;

    @FXML
    private TextField idPeticion;

    @FXML
    private TextField responsableCentro;

    @FXML
    private TextField repSolution;

    @FXML
    private TextField ncs;


    @FXML
    void onlyNumbers(KeyEvent ke) {
        String caracter = ke.getCharacter();
        if (!"0123456789".contains(caracter)) {
            ke.consume();
        }
    }
    
    TableView<PeticionesVO> tablePeticiones;
    RecuentoVO recuentos;
    
    public void setDataIni(TableView<PeticionesVO> tablePeticiones, RecuentoVO recuentos){
    	
    	Tooltip tooltipCen = new Tooltip();
    	Tooltip tooltipSol = new Tooltip();
    	Tooltip tooltipRtc = new Tooltip();
    	Tooltip tooltipDesc = new Tooltip();
    	
    	this.tablePeticiones = tablePeticiones;
    	this.recuentos = recuentos;
    	PeticionesVO peticion = tablePeticiones.getSelectionModel().getSelectedItem();
    	
    	descripcion.setText(peticion.getDescripcion().trim());
    	tooltipDesc.setText(peticion.getDescripcion().trim());
    	descripcion.setTooltip(tooltipDesc);
    	
    	repCentro.setText(peticion.getDirCentro().trim());
    	tooltipCen.setText(peticion.getDirCentro().trim());
    	repCentro.setTooltip(tooltipCen);
    	
    	responsableCentro.setText(peticion.getResponsable().trim());
    	TipoPeticionVO tipoPet = new TipoPeticionVO();
    	tipoPet.setTipo(peticion.getTipoPeticion().trim());
    	tipoPeticion.setValue(tipoPet);
    	idPeticion.setText(String.valueOf(peticion.getIdPeticion()));
    	idDemanda.setText(peticion.getCodDemanda().trim());
    	esfuerzo.setText(peticion.getEsfuerzo().trim());
    	
    	repSolution.setText(peticion.getDirRtcOsi().trim());
    	tooltipSol.setText(peticion.getDirRtcOsi().trim());
    	repSolution.setTooltip(tooltipSol);
    	
    	tooltipRtc.setText(peticion.getDirRtcPap().trim());
    	repRTC.setText(peticion.getDirRtcPap().trim());
    	repRTC.setTooltip(tooltipRtc);
    	
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		String sFecIni = peticion.getFechaIni().trim();
		String sFecFin = peticion.getFechaFin().trim();
		try {
			if(!sFecIni.trim().equals(Constantes.S_EMPTY) && !sFecFin.trim().equals(Constantes.S_EMPTY)){
				Date dFechaIni = (Date) formatoDelTexto.parse(sFecIni);
				fechaInicio.setValue(dFechaIni.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				Date dFechaFin = (Date) formatoDelTexto.parse(sFecFin);
		    	fechaFin.setValue(dFechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}
		} catch (ParseException e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		
    	EstadosVO estadoPet = new EstadosVO();
    	estadoPet.setEstado(peticion.getEstado().trim());
    	estado.setValue(estadoPet);
    	
    	EtapaPPMVO etapa = new EtapaPPMVO();
    	etapa.setEtapa(peticion.getEtapaPPM());
    	etapaPPM.setValue(etapa);
    	
    	EtapaPPMVO etapaPet = new EtapaPPMVO();
    	etapaPet.setEtapa(peticion.getEtapaPeticion());
    	etapaPeticion.setValue(etapaPet);
    	
    	responsableStgo.setText(peticion.getResponsableStgo());
    	incidencias.setText(peticion.getIncidencia());
    	
		String sFecKiuwan = peticion.getFechaKiuwan();
		String sFecPPM = peticion.getPpmFecha();
		
		try {
			if(null != sFecKiuwan &&  !sFecKiuwan.trim().equals(Constantes.S_EMPTY)){
				Date dFechaKi = (Date) formatoDelTexto.parse(sFecKiuwan.trim());
				fechaKiuwan.setValue(dFechaKi.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}
			if(null != sFecPPM && !sFecPPM.trim().equals(Constantes.S_EMPTY)){
				Date dFechaPPM = (Date) formatoDelTexto.parse(sFecPPM.trim());
				ppmFecha.setValue(dFechaPPM.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}
		} catch (ParseException e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
    	
    	ncs.setText(peticion.getNcs());
    	dudas.setText(peticion.getDudas());
    }

    @FXML
    void guardar(ActionEvent event) {
    	
    	CompFXEjecucionVO componentes = new CompFXEjecucionVO();
    	componentes.setIdPeticion(idPeticion);
    	componentes.setIdDemanda(idDemanda);
    	componentes.setTipoPeticion(tipoPeticion);
    	componentes.setDescripcion(descripcion);
    	componentes.setRepCentro(repCentro);
    	componentes.setRepSolution(repSolution);
    	componentes.setRepRTC(repRTC);
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
    	
    	if(componentes.getRepCentro().getText().contains(componentes.getIdDemanda().getText())){
    		CheckBox cModelo2 = new CheckBox();
    		cModelo2.selectedProperty().set(true);
    		componentes.setcModelo2(cModelo2);
    	}
    	
    	
    	boolean resultado = DatosAction.actualizarRegistro(componentes);
    	if(resultado){
    		MessagesBox.createAlert("Exito", "Registro actualizado correctamente.");
    		if(estado.getSelectionModel().getSelectedItem().getEstado().equals(Constantes.sEjecucion) || estado.getSelectionModel().getSelectedItem().getEstado().equals(Constantes.sAplazada)){
    			DatosAction.iniciar(tablePeticiones, recuentos);
    		}else{
    			DatosAction.iniciarEntregadas(tablePeticiones, recuentos);
    		}
    		
	  	    Stage stage = (Stage) idPeticion.getScene().getWindow();
	  	    stage.close();
    	}else{
    		MessagesBox.createAlert("Error", "Error al actualizar registro.");
    	}
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DatosAction.cargaComboTipos(tipoPeticion);
		DatosAction.cargaComboEstado(estado);
		DatosAction.cargaComboEtapas(etapaPPM);
		DatosAction.cargaComboEtapas(etapaPeticion);
	}

}
