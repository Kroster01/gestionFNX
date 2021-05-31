package pet.vo;


public class SprintUmaneVO {
	
	private String celula = null;
	private String sprint = null;
	private String fechaInicio = null;
	private String fechaFin = null;
	private String huPlanif = null;
	private String puntosPlanif = null;
	private String estadoHu = null;
	private String fechaHUDef = null;
	private String fechaHUInProgres = null;
	private String fechaHUCompletada = null;
	private String fechaHUAceptada = null;
	private String fechaHURelease = null;
	private String cantDefectos = null;

	@Override
	public String toString(){
		return this.sprint;
	}

	public String getCelula() {
		return celula;
	}

	public void setCelula(String celula) {
		this.celula = celula;
	}

	public String getSprint() {
		return sprint;
	}

	public void setSprint(String sprint) {
		this.sprint = sprint;
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

	public String getHuPlanif() {
		return huPlanif;
	}

	public void setHuPlanif(String huPlanif) {
		this.huPlanif = huPlanif;
	}

	public String getPuntosPlanif() {
		return puntosPlanif;
	}

	public void setPuntosPlanif(String puntosPlanif) {
		this.puntosPlanif = puntosPlanif;
	}

	public String getEstadoHu() {
		return estadoHu;
	}

	public void setEstadoHu(String estadoHu) {
		this.estadoHu = estadoHu;
	}

	public String getFechaHUDef() {
		return fechaHUDef;
	}

	public void setFechaHUDef(String fechaHUDef) {
		this.fechaHUDef = fechaHUDef;
	}

	public String getFechaHUInProgres() {
		return fechaHUInProgres;
	}

	public void setFechaHUInProgres(String fechaHUInProgres) {
		this.fechaHUInProgres = fechaHUInProgres;
	}

	public String getFechaHUCompletada() {
		return fechaHUCompletada;
	}

	public void setFechaHUCompletada(String fechaHUCompletada) {
		this.fechaHUCompletada = fechaHUCompletada;
	}

	public String getFechaHUAceptada() {
		return fechaHUAceptada;
	}

	public void setFechaHUAceptada(String fechaHUAceptada) {
		this.fechaHUAceptada = fechaHUAceptada;
	}

	public String getFechaHURelease() {
		return fechaHURelease;
	}

	public void setFechaHURelease(String fechaHURelease) {
		this.fechaHURelease = fechaHURelease;
	}

	public String getCantDefectos() {
		return cantDefectos;
	}

	public void setCantDefectos(String cantDefectos) {
		this.cantDefectos = cantDefectos;
	}
	
	

}
