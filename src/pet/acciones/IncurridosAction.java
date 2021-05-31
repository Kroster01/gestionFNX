package pet.acciones;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pet.util.CnvString;
import pet.util.Constantes;
import pet.vo.DiaVO;
import pet.vo.EmpleadoPrevVO;
import pet.vo.EmpleadoVO;
import pet.vo.PrevisionVO;

public class IncurridosAction {

	public static String getDescInc(EmpleadoVO empleado, int dia) {

		String descripcion = Constantes.S_EMPTY;
		switch (dia) {
		case 1:
			if(null != empleado && null != empleado.getDescDay1()){
				descripcion = empleado.getDescDay1();
			}	
			break;
		case 2:
			if(null != empleado && null != empleado.getDescDay2()){
				descripcion = empleado.getDescDay2();
			}
			break;
		case 3:
			if(null != empleado && null != empleado.getDescDay3()){
				descripcion = empleado.getDescDay3();
			}
			break;
		case 4:
			if(null != empleado && null != empleado.getDescDay4()){
				descripcion = empleado.getDescDay4();
			}
			break;
		case 5:
			if(null != empleado && null != empleado.getDescDay5()){
				descripcion = empleado.getDescDay5();
			}
			break;
		case 6:
			if(null != empleado && null != empleado.getDescDay6()){
				descripcion = empleado.getDescDay6();
			}
			break;
		case 7:
			if(null != empleado && null != empleado.getDescDay7()){
				descripcion = empleado.getDescDay7();
			}
			break;
		case 8:
			if(null != empleado && null != empleado.getDescDay8()){
				descripcion = empleado.getDescDay8();
			}
			break;
		case 9:
			if(null != empleado && null != empleado.getDescDay9()){
				descripcion = empleado.getDescDay9();
			}
			break;
		case 10:
			if(null != empleado && null != empleado.getDescDay10()){
				descripcion = empleado.getDescDay10();
			}
			break;
		case 11:
			if(null != empleado && null != empleado.getDescDay11()){
				descripcion = empleado.getDescDay11();
			}
			break;
		case 12:
			if(null != empleado && null != empleado.getDescDay12()){
				descripcion = empleado.getDescDay12();
			}
			break;
		case 13:
			if(null != empleado && null != empleado.getDescDay13()){
				descripcion = empleado.getDescDay13();
			}
			break;
		case 14:
			if(null != empleado && null != empleado.getDescDay14()){
				descripcion = empleado.getDescDay14();
			}
			break;
		case 15:
			if(null != empleado && null != empleado.getDescDay15()){
				descripcion = empleado.getDescDay15();
			}
			break;
		case 16:
			if(null != empleado && null != empleado.getDescDay16()){
				descripcion = empleado.getDescDay16();
			}
			break;
		case 17:
			if(null != empleado && null != empleado.getDescDay17()){
				descripcion = empleado.getDescDay17();
			}
			break;
		case 18:
			if(null != empleado && null != empleado.getDescDay18()){
				descripcion = empleado.getDescDay18();
			}		
			break;
		case 19:
			if(null != empleado && null != empleado.getDescDay19()){
				descripcion = empleado.getDescDay19();
			}
			break;
		case 20:
			if(null != empleado && null != empleado.getDescDay20()){
				descripcion = empleado.getDescDay20();
			}
			break;
		case 21:
			if(null != empleado && null != empleado.getDescDay21()){
				descripcion = empleado.getDescDay21();
			}
			break;
		case 22:
			if(null != empleado && null != empleado.getDescDay22()){
				descripcion = empleado.getDescDay22();
			}
			break;
		case 23:
			if(null != empleado && null != empleado.getDescDay23()){
				descripcion = empleado.getDescDay23();
			}
			break;
		case 24:
			if(null != empleado && null != empleado.getDescDay24()){
				descripcion = empleado.getDescDay24();
			}
			break;
		case 25:
			if(null != empleado && null != empleado.getDescDay25()){
				descripcion = empleado.getDescDay25();
			}
			break;
		case 26:
			if(null != empleado && null != empleado.getDescDay26()){
				descripcion = empleado.getDescDay26();
			}
			break;
		case 27:
			if(null != empleado && null != empleado.getDescDay27()){
				descripcion = empleado.getDescDay27();
			}
			break;
		case 28:
			if(null != empleado && null != empleado.getDescDay28()){
				descripcion = empleado.getDescDay28();
			}
			break;
		case 29:
			if(null != empleado && null != empleado.getDescDay29()){
				descripcion = empleado.getDescDay29();
			}
			break;
		case 30:
			if(null != empleado && null != empleado.getDescDay30()){
				descripcion = empleado.getDescDay30();
			}
			break;
		case 31:
			if(null != empleado && null != empleado.getDescDay31()){
				descripcion = empleado.getDescDay31();
			}
			break;
		default:
			// code block
		}

		return descripcion;
	}

