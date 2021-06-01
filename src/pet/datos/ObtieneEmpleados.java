package pet.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pet.util.Configuraciones;
import pet.vo.EmpleadoVO;

public class ObtieneEmpleados {

	private static String URL = Configuraciones.getConfigValue("url");
	private static String USER = Configuraciones.getConfigValue("user");
	private static String PASSWORD = Configuraciones.getConfigValue("pass");

	public static void main(String[] args) {

		try {
			ObservableList<EmpleadoVO> empleados = obtieneEmpleadosFenix(null);

			for (EmpleadoVO empleadoVO : empleados) {
				System.out.println("Empleado: " + empleadoVO.getNomEmpleado());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ObservableList<EmpleadoVO> obtieneEmpleadosFenix(String celda) throws SQLException {
		ObservableList<EmpleadoVO> empleados = FXCollections.observableArrayList();

		String query = crearQuery();

		if (Configuraciones.getIsMock("is_mock")) {
			System.out.println("Query Result:");
			System.out.println(query);
			return obtieneEmpleadosFenixMock();
		}

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				EmpleadoVO empleado = new EmpleadoVO();

				empleado.setNroEmpleado(rs.getString("NRO_EMPLEADO"));
				empleado.setNomEmpleado(rs.getString("NOMBRE") + " " + rs.getString("APELLIDO"));
				empleados.add(empleado);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return empleados;
	}

	public static String crearQuery() {
		String query = "SELECT H.NRO_EMPLEADO AS NRO_EMPLEADO, H.NOMBRE AS NOMBRE, H.APELLIDO AS APELLIDO "
				+ "FROM REPORT_IND_HEADCOUNT H " + "WHERE H.LINEA = 'L. Movistar Chile' "
				+ "AND H.CELDA IN ('C. DES Telefonica Agile', 'C. QA') " + "AND H.FECHA_BAJA IS NULL ";
		return query;
	}

	public static ObservableList<EmpleadoVO> obtieneEmpleadosFenixMock() {
		ObservableList<EmpleadoVO> empleados = FXCollections.observableArrayList();
		return empleados;
	}

}
