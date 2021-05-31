package pet.vo;

import java.util.Date;

public class IncurridoVO {
	
	private int nroEmpleado;
	private Date fecha;
	private double horas;
	private String descIncurrido;
	
	public int getNroEmpleado() {
		return nroEmpleado;
	}
	public void setNroEmpleado(int nroEmpleado) {
		this.nroEmpleado = nroEmpleado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getHoras() {
		return horas;
	}
	public void setHoras(double horas) {
		this.horas = horas;
	}
	public String getDescIncurrido() {
		return descIncurrido;
	}
	public void setDescIncurrido(String descIncurrido) {
		this.descIncurrido = descIncurrido;
	}
	

}