	public static void setHorasInc(EmpleadoVO empleado,
			HashMap<Date, DiaVO> incuAgrup) throws ParseException {

		for (Map.Entry<Date, DiaVO> ent : incuAgrup.entrySet()) {
			Date fecha = ent.getKey();
			int dia = CnvString.getDayOfDate(fecha);
			DiaVO diaVO = ent.getValue();
			Double horas = diaVO.getHoras();
			String descDia = diaVO.getDescIncurrido();
			// System.out.println("Fecha: " + fecha + " Dia: " + dia +
			// " dia de la semana: " + CnvString.isWeekend(fecha));
			switch (dia) {
			case 1:
				empleado.setDay1(String.valueOf(horas));
				empleado.setDescDay1(descDia);
				break;
			case 2:
				empleado.setDay2(String.valueOf(horas));
				empleado.setDescDay2(descDia);
				break;
			case 3:
				empleado.setDay3(String.valueOf(horas));
				empleado.setDescDay3(descDia);
				break;
			case 4:
				empleado.setDay4(String.valueOf(horas));
				empleado.setDescDay4(descDia);
				break;
			case 5:
				empleado.setDay5(String.valueOf(horas));
				empleado.setDescDay5(descDia);
				break;
			case 6:
				empleado.setDay6(String.valueOf(horas));
				empleado.setDescDay6(descDia);
				break;
			case 7:
				empleado.setDay7(String.valueOf(horas));
				empleado.setDescDay7(descDia);
				break;
			case 8:
				empleado.setDay8(String.valueOf(horas));
				empleado.setDescDay8(descDia);
				break;
			case 9:
				empleado.setDay9(String.valueOf(horas));
				empleado.setDescDay9(descDia);
				break;
			case 10:
				empleado.setDay10(String.valueOf(horas));
				empleado.setDescDay10(descDia);
				break;
			case 11:
				empleado.setDay11(String.valueOf(horas));
				empleado.setDescDay11(descDia);
				break;
			case 12:
				empleado.setDay12(String.valueOf(horas));
				empleado.setDescDay12(descDia);
				break;
			case 13:
				empleado.setDay13(String.valueOf(horas));
				empleado.setDescDay13(descDia);
				break;
			case 14:
				empleado.setDay14(String.valueOf(horas));
				empleado.setDescDay14(descDia);
				break;
			case 15:
				empleado.setDay15(String.valueOf(horas));
				empleado.setDescDay15(descDia);
				break;
			case 16:
				empleado.setDay16(String.valueOf(horas));
				empleado.setDescDay16(descDia);
				break;
			case 17:
				empleado.setDay17(String.valueOf(horas));
				empleado.setDescDay17(descDia);
				break;
			case 18:
				empleado.setDay18(String.valueOf(horas));
				empleado.setDescDay18(descDia);
				break;
			case 19:
				empleado.setDay19(String.valueOf(horas));
				empleado.setDescDay19(descDia);
				break;
			case 20:
				empleado.setDay20(String.valueOf(horas));
				empleado.setDescDay20(descDia);
				break;
			case 21:
				empleado.setDay21(String.valueOf(horas));
				empleado.setDescDay21(descDia);
				break;
			case 22:
				empleado.setDay22(String.valueOf(horas));
				empleado.setDescDay22(descDia);
				break;
			case 23:
				empleado.setDay23(String.valueOf(horas));
				empleado.setDescDay23(descDia);
				break;
			case 24:
				empleado.setDay24(String.valueOf(horas));
				empleado.setDescDay24(descDia);
				break;
			case 25:
				empleado.setDay25(String.valueOf(horas));
				empleado.setDescDay25(descDia);
				break;
			case 26:
				empleado.setDay26(String.valueOf(horas));
				empleado.setDescDay26(descDia);
				break;
			case 27:
				empleado.setDay27(String.valueOf(horas));
				empleado.setDescDay27(descDia);
				break;
			case 28:
				empleado.setDay28(String.valueOf(horas));
				empleado.setDescDay28(descDia);
				break;
			case 29:
				empleado.setDay29(String.valueOf(horas));
				empleado.setDescDay29(descDia);
				break;
			case 30:
				empleado.setDay30(String.valueOf(horas));
				empleado.setDescDay30(descDia);
				break;
			case 31:
				empleado.setDay31(String.valueOf(horas));
				empleado.setDescDay31(descDia);
				break;
			default:
				// code block
			}
		}
	}

