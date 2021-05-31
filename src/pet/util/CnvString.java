package pet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import pet.acciones.DatosAction;
import pet.vo.EmpleadoPrevVO;
import pet.vo.EmpleadoVO;

public class CnvString {

	public static Date getLastDateOfMonth(DatePicker dPeriodoLibroGuia)
			throws ParseException {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDesde;
		fechaDesde = (Date) sdf.parse(dPeriodoLibroGuia.getValue().toString());
		calendar.setTime(fechaDesde);

		int last = calendar.getActualMaximum(Calendar.DATE);
		calendar.set(Calendar.DATE, last);
		Date fecha = calendar.getTime();

		return fecha;

	}
	
	public static int convertInt(String string){
		if(null != string && !string.equals(Constantes.S_EMPTY) && string.split("\\.").length > 0 && !string.equals(Constantes.S_NULL)){
			return Integer.valueOf(string.split("\\.")[0]);
		}else{
			return Integer.valueOf(string);
		}
		
	}
	
	public static int convertIntNull(String string){
		if(null != string && !string.equals(Constantes.S_EMPTY) && !string.equals(Constantes.S_NULL) && !string.contains("-")){
			return Integer.valueOf(string);
		}else{
			return 0;
		}
	}
	
	public static double convertDoubleNull(String string){
		if(null != string && !string.equals(Constantes.S_EMPTY) && !string.equals(Constantes.S_NULL) && !string.contains("-")){
			return Double.valueOf(string);
		}else{
			return 0;
		}
	}

	public static String clean(String string) {
		String repla = string.replace("\"", "");
		return repla;
	}

	public static String convertFecha(String fechaStr) {
		String formato = Constantes.S_EMPTY;
		if (fechaStr.split("-").length > 1) {
			formato = "dd-MM-yyyy";
		} else {
			formato = "MM-dd-yyyy";
		}
		Date fecha = getDate(fechaStr, formato);
		String strFecha = Constantes.S_EMPTY;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		strFecha = sdf.format(fecha);
		return strFecha;
	}

	public static Date getDate(String fecha, String format) {
		if (fecha.split("-").length > 1) {
			return getDateGuion(fecha, format);
		} else {
			return getDateGuion(fecha.split(" ")[0].replace("/", "-")
					.substring(1), format);
		}
	}

	public static Date getDateGuion(String fecha, String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = (Date) sdf.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateSlash(String fecha, String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = (Date) sdf.parse(fecha.split(" ")[0].replace("/", "-").substring(1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static boolean isWeekEnd(DatePicker dFecha, int day) {
		
		boolean isWeekEnd = false;
		Calendar calendar = getCalendar(dFecha, day);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		if (dayOfWeek == 1 || dayOfWeek == 7) {
			isWeekEnd = true;
		}

		return isWeekEnd;
	}
	
	public static boolean isWeekEnd(Date fecha) {
		
		boolean isWeekEnd = false;
		Calendar calendar = Calendar. getInstance();
		calendar. setTime(fecha);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		if (dayOfWeek == 1 || dayOfWeek == 7) {
			isWeekEnd = true;
		}

		return isWeekEnd;
	}

	public static boolean isFeriado(DatePicker dFecha, int day) {
		ObservableList<String> feriados = DatosAction.obtieneFeriados();
		boolean isFeriado = false;
		Calendar calendar = getCalendar(dFecha, day);
		Date fechaDia = calendar.getTime();
		String sFecha = CnvString.convertFecha(fechaDia);
		
		for (String feriado : feriados) {
			if(feriado.equals(sFecha)){
				isFeriado = true;
			}
		}

		return isFeriado;
	}

	public static Calendar getCalendar(DatePicker dFecha, int day) {
		LocalDate lDate = dFecha.getValue();
		int month = lDate.getMonthValue();
		int year = lDate.getYear();
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, 0, 0);
		return calendar;
	}

	public static Date getFirstDateOfMonth(DatePicker dPeriodoLibroGuia)
			throws ParseException {

		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDesde;
		fechaDesde = (Date) sdf.parse(dPeriodoLibroGuia.getValue().toString());
		calendar.setTime(fechaDesde);

		int last = calendar.getActualMinimum(Calendar.DATE);
		calendar.set(Calendar.DATE, last);

		Date fecha = calendar.getTime();

		return fecha;

	}

	public static String getBaseMesRuta(DatePicker fechaInforme) {
		String baseRuta = Constantes.S_EMPTY;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date fecha = (Date) sdf.parse(fechaInforme.getValue().toString());
			int mes = getMonthOfDate(fecha);
			baseRuta = generaRuta(mes);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return baseRuta;
	}

	public static String generaRuta(int mes) {
		String ruta = Constantes.S_EMPTY;

		switch (mes) {
		case 0:
			ruta = "01 - Enero";
			break;
		case 1:
			ruta = "02 - Febrero";
			break;
		case 2:
			ruta = "03 - Marzo";
			break;
		case 3:
			ruta = "04 - Abril";
			break;
		case 4:
			ruta = "05 - Mayo";
			break;
		case 5:
			ruta = "06 - Junio";
			break;
		case 6:
			ruta = "07 - Julio";
			break;
		case 7:
			ruta = "08 - Agosto";
			break;
		case 8:
			ruta = "09 - Septiembre";
			break;
		case 9:
			ruta = "10 - Octubre";
			break;
		case 10:
			ruta = "11 - Noviembre";
			break;
		case 11:
			ruta = "12 - Diciembre";
			break;
		default:
			ruta = "10 - Octubre";
			break;
		}
		return ruta;

	}

	public static String formatDateStrangePrev(String fechaRara) {
		String dia = Constantes.S_EMPTY;
		String mes = Constantes.S_EMPTY;
		String año = Constantes.S_EMPTY;

		if (fechaRara.split(" ").length > 1) {
			dia = fechaRara.split(" ")[2].trim();
			mes = getFormatMes(fechaRara.split(" ")[1].trim());
			año = fechaRara.split(" ")[5].trim();
			return dia + "-" + mes + "-" + año;
		} else {
			return fechaRara;
		}
	}

	public static String formatDateStrange(String fechaRara) {
		String dia = Constantes.S_EMPTY;
		String mes = Constantes.S_EMPTY;
		String año = Constantes.S_EMPTY;

		if (fechaRara.split(" ").length > 1) {
			dia = fechaRara.split(" ")[2].trim();
			mes = getFormatMes(fechaRara.split(" ")[1].trim());
			año = fechaRara.split(" ")[3].trim();
			return dia + "-" + mes + "-" + año;
		} else {
			return fechaRara;
		}
	}

	public static String getFormatMes(String mes) {
		String formatMes = Constantes.S_EMPTY;
		switch (mes) {
		case "Jan":
			formatMes = "01";
			break;
		case "Feb":
			formatMes = "02";
			break;
		case "Mar":
			formatMes = "03";
			break;
		case "Apr":
			formatMes = "04";
			break;
		case "May":
			formatMes = "05";
			break;
		case "Jun":
			formatMes = "06";
			break;
		case "Jul":
			formatMes = "07";
			break;
		case "Aug":
			formatMes = "08";
			break;
		case "Sep":
			formatMes = "09";
			break;
		case "Oct":
			formatMes = "10";
			break;
		case "Nov":
			formatMes = "11";
			break;
		case "Dec":
			formatMes = "12";
			break;
		default:
			formatMes = "10";
			break;
		}
		return formatMes;
	}

	public static int getYearOfDate(Date fecha) throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonthOfDate(Date fecha) throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		return calendar.get(Calendar.MONTH);
	}

	public static int getDayOfDate(Date fecha) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static String convertFecha(Date fecha) {
		String strFecha = Constantes.S_EMPTY;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		strFecha = sdf.format(fecha);
		return strFecha;
	}
	
	public static boolean beforeOrEqual(Date fecha1, Date fecha2){
		return fecha1.before(fecha2) || fecha1.equals(fecha2);
	}
	
	public static boolean afterOrEqual(Date fecha1, Date fecha2){
		return fecha1.after(fecha2) || fecha1.equals(fecha2);
	}

	public static void evaluaIncurrido(String item,
			TableCell<EmpleadoVO, String> col, DatePicker dFecha, int orden) {
		if (CnvString.isWeekEnd(dFecha, orden) || CnvString.isFeriado(dFecha, orden)) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #9798A0");
		} else if (isBefore(dFecha, orden)
				&& (null == item || item.equals(Constantes.S_EMPTY))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #E88718");
		} else if (null != item && !item.equals(Constantes.S_EMPTY)
				&& Double.valueOf(item) < 9) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #E5C61A");
		} else {
			col.setStyle("-fx-alignment: CENTER;");
		}
	}
	
