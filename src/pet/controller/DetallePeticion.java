package pet.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import pet.util.Constantes;
import pet.util.PrintLog;
import pet.vo.PeticionesVO;

public class DetallePeticion implements Initializable{

	  @FXML
	    private TextField descripcion;

	    @FXML
	    private TextField estado;

	    @FXML
	    private TextField repCentro;

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
	    private TextField etapaPPM;
	    
	    @FXML
	    private TextField etapaPeticion;

	    @FXML
	    private TextField repRTC;

	    @FXML
	    private Pane principal;

	    @FXML
	    private TextField responsableStgo;

	    @FXML
	    private TextField dudas;

	    @FXML
	    private DatePicker fechaInicio;

	    @FXML
	    private TextField tipoPeticion;

	    @FXML
	    private DatePicker ppmFecha;

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
    
    public void setDataIni(PeticionesVO peticion){
    	Tooltip tooltipCen = new Tooltip();
    	Tooltip tooltipSol = new Tooltip();
    	Tooltip tooltipRtc = new Tooltip();
    	Tooltip tooltipDesc = new Tooltip();
    	
    	descripcion.setText(peticion.getDescripcion().trim());
    	tooltipDesc.setText(peticion.getDescripcion().trim());
    	descripcion.setTooltip(tooltipDesc);
    	
    	repCentro.setText(peticion.getDirCentro().trim());
    	tooltipCen.setText(peticion.getDirCentro().trim());
    	repCentro.setTooltip(tooltipCen);
    	
    	responsableCentro.setText(peticion.getResponsable().trim());
    	tipoPeticion.setText(peticion.getTipoPeticion().trim());
    	idPeticion.setText(String.valueOf(peticion.getIdPeticion()));
    	idDemanda.setText(peticion.getCodDemanda().trim());
    	esfuerzo.setText(peticion.getEsfuerzo().trim());
    	
    	tooltipSol.setText(peticion.getDirRtcOsi().trim());
    	repSolution.setText(peticion.getDirRtcOsi().trim());
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
		
		estado.setText(peticion.getEstado());
		
		
    	etapaPPM.setText(peticion.getEtapaPPM());
    	etapaPeticion.setText(peticion.getEtapaPeticion());
    	responsableStgo.setText(peticion.getResponsableStgo());
    	incidencias.setText(peticion.getIncidencia());
    	
		String sFecKiuwan = peticion.getFechaKiuwan();
		String sFecPPM = peticion.getPpmFecha();
		
		try {
			if(null != sFecKiuwan && !sFecKiuwan.trim().equals(Constantes.S_EMPTY)){
				Date dFechaKi = (Date) formatoDelTexto.parse(sFecKiuwan.trim());
				fechaKiuwan.setValue(dFechaKi.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}
			if(null != sFecPPM && !sFecPPM.trim().equals(Constantes.S_EMPTY)){
				Date dFechaPPM = (Date) formatoDelTexto.parse(sFecPPM);
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
}
