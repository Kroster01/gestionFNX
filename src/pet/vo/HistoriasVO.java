package pet.vo;

public class HistoriasVO {

	private String idHistoria = null;
	private String descHistoria = null;
	private String sprint = null;
	private String fechaAceptacion = null;
	private String fechaCreacion = null;
	private String itemTrabajo = null;
	private String inicioCiclo = null;
	private String finCiclo = null;
	private String estimado = null;
	private String defectos = null;

	private String estado = null;
	
	public String getIdHistoria() {
		return idHistoria;
	}
	public void setIdHistoria(String idHistoria) {
		this.idHistoria = idHistoria;
	}
	public String getDescHistoria() {
		return descHistoria;
	}
	public void setDescHistoria(String descHistoria) {
		this.descHistoria = descHistoria;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getSprint() {
		return sprint;
	}
	public void setSprint(String sprint) {
		this.sprint = sprint;
	}
	public String getFechaAceptacion() {
		return fechaAceptacion;
	}
	public void setFechaAceptacion(String fechaAceptacion) {
		this.fechaAceptacion = fechaAceptacion;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getItemTrabajo() {
		return itemTrabajo;
	}
	public void setItemTrabajo(String itemTrabajo) {
		this.itemTrabajo = itemTrabajo;
	}
	public String getInicioCiclo() {
		return inicioCiclo;
	}
	public void setInicioCiclo(String inicioCiclo) {
		this.inicioCiclo = inicioCiclo;
	}
	public String getFinCiclo() {
		return finCiclo;
	}
	public void setFinCiclo(String finCiclo) {
		this.finCiclo = finCiclo;
	}
	
	@Override
	public String toString(){
		return this.idHistoria;
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(this.idHistoria);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		//si el objeto no es instancia de SgVendedor return false
		if ((obj != null) && obj instanceof EstadosVO) {
			HistoriasVO tipoPeticion = (HistoriasVO) obj;
			if (idHistoria == null && tipoPeticion.getIdHistoria() == null) {
				result = idHistoria.equals(tipoPeticion.getIdHistoria());
			} 
		}
		return result;
	}
	public String getEstimado() {
		return estimado;
	}
	public void setEstimado(String estimado) {
		this.estimado = estimado;
	}
	public String getDefectos() {
		return defectos;
	}
	public void setDefectos(String defectos) {
		this.defectos = defectos;
	}
	
}
