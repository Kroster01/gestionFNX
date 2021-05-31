package pet.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pet.util.Configuraciones;
import pet.util.Constantes;
import pet.vo.FenixReportVO;

public class ObtieneReportes {

	public static void main(String[] args) {

		String estado = "'EN_EJECUCION', 'APLAZADA', 'ENTREGADA', 'ENTREGA_ACEPTADA'";
		String aplicacion = "'SemiAgile', 'FullAgile', 'Movilidad'";
		String fecha = "'01-06-2020'";

		try {
			ObservableList<FenixReportVO> petisList = obtienePeticionesFenix(
					estado, aplicacion, fecha);
			System.out.println(petisList.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static String URL = Configuraciones.getConfigValue("url");
	private static String USER = Configuraciones.getConfigValue("user");
	private static String PASSWORD = Configuraciones.getConfigValue("pass");

	public static ObservableList<FenixReportVO> obtienePeticionesFenix(String estado, String aplicacion, String fecha) throws SQLException {
		ObservableList<FenixReportVO> petisList = FXCollections.observableArrayList();
		aplicacion = "'SemiAgile', 'FullAgile', 'Movilidad'";

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			Statement stmtNC = conn.createStatement();
			ResultSet rs;
			ResultSet rsNC;

			String query = "SELECT S.PETICION AS PETICION, S.ESTADO AS ESTADO, S.NOMBRE AS NOMBRE, S.TIPO_PETICION AS TIPO, S.ACUERDO_FECHA_INICIO AS INICIO, S.ACUERDO_FECHA_FIN AS FIN,"
					+ " S.HORAS_PETICIONARIO AS HH_CHP, S.HORAS_ACUERDO AS HH_CENTRO, S.PLANIFICADO_TOTAL AS PLANIFICADO, S.INCURRIDO_TOTAL AS INCURRIDO,"
					+ " S.ETC AS ETC, S.ETC_AUTOMATICO AS ETC_AUTOMATICO, S.INC_TOTALES_INTERNAS AS INC_INTERNA, S.INCS_CENTRO_INCURRIDO AS INCURRIDO_INT, S.INC_TOTALES_EXTERNAS AS INC_EXTERNA,"
					+ " S.CELDA, D.NRO_TOTAL_DUDAS AS DUDAS,"
					+ " S.DIRECTORIO_DE_ENTREGA AS DIRECTORIO, S.ACUERDO_FECHA_FIN AS FECHA_FIN, S.CONTRAPARTE_CENTRO AS RESPONSABLE, "
					+ " S.ACUERDO_FECHA_INICIO AS FECHA_INICIO, S.ACUERDO_FECHA_FIN AS FECHA_FIN"
					+ " FROM REPORT_SEG_PETICIONES S, REPORT_NRO_DUDAS_POR_PET D WHERE S.CLIENTE = 'Movistar Chile' ";
			
			String queryNC = "SELECT COUNT(N.ID_REQUERIMIENTO) AS NCS FROM REPORT_NC N WHERE N.ID_REQUERIMIENTO = ";

			if (null != estado && !estado.equals(Constantes.S_EMPTY)) {
				query = query + " AND S.ESTADO IN (" + estado + ")";
			}

			if (null != aplicacion && !aplicacion.equals(Constantes.S_EMPTY)) {
				query = query + " AND S.APLICACION IN (" + aplicacion + ")";
			}

			if (null != fecha && !fecha.equals(Constantes.S_EMPTY)) {
				query = query + " AND S.FECHA_SOLICITUD >=" + "'" + fecha + "'";
			}

			query = query
					+ " AND S.ESTADO != 'DESESTIMADA'"
					+ " AND S.PETICION_OT = 'PET' AND D.ID_PETICION = S.PETICION ORDER BY S.PETICION DESC";

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				FenixReportVO fenixReport = new FenixReportVO();

				fenixReport.setDescripcion(rs.getString("NOMBRE"));
				fenixReport.setDirCentro(rs.getString("DIRECTORIO"));
				fenixReport.setDudas(rs.getString("DUDAS"));
				fenixReport.setEstado(rs.getString("ESTADO"));
				fenixReport.setEtc(rs.getString("ETC"));
				fenixReport.setEtcAut(rs.getString("ETC_AUTOMATICO"));
				fenixReport.setHorPlanif(rs.getString("PLANIFICADO"));
				fenixReport.setHorasAcuerdo(rs.getString("HH_CENTRO"));
				fenixReport.setIdPeticion(rs.getInt("PETICION"));
				fenixReport.setIncExt(rs.getString("INC_EXTERNA"));
				fenixReport.setIncInt(rs.getString("INC_INTERNA"));
				fenixReport.setFechaInicio(rs.getString("FECHA_INICIO"));
				fenixReport.setFechaFin(rs.getString("FECHA_FIN"));
				fenixReport.setResponsable(rs.getString("RESPONSABLE"));
				fenixReport.setTipoPeticion(rs.getString("TIPO"));
				fenixReport.setIncurridoInt(rs.getString("INCURRIDO_INT"));
				
				rsNC = stmtNC.executeQuery(queryNC + fenixReport.getIdPeticion());
				
				while (rsNC.next()){
					fenixReport.setNcs(String.valueOf(rsNC.getInt("NCS")));
				}

				petisList.add(fenixReport);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return petisList;
	}
}
