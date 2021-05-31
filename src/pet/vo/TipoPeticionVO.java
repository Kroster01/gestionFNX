package pet.vo;

public class TipoPeticionVO {

	private String tipo = null;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString(){
		return this.tipo;
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(this.tipo);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		//si el objeto no es instancia de SgVendedor return false
		if ((obj != null) && obj instanceof TipoPeticionVO) {
			TipoPeticionVO tipoPeticion = (TipoPeticionVO) obj;
			if (tipo == null && tipoPeticion.getTipo() == null) {
				result = tipo.equals(tipoPeticion.getTipo());
			} 
		}
		return result;
	}

}
