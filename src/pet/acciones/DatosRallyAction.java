package pet.acciones;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import pet.util.CnvString;
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.util.PrintLog;
import pet.vo.CelulaVO;
import pet.vo.HistoriasVO;
import pet.vo.IndicadoresVO;
import pet.vo.PeticionesVO;
import pet.vo.ResumenByCells;
import pet.vo.RiesgoVO;
import pet.vo.SprintUmaneVO;
import pet.vo.SprintVO;

public class DatosRallyAction {
	

    	
    //Comparator for int, by Number
    static Comparator<? super PeticionesVO> comparador = new Comparator<PeticionesVO>() {
        public int compare(PeticionesVO o1, PeticionesVO o2) {
            return o2.getIdPeticion() - o1.getIdPeticion();
        }
    };

    public static IndicadoresVO cargaIndicTelef(ObservableList<SprintVO> sprints, DatePicker fechaInforme){
    	IndicadoresVO indicadores = new IndicadoresVO();
    	double productividad = 0;
    	double plazos = 0;
    	int cantSprints = 0;
    	int cantHU = 0;
    	
    	int huPlazo = 0;
    	int huAntes = 0;
    	int huRetraso = 0;
    	int huPend = 0;
    	
    	try {
			Date primerDia = CnvString.getFirstDateOfMonth(fechaInforme);
	    	Date ultimoDia = CnvString.getLastDateOfMonth(fechaInforme);
	    	
	    	for (SprintVO sprintVO : sprints) {
	    		if(CnvString.getDate(sprintVO.getFechaFin(), "dd-MM-yyyy").after(primerDia) && CnvString.getDate(sprintVO.getFechaFin(), "dd-MM-yyyy").before(ultimoDia)){
	    			cantSprints++;
	    			cantHU = cantHU + sprintVO.getHistorias().size();
	    			
					for (HistoriasVO historia : sprintVO.getHistorias()) {

	    				if(null != historia.getFinCiclo() && !historia.getFinCiclo().equals(Constantes.S_EMPTY)){
	    					long dif = Math.abs((CnvString.getDate(historia.getFinCiclo().split(" ")[0], "MM-dd-yyyy").getTime() - 
	    							CnvString.getDate(historia.getInicioCiclo().split(" ")[0], "MM-dd-yyyy").getTime())/86400000);

	    					if(dif == Long.valueOf(sprintVO.getCycleTime())){
	    						huPlazo++;
	    					}else if(dif < Long.valueOf(sprintVO.getCycleTime())){
	    						huAntes++;
	    					}else if (dif > Long.valueOf(sprintVO.getCycleTime())){
	    						huRetraso++;
	    					}
	    				}else{
	    					if(CnvString.clean(historia.getEstado()).equals("In-Progress")){
	    						huPend++;
	    					}
	    				}
					}
	    		}
			}
	    	productividad = 100*(cantHU /  cantSprints);
	    	plazos = (double) 100*(huPlazo + huAntes)/(huPlazo + huAntes + huRetraso + huPend);
	    	indicadores.setPlazos(BigDecimal.valueOf(plazos).setScale(3, RoundingMode.HALF_UP).doubleValue());
	    	indicadores.setProductividad(BigDecimal.valueOf(productividad).setScale(3, RoundingMode.HALF_UP).doubleValue());
	    	
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return indicadores;
    }
    
    
    public static ObservableList<SprintVO> cargaInfoRally(String celula, DatePicker fechaInforme){
    	
//    	String baseRuta = CnvString.getBaseMesRuta(fechaInforme);
    	ObservableList<SprintVO> sprintsCel = FXCollections.observableArrayList();
    	ObservableList<SprintVO> sprints = cargaSprints(Constantes.RUTA_ARCHIVO_RESUMEN);
    	if(sprints.size() > 1){
    		sprints.remove(0);
    	}
    	
    	for (SprintVO sprintVO : sprints) {
			if(celula.equals(sprintVO.getCelula())){
				sprintsCel.add(sprintVO);
			}
		}
    	    	    	
    	return renombraSprints(sprintsCel);
    }
    
    public static ObservableList<ResumenByCells> cargaInfoRallyAll(){
    	
    	HashMap<String, ArrayList<SprintVO>> sprintsAgrup = new HashMap<String, ArrayList<SprintVO>>();
    	ObservableList<ResumenByCells> sprintsCel = FXCollections.observableArrayList();
    	ObservableList<SprintVO> sprints = cargaSprints(Constantes.RUTA_ARCHIVO_RESUMEN);
    	
    	if(sprints.size() > 1){
    		sprints.remove(0);
    	}
    	
    	for (SprintVO sprintVO : sprints) {
			if(null != sprintsAgrup.get(sprintVO.getCelula())){
				sprintsAgrup.get(sprintVO.getCelula()).add(sprintVO);
			}else{
				ArrayList<SprintVO> listaSprints = new ArrayList<SprintVO>();
				listaSprints.add(sprintVO);
				sprintsAgrup.put(sprintVO.getCelula(), listaSprints);
			}
		}
    	
    	for (HashMap.Entry<String, ArrayList<SprintVO>> ent : sprintsAgrup.entrySet()) {
    		ArrayList<SprintVO> sprintsCelula = ent.getValue();
    		int contHistPlan = 0;
    		int contHistComp = 0;
    		int contPuntPlan = 0;
    		int contPuntComp = 0;
    		int contCycle = 0;
    		double totCycle = 0;
    		
    		for (SprintVO sprintVO : sprintsCelula) {
				contHistPlan = contHistPlan + CnvString.convertInt(sprintVO.getNHUSprint());
				contHistComp = contHistComp + CnvString.convertInt(sprintVO.getNHUComplete());
				contPuntPlan = contPuntPlan + CnvString.convertInt(sprintVO.getNPuntos());
				contPuntComp = contPuntComp + CnvString.convertInt(sprintVO.getNPuntosComplete());
				totCycle = totCycle + CnvString.convertDoubleNull(sprintVO.getCycleTime());
				contCycle++;
			}
    		ResumenByCells resumen = new ResumenByCells();
    		resumen.setCelula(ent.getKey().substring(7));
    		resumen.setnHUSprint(String.valueOf(contHistPlan/contCycle));
    		resumen.setnHUComplete(String.valueOf(contHistComp/contCycle));
    		resumen.setnPuntos(String.valueOf(contPuntPlan/contCycle));
    		resumen.setnPuntosComplete(String.valueOf(contPuntComp/contCycle));
    		resumen.setCycleTime(String.valueOf(totCycle/contCycle));
    		sprintsCel.add(resumen);
    	}
    	    	    	
    	return sprintsCel;
    }
    
    
    public static ObservableList<SprintVO> renombraSprints(ObservableList<SprintVO> sprints){
    	ObservableList<SprintVO> sprintLimpios = FXCollections.observableArrayList();
    	int cantSprints = sprints.size();
    	
    	for (SprintVO sprint : sprints) {
    		ObservableList<HistoriasVO> historias = sprint.getHistorias();
    		ObservableList<HistoriasVO> historiasLimpias = FXCollections.observableArrayList();
    		if(null != historias && historias.size() > 0){
        		for (HistoriasVO historia : historias) {
    				historia.setSprint(String.valueOf(cantSprints));
    				historiasLimpias.add(historia);
    			}
        		sprint.setHistorias(historiasLimpias);
    		}
    		sprint.setNombreSprint(sprint.getNSprint());
    		sprint.setNSprint(String.valueOf(cantSprints));
    		sprintLimpios.add(sprint);
    		cantSprints--;
		}
    	
    	
    	return sprintLimpios;
    }
	
	public static SprintVO creaSprintVO(String linea){
		SprintVO sprint = new SprintVO();
		String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
		sprint.setCelula(elementos[0].trim());
		sprint.setNSprint(elementos[1].trim());
		sprint.setFechaInicio(elementos[2].trim());
		sprint.setFechaFin(elementos[3].trim());
		sprint.setNHUSprint(elementos[4].trim());
		sprint.setNHUComplete(elementos[5].trim());
		sprint.setNPuntos(elementos[6].trim());
		sprint.setNPuntosComplete(elementos[7].trim());
		sprint.setCycleTime(elementos[9].trim());
		return sprint;
	}
	
	
	public static HistoriasVO creaHistoriaVO(String linea){
		HistoriasVO historia = new HistoriasVO();
		String[] elementos = linea.split(Constantes.S_COMA);		
		historia.setIdHistoria(elementos[0].trim());
		historia.setDescHistoria(elementos[1].trim());
		historia.setSprint(elementos[3].trim());
		historia.setEstado(elementos[4].trim());

		if(null != elementos[5].trim() && !elementos[5].trim().contains("Plan Estimate") && elementos[5].trim().split("\\.").length > 1){
			historia.setEstimado(elementos[5].trim().split("\\.")[0]);
		}else{
			historia.setEstimado("");
		}
//		if(null != elementos[7] && !elementos[7].trim().contains("Defects")){
//			historia.setDefectos(elementos[7].trim());
//		}else{
//			historia.setDefectos("");
//		}
		
		
//		historia.setFechaAceptacion(elementos[9].trim());
//		historia.setFechaCreacion(elementos[6].trim());
//		historia.setItemTrabajo(elementos[7].trim());
//		historia.setInicioCiclo(elementos[9].trim());
//		historia.setFinCiclo(elementos[10].trim());
		return historia;
	}

	
	@SuppressWarnings("resource")
	public static ObservableList<SprintVO> cargaSprints(String rSprint){
		ObservableList<SprintVO> sprints = FXCollections.observableArrayList();
		BufferedReader lector = null;
		FileInputStream fis;
		String linea;
		try {
			fis = new FileInputStream(rSprint);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			
			while ((linea = lector.readLine()) != null) {
				SprintVO sprint = new SprintVO();
				sprint = creaSprintVO(linea);
				sprints.add(sprint);
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
		return sprints;
	}
	
	@SuppressWarnings("resource")
	public static ObservableList<HistoriasVO> cargaHistorias(String cicle){
		ObservableList<HistoriasVO> historias = FXCollections.observableArrayList();
		BufferedReader lector = null;
		FileInputStream fis;
		String linea;
		try {
			fis = new FileInputStream(Constantes.RUTA_BASE_RALLY + cicle);
			lector = new BufferedReader(new FileReader(fis.getFD()));

			while ((linea = lector.readLine()) != null) {
				HistoriasVO historia = new HistoriasVO();
				historia = creaHistoriaVO(linea);
				historias.add(historia);
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
		return historias;
	}
	
	@SuppressWarnings("resource")
	public static ObservableList<HistoriasVO> cargaCycleHistorias(String cicle){
		ObservableList<HistoriasVO> historias = FXCollections.observableArrayList();
		BufferedReader lector = null;
		FileInputStream fis;
		String linea;
		try {
			fis = new FileInputStream(Constantes.RUTA_BASE_RALLY + cicle);
			lector = new BufferedReader(new FileReader(fis.getFD()));

			while ((linea = lector.readLine()) != null) {
				HistoriasVO historia = new HistoriasVO();
				historia = creaCycleHistoriaVO(linea);
				historias.add(historia);
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
		return historias;
	}

	public static HistoriasVO creaCycleHistoriaVO(String linea){
		HistoriasVO historia = new HistoriasVO();
		String[] elementos = linea.split(Constantes.S_COMA);		
		historia.setIdHistoria(elementos[0].trim());
		historia.setDescHistoria(elementos[1].trim());
		historia.setSprint(elementos[2].trim());
		historia.setEstimado(elementos[3].trim());
		historia.setEstado(elementos[4].trim());
		historia.setFechaAceptacion(elementos[5].trim());		
		historia.setFechaCreacion(elementos[6].trim());
		historia.setItemTrabajo(elementos[7].trim());
		historia.setInicioCiclo(elementos[9].trim());
		historia.setFinCiclo(elementos[10].trim());
		return historia;
	}
	
	public static ObservableList<CelulaVO> cargaCelulas(){
		ObservableList<CelulaVO> estados = FXCollections.observableArrayList();
		BufferedReader lector = null;
		FileInputStream fis;
		String linea;
		try {
			fis = new FileInputStream(Constantes.RUTA_ARCHIVO_CEL_RALLY);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			
			while ((linea = lector.readLine()) != null) {
				CelulaVO estadoPet = new CelulaVO();
				estadoPet.setCelula(linea);
				estados.add(estadoPet);
			}
			fis.close();
			lector.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}		
		return estados;
	}
	
	public static boolean guardarRegistroUmane(SprintUmaneVO sprint){
		boolean resultado = false;
		
		StringBuffer registro = new StringBuffer();
		registro.append(sprint.getCelula() + Constantes.S_PUNTO_COMA);
		registro.append(sprint.getSprint()+ Constantes.S_PUNTO_COMA);
		registro.append(sprint.getFechaInicio()+ Constantes.S_PUNTO_COMA);	
		registro.append(sprint.getFechaFin()+ Constantes.S_PUNTO_COMA);	
		registro.append(sprint.getHuPlanif()+ Constantes.S_PUNTO_COMA);
		registro.append(sprint.getPuntosPlanif()+ Constantes.S_PUNTO_COMA);			
		registro.append(sprint.getEstadoHu()+ Constantes.S_PUNTO_COMA);	
		registro.append(sprint.getFechaHUDef()+ Constantes.S_PUNTO_COMA);	
		registro.append(sprint.getFechaHUInProgres()+ Constantes.S_PUNTO_COMA);
		registro.append(sprint.getFechaHUCompletada()+ Constantes.S_PUNTO_COMA);
		registro.append(sprint.getFechaHUAceptada()+ Constantes.S_PUNTO_COMA);	
		registro.append(sprint.getFechaHURelease()+ Constantes.S_PUNTO_COMA);	
		registro.append(sprint.getCantDefectos()+ Constantes.S_PUNTO_COMA);

		// Ingresa en el fichero el registro
		resultado = escribeFicheroUmane(registro.toString());
		
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
		registro.append(sprint.getCycleTime() + Constantes.S_PUNTO_COMA);	
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
	
	public static boolean escribeFicheroUmane(String linea){
		boolean resultado = false;
		// Se escribe en fichero principal
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_AGILE_UMANE, true);
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
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_AGILE_RALLY, true);
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
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_HIST_RALLY, true);
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
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_RISK_RALLY, true);
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


}
