package pet.util;

import java.text.ParseException;

import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import pet.acciones.IncurridosAction;
import pet.vo.EmpleadoPrevVO;
import pet.vo.EmpleadoVO;

public class TableColumnUtil {
	
	public static void setIniColumnInc(TableColumn<EmpleadoVO, String> column,
			final DatePicker dFecha, final int orden, String elemento) {

		column.setStyle("-fx-alignment: CENTER;");
		column.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>(elemento));
		column.setCellFactory(new Callback<TableColumn<EmpleadoVO, String>, TableCell<EmpleadoVO, String>>() {
            @Override
            public TableCell<EmpleadoVO, String> call(TableColumn<EmpleadoVO, String> p) {
                TableCell<EmpleadoVO, String> cell = new TableCell<EmpleadoVO, String>() {
                	private Tooltip tooltip = new Tooltip();
                    @Override
                    public void updateItem(String item, boolean empty) {                    	
                        super.updateItem(item, empty);
                        setText(item);
                        EmpleadoVO emp = (EmpleadoVO) getTableRow().getItem();
                        String descripcion = IncurridosAction.getDescInc(emp, orden);
                        tooltip.setText(descripcion.replace(';', '\n'));
                        setTooltip(tooltip);
                    	CnvString.evaluaIncurrido(item, this, dFecha, orden);
                    	try {
							CnvString.evaluaIncurridoVacacion(descripcion, this, dFecha, orden);
						} catch (ParseException e) {
							e.printStackTrace();
						}
                    }
                };
                return cell;
            }
        });

	}

	public static void setIniColumn(TableColumn<EmpleadoVO, String> column,
			final DatePicker dFecha, final int orden, String elemento) {

		column.setStyle("-fx-alignment: CENTER;");
		column.setCellValueFactory(new PropertyValueFactory<EmpleadoVO, String>(elemento));
		column.setCellFactory(new Callback<TableColumn<EmpleadoVO, String>, TableCell<EmpleadoVO, String>>() {
			@Override
			public TableCell<EmpleadoVO, String> call(
					TableColumn<EmpleadoVO, String> p) {

				TableCell<EmpleadoVO, String> cell = new EditingCell() {
					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						setText(item);
						try {
							CnvString.evaluaPrevision(item, this, dFecha, orden);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				};

				return cell;
			}
		});

		column.setOnEditCommit(new EventHandler<CellEditEvent<EmpleadoVO, String>>() {
			@Override
			public void handle(CellEditEvent<EmpleadoVO, String> t) {
				int col = t.getTablePosition().getColumn() -1;
				setDia(((EmpleadoVO) t.getTableView().getItems().get(t.getTablePosition().getRow())), col, t.getNewValue());
			}
		});
	}
	
	public static void setIniColumnPrev(TableColumn<EmpleadoPrevVO, String> column, final DatePicker dFecha, 
			final int orden, String elemento) {

		column.setStyle("-fx-alignment: CENTER;");
		column.setCellValueFactory(new PropertyValueFactory<EmpleadoPrevVO, String>(elemento));
		column.setCellFactory(new Callback<TableColumn<EmpleadoPrevVO, String>, TableCell<EmpleadoPrevVO, String>>() {
			@Override
			public TableCell<EmpleadoPrevVO, String> call(
					TableColumn<EmpleadoPrevVO, String> p) {

				TableCell<EmpleadoPrevVO, String> cell = new EditingCellPrev() {
					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						setText(item);
						try {
							CnvString.evaluaPrevisionPre(item, this, dFecha, orden);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				};

				return cell;
			}
		});

		column.setOnEditCommit(new EventHandler<CellEditEvent<EmpleadoPrevVO, String>>() {
			@Override
			public void handle(CellEditEvent<EmpleadoPrevVO, String> t) {
				int col = t.getTablePosition().getColumn() -1;
				setDia(((EmpleadoPrevVO) t.getTableView().getItems().get(t.getTablePosition().getRow())), col, t.getNewValue());
			}
		});
	}
	
	public static void setDia(EmpleadoPrevVO empleado, int dia, String horas) {
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
			// code block
		}
	}

	public static void setDia(EmpleadoVO empleado, int dia, String horas) {
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
			// code block
		}
	}

}
