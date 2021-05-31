package pet.vo;

public class AplicacionVO {

	private String aplicacion = null;


	
	@Override
	public String toString(){
		return this.aplicacion;
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(this.aplicacion);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		//si el objeto no es instancia de SgVendedor return false
		if ((obj != null) && obj instanceof AplicacionVO) {
			AplicacionVO tipoPeticion = (AplicacionVO) obj;
			if (aplicacion == null && tipoPeticion.getAplicacion() == null) {
				result = aplicacion.equals(tipoPeticion.getAplicacion());
			} 
		}
		return result;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

}
