package pet.vo;

public class RutasVO {

	private int idRuta;
	private String descripcion = null;
	private String directorio = null;

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDirectorio() {
		return directorio;
	}
	public void setDirectorio(String directorio) {
		this.directorio = directorio;
	}
	public int getIdRuta() {
		return idRuta;
	}
	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

    public int compareTo(RutasVO o)
    {
        return Integer.compare(idRuta , o.getIdRuta());
    }
}
