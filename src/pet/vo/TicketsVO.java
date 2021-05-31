package pet.vo;

public class TicketsVO {

	private int idPeticion;
	private String codDemanda;
	private String idObservacion;
	private String descTicket;	


	public String getDescTicket() {
		return descTicket;
	}
	public void setDescTicket(String descTicket) {
		this.descTicket = descTicket;
	}

	public int getIdPeticion() {
		return idPeticion;
	}
	public void setIdPeticion(int idPeticion) {
		this.idPeticion = idPeticion;
	}

	public String getCodDemanda() {
		return codDemanda;
	}
	public void setCodDemanda(String codDemanda) {
		this.codDemanda = codDemanda;
	}
	public String getIdObservacion() {
		return idObservacion;
	}
	public void setIdObservacion(String idObservacion) {
		this.idObservacion = idObservacion;
	}
	
	
}
