package pet.acciones;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pet.controller.DetallePeticion;
import pet.util.CnvString;
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.util.PrintLog;
import pet.vo.AplicacionVO;
import pet.vo.CelulaVO;
import pet.vo.CompFXEjecucionVO;
import pet.vo.EmpleadoPrevVO;
import pet.vo.EmpleadoVO;
import pet.vo.EstadosVO;
import pet.vo.EtapaPPMVO;
import pet.vo.HistoriasVO;
import pet.vo.PeticionesVO;
import pet.vo.PrevisionVO;
import pet.vo.RecuentoVO;
import pet.vo.RiesgoVO;
import pet.vo.SprintVO;
import pet.vo.TipoPeticionVO;

public class DatosAction {
	

    	
    //Comparator for int, by Number
    static Comparator<? super PeticionesVO> comparador = new Comparator<PeticionesVO>() {
        public int compare(PeticionesVO o1, PeticionesVO o2) {
            return o2.getIdPeticion() - o1.getIdPeticion();
        }
    };
    
    public static ObservableList<EmpleadoVO> getEmpleados(){
    	ObservableList<EmpleadoVO> empleados = FXCollections.observableArrayList();
    	ObservableList<String> lineas = leeFicheros(Constantes.RUTA_ARCHIVO_EMPLEADOS);
    	
    	for (String string : lineas) {
			EmpleadoVO emppleado = crearEmpleadoVO(string);
			empleados.add(emppleado);
		}
    	
    	return empleados;
    }
    
    public static ObservableList<EmpleadoPrevVO> getEmpleadosPrev(){
    	ObservableList<EmpleadoPrevVO> empleados = FXCollections.observableArrayList();
    	ObservableList<String> lineas = leeFicheros(Constantes.RUTA_ARCHIVO_EMPLEADOS);
    	
    	for (String string : lineas) {
    		EmpleadoPrevVO emppleado = crearEmpleadoPrevVO(string);
			empleados.add(emppleado);
		}
    	
    	return empleados;
    }
    