	public static void evaluaIncurridoVacacion(String item, TableCell<EmpleadoVO, String> col, DatePicker dFecha, int orden) throws ParseException {
		if (null != item && (item.contains("Vacacion"))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #7984DF");
		} else if (null != item && (item.contains("Compensado"))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #11A91A");
		} else if (null != item && (item.contains("Enfermedad"))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #D3D618");
		}
	}

	public static void evaluaPrevision(String item, TableCell<EmpleadoVO, String> col, DatePicker dFecha, int orden)
			throws ParseException {
		if (null != item && (item.equals("V"))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #EF8308");
		} else if (null != item && (item.equals("C"))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #11A91A");
		} else if (null != item && (item.equals("L"))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #D3D618");
		} else if (CnvString.isFeriado(dFecha, orden)) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #7580e6");
		} else if (CnvString.isWeekEnd(dFecha, orden)) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #9798A0");
		} else if (null != item && (item.equals("B"))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #043042");
		} else {
			col.setStyle("-fx-alignment: CENTER;");
		}
	}
	
	public static void evaluaPrevisionPre(String item, TableCell<EmpleadoPrevVO, String> col, DatePicker dFecha, int orden)
			throws ParseException {
		if (null != item && (item.equals("V"))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #EF8308");
		} else if (null != item && (item.equals("C"))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #11A91A");
		} else if (null != item && (item.equals("L"))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #D3D618");
		} else if (CnvString.isFeriado(dFecha, orden)) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #7580e6");
		} else if (CnvString.isWeekEnd(dFecha, orden)) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #9798A0");
		} else if (null != item && (item.equals("B"))) {
			col.setStyle("-fx-alignment: CENTER; -fx-background-color:  #043042");
		} else {
			col.setStyle("-fx-alignment: CENTER;");
		}
	}

	public static Date gateDateFromPicker(DatePicker dFecha) {
		LocalDate lDate = dFecha.getValue();
		int day = lDate.getDayOfMonth();
		int month = lDate.getMonthValue();
		int year = lDate.getYear();
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, 0, 0);
		Date fecha = calendar.getTime();
		return fecha;
	}

	public static boolean isBefore(DatePicker dFecha, int day) {
		Calendar calendar = getCalendar(dFecha, day);
		Date fecha = calendar.getTime();
		return fecha.before(new Date());
	}

}
