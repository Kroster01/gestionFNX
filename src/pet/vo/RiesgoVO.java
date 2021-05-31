package pet.vo;

public class RiesgoVO {
	
	private String riesgo = null;
	private String impacto = null;
	private String accion = null;
	private String responsable = null;
	private String fecha = null;
	
	public String getRiesgo() {
		return riesgo;
	}
	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}
	public String getImpacto() {
		return impacto;
	}
	public void setImpacto(String impacto) {
		this.impacto = impacto;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString(){
		return this.riesgo;
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(this.riesgo);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		//si el objeto no es instancia de SgVendedor return false
		if ((obj != null) && obj instanceof EstadosVO) {
			RiesgoVO tipoPeticion = (RiesgoVO) obj;
			if (riesgo == null && tipoPeticion.getRiesgo() == null) {
				result = riesgo.equals(tipoPeticion.getRiesgo());
			} 
		}
		return result;
	}
}
