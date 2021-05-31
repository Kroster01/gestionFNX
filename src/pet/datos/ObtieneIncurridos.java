package pet.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pet.util.Configuraciones;
import pet.util.Constantes;
import pet.vo.EmpleadoVO;
import pet.vo.IncurridoVO;

public class ObtieneIncurridos {

	public static void main(String[] args) {

		try {
			HashMap<String, EmpleadoVO> incurridos = obtieneIncurridosFenix(null, "01-01-2021", "31-01-2021");
			
			for(Map.Entry<String, EmpleadoVO> entry : incurridos.entrySet()) {
			    String nroEmpleado = entry.getKey();
			    EmpleadoVO empleado = entry.getValue();
			    
			    System.out.println(nroEmpleado + ";" + empleado.getNomEmpleado() + ";" + empleado.getCelula());
			    ObservableList<IncurridoVO> incu = empleado.getIncurridos();
			    HashMap<Date, Double> incuAgrup = new HashMap<Date, Double>();
			    
			    for (IncurridoVO incurridoVO : incu) {
			    	if(null != incuAgrup.get(incurridoVO.getFecha())){
			    		Double horas = incuAgrup.get(incurridoVO.getFecha());
			    		horas = horas + incurridoVO.getHoras();
			    		incuAgrup.remove(incurridoVO.getFecha());
			    		incuAgrup.put(incurridoVO.getFecha(), horas);
			    	}else{
			    		incuAgrup.put(incurridoVO.getFecha(), incurridoVO.getHoras());
			    	}
				}
			    
			    for(Map.Entry<Date, Double> ent : incuAgrup.entrySet()) {
			    	Date fecha = ent.getKey();
			    	Double horas = ent.getValue();
//			    	System.out.println("                   Fecha: " + fecha + " horas: " + horas);
			    }
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	private static String URL = Configuraciones.getConfigValue("url");
	private static String USER = Configuraciones.getConfigValue("user");
	private static String PASSWORD = Configuraciones.getConfigValue("pass");

	public static HashMap<String, EmpleadoVO> obtieneIncurridosFenix(String celda, String fechaDesde, String fechaHasta) throws SQLException {
		HashMap<String, EmpleadoVO> incurridos = new HashMap<String, EmpleadoVO>();

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rs;

			String query = "SELECT H.NRO_EMPLEADO AS NRO_EMPLEADO, H.NOMBRE AS NOMBRE, H.APELLIDO AS APELLIDO, I.FECHA AS FECHA, I.HORAS_INCURRIDAS AS HORAS_INCURRIDAS, I.DESC_TAREA AS TAREA " +
						   "FROM FENIX_REP.REPORT_IND_HEADCOUNT H, FENIX_REP.REPORT_INCURRIDO I " +
						   "WHERE I.NRO_EMPLEADO = H.NRO_EMPLEADO " +
						   "AND H.CENTRO = I.FACTORIA " +
						   "AND H.LINEA = 'L. Movistar Chile' " +
						   "AND H.CELDA IN ('C. DES Telefonica Agile', 'C. DES CEL HACINADOS', 'C. QA', 'C. DES CEL MOVISTAR CLICK', 'C. DES Movilidad') " + 
						   "AND H.FECHA_BAJA IS NULL ";			

			if (null != fechaDesde && !fechaDesde.equals(Constantes.S_EMPTY)) {
				query = query + "AND I.FECHA >=" + "'" + fechaDesde + "'";
			}
			if (null != fechaHasta && !fechaHasta.equals(Constantes.S_EMPTY)) {
				query = query + "AND I.FECHA <=" + "'" + fechaHasta + "'";
			}

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				EmpleadoVO empleado = new EmpleadoVO();
				IncurridoVO incurrido = new IncurridoVO();
				
				empleado.setNroEmpleado(rs.getString("NRO_EMPLEADO"));
				empleado.setNomEmpleado(rs.getString("NOMBRE") + " " + rs.getString("APELLIDO"));
				
				incurrido.setNroEmpleado(rs.getInt("NRO_EMPLEADO"));
				incurrido.setFecha(rs.getDate("FECHA"));
				incurrido.setHoras(rs.getDouble("HORAS_INCURRIDAS"));
				incurrido.setDescIncurrido(rs.getString("TAREA"));
				
				if(null != incurridos.get(empleado.getNroEmpleado())){
					ObservableList<IncurridoVO> incEmpl =  incurridos.get(empleado.getNroEmpleado()).getIncurridos();
					incEmpl.add(incurrido);
					incurridos.get(empleado.getNroEmpleado()).setIncurridos(incEmpl);
				}else{
					ObservableList<IncurridoVO> incEmpl = FXCollections.observableArrayList();
					incEmpl.add(incurrido);
					empleado.setIncurridos(incEmpl);
					incurridos.put(empleado.getNroEmpleado(), empleado);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return incurridos;
	}
}