	public static ObservableList<PrevisionVO> mixListasPrevision(
			ObservableList<PrevisionVO> previsiones,
			ObservableList<PrevisionVO> previsionesUpdate) {
		ObservableList<PrevisionVO> previsionesFinal = FXCollections
				.observableArrayList();

		for (PrevisionVO prevOld : previsiones) {
			for (PrevisionVO prevNew : previsionesUpdate) {
				if (prevOld.getNroEmpleado() == prevNew.getNroEmpleado() && prevOld.getFecha().equals(prevNew.getFecha())) {
					prevOld.setHoras(prevNew.getHoras());
				}
			}
			previsionesFinal.add(prevOld);
		}

		for (PrevisionVO prevNew : previsionesUpdate) {
			boolean esta = false;
			for (PrevisionVO prevOld : previsiones) {
				if (prevOld.getNroEmpleado() == prevNew.getNroEmpleado() && prevOld.getFecha().equals(prevNew.getFecha())) {
					esta = true;
				}
			}
			if (!esta) {
				previsionesFinal.add(prevNew);
			}
		}

		return previsionesFinal;
	}

	public static ObservableList<PrevisionVO> updatePrevisiones(ObservableList<EmpleadoPrevVO> empleados, DatePicker fechaInforme)
			throws ParseException {

		ObservableList<PrevisionVO> previsiones = DatosAction.iniciarPrevision();
		ObservableList<PrevisionVO> previsionesUpdate = FXCollections.observableArrayList();
		ObservableList<PrevisionVO> previsionesNew = FXCollections.observableArrayList();

		int mes = CnvString.getMonthOfDate(CnvString.gateDateFromPicker(fechaInforme));
		int year = CnvString.getYearOfDate(CnvString.gateDateFromPicker(fechaInforme));

		for (EmpleadoPrevVO emp : empleados) {
			getPrevisionEmpleadoMes(emp, mes, year, previsionesUpdate);
		}
		previsionesNew = mixListasPrevision(previsiones, previsionesUpdate);

		return previsionesNew;
	}

	public static PrevisionVO setPrevision(EmpleadoPrevVO emp, String dia, int mes,int year, String horas) {
		PrevisionVO prevision = new PrevisionVO();
		prevision.setNroEmpleado(Integer.valueOf(emp.getNroEmpleado()));
		Date fecha = CnvString.getDate(dia + "-" + String.valueOf(mes) + "-" + String.valueOf(year), "dd-MM-yyyy");
		prevision.setFecha(fecha);
		if (null == horas || horas.equals(Constantes.S_EMPTY)) {
			prevision.setHoras(Constantes.S_EMPTY);
		} else {
			prevision.setHoras(horas);
		}
		return prevision;
	}