    //TODO
	public static boolean escribeFicheroEmpleados(String linea){
		boolean resultado = false;
		// Se escribe en fichero principal
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_EMPLEADOS, true);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println(linea);
			fichero.close();
			resultado = true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			MessagesBox.createAlert("Error", "Error al ingresar registro.");
			e.printStackTrace();
		}
		return resultado;
	}
	
	@SuppressWarnings("resource")
	public static void eliminaRegEmp(String nroEmpleado){		
		ArrayList<String> registros = new ArrayList<String>();
		String linea;
		// Se guardan todos los registros del fichero menos el seleccionado
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_EMPLEADOS);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			while ((linea = lector.readLine()) != null) {
				String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
				if(!nroEmpleado.equals(elementos[0].trim())){
					registros.add(linea);
				}
			}
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		// Se rescribe el fichero con los registros que han quedado
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_EMPLEADOS);
			PrintWriter pw = new PrintWriter(fichero);
			for (String reg : registros) {
				pw.println(reg);
			}
			fichero.close();
		}catch (Exception ex){
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(ex.getMessage());
			PrintLog.closeHandler(fh);
			ex.printStackTrace();
		}
		
	}
        
    public static boolean incurridoIntMas(String horasPet, String horasInc){    	
    	float porcentaje = 0;
    	porcentaje = Math.round((Float.valueOf(horasInc)*100)/Float.valueOf(horasPet));
    	return porcentaje > 5 || porcentaje == 0;
    }
    
    public static boolean ratioIncMas(String horasPet, String incidencias){    	
    	float porcentaje = 0;
    	porcentaje = Math.round((Float.valueOf(horasPet))/Float.valueOf(incidencias));
    	return porcentaje < 30 || porcentaje > 70;
    }
    
    public static String getRetrabajo(String horasPet, String horasInc){    	
    	float porcentaje = 0;
    	porcentaje = Math.round((Float.valueOf(horasInc)*100)/Float.valueOf(horasPet));
    	return String.valueOf(porcentaje);
    }
    
    public static String getRatio(String horasPet, String incidencias){    	
    	double porcentaje = 0;
    	porcentaje = Math.round((Float.valueOf(horasPet))/Float.valueOf(incidencias));
    	return String.valueOf(porcentaje);
    }
	
	public static boolean validaDatosObligatorios(CompFXEjecucionVO componentes){
		boolean resultado = true;
		
		if(null == componentes.getIdPeticion() || null == componentes.getIdPeticion().getText() || componentes.getIdPeticion().getText().equals(Constantes.S_EMPTY)){
			MessagesBox.createAlert("Error", "Debe informar id de la petición.");
			resultado = false;
			return resultado;
		}
		
		if(null == componentes.getIdDemanda() || null == componentes.getIdDemanda().getText() || componentes.getIdDemanda().getText().equals(Constantes.S_EMPTY)){
			MessagesBox.createAlert("Error", "Debe informar Código de Demanda.");
			resultado = false;
			return resultado;
		}
		
		if(null == componentes.getTipoPeticion().getSelectionModel() || null == componentes.getTipoPeticion().getSelectionModel().getSelectedItem() || 
				null == componentes.getTipoPeticion().getSelectionModel().getSelectedItem().getTipo()){
			MessagesBox.createAlert("Error", "Debe seleccionar tipo de petición.");
			resultado = false;
			return resultado;
		}
		
		if(null == componentes.getEstado().getSelectionModel() || null == componentes.getEstado().getSelectionModel().getSelectedItem() || null == componentes.getEstado().getSelectionModel().getSelectedItem().getEstado()){
			MessagesBox.createAlert("Error", "Debe seleccionar estado de la petición.");
			resultado = false;
			return resultado;
		}
		
		if(null == componentes.getDescripcion() || null == componentes.getDescripcion().getText() || componentes.getDescripcion().getText().equals(Constantes.S_EMPTY)){
			MessagesBox.createAlert("Error", "Debe informar descripción de la petición.");
			resultado = false;
			return resultado;
		}
		
		if(null == componentes.getResponsableCentro() || null == componentes.getResponsableCentro().getText() || componentes.getResponsableCentro().getText().equals(Constantes.S_EMPTY)){
			MessagesBox.createAlert("Error", "Debe informar responsable de centros de la petición.");
			resultado = false;
			return resultado;
		}
		
		if(null == componentes.getResponsableStgo() || null == componentes.getResponsableStgo().getText() || componentes.getResponsableStgo().getText().equals(Constantes.S_EMPTY)){
			MessagesBox.createAlert("Error", "Debe informar responsable de santiago de la petición.");
			resultado = false;
			return resultado;
		}
		
		if(null == componentes.getRepCentro() || null == componentes.getRepCentro().getText() || componentes.getRepCentro().getText().equals(Constantes.S_EMPTY)){
			MessagesBox.createAlert("Error", "Debe informar ruta del repositorio centro.");
			resultado = false;
			return resultado;
		}
		
		if(null == componentes.getFechaInicio() || null == componentes.getFechaInicio().getValue()){
			MessagesBox.createAlert("Error", "Debe informar fecha de inicio de la petición.");
			resultado = false;
			return resultado;
		}
		
		if(null == componentes.getFechaFin() || null == componentes.getFechaFin().getValue()){
			MessagesBox.createAlert("Error", "Debe informar fecha de fin de la petición.");
			resultado = false;
			return resultado;
		}
		
		return resultado;
	}
	
	@SuppressWarnings("resource")
	public static boolean validaExisteReg(String idPeticion){
		String linea;
		boolean existe = false;
		BufferedReader lector = null;
		try {
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_REG);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			while ((linea = lector.readLine()) != null) {
				if (linea.contains(idPeticion)) {
					MessagesBox.createAlert("Error", "Petición ya existe en el registro.");
					existe = true;
				}
			}
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		return existe;
	}
	
	public static boolean escribeFichero(String linea){
		boolean resultado = false;
		// Se escribe en fichero principal
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_REG, true);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println(linea);
			fichero.close();
			resultado = true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			MessagesBox.createAlert("Error", "Error al ingresar registro.");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static boolean escribeFicheroAgile(String linea){
		boolean resultado = false;
		// Se escribe en fichero principal
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_AGILE, true);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println(linea);
			fichero.close();
			resultado = true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			MessagesBox.createAlert("Error", "Error al ingresar registro.");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static boolean escribeFicheroHistorias(String linea){
		boolean resultado = false;
		// Se escribe en fichero principal
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_HIST, true);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println(linea);
			fichero.close();
			resultado = true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			MessagesBox.createAlert("Error", "Error al ingresar registro.");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static boolean escribeFicheroRiesgos(String linea){
		boolean resultado = false;
		// Se escribe en fichero principal
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_RISK, true);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println(linea);
			fichero.close();
			resultado = true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			MessagesBox.createAlert("Error", "Error al ingresar registro.");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static boolean escribeFicheroCMMI(String linea){
		boolean resultado = false;
		// Se escribe en fichero principal
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_CMMI, true);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println(linea);
			fichero.close();
			resultado = true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			MessagesBox.createAlert("Error", "Error al ingresar registro.");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static void prepDataBorrar(TableView<PeticionesVO> tablePeticiones, TableColumn<PeticionesVO, Integer> colIdPet){
		String idPeticion = Constantes.S_EMPTY;
		int row = tablePeticiones.getSelectionModel().getSelectedCells().get(0).getRow();
		idPeticion = colIdPet.getCellData(row).toString();
		eliminaReg(idPeticion);
		eliminaRegCMMI(idPeticion);
	}
	
	public static void limpiaFicheroPrevision(){
		try {
			File file = new File(Constantes.RUTA_ARCHIVO_PREVISION);
			file.delete();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			MessagesBox.createAlert("Error", "Error al ingresar registro.");
			e.printStackTrace();
		}
	}
	
	
	public static boolean escribeFicheroPrevision(String linea){
		boolean resultado = false;
		// Se escribe en fichero principal
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_PREVISION, true);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println(linea);
			fichero.close();
			resultado = true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			MessagesBox.createAlert("Error", "Error al ingresar registro.");
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	public static void prepDataEntrega(TableView<PeticionesVO> tablePeticiones, TableColumn<PeticionesVO, Integer> colIdPet){
		String idPeticion = Constantes.S_EMPTY;
		int row = tablePeticiones.getSelectionModel().getSelectedCells().get(0).getRow();
		idPeticion = colIdPet.getCellData(row).toString();
		eliminaReg(idPeticion);
		eliminaRegCMMI(idPeticion);
		
		PeticionesVO peticion = tablePeticiones.getSelectionModel().getSelectedItem();
		
		StringBuffer registro = new StringBuffer();
		registro.append(peticion.getIdPeticion() + Constantes.S_PUNTO_COMA);
		registro.append(peticion.getCodDemanda() + Constantes.S_PUNTO_COMA);
		registro.append(peticion.getTipoPeticion() + Constantes.S_PUNTO_COMA);
		registro.append(peticion.getDescripcion() + Constantes.S_PUNTO_COMA);
		
		if(null != peticion.getDirCentro() && !peticion.getDirCentro().equals(Constantes.S_EMPTY)){
			registro.append(peticion.getDirCentro() + Constantes.S_PUNTO_COMA);
		}else{
			registro.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
		}
		
		
		if(null != peticion.getDirRtcPap() && !peticion.getDirRtcPap().equals(Constantes.S_EMPTY)){
			registro.append(peticion.getDirRtcPap() + Constantes.S_PUNTO_COMA);
		}else{
			registro.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
		}
		
		if(null != peticion.getDirRtcOsi() && !peticion.getDirRtcOsi().equals(Constantes.S_EMPTY)){
			registro.append(peticion.getDirRtcOsi() + Constantes.S_PUNTO_COMA);
		}else{
			registro.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
		}
		
		registro.append(peticion.getResponsable() + Constantes.S_PUNTO_COMA);
		
		if(null != peticion.getEsfuerzo() && !peticion.getEsfuerzo().equals(Constantes.S_EMPTY)){
			registro.append(peticion.getEsfuerzo() + Constantes.S_PUNTO_COMA);
		}else{
			registro.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
		}
		
		registro.append(peticion.getFechaIni() + Constantes.S_PUNTO_COMA);
		registro.append(peticion.getFechaFin() + Constantes.S_PUNTO_COMA);
		registro.append(Constantes.sEntregada + Constantes.S_PUNTO_COMA);
		
		// Ingresa en el fichero el registro
		MessagesBox.createAlert("Exito", "Petición entregada correctamente.");
		boolean resultado = escribeFichero(registro.toString());
		
		if(resultado){
			StringBuffer registroCCMI = new StringBuffer();
			
			registroCCMI.append(peticion.getIdPeticion() + Constantes.S_PUNTO_COMA);
			
			if(null != peticion.getEtapaPPM()  && !peticion.getEtapaPPM().equals(Constantes.S_EMPTY)){
				registroCCMI.append(peticion.getEtapaPPM() + Constantes.S_PUNTO_COMA);
			}else{
				registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			}
			
			if(null != peticion.getResponsableStgo() && !peticion.getResponsableStgo().equals(Constantes.S_EMPTY)){
				registroCCMI.append(peticion.getResponsableStgo() + Constantes.S_PUNTO_COMA);
			}else{
				registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			}
			
			if(null != peticion.getIncidencia() && !peticion.getIncidencia().equals(Constantes.S_EMPTY)){
				registroCCMI.append(peticion.getIncidencia() + Constantes.S_PUNTO_COMA);
			}else{
				registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			}
			
			if(null != peticion.getFechaKiuwan() && !peticion.getFechaKiuwan().equals(Constantes.S_EMPTY)){
				registroCCMI.append(peticion.getFechaKiuwan()+ Constantes.S_PUNTO_COMA);
			}else{
				registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			}
			
			if(null != peticion.getPpmFecha() && !peticion.getPpmFecha().equals(Constantes.S_EMPTY)){
				registroCCMI.append(peticion.getPpmFecha() + Constantes.S_PUNTO_COMA);
			}else{
				registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			} 
			
			if(null != peticion.getNcs() && !peticion.getNcs().equals(Constantes.S_EMPTY)){
				registroCCMI.append(peticion.getNcs() + Constantes.S_PUNTO_COMA);
			}else{
				registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			}
			
			if(null != peticion.getDudas() && !peticion.getDudas().equals(Constantes.S_EMPTY)){
				registroCCMI.append(peticion.getDudas() + Constantes.S_PUNTO_COMA);
			}else{
				registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			}
			
			if(null != peticion.getEtapaPeticion() && !peticion.getEtapaPeticion().equals(Constantes.S_EMPTY)){
				registroCCMI.append(peticion.getEtapaPeticion() + Constantes.S_PUNTO_COMA);
			}else{
				registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			}

			resultado = escribeFicheroCMMI(registroCCMI.toString());
		}
		
	}
	
	@SuppressWarnings("resource")
	public static void eliminaReg(String idPeticion){		
		ArrayList<String> registros = new ArrayList<String>();
		String linea;
		// Se guardan todos los registros del fichero menos el seleccionado
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_REG);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			while ((linea = lector.readLine()) != null) {
				String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
				if(!idPeticion.equals(elementos[0].trim())){
					registros.add(linea);
				}
			}
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		// Se rescribe el fichero con los registros que han quedado
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_REG);
			PrintWriter pw = new PrintWriter(fichero);
			for (String reg : registros) {
				pw.println(reg);
			}
			fichero.close();
		}catch (Exception ex){
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(ex.getMessage());
			PrintLog.closeHandler(fh);
			ex.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("resource")
	public static void eliminaRegAgile(String celula, String sprint){		
		ArrayList<String> registros = new ArrayList<String>();
		String linea;
		// Se guardan todos los registros del fichero menos el seleccionado
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_AGILE);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			while ((linea = lector.readLine()) != null) {
				String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
				if(!(celula.equals(elementos[0].trim()) && sprint.equals(elementos[1].trim()))){
					registros.add(linea);
				}
			}
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		// Se rescribe el fichero con los registros que han quedado
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_AGILE);
			PrintWriter pw = new PrintWriter(fichero);
			for (String reg : registros) {
				pw.println(reg);
			}
			fichero.close();
		}catch (Exception ex){
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(ex.getMessage());
			PrintLog.closeHandler(fh);
			ex.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("resource")
	public static void eliminaRegHist(String celula, String sprint){		
		ArrayList<String> registros = new ArrayList<String>();
		String linea;
		// Se guardan todos los registros del fichero menos el seleccionado
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_HIST);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			while ((linea = lector.readLine()) != null) {
				String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
				if(!(celula.equals(elementos[0].trim()) && sprint.equals(elementos[1].trim()))){
					registros.add(linea);
				}
			}
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		// Se rescribe el fichero con los registros que han quedado
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_HIST);
			PrintWriter pw = new PrintWriter(fichero);
			for (String reg : registros) {
				pw.println(reg);
			}
			fichero.close();
		}catch (Exception ex){
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(ex.getMessage());
			PrintLog.closeHandler(fh);
			ex.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("resource")
	public static void eliminaRegRisk(String celula, String sprint){		
		ArrayList<String> registros = new ArrayList<String>();
		String linea;
		// Se guardan todos los registros del fichero menos el seleccionado
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_RISK);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			while ((linea = lector.readLine()) != null) {
				String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
				if(!(celula.equals(elementos[0].trim()) && sprint.equals(elementos[1].trim()))){
					registros.add(linea);
				}
			}
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		// Se rescribe el fichero con los registros que han quedado
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_RISK);
			PrintWriter pw = new PrintWriter(fichero);
			for (String reg : registros) {
				pw.println(reg);
			}
			fichero.close();
		}catch (Exception ex){
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(ex.getMessage());
			PrintLog.closeHandler(fh);
			ex.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("resource")
	public static void eliminaRegCMMI(String idPeticion){		
		ArrayList<String> registros = new ArrayList<String>();
		String linea;
		// Se guardan todos los registros del fichero menos el seleccionado
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_CMMI);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			while ((linea = lector.readLine()) != null) {
				String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
				if(!idPeticion.equals(elementos[0].trim())){
					registros.add(linea);
				}
			}
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		// Se rescribe el fichero con los registros que han quedado
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_CMMI);
			PrintWriter pw = new PrintWriter(fichero);
			for (String reg : registros) {
				pw.println(reg);
			}
			fichero.close();
		}catch (Exception ex){
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(ex.getMessage());
			PrintLog.closeHandler(fh);
			ex.printStackTrace();
		}
		
	}
	
	public static boolean actualizarRegistro(CompFXEjecucionVO componentes){
		boolean resultado = false;
		if(null != componentes.getIdPeticion() && null != componentes.getIdPeticion().getText() && !componentes.getIdPeticion().getText().equals(Constantes.S_EMPTY)){
			eliminaReg(componentes.getIdPeticion().getText());
			eliminaRegCMMI(componentes.getIdPeticion().getText());
			resultado = guardarRegistro(componentes);
		}
		return resultado;
	}
	
	public static boolean guardarRegistro(PrevisionVO prevision){
		boolean resultado = false;
		
		StringBuffer registro = new StringBuffer();
		registro.append(prevision.getNroEmpleado() + Constantes.S_PUNTO_COMA);
		registro.append(prevision.getFecha() + Constantes.S_PUNTO_COMA);
		registro.append(prevision.getHoras() + Constantes.S_PUNTO_COMA);

		// Ingresa en el fichero el registro
		resultado = escribeFicheroPrevision(registro.toString());

		
		return resultado;
	}
	
	
	public static boolean guardarRegistro(SprintVO sprint){
		boolean resultado = false;
		
		StringBuffer registro = new StringBuffer();
		registro.append(sprint.getCelula() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getNSprint() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getObjetivo() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getHighlight() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getNHUSprint() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getNPuntos() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getNHUComplete() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getNPuntosComplete() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getFechaInicio() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getFechaFin() + Constantes.S_PUNTO_COMA);
	
		// Ingresa en el fichero el registro
		resultado = escribeFicheroAgile(registro.toString());
		
		if(null != sprint.getHistorias()){
			StringBuffer strHistorias = new StringBuffer();
			strHistorias.append(sprint.getCelula() + Constantes.S_COMA);
			strHistorias.append(sprint.getNSprint() + Constantes.S_PUNTO_COMA);
			
			ObservableList<HistoriasVO> historias = sprint.getHistorias();
			for (HistoriasVO historiasVO : historias) {
				strHistorias.append(historiasVO.getIdHistoria() + Constantes.S_COMA);
				strHistorias.append(historiasVO.getDescHistoria() + Constantes.S_COMA);
				strHistorias.append(historiasVO.getEstado() + Constantes.S_PUNTO_COMA);
			}
			
			escribeFicheroHistorias(strHistorias.toString());
		}
		
		if(null != sprint.getRiesgos()){
			StringBuffer strRiesgos = new StringBuffer();
			strRiesgos.append(sprint.getCelula() + Constantes.S_COMA);
			strRiesgos.append(sprint.getNSprint() + Constantes.S_PUNTO_COMA);
			
			ObservableList<RiesgoVO> riesgos = sprint.getRiesgos();
			for (RiesgoVO riesgoVO : riesgos) {
				strRiesgos.append(riesgoVO.getRiesgo() + Constantes.S_COMA);
				strRiesgos.append(riesgoVO.getImpacto() + Constantes.S_COMA);
				strRiesgos.append(riesgoVO.getAccion() + Constantes.S_COMA);
				strRiesgos.append(riesgoVO.getResponsable() + Constantes.S_COMA);
				strRiesgos.append(riesgoVO.getFecha() + Constantes.S_PUNTO_COMA);
			}
			
			escribeFicheroRiesgos(strRiesgos.toString());
		}
		
		return resultado;
	}
	
	public static boolean modificaRegistro(SprintVO sprint){
		boolean resultado = false;
		
		StringBuffer registro = new StringBuffer();
		registro.append(sprint.getCelula() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getNSprint() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getObjetivo() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getHighlight() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getNHUSprint() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getNPuntos() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getNHUComplete() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getNPuntosComplete() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getFechaInicio() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getFechaFin() + Constantes.S_PUNTO_COMA);
	
		// Ingresa en el fichero el registro
		eliminaRegAgile(sprint.getCelula(), sprint.getNSprint());
		eliminaRegHist(sprint.getCelula(), sprint.getNSprint());
		eliminaRegRisk(sprint.getCelula(), sprint.getNSprint());
		
		resultado = escribeFicheroAgile(registro.toString());
		
		if(null != sprint.getHistorias()){
			StringBuffer strHistorias = new StringBuffer();
			strHistorias.append(sprint.getCelula() + Constantes.S_COMA);
			strHistorias.append(sprint.getNSprint() + Constantes.S_PUNTO_COMA);
			
			ObservableList<HistoriasVO> historias = sprint.getHistorias();
			for (HistoriasVO historiasVO : historias) {
				strHistorias.append(historiasVO.getIdHistoria() + Constantes.S_COMA);
				strHistorias.append(historiasVO.getDescHistoria() + Constantes.S_COMA);
				strHistorias.append(historiasVO.getEstado() + Constantes.S_PUNTO_COMA);
			}
			
			escribeFicheroHistorias(strHistorias.toString());
		}
		
		if(null != sprint.getRiesgos()){
			StringBuffer strRiesgos = new StringBuffer();
			strRiesgos.append(sprint.getCelula() + Constantes.S_COMA);
			strRiesgos.append(sprint.getNSprint() + Constantes.S_PUNTO_COMA);
			
			ObservableList<RiesgoVO> riesgos = sprint.getRiesgos();
			for (RiesgoVO riesgoVO : riesgos) {
				strRiesgos.append(riesgoVO.getRiesgo() + Constantes.S_COMA);
				strRiesgos.append(riesgoVO.getImpacto() + Constantes.S_COMA);
				strRiesgos.append(riesgoVO.getAccion() + Constantes.S_COMA);
				strRiesgos.append(riesgoVO.getResponsable() + Constantes.S_COMA);
				strRiesgos.append(riesgoVO.getFecha() + Constantes.S_PUNTO_COMA);
			}
			
			escribeFicheroRiesgos(strRiesgos.toString());
		}
		
		return resultado;
	}
	
	
	
	public static boolean guardarRegistro(CompFXEjecucionVO componentes){
		boolean resultado = false;
		
		if(validaDatosObligatorios(componentes) && !validaExisteReg(componentes.getIdPeticion().getText())){
			StringBuffer registro = new StringBuffer();
			registro.append(componentes.getIdPeticion().getText() + Constantes.S_PUNTO_COMA);
			registro.append(componentes.getIdDemanda().getText() + Constantes.S_PUNTO_COMA);
			registro.append(componentes.getTipoPeticion().getSelectionModel().getSelectedItem().getTipo() + Constantes.S_PUNTO_COMA);
			registro.append(componentes.getDescripcion().getText() + Constantes.S_PUNTO_COMA);
			
			
			if(null != componentes.getcModelo2() && componentes.getcModelo2().isSelected()){
				if(!componentes.getRepCentro().getText().contains(componentes.getIdDemanda().getText())){
					registro.append(componentes.getRepCentro().getText() + Constantes.S_DOUBLE_SLASH + componentes.getIdDemanda().getText() + Constantes.S_PUNTO_COMA);
				}else{
					registro.append(componentes.getRepCentro().getText() + Constantes.S_PUNTO_COMA);
				}
			}else{
				if(!componentes.getRepCentro().getText().contains(componentes.getIdPeticion().getText())){
					registro.append(componentes.getRepCentro().getText() + Constantes.S_DOUBLE_SLASH + componentes.getIdPeticion().getText() + Constantes.S_PUNTO_COMA);
				}else{
					registro.append(componentes.getRepCentro().getText() + Constantes.S_PUNTO_COMA);
				}
			}
			
			if(null != componentes.getRepSolution() && null != componentes.getRepSolution().getText() && !componentes.getRepSolution().getText().equals(Constantes.S_EMPTY)){
				registro.append(componentes.getRepSolution().getText() + Constantes.S_PUNTO_COMA);
			}else{
				registro.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			}
			
			if(null != componentes.getRepRTC() && null != componentes.getRepRTC().getText() && !componentes.getRepRTC().getText().equals(Constantes.S_EMPTY)){
				registro.append(componentes.getRepRTC().getText() + Constantes.S_PUNTO_COMA);
			}else{
				registro.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			}
			
			if(null != componentes.getResponsableCentro() && !componentes.getResponsableCentro().equals(Constantes.S_EMPTY)){
				registro.append(componentes.getResponsableCentro().getText() + Constantes.S_PUNTO_COMA);
			}else{
				registro.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			}
			
			
			if(null != componentes.getEsfuerzo() && null != componentes.getEsfuerzo().getText() && !componentes.getEsfuerzo().getText().equals(Constantes.S_EMPTY)){
				registro.append(componentes.getEsfuerzo().getText() + Constantes.S_PUNTO_COMA);
			}else{
				registro.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
			}
			
			
			registro.append(componentes.getFechaInicio().getValue() + Constantes.S_PUNTO_COMA);
			registro.append(componentes.getFechaFin().getValue() + Constantes.S_PUNTO_COMA);
			registro.append(componentes.getEstado().getSelectionModel().getSelectedItem().getEstado() + Constantes.S_PUNTO_COMA);
			
			// Ingresa en el fichero el registro
			resultado = escribeFichero(registro.toString());
			
			if(resultado){
				StringBuffer registroCCMI = new StringBuffer();
				
				registroCCMI.append(componentes.getIdPeticion().getText() + Constantes.S_PUNTO_COMA);
				
				if(null != componentes.getEtapaPPM() && null != componentes.getEtapaPPM().getSelectionModel().getSelectedItem() 
						&& null != componentes.getEtapaPPM().getSelectionModel().getSelectedItem().getEtapa() && !componentes.getEtapaPPM().getSelectionModel().getSelectedItem().getEtapa().equals(Constantes.S_EMPTY)){
					registroCCMI.append(componentes.getEtapaPPM().getSelectionModel().getSelectedItem().getEtapa() + Constantes.S_PUNTO_COMA);
				}else{
					registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
				}
				
				if(null != componentes.getResponsableStgo() && null != componentes.getResponsableStgo().getText() && !componentes.getResponsableStgo().getText().equals(Constantes.S_EMPTY)){
					registroCCMI.append(componentes.getResponsableStgo().getText() + Constantes.S_PUNTO_COMA);
				}else{
					registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
				}
				
				if(null != componentes.getIncidencias() && null != componentes.getIncidencias().getText() && !componentes.getIncidencias().getText().equals(Constantes.S_EMPTY)){
					registroCCMI.append(componentes.getIncidencias().getText() + Constantes.S_PUNTO_COMA);
				}else{
					registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
				}
				
				if(null != componentes.getFechaKiuwan() && null != componentes.getFechaKiuwan().getValue() && !componentes.getFechaKiuwan().getValue().equals(Constantes.S_EMPTY)){
					registroCCMI.append(componentes.getFechaKiuwan().getValue() + Constantes.S_PUNTO_COMA);
				}else{
					registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
				}
				
				if(null != componentes.getPpmFecha() && null != componentes.getPpmFecha().getValue() && !componentes.getPpmFecha().getValue().equals(Constantes.S_EMPTY)){
					registroCCMI.append(componentes.getPpmFecha().getValue() + Constantes.S_PUNTO_COMA);
				}else{
					registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
				} 
				
				if(null != componentes.getNcs() && null != componentes.getNcs().getText() && !componentes.getNcs().getText().equals(Constantes.S_EMPTY)){
					registroCCMI.append(componentes.getNcs().getText() + Constantes.S_PUNTO_COMA);
				}else{
					registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
				}
				
				if(null != componentes.getDudas() && null != componentes.getDudas().getText() && !componentes.getDudas().getText().equals(Constantes.S_EMPTY)){
					registroCCMI.append(componentes.getDudas().getText() + Constantes.S_PUNTO_COMA);
				}else{
					registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
				}
				
				if(null != componentes.getEtapaPeticion() && null != componentes.getEtapaPeticion().getSelectionModel().getSelectedItem() 
						&& null != componentes.getEtapaPeticion().getSelectionModel().getSelectedItem().getEtapa() && !componentes.getEtapaPeticion().getSelectionModel().getSelectedItem().getEtapa().equals(Constantes.S_EMPTY)){
					registroCCMI.append(componentes.getEtapaPeticion().getSelectionModel().getSelectedItem().getEtapa() + Constantes.S_PUNTO_COMA);
				}else{
					registroCCMI.append(Constantes.S_SPACE + Constantes.S_PUNTO_COMA);
				}

				resultado = escribeFicheroCMMI(registroCCMI.toString());
			}
		}
		
		return resultado;
	}
	
	public static boolean creaLineaBaseCentro(TextField idPeticion, TipoPeticionVO tipoPeticion, TextField  repCentro){
		boolean resultado = false;
		String[] datosLineaBase = new String[3];
		datosLineaBase[0] = idPeticion.getText();
		datosLineaBase[1] = tipoPeticion.getTipo();
		datosLineaBase[2] = repCentro.getText();
		resultado = LineaBaseAction.creacionLinCentro(datosLineaBase);
		return resultado;
	}
	
	public static boolean creaLineaBaseSolution(TextField codDemanda, TextField  repSolution){
		boolean resultado = false;
		
		String[] datosLineaBase = new String[2];
		datosLineaBase[0] = codDemanda.getText();
		datosLineaBase[1] = repSolution.getText();
		
		resultado = LineaBaseAction.creacionLinSolution(datosLineaBase);
		
		return resultado;
	}
	
	public static PeticionesVO crearPeticionVO(String linea){
		PeticionesVO peticion = new PeticionesVO();
		String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
		peticion.setIdPeticion(Integer.valueOf(elementos[0].trim()));
		peticion.setCodDemanda(elementos[1].trim());
		peticion.setTipoPeticion(elementos[2].trim());
		peticion.setDescripcion(elementos[3].trim());
		peticion.setDirCentro(elementos[4].trim());
		peticion.setDirRtcOsi(elementos[5].trim());
		peticion.setDirRtcPap(elementos[6].trim());
		peticion.setResponsable(elementos[7].trim());
		peticion.setEsfuerzo(elementos[8].trim());
		peticion.setFechaIni(elementos[9].trim());
		peticion.setFechaFin(elementos[10].trim());
		peticion.setEstado(elementos[11].trim());
		return peticion;
	}
	
	public static EmpleadoPrevVO crearEmpleadoPrevVO(String linea){
		EmpleadoPrevVO empleado = new EmpleadoPrevVO();
		String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
		empleado.setNroEmpleado(elementos[0].trim());
		empleado.setNomEmpleado(elementos[1].trim());
		empleado.setCelula(elementos[2].trim());
		return empleado;
	}
	
	
	public static EmpleadoVO crearEmpleadoVO(String linea){
		EmpleadoVO empleado = new EmpleadoVO();
		String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
		empleado.setNroEmpleado(elementos[0].trim());
		empleado.setNomEmpleado(elementos[1].trim());
		empleado.setCelula(elementos[2].trim());
		return empleado;
	}
	
	public static PrevisionVO obtienePrevisionVO(String linea){
		PrevisionVO prevision = new PrevisionVO();
		String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
		
		prevision.setNroEmpleado(Integer.valueOf(elementos[0].trim()));
		prevision.setFecha(CnvString.getDate(CnvString.formatDateStrangePrev(elementos[1].trim()), "dd-MM-yyyy"));
		if(elementos.length > 2){
			prevision.setHoras(elementos[2].trim());
		}else{
			prevision.setHoras(Constantes.S_EMPTY);
		}
		
		return prevision;
	}
	
	public static SprintVO obtieneSprintVO(String linea){
		SprintVO sprint = new SprintVO();
		String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
		sprint.setCelula(elementos[0].trim());
		sprint.setNSprint(elementos[1].trim());
		sprint.setObjetivo(elementos[2].trim());
		sprint.setHighlight(elementos[3].trim());
		sprint.setNHUSprint(elementos[4].trim());
		sprint.setNPuntos(elementos[5].trim());
		sprint.setNHUComplete(elementos[6].trim());
		sprint.setNPuntosComplete(elementos[7].trim());
		sprint.setFechaInicio(elementos[8].trim());
		sprint.setFechaFin(elementos[9].trim());
		return sprint;
	}
	
	public static ObservableList<HistoriasVO> obtieneHistoriaVO(String linea){
		ObservableList<HistoriasVO> historias = FXCollections.observableArrayList();
		String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
		
		for (int i = 0; i < elementos.length; i++) {
			if(i > 0){
				String[] elemHist = elementos[i].split(Constantes.S_COMA);
				HistoriasVO historia = new HistoriasVO();
				historia.setIdHistoria(elemHist[0].trim());
				historia.setDescHistoria(elemHist[1].trim());
				historia.setEstado(elemHist[2].trim());
				
				historias.add(historia);
			}
		}
		
		return historias;
	}
	
	public static HistoriasVO obtieneHistoriasVO(String linea){
		HistoriasVO historia = new HistoriasVO();
		String[] elementos = linea.split(Constantes.S_COMA);
		historia.setIdHistoria(elementos[0].trim());
		historia.setDescHistoria(elementos[1].trim());
		historia.setEstado(elementos[2].trim());
		return historia;
	}
	
	public static RiesgoVO obtieneRiesgoVO(String linea){
		RiesgoVO riesgo = new RiesgoVO();
		String[] elementos = linea.split(Constantes.S_COMA);
		riesgo.setRiesgo(elementos[0].trim());
		riesgo.setImpacto(elementos[1].trim());
		riesgo.setAccion(elementos[2].trim());
		riesgo.setResponsable(elementos[3].trim());
		riesgo.setFecha(elementos[4].trim());
		return riesgo;
	}
	
	public static void agregaDataCMMI(String linea, PeticionesVO peticion){
		String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
		
		peticion.setEtapaPPM(elementos[1].trim());
		peticion.setResponsableStgo(elementos[2].trim());
		peticion.setIncidencia(elementos[3].trim()); 
		peticion.setFechaKiuwan(elementos[4].trim());
		peticion.setPpmFecha(elementos[5].trim()); 
		peticion.setNcs(elementos[6].trim());
		peticion.setDudas(elementos[7].trim());
		peticion.setEtapaPeticion(elementos[8].trim());
	}
	
	@SuppressWarnings("resource")
	public static void buscarRegistro(TableView<PeticionesVO> tablePeticiones, TextField tBusqueda){
		String linea;
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_REG);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			ObservableList<PeticionesVO> peticiones = FXCollections.observableArrayList();

			while ((linea = lector.readLine()) != null) {
				if(linea.toLowerCase().contains(tBusqueda.getText().toLowerCase())){
					PeticionesVO peticion = crearPeticionVO(linea);
					if(peticion.getEstado().equals(Constantes.sEjecucion) || peticion.getTipoPeticion().equals(Constantes.sAplazada)){
						peticiones.add(peticion);
					}
				}
			}
			FXCollections.sort(peticiones, comparador);
			tablePeticiones.getSelectionModel().clearSelection();
			tablePeticiones.setItems(peticiones);
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public static void buscarRegistroEnt(TableView<PeticionesVO> tablePeticiones, TextField tBusqueda){
		String linea;
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_REG);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			ObservableList<PeticionesVO> peticiones = FXCollections.observableArrayList();

			while ((linea = lector.readLine()) != null) {
				if(linea.toLowerCase().contains(tBusqueda.getText().toLowerCase())){
					PeticionesVO peticion = crearPeticionVO(linea);
					if(peticion.getEstado().equals(Constantes.sEntregada)){
						peticiones.add(peticion);
					}
				}
			}
			FXCollections.sort(peticiones, comparador);
			tablePeticiones.getSelectionModel().clearSelection();
			tablePeticiones.setItems(peticiones);
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
	}
	
	public static void selectDirectorio(TextField directorio){
		DirectoryChooser directory = new DirectoryChooser();
		directory.setTitle(Constantes.S_TITULO);
		File selectedFile = directory.showDialog(null);
		if(null != selectedFile){
			directorio.setText(selectedFile.toString());
		}
	}
	
	
	public static void cargaComboTipos(ComboBox<TipoPeticionVO> tipoPeticion){
		ObservableList<TipoPeticionVO> tipos = FXCollections.observableArrayList();
		BufferedReader lector = null;
		FileInputStream fis;
		String linea;
		try {
			fis = new FileInputStream(Constantes.RUTA_ARCHIVO_TIPO);
			
			lector = new BufferedReader(new FileReader(fis.getFD()));
			
			while ((linea = lector.readLine()) != null) {
				TipoPeticionVO tipoPet = new TipoPeticionVO();
				tipoPet.setTipo(linea);
				tipos.add(tipoPet);
			}
			tipoPeticion.setItems(tipos);
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		} 

	}
	
	public static void cargaComboEstado(ComboBox<EstadosVO> cbEstado){
		ObservableList<EstadosVO> estados = FXCollections.observableArrayList();
		BufferedReader lector = null;
		FileInputStream fis;
		String linea;
		try {
			fis = new FileInputStream(Constantes.RUTA_ARCHIVO_ESTADO);
			
			lector = new BufferedReader(new FileReader(fis.getFD()));
			
			while ((linea = lector.readLine()) != null) {
				EstadosVO estadoPet = new EstadosVO();
				estadoPet.setEstado(linea);
				estados.add(estadoPet);
			}
			cbEstado.setItems(estados);
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		} 

	}
	
	public static void cargaComboEstadoFenix(ComboBox<EstadosVO> cbEstado){
		ObservableList<EstadosVO> estados = FXCollections.observableArrayList();
		BufferedReader lector = null;
		FileInputStream fis;
		String linea;
		try {
			fis = new FileInputStream(Constantes.RUTA_ARCHIVO_ESTADO_FENIX);
			
			lector = new BufferedReader(new FileReader(fis.getFD()));
			
			while ((linea = lector.readLine()) != null) {
				EstadosVO estadoPet = new EstadosVO();
				estadoPet.setEstado(linea);
				estados.add(estadoPet);
			}
			cbEstado.setItems(estados);
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		} 

	}
	
	public static void cargaComboAplicaciones(ComboBox<AplicacionVO> cbEstado){
		ObservableList<AplicacionVO> estados = FXCollections.observableArrayList();
		BufferedReader lector = null;
		FileInputStream fis;
		String linea;
		try {
			fis = new FileInputStream(Constantes.RUTA_ARCHIVO_APP);
			
			lector = new BufferedReader(new FileReader(fis.getFD()));
			
			while ((linea = lector.readLine()) != null) {
				AplicacionVO estadoPet = new AplicacionVO();
				estadoPet.setAplicacion(linea);
				estados.add(estadoPet);
			}
			cbEstado.setItems(estados);
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
	}
	
	public static void cargaComboEstadoHU(ComboBox<EstadosVO> cbEstado){
		ObservableList<EstadosVO> estados = FXCollections.observableArrayList();
		BufferedReader lector = null;
		FileInputStream fis;
		String linea;
		try {
			fis = new FileInputStream(Constantes.RUTA_ARCHIVO_EHU);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			
			while ((linea = lector.readLine()) != null) {
				EstadosVO estadoPet = new EstadosVO();
				estadoPet.setEstado(linea);
				estados.add(estadoPet);
			}
			cbEstado.setItems(estados);
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
	}
	
	public static void cargaComboCelulas(ComboBox<CelulaVO> cbEstado){
		ObservableList<CelulaVO> estados = FXCollections.observableArrayList();
		BufferedReader lector = null;
		FileInputStream fis;
		String linea;
		try {
			fis = new FileInputStream(Constantes.RUTA_ARCHIVO_CEL_RALLY_ALL);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			
			while ((linea = lector.readLine()) != null) {
				CelulaVO estadoPet = new CelulaVO();
				estadoPet.setCelula(linea);
				estados.add(estadoPet);
			}
			cbEstado.setItems(estados);
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
	}
	
	public static void cargaComboEtapas(ComboBox<EtapaPPMVO> cbEtapa){
		cbEtapa.setItems(cargaListaEtapas());
	}
	
	public static ObservableList<EtapaPPMVO> cargaListaEtapas(){
		ObservableList<EtapaPPMVO> etapas = FXCollections.observableArrayList();
		BufferedReader lector = null;
		FileInputStream fis;
		String linea;
		try {
			fis = new FileInputStream(Constantes.RUTA_ARCHIVO_ETAPA);
			
			lector = new BufferedReader(new FileReader(fis.getFD()));
			
			while ((linea = lector.readLine()) != null) {
				EtapaPPMVO etapaPet = new EtapaPPMVO();
				String[] datos = linea.split(Constantes.S_PUNTO_COMA);
				etapaPet.setIdEtapa(datos[0]);
				etapaPet.setEtapa(datos[1]);
				etapas.add(etapaPet);
			}
			lector.close();
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
			return etapas;
		}

		return etapas;
	}
	

	// Metodo para iniciar la Ventana principal al retornar.
	@SuppressWarnings("resource")
	public static boolean iniciar(TableView<PeticionesVO> tabla, RecuentoVO recuentos) {
		String linea;
		String lineaCmmi;
		
		int aplazadas = 0;
		int entregadas = 0;
		int hoy = 0;
		int tres = 0;
		int cinco = 0;
		int ejecucion = 0;
		
		Date fechaActual = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		String fechaSistema = formateador.format(fechaActual);
		
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_REG);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			ObservableList<PeticionesVO> peticiones = FXCollections.observableArrayList();

			while ((linea = lector.readLine()) != null) {
				PeticionesVO peticion = crearPeticionVO(linea);
      			 String fechaPet = peticion.getFechaFin();
      			 String estadoPet = peticion.getEstado();
				
				if(estadoPet.equals(Constantes.sEntregada)){
					entregadas ++;
				}else if(estadoPet.equals(Constantes.sAplazada)){
					aplazadas ++;
				}else{
					if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
						hoy ++;
					 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
						 tres ++;
					 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
						 cinco ++;
					 }else if(estadoPet.equals(Constantes.sEjecucion)){
						 ejecucion ++;
					 }
				}
				
				if(estadoPet.equals(Constantes.sEjecucion) || estadoPet.equals(Constantes.sAplazada)){
					
					BufferedReader lectorCmmi = null;
					FileInputStream fisCmmi = new FileInputStream(Constantes.RUTA_ARCHIVO_CMMI);
					lectorCmmi = new BufferedReader(new FileReader(fisCmmi.getFD()));
					
					while ((lineaCmmi = lectorCmmi.readLine()) != null) {
						String idPeticion = lineaCmmi.split(Constantes.S_PUNTO_COMA)[0].trim();
						if(!lineaCmmi.equals(Constantes.S_EMPTY) && String.valueOf(peticion.getIdPeticion()).equals(idPeticion)){
							agregaDataCMMI(lineaCmmi, peticion);
						}
					}
					peticiones.add(peticion);
				}
			}
			
			if(null != recuentos.gettAplazadas()){
				recuentos.gettAplazadas().setText(String.valueOf(aplazadas));
			}
			if(null != recuentos.gettEntregadas()){
				recuentos.gettEntregadas().setText(String.valueOf(entregadas));
			}
			if(null != recuentos.gettPeticiones3()){
				recuentos.gettPeticiones3().setText(String.valueOf(tres));
			}
			if(null != recuentos.gettPeticiones5()){
				recuentos.gettPeticiones5().setText(String.valueOf(cinco));
			}
			if(null != recuentos.gettPeticionesHoy()){
				recuentos.gettPeticionesHoy().setText(String.valueOf(hoy));
			}
			if(null != recuentos.gettEjecucion()){
				recuentos.gettEjecucion().setText(String.valueOf(ejecucion));
			}
			
			FXCollections.sort(peticiones, comparador);
			tabla.getItems().clear();
			tabla.setItems(peticiones);
			fis.close();
			return true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
			return false;
		}
	}
	
	// Metodo para iniciar la Ventana principal al retornar.
	@SuppressWarnings("resource")
	public static boolean iniciarAgile(TableView<SprintVO> tabla) {
		String linea = Constantes.S_EMPTY;
		String lineaHist = Constantes.S_EMPTY;
//		String lineaRisk = Constantes.S_EMPTY;
		
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_AGILE);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			ObservableList<SprintVO> peticiones = FXCollections.observableArrayList();

			while ((linea = lector.readLine()) != null) {
				SprintVO sprint = obtieneSprintVO(linea);
				
				BufferedReader lectorH = null;
				FileInputStream fisH = new FileInputStream(Constantes.RUTA_ARCHIVO_HIST);
				lectorH = new BufferedReader(new FileReader(fisH.getFD()));
				ObservableList<HistoriasVO> historias = FXCollections.observableArrayList();
				while ((lineaHist = lectorH.readLine()) != null) {
					if(lineaHist.split(Constantes.S_PUNTO_COMA)[0].split(Constantes.S_COMA)[0].equals(sprint.getCelula()) 
							&& lineaHist.split(Constantes.S_PUNTO_COMA)[0].split(Constantes.S_COMA)[1].equals(sprint.getNSprint())){
						historias = obtieneHistoriaVO(lineaHist);
						
					}
				}
				
				sprint.setHistorias(historias);
				peticiones.add(sprint);
			}
			tabla.getItems().clear();
			tabla.setItems(peticiones);
			fis.close();
			return true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
			return false;
		}
	}
	
	// Metodo para iniciar la Ventana principal al retornar.
	@SuppressWarnings("resource")
	public static ObservableList<PrevisionVO> iniciarPrevision() {
		String linea = Constantes.S_EMPTY;
		ObservableList<PrevisionVO> previsiones = FXCollections.observableArrayList();
		
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_PREVISION);
			lector = new BufferedReader(new FileReader(fis.getFD()));

			while ((linea = lector.readLine()) != null) {
				PrevisionVO prevision = obtienePrevisionVO(linea);
				previsiones.add(prevision);
			}
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		return previsiones;
	}
	
	// Metodo para iniciar la Ventana principal al retornar.
	//TODO
	@SuppressWarnings("resource")
	public static ObservableList<String> obtieneFeriados() {
		String linea = Constantes.S_EMPTY;
		ObservableList<String> feriados = FXCollections.observableArrayList();
		
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_FERIADOS);
			lector = new BufferedReader(new FileReader(fis.getFD()));

			while ((linea = lector.readLine()) != null) {
				feriados.add(linea);
			}
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		return feriados;
	}
	
	@SuppressWarnings("resource")
	public static ObservableList<String> leeFicheros(String rutaArchivo) {
		String linea = Constantes.S_EMPTY;
		ObservableList<String> feriados = FXCollections.observableArrayList();
		
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(rutaArchivo);
			lector = new BufferedReader(new FileReader(fis.getFD()));

			while ((linea = lector.readLine()) != null) {
				feriados.add(linea);
			}
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		return feriados;
	}
	
	// Metodo para iniciar la Ventana principal al retornar.
	@SuppressWarnings("resource")
	public static boolean iniciarEntregadas(TableView<PeticionesVO> tabla, RecuentoVO recuentos) {
		String linea;
		String lineaCmmi;
		
		int aplazadas = 0;
		int entregadas = 0;
		int hoy = 0;
		int tres = 0;
		int cinco = 0;
		int ejecucion = 0;
		
		Date fechaActual = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		String fechaSistema = formateador.format(fechaActual);
		
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_REG);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			ObservableList<PeticionesVO> peticiones = FXCollections.observableArrayList();

			while ((linea = lector.readLine()) != null) {
				PeticionesVO peticion = crearPeticionVO(linea);
      			 String fechaPet = peticion.getFechaFin();
      			 String estadoPet = peticion.getEstado();
				
				if(estadoPet.equals(Constantes.sEntregada)){
					entregadas ++;
				}else if(estadoPet.equals(Constantes.sAplazada)){
					aplazadas ++;
				}else{
					if((fechaPet.equals(fechaSistema) && estadoPet.equals(Constantes.sEjecucion)) || (fechaActual.after(formateador.parse(fechaPet)) && estadoPet.equals(Constantes.sEjecucion))){
						hoy ++;
					 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 3){
						 tres ++;
					 }else if(estadoPet.equals(Constantes.sEjecucion) && DatosAction.restaFechas(formateador.parse(fechaPet), fechaActual) <= 5){
						 cinco ++;
					 }else if(estadoPet.equals(Constantes.sEjecucion)){
						 ejecucion ++;
					 }
				}
				if(estadoPet.equals(Constantes.sEntregada)){
					
					BufferedReader lectorCmmi = null;
					FileInputStream fisCmmi = new FileInputStream(Constantes.RUTA_ARCHIVO_CMMI);
					lectorCmmi = new BufferedReader(new FileReader(fisCmmi.getFD()));
					
					while ((lineaCmmi = lectorCmmi.readLine()) != null) {
						String idPeticion = lineaCmmi.split(Constantes.S_PUNTO_COMA)[0].trim();
						if(!lineaCmmi.equals(Constantes.S_EMPTY) && String.valueOf(peticion.getIdPeticion()).equals(idPeticion)){
							agregaDataCMMI(lineaCmmi, peticion);
						}
					}
					peticiones.add(peticion);
				}

			}
					
			if(null != recuentos.gettAplazadas()){
				recuentos.gettAplazadas().setText(String.valueOf(aplazadas));
			}
			if(null != recuentos.gettEntregadas()){
				recuentos.gettEntregadas().setText(String.valueOf(entregadas));
			}
			if(null != recuentos.gettPeticiones3()){
				recuentos.gettPeticiones3().setText(String.valueOf(tres));
			}
			if(null != recuentos.gettPeticiones5()){
				recuentos.gettPeticiones5().setText(String.valueOf(cinco));
			}
			if(null != recuentos.gettPeticionesHoy()){
				recuentos.gettPeticionesHoy().setText(String.valueOf(hoy));
			}
			if(null != recuentos.gettEjecucion()){
				recuentos.gettEjecucion().setText(String.valueOf(ejecucion));
			}
			
			FXCollections.sort(peticiones, comparador);
			tabla.getItems().clear();
			tabla.setItems(peticiones);
			fis.close();
			return true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static boolean abrirDirectorio(TableView<PeticionesVO> tablePeticiones, TableColumn<PeticionesVO, String> colDCentro,
			TableColumn<PeticionesVO, String> colDSolution, TableColumn<PeticionesVO, String> colDRTC) {
		boolean resultado = false;
		String ruta = Constantes.S_EMPTY;
		int column = tablePeticiones.getSelectionModel().getSelectedCells().get(0).getColumn();
		int row = tablePeticiones.getSelectionModel().getSelectedCells().get(0).getRow();

		switch (column) {
		case 1:
			DatosAction datosAction = new DatosAction();
			datosAction.abrirDetalle(tablePeticiones);
			resultado = true;
			break;
		// Ruta Centro
		case 6:
			ruta = colDCentro.getCellData(row).trim();
			if(null != ruta){
				resultado = abrir(ruta);
			}
			break;
			// Ruta Solution
		case 7:
			ruta = colDSolution.getCellData(row).trim();
			if(null != ruta){
				resultado = abrir(ruta);
			}
			break;
			// Ruta RTC
		case 8:
			ruta = colDRTC.getCellData(row).trim();
			if(null != ruta){
				resultado = abrir(ruta);
			}
			break;
		default:
			resultado = true;
			break;
		}
		return resultado;
	}
	
	public static boolean abrirDirectorioEjec(TableView<PeticionesVO> tablePeticiones, TableColumn<PeticionesVO, String> colDCentro,
			TableColumn<PeticionesVO, String> colDRtcOsi, TableColumn<PeticionesVO, String> colDRtcPap) {
		boolean resultado = false;
		String ruta = Constantes.S_EMPTY;
		int column = tablePeticiones.getSelectionModel().getSelectedCells().get(0).getColumn();
		int row = tablePeticiones.getSelectionModel().getSelectedCells().get(0).getRow();

		switch (column) {
		case 1:
			DatosAction datosAction = new DatosAction();
			datosAction.abrirDetalle(tablePeticiones);
			resultado = true;
			break;
		// Ruta Centro
		case 7:
			ruta = colDCentro.getCellData(row).trim();
			if(null != ruta && !ruta.equals(Constantes.S_EMPTY)){
				resultado = abrir(ruta);
			}
			break;
		// Ruta RTC OSI
		case 8:
			ruta = colDRtcOsi.getCellData(row).trim();
			if(null != ruta && !ruta.equals(Constantes.S_EMPTY)){
				resultado = abrir(ruta);
			}else{
				MessagesBox.createAlert("Aviso", "Ruta sin datos.");
			}
			break;
			// Ruta RTC Pap
			case 9:
				ruta = colDRtcPap.getCellData(row).trim();
				if(null != ruta && !ruta.equals(Constantes.S_EMPTY)){
					resultado = abrir(ruta);
				}else{
					MessagesBox.createAlert("Aviso", "Ruta sin datos.");
				}
				break;
		default:
			resultado = true;
			break;
		}
		return resultado;
	}

	public static boolean abrir(String valor) {
		Desktop desktop = null;
		boolean resultado = false;
		if(null != valor && !valor.equals(Constantes.S_EMPTY)){
			// Abre directorio
			File file = new File(valor.trim());
			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
			}
			try {
				desktop.open(file);
				resultado = true;
			} catch (Exception ex) {
				Logger log = PrintLog.getLogger();
				FileHandler fh = PrintLog.getHandler();
				log.addHandler(fh);
				log.info(ex.getMessage());
				PrintLog.closeHandler(fh);
				ex.printStackTrace();
			}
		}else{
			// Se fuerza como resultado positivo ya que no hay ruta ingresada en el registro
			resultado = true;
		}

		return resultado;
	}
	
	public static void resetDatos(CompFXEjecucionVO componentes){
		componentes.getIdPeticion().setText(Constantes.S_EMPTY);
		componentes.getIdDemanda().setText(Constantes.S_EMPTY);
		componentes.getTipoPeticion().getSelectionModel().clearSelection();
		componentes.getDescripcion().setText(Constantes.S_EMPTY);
		componentes.getRepCentro().setText(Constantes.S_EMPTY);
		componentes.getRepSolution().setText(Constantes.S_EMPTY);
		componentes.getRepRTC().setText(Constantes.S_EMPTY);
		componentes.getResponsableCentro().setText(Constantes.S_EMPTY);
		componentes.getEsfuerzo().setText(Constantes.S_EMPTY);
		componentes.getFechaInicio().setValue(null);
		componentes.getFechaFin().setValue(null);
		
    	componentes.getEtapaPPM().setValue(null);;
    	componentes.getResponsableStgo().setText(Constantes.S_EMPTY);
    	componentes.getIncidencias().setText(Constantes.S_EMPTY);
    	componentes.getFechaKiuwan().setValue(null);
    	componentes.getPpmFecha().setValue(null);
    	componentes.getNcs().setText(Constantes.S_EMPTY);
    	componentes.getDudas().setText(Constantes.S_EMPTY); 
	}
	
	public void abrirDetalle(TableView<PeticionesVO> tablePeticiones){
		try {
			// Nueva Pantalla detalle de compra
			final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/detallePeticion.fxml");
			final FXMLLoader fxmlLoader = new FXMLLoader(clase);
			Parent root;
			root = (Parent)fxmlLoader.load();
			
			DetallePeticion controller = fxmlLoader.<DetallePeticion>getController();
			controller.setDataIni(tablePeticiones.getSelectionModel().getSelectedItem());

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
	}
	
	public static boolean validaFechaEntrega(TableView<PeticionesVO> tablePeticiones){	
		boolean finHoy = false;
		
		
		// TODO recorrer tabla
//   		if(DatosAction.validaFechaEntrega((PeticionesVO) getTableRow().getItem())){
//			getTableRow().setStyle("-fx-alignment: CENTER; -fx-background-color:  d31f05");
//		}
		
		
//		 Date fechaActual = new Date();
//		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
//		 String fechaSistema = formateador.format(fechaActual);
//		 if(null != peticion && null != peticion.getFechaFin()){
//			 String fechaPet = peticion.getFechaFin();
//			 if(fechaPet.equals(fechaSistema)){
//				 finHoy=true;
//			 }
//		 }
		 return finHoy;
	}
	
	public static int restaFechas(Date fecha1, Date fecha2){
		long diferenciaEn_ms = fecha1.getTime() - fecha2.getTime();
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return (int) dias;
	}
	
	public static boolean validaCriterioPPM(PeticionesVO peticion){
		boolean aplicaPPM = true;
				
		if(null == peticion){
			aplicaPPM = false;
		}else if(null == peticion.getEsfuerzo()){
			aplicaPPM = false;
		}else if(peticion.getEsfuerzo().equals(Constantes.S_EMPTY)){
			aplicaPPM = false;
		}else if(peticion.getTipoPeticion().equals(Constantes.S_TIP_PET_DT)){
			aplicaPPM = false;
		}else if(peticion.getTipoPeticion().equals(Constantes.S_TIP_PET_SP)){
			aplicaPPM = false;
		}else if(Integer.valueOf(peticion.getEsfuerzo()) <= Constantes.iCodOneHFifty){
			aplicaPPM = false;
		}else if(peticion.getEstado().equals(Constantes.sEntregada)){
			aplicaPPM = false;
		}else if(peticion.getTipoPeticion().equals(Constantes.S_TIP_PET_SO)){
			aplicaPPM = false;
		}
		return aplicaPPM;
	}
	
	public static boolean cumplePPM(PeticionesVO peticion, Date fechaActual) throws ParseException{
		boolean cumplePPM = true;
		
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd"); 
		
		if(null == peticion){
			cumplePPM = false;
		}else if(null == peticion.getPpmFecha()){
			cumplePPM = false;
		}else if(peticion.getPpmFecha().equals(Constantes.S_EMPTY)){
			cumplePPM = false;
		}else if(validaEtapaPPM(peticion) && fechaActual.after(formateador.parse(peticion.getPpmFecha()))){
			cumplePPM = false;
		}
		
		return cumplePPM;
	}
	
	public static boolean validaEtapaPPM(PeticionesVO peticion){
		boolean cumple = false;
		int etapaPet = 0;
		int etapaPPM = 0;
		ObservableList<EtapaPPMVO> etapas = cargaListaEtapas();
		
		for (EtapaPPMVO etapaPPMVO : etapas) {
			if(etapaPPMVO.getEtapa().equals(peticion.getEtapaPeticion())){
				etapaPet = Integer.valueOf(etapaPPMVO.getIdEtapa());
			}
			if(etapaPPMVO.getEtapa().equals(peticion.getEtapaPPM())){
				etapaPPM = Integer.valueOf(etapaPPMVO.getIdEtapa());
			}
		}

		if(etapaPet > etapaPPM){
			cumple = true;
		}
		
		return cumple;
	}
	
	public static boolean validaNCs(PeticionesVO peticion){
		boolean nok = false;
		if(null == peticion){
			return nok;
		}else if(null == peticion.getNcs()){
			return nok;
		}else if(peticion.getNcs().equals(Constantes.S_EMPTY)){
			return nok;
		}else if(Integer.valueOf(peticion.getNcs()) > Constantes.iCodZero){
			nok = true;
		}
		return nok;
	}
	
	public static boolean tipoPeticion(String item){
		boolean tipo = false;
		
		if(item.equals(Constantes.S_TIP_PET_CO)){
			tipo = true;
		}else if(item.equals(Constantes.S_TIP_PET_DTCO)){
			tipo = true;
		}else if(item.equals(Constantes.S_TIP_PET_DT)){
			tipo = true;
		}else if(item.equals(Constantes.S_TIP_PET_ME)){
			tipo = true;
		}else if(item.equals(Constantes.S_TIP_PET_ES)){
			tipo = true;
		}
		return tipo;
	}
	
	public static boolean aplicaDudas(PeticionesVO peticion){
		boolean aplica = true;
		
		if(Integer.valueOf(peticion.getEsfuerzo()) <= Constantes.iCodForthy){
			aplica = false;
		}else if(peticion.getTipoPeticion().equals(Constantes.S_TIP_PET_SP)){
			aplica = false;
		}else if(peticion.getEstado().equals(Constantes.sEntregada)){
			aplica = false;
		}
		
		return aplica;
	}
	
	public static boolean aplicaIncidencias(PeticionesVO peticion){
		boolean aplica = true;
		
		if(Integer.valueOf(peticion.getEsfuerzo()) <= Constantes.iCodOneHundred){
			aplica = false;
		}else if(peticion.getTipoPeticion().equals(Constantes.S_TIP_PET_SP)){
			aplica = false;
		}else if(peticion.getEstado().equals(Constantes.sEntregada)){
			aplica = false;
		}
		
		return aplica;
	}

}
