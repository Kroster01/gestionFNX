package pet.vo;

public class PeticionesVO {

	private int idPeticion;
	private String codDemanda = null;
	private String tipoPeticion = null;
	private String descripcion = null;
	private String dirCentro = null;
	private String dirRtcPap = null;
	private String dirRtcOsi = null;
	private String responsable = null;
	private String esfuerzo = null;
	private String fechaIni = null;
	private String fechaFin = null;
	private String estado = null;
	
	private String etapaPPM = null;
	private String etapaPeticion = null;
	private String responsableStgo = null;
	private String incidencia = null;
	private String fechaKiuwan = null;
	private String ppmFecha = null;
	private String ncs = null;
	private String dudas = null;
	

	public String getCodDemanda() {
		return codDemanda;
	}
	public void setCodDemanda(String codDemanda) {
		this.codDemanda = codDemanda;
	}
	public String getTipoPeticion() {
		return tipoPeticion;
	}
	public void setTipoPeticion(String tipoPeticion) {
		this.tipoPeticion = tipoPeticion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDirCentro() {
		return dirCentro;
	}
	public void setDirCentro(String dirCentro) {
		this.dirCentro = dirCentro;
	}
	public String getDirRtcPap() {
		return dirRtcPap;
	}
	public void setDirRtcPap(String dirRtcPap) {
		this.dirRtcPap = dirRtcPap;
	}
	public String getDirRtcOsi() {
		return dirRtcOsi;
	}
	public void setDirRtcOsi(String dirRtcOsi) {
		this.dirRtcOsi = dirRtcOsi;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getEsfuerzo() {
		return esfuerzo;
	}
	public void setEsfuerzo(String esfuerzo) {
		this.esfuerzo = esfuerzo;
	}
	public String getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getIdPeticion() {
		return idPeticion;
	}
	public void setIdPeticion(int idPeticion) {
		this.idPeticion = idPeticion;
	}
	
    public int compareTo(PeticionesVO o)
    {
        return Integer.compare(idPeticion , o.getIdPeticion());
    }
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEtapaPPM() {
		return etapaPPM;
	}
	public void setEtapaPPM(String etapaPPM) {
		this.etapaPPM = etapaPPM;
	}
	public String getResponsableStgo() {
		return responsableStgo;
	}
	public void setResponsableStgo(String responsableStgo) {
		this.responsableStgo = responsableStgo;
	}
	public String getIncidencia() {
		return incidencia;
	}
	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}
	public String getFechaKiuwan() {
		return fechaKiuwan;
	}
	public void setFechaKiuwan(String fechaKiuwan) {
		this.fechaKiuwan = fechaKiuwan;
	}
	public String getPpmFecha() {
		return ppmFecha;
	}
	public void setPpmFecha(String ppmFecha) {
		this.ppmFecha = ppmFecha;
	}
	public String getNcs() {
		return ncs;
	}
	public void setNcs(String ncs) {
		this.ncs = ncs;
	}
	public String getDudas() {
		return dudas;
	}
	public void setDudas(String dudas) {
		this.dudas = dudas;
	}
	public String getEtapaPeticion() {
		return etapaPeticion;
	}
	public void setEtapaPeticion(String etapaPeticion) {
		this.etapaPeticion = etapaPeticion;
	}
}
