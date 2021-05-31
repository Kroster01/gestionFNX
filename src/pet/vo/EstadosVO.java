package pet.vo;

public class EstadosVO {

	private String estado = null;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String tipo) {
		this.estado = tipo;
	}
	
	@Override
	public String toString(){
		return this.estado;
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(this.estado);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		//si el objeto no es instancia de SgVendedor return false
		if ((obj != null) && obj instanceof EstadosVO) {
			EstadosVO tipoPeticion = (EstadosVO) obj;
			if (estado == null && tipoPeticion.getEstado() == null) {
				result = estado.equals(tipoPeticion.getEstado());
			} 
		}
		return result;
	}

}