	public static void getPrevisionEmpleadoMes(EmpleadoPrevVO emp, int mes,
			int year, ObservableList<PrevisionVO> previsiones) {
		mes++;
		previsiones.add(setPrevision(emp, "01", mes, year, emp.getDay1()));
		previsiones.add(setPrevision(emp, "02", mes, year, emp.getDay2()));
		previsiones.add(setPrevision(emp, "03", mes, year, emp.getDay3()));
		previsiones.add(setPrevision(emp, "04", mes, year, emp.getDay4()));
		previsiones.add(setPrevision(emp, "05", mes, year, emp.getDay5()));
		previsiones.add(setPrevision(emp, "06", mes, year, emp.getDay6()));
		previsiones.add(setPrevision(emp, "07", mes, year, emp.getDay7()));
		previsiones.add(setPrevision(emp, "08", mes, year, emp.getDay8()));
		previsiones.add(setPrevision(emp, "09", mes, year, emp.getDay9()));
		previsiones.add(setPrevision(emp, "10", mes, year, emp.getDay10()));
		previsiones.add(setPrevision(emp, "11", mes, year, emp.getDay11()));
		previsiones.add(setPrevision(emp, "12", mes, year, emp.getDay12()));
		previsiones.add(setPrevision(emp, "13", mes, year, emp.getDay13()));
		previsiones.add(setPrevision(emp, "14", mes, year, emp.getDay14()));
		previsiones.add(setPrevision(emp, "15", mes, year, emp.getDay15()));
		previsiones.add(setPrevision(emp, "16", mes, year, emp.getDay16()));
		previsiones.add(setPrevision(emp, "17", mes, year, emp.getDay17()));
		previsiones.add(setPrevision(emp, "18", mes, year, emp.getDay18()));
		previsiones.add(setPrevision(emp, "19", mes, year, emp.getDay19()));
		previsiones.add(setPrevision(emp, "20", mes, year, emp.getDay20()));
		previsiones.add(setPrevision(emp, "21", mes, year, emp.getDay21()));
		previsiones.add(setPrevision(emp, "22", mes, year, emp.getDay22()));
		previsiones.add(setPrevision(emp, "23", mes, year, emp.getDay23()));
		previsiones.add(setPrevision(emp, "24", mes, year, emp.getDay24()));
		previsiones.add(setPrevision(emp, "25", mes, year, emp.getDay25()));
		previsiones.add(setPrevision(emp, "26", mes, year, emp.getDay26()));
		previsiones.add(setPrevision(emp, "27", mes, year, emp.getDay27()));
		previsiones.add(setPrevision(emp, "28", mes, year, emp.getDay28()));
		previsiones.add(setPrevision(emp, "29", mes, year, emp.getDay29()));
		previsiones.add(setPrevision(emp, "30", mes, year, emp.getDay30()));
		previsiones.add(setPrevision(emp, "31", mes, year, emp.getDay31()));
	}
	
