package pet.vo;

public class FenixReportVO {

	private int idPeticion;
	private String tipoPeticion = null;
	private String descripcion = null;
	private String dirCentro = null;
	private String responsable = null;
	private String horasAcuerdo = null;
	private String horPlanif = null;
	private String incInt = null;
	private String incExt = null;
	private String etc = null;
	private String etcAut = null;
	private String estado = null;
	private String ncs = null;
	private String dudas = null;
	private String fechaFin = null;
	private String fechaInicio = null;
	private String incurridoInt = null;
	

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
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public int getIdPeticion() {
		return idPeticion;
	}
	public void setIdPeticion(int idPeticion) {
		this.idPeticion = idPeticion;
	}
	
    public int compareTo(FenixReportVO o)
    {
        return Integer.compare(idPeticion , o.getIdPeticion());
    }
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	public String getHorPlanif() {
		return horPlanif;
	}
	public void setHorPlanif(String horPlanif) {
		this.horPlanif = horPlanif;
	}
	public String getIncInt() {
		return incInt;
	}
	public void setIncInt(String incInt) {
		this.incInt = incInt;
	}
	public String getIncExt() {
		return incExt;
	}
	public void setIncExt(String incExt) {
		this.incExt = incExt;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getEtcAut() {
		return etcAut;
	}
	public void setEtcAut(String etcAut) {
		this.etcAut = etcAut;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getIncurridoInt() {
		return incurridoInt;
	}
	public void setIncurridoInt(String incurridoInt) {
		this.incurridoInt = incurridoInt;
	}
	public String getHorasAcuerdo() {
		return horasAcuerdo;
	}
	public void setHorasAcuerdo(String horasAcuerdo) {
		this.horasAcuerdo = horasAcuerdo;
	}
}
