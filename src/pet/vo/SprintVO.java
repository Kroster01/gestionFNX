package pet.vo;

import javafx.collections.ObservableList;

public class SprintVO {
	
	private String objetivo = null;
	private String highlight = null;
	private String celula = null;
	private String nSprint = null;
	private String nHUSprint = null;
	private String nPuntos = null;
	private String nHUComplete = null;
	private String nPuntosComplete = null;
	private String fechaInicio = null;
	private String fechaFin = null;
	private String cycleTime = null;
	private String nombreSprint = null;
	
	private ObservableList<HistoriasVO> historias;
	private ObservableList<RiesgoVO> riesgos;
	
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getHighlight() {
		return highlight;
	}
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	public String getCelula() {
		return celula;
	}
	public void setCelula(String celula) {
		this.celula = celula;
	}
	public String getNSprint() {
		return nSprint;
	}
	public void setNSprint(String nSprint) {
		this.nSprint = nSprint;
	}
	public String getNHUSprint() {
		return nHUSprint;
	}
	public void setNHUSprint(String nHUSprint) {
		this.nHUSprint = nHUSprint;
	}
	public String getNPuntos() {
		return nPuntos;
	}
	public void setNPuntos(String nPuntos) {
		this.nPuntos = nPuntos;
	}
	public String getNHUComplete() {
		return nHUComplete;
	}
	public void setNHUComplete(String nHUComplete) {
		this.nHUComplete = nHUComplete;
	}
	public String getNPuntosComplete() {
		return nPuntosComplete;
	}
	public void setNPuntosComplete(String nPuntosComplete) {
		this.nPuntosComplete = nPuntosComplete;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public ObservableList<HistoriasVO> getHistorias() {
		return historias;
	}
	public void setHistorias(ObservableList<HistoriasVO> historias) {
		this.historias = historias;
	}
	public ObservableList<RiesgoVO> getRiesgos() {
		return riesgos;
	}
	public void setRiesgos(ObservableList<RiesgoVO> riesgos) {
		this.riesgos = riesgos;
	}
	
	@Override
	public String toString(){
		return this.nSprint;
	}
	public String getCycleTime() {
		return cycleTime;
	}
	public void setCycleTime(String cycleTime) {
		this.cycleTime = cycleTime;
	}
	public String getNombreSprint() {
		return nombreSprint;
	}
	public void setNombreSprint(String nombreSprint) {
		this.nombreSprint = nombreSprint;
	}

}