	public static void delHorasWeekendFeriado(ObservableList<EmpleadoPrevVO> empleadosFull, DatePicker dFecha, TextField tTotalProyecto){
		
		int totalProyecto = 0;
		for (EmpleadoPrevVO empleadoVO : empleadosFull) {
			int total = 0;
			if(CnvString.isWeekEnd(dFecha, 1)){
				empleadoVO.setDay1(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay1().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 2)){
				empleadoVO.setDay2(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay2().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 3)){
				empleadoVO.setDay3(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay3().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 4)){
				empleadoVO.setDay4(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay4().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 5)){
				empleadoVO.setDay5(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay5().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 6)){
				empleadoVO.setDay6(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay6().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 7)){
				empleadoVO.setDay7(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay7().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 8)){
				empleadoVO.setDay8(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay8().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 9)){
				empleadoVO.setDay9(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay9().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 10)){
				empleadoVO.setDay10(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay10().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 11)){
				empleadoVO.setDay11(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay11().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 12)){
				empleadoVO.setDay12(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay12().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 13)){
				empleadoVO.setDay13(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay13().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isFeriado(dFecha, 14) || CnvString.isWeekEnd(dFecha, 14)){
				empleadoVO.setDay14(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay14().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 15)){
				empleadoVO.setDay1(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay15().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 16)){
				empleadoVO.setDay16(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay16().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 17)){
				empleadoVO.setDay17(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay17().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 18)){
				empleadoVO.setDay18(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay18().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 19)){
				empleadoVO.setDay19(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay19().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 20)){
				empleadoVO.setDay20(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay20().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 21)){
				empleadoVO.setDay21(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay21().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 22)){
				empleadoVO.setDay22(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay22().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 23)){
				empleadoVO.setDay23(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay23().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 24)){
				empleadoVO.setDay24(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay24().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 25)){
				empleadoVO.setDay25(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay25().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 26)){
				empleadoVO.setDay26(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay26().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 27)){
				empleadoVO.setDay27(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay27().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 28)){
				empleadoVO.setDay28(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay28().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 29)){
				empleadoVO.setDay29(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay29().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 30)){
				empleadoVO.setDay30(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay30().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			
			if(CnvString.isWeekEnd(dFecha, 31)){
				empleadoVO.setDay31(Constantes.S_EMPTY);
			}else if(empleadoVO.getDay31().equals(Constantes.sCodNine)){
				total = total + 9;
			}
			empleadoVO.setTotal(String.valueOf(total));
			totalProyecto = totalProyecto + total;
		}
		tTotalProyecto.setText(String.valueOf(totalProyecto));
		
	}

	public static void setHorasPrevision(EmpleadoPrevVO empleado, HashMap<Date, String> incuAgrup) throws ParseException {

		for (Map.Entry<Date, String> ent : incuAgrup.entrySet()) {
			Date fecha = ent.getKey();
			int dia = CnvString.getDayOfDate(fecha);
			String horas = ent.getValue();

			switch (dia) {
			case 1:
				empleado.setDay1(String.valueOf(horas));
				break;
			case 2:
				empleado.setDay2(String.valueOf(horas));
				break;
			case 3:
				empleado.setDay3(String.valueOf(horas));
				break;
			case 4:
				empleado.setDay4(String.valueOf(horas));
				break;
			case 5:
				empleado.setDay5(String.valueOf(horas));
				break;
			case 6:
				empleado.setDay6(String.valueOf(horas));
				break;
			case 7:
				empleado.setDay7(String.valueOf(horas));
				break;
			case 8:
				empleado.setDay8(String.valueOf(horas));
				break;
			case 9:
				empleado.setDay9(String.valueOf(horas));
				break;
			case 10:
				empleado.setDay10(String.valueOf(horas));
				break;
			case 11:
				empleado.setDay11(String.valueOf(horas));
				break;
			case 12:
				empleado.setDay12(String.valueOf(horas));
				break;
			case 13:
				empleado.setDay13(String.valueOf(horas));
				break;
			case 14:
				empleado.setDay14(String.valueOf(horas));
				break;
			case 15:
				empleado.setDay15(String.valueOf(horas));
				break;
			case 16:
				empleado.setDay16(String.valueOf(horas));
				break;
			case 17:
				empleado.setDay17(String.valueOf(horas));
				break;
			case 18:
				empleado.setDay18(String.valueOf(horas));
				break;
			case 19:
				empleado.setDay19(String.valueOf(horas));
				break;
			case 20:
				empleado.setDay20(String.valueOf(horas));
				break;
			case 21:
				empleado.setDay21(String.valueOf(horas));
				break;
			case 22:
				empleado.setDay22(String.valueOf(horas));
				break;
			case 23:
				empleado.setDay23(String.valueOf(horas));
				break;
			case 24:
				empleado.setDay24(String.valueOf(horas));
				break;
			case 25:
				empleado.setDay25(String.valueOf(horas));
				break;
			case 26:
				empleado.setDay26(String.valueOf(horas));
				break;
			case 27:
				empleado.setDay27(String.valueOf(horas));
				break;
			case 28:
				empleado.setDay28(String.valueOf(horas));
				break;
			case 29:
				empleado.setDay29(String.valueOf(horas));
				break;
			case 30:
				empleado.setDay30(String.valueOf(horas));
				break;
			case 31:
				empleado.setDay31(String.valueOf(horas));
				break;
			default:
			}
		}
	}

}
