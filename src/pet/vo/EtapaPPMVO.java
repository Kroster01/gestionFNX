package pet.vo;

public class EtapaPPMVO {
	
	private String etapa = null;
	private String idEtapa = null;

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	@Override
	public String toString(){
		return this.etapa;
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(this.etapa);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		//si el objeto no es instancia de SgVendedor return false
		if ((obj != null) && obj instanceof EstadosVO) {
			EtapaPPMVO tipoPeticion = (EtapaPPMVO) obj;
			if (etapa == null && tipoPeticion.getEtapa() == null) {
				result = etapa.equals(tipoPeticion.getEtapa());
			} 
		}
		return result;
	}

	public String getIdEtapa() {
		return idEtapa;
	}

	public void setIdEtapa(String idEtapa) {
		this.idEtapa = idEtapa;
	}

}
