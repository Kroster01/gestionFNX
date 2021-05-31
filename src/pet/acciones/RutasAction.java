package pet.acciones;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.util.PrintLog;
import pet.vo.RutasVO;

public class RutasAction {
	
    //Comparator for int, by Number
    static Comparator<? super RutasVO> comparador = new Comparator<RutasVO>() {
        public int compare(RutasVO o1, RutasVO o2) {
            return o1.getIdRuta() - o2.getIdRuta();
        }
    };
	
	// Metodo para iniciar la Ventana principal al retornar.
	@SuppressWarnings("resource")
	public static boolean iniciar(TableView<RutasVO> tabla) {
		String linea;
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_RUT);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			ObservableList<RutasVO> rutas = FXCollections.observableArrayList();

			while ((linea = lector.readLine()) != null) {
				RutasVO peticion = crearPeticionVO(linea);
				rutas.add(peticion);
			}
			FXCollections.sort(rutas, comparador);
			tabla.getItems().clear();
			tabla.setItems(rutas);
			fis.close();
			return true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
			return false;
		}
	}
	
	public static RutasVO crearPeticionVO(String linea){
		RutasVO ruta = new RutasVO();
		String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
		ruta.setIdRuta(Integer.valueOf(elementos[0].trim()));
		ruta.setDescripcion(elementos[1].trim());
		ruta.setDirectorio(elementos[2].trim());
		return ruta;
	}
	
	@SuppressWarnings("resource")
	public static void eliminaReg(String idRuta){		
		ArrayList<String> registros = new ArrayList<String>();
		String linea;
		// Se guardan todos los registros del fichero menos el seleccionado
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_RUT);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			while ((linea = lector.readLine()) != null) {
				String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
				if(!idRuta.equals(elementos[0].trim())){
					registros.add(linea);
				}
			}
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
		// Se rescribe el fichero con los registros que han quedado
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_RUT);
			PrintWriter pw = new PrintWriter(fichero);
			for (String reg : registros) {
				pw.println(reg);
			}
			fichero.close();
		}catch (Exception ex){
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(ex.getMessage());
			PrintLog.closeHandler(fh);
			ex.printStackTrace();
		}
		
	}
	
	public static void prepDataBorrar(TableView<RutasVO> tableRutas, TableColumn<RutasVO, Integer> colIdRuta){
		String idRuta = Constantes.S_EMPTY;
		int row = tableRutas.getSelectionModel().getSelectedCells().get(0).getRow();
		idRuta = colIdRuta.getCellData(row).toString();
		eliminaReg(idRuta);
	}
	
	public static boolean actualizarRegistro(int idRuta, TextField descripcion, TextField directorio){
		boolean resultado = false;
		if(null != descripcion && null != descripcion.getText() && !descripcion.getText().equals(Constantes.S_EMPTY)){
			eliminaReg(String.valueOf(idRuta));
			resultado = guardarRegistro(idRuta, descripcion, directorio);
		}
		return resultado;
	}
	
	public static boolean guardarRegistro(int idRuta, TextField descripcion, TextField directorion){
		boolean resultado = false;
		
		if(validaDatosObligatorios(descripcion, directorion) && obtieneId() > 0){
			StringBuffer registro = new StringBuffer();
			if(idRuta > 0){
				registro.append(String.valueOf(idRuta) + Constantes.S_PUNTO_COMA);
			}else{
				registro.append(String.valueOf(obtieneId()) + Constantes.S_PUNTO_COMA);
			}
			
			registro.append(descripcion.getText() + Constantes.S_PUNTO_COMA);
			registro.append(directorion.getText() + Constantes.S_PUNTO_COMA);

			// Ingresa en el fichero el registro
			resultado = escribeFichero(registro.toString());
		}
		
		return resultado;
	}
	
	public static boolean escribeFichero(String linea){
		boolean resultado = false;
		// Se escribe en fichero principal
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_RUT, true);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println(linea);
			fichero.close();
			resultado = true;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			MessagesBox.createAlert("Error", "Error al ingresar registro.");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static boolean validaDatosObligatorios(TextField descripcion, TextField directorio){
		boolean resultado = true;
		
		
		if(null == descripcion || null == descripcion.getText() || descripcion.getText().equals(Constantes.S_EMPTY)){
			MessagesBox.createAlert("Error", "Debe informar descripción.");
			resultado = false;
			return resultado;
		}
		
		if(null == directorio || null == directorio.getText() || directorio.getText().equals(Constantes.S_EMPTY)){
			MessagesBox.createAlert("Error", "Debe informar directorio.");
			resultado = false;
			return resultado;
		}

		
		return resultado;
	}
	
	@SuppressWarnings("resource")
	public static int obtieneId() {
		String linea;
		int id = 0; 
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_RUT);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			while ((linea = lector.readLine()) != null) {
				String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
				if(Integer.valueOf(elementos[0].trim()) - id > 0){
					id = Integer.valueOf(elementos[0].trim());
				}
			}
			fis.close();
			return id + Constantes.iCodOne;
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
			return id;
		}
	}
	
	public static boolean abrirDirectorio(TableView<RutasVO> tablePath, TableColumn<RutasVO, String> colDescripcion,
			TableColumn<RutasVO, String> colPath) {
		boolean resultado = false;
		String ruta = Constantes.S_EMPTY;
		int column = tablePath.getSelectionModel().getSelectedCells().get(0).getColumn();
		int row = tablePath.getSelectionModel().getSelectedCells().get(0).getRow();

		switch (column) {
		// Ruta Centro
		case 1:
			ruta = colDescripcion.getCellData(row).trim();
			if(null != ruta){
				resultado = true;
			}
			break;
			// Ruta Solution
		case 2:
			ruta = colPath.getCellData(row).trim();
			if(null != ruta){
				resultado = abrir(ruta);
			}
			break;
		default:
			resultado = true;
			break;
		}
		return resultado;
	}

	public static boolean abrir(String valor) {
		Desktop desktop = null;
		boolean resultado = false;
		if(null != valor && !valor.equals(Constantes.S_EMPTY)){
			// Abre directorio
			File file = new File(valor.trim());
			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
			}
			try {
				desktop.open(file);
				resultado = true;
			} catch (Exception ex) {
				Logger log = PrintLog.getLogger();
				FileHandler fh = PrintLog.getHandler();
				log.addHandler(fh);
				log.info(ex.getMessage());
				PrintLog.closeHandler(fh);
				ex.printStackTrace();
			}
		}else{
			// Se fuerza como resultado positivo ya que no hay ruta ingresada en el registro
			resultado = true;
		}

		return resultado;
	}
	
	public static void resetDatos(TextField descripcion, TextField directorio){
		descripcion.setText(Constantes.S_EMPTY);
		directorio.setText(Constantes.S_EMPTY);
	}
	
	@SuppressWarnings("resource")
	public static void buscarRegistro(TableView<RutasVO> tablePath, TextField tBusqueda){
		String linea;
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_RUT);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			ObservableList<RutasVO> rutas = FXCollections.observableArrayList();

			while ((linea = lector.readLine()) != null) {
				if(linea.toLowerCase().contains(tBusqueda.getText().toLowerCase())){
					RutasVO ruta = crearPeticionVO(linea);
					rutas.add(ruta);
				}
			}
			FXCollections.sort(rutas, comparador);
			tablePath.getSelectionModel().clearSelection();
			tablePath.setItems(rutas);
			fis.close();
		} catch (Exception e) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e.getMessage());
			PrintLog.closeHandler(fh);
			e.printStackTrace();
		}
	}
}
