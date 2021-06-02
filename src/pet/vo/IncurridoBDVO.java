package pet.vo;

import java.util.Date;

public class IncurridoBDVO {

	private Integer NRO_EMPLEADO;
	private String NOMBRE;
	private String APELLIDO;
	private Date FECHA;
	private double HORAS_INCURRIDAS;
	private String TAREA;

	public Integer getNRO_EMPLEADO() {
		return NRO_EMPLEADO;
	}

	public void setNRO_EMPLEADO(Integer nRO_EMPLEADO) {
		NRO_EMPLEADO = nRO_EMPLEADO;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public String getAPELLIDO() {
		return APELLIDO;
	}

	public void setAPELLIDO(String aPELLIDO) {
		APELLIDO = aPELLIDO;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public Date getFECHA() {
		return FECHA;
	}

	public void setFECHA(Date fECHA) {
		FECHA = fECHA;
	}

	public double getHORAS_INCURRIDAS() {
		return HORAS_INCURRIDAS;
	}

	public void setHORAS_INCURRIDAS(double hORAS_INCURRIDAS) {
		HORAS_INCURRIDAS = hORAS_INCURRIDAS;
	}

	public String getTAREA() {
		return TAREA;
	}

	public void setTAREA(String tAREA) {
		TAREA = tAREA;
	}

}
