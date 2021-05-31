package pet.vo;

public class CelulaVO {

	private String celula = null;

	public String getCelula() {
		return celula;
	}

	public void setCelula(String celula) {
		this.celula = celula;
	}

	@Override
	public String toString(){
		return this.celula;
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(this.celula);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		//si el objeto no es instancia de SgVendedor return false
		if ((obj != null) && obj instanceof CelulaVO) {
			CelulaVO tipoPeticion = (CelulaVO) obj;
			if (celula == null && tipoPeticion.getCelula() == null) {
				result = celula.equals(tipoPeticion.getCelula());
			} 
		}
		return result;
	}

}
