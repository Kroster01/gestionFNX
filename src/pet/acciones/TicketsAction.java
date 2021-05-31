package pet.acciones;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pet.controller.UpdateTicket;
import pet.util.Constantes;
import pet.util.MessagesBox;
import pet.util.PrintLog;
import pet.vo.TicketsVO;

public class TicketsAction {

    //Comparator for int, by Number
    static Comparator<? super TicketsVO> comparador = new Comparator<TicketsVO>() {
        public int compare(TicketsVO o1, TicketsVO o2) {
            return o1.getIdPeticion() - o2.getIdPeticion();
        }
    };
    
	// Metodo para iniciar la Ventana principal al retornar.
	@SuppressWarnings("resource")
	public static boolean iniciar(TableView<TicketsVO> tabla, String codDemanda) {
		String linea;
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_TIK);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			ObservableList<TicketsVO> tickets = FXCollections.observableArrayList();

			while ((linea = lector.readLine()) != null) {
				if(linea.contains(String.valueOf(codDemanda))){
					TicketsVO ticket = crearTicketVO(linea);
					tickets.add(ticket);
				}
			}
			FXCollections.sort(tickets, comparador);
			tabla.getItems().clear();
			tabla.setItems(tickets);
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
	
	@SuppressWarnings("resource")
	public static void buscarRegistro(TableView<TicketsVO> tableTicket, TextField tBusqueda){
		String linea;
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_TIK);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			ObservableList<TicketsVO> tickets = FXCollections.observableArrayList();

			while ((linea = lector.readLine()) != null) {
				if(linea.toLowerCase().contains(tBusqueda.getText().toLowerCase())){
					TicketsVO ticket = crearTicketVO(linea);
					tickets.add(ticket);
				}
			}
			FXCollections.sort(tickets, comparador);
			tableTicket.getSelectionModel().clearSelection();
			tableTicket.setItems(tickets);
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
	
	public static TicketsVO crearTicketVO(String linea){
		TicketsVO ticket = new TicketsVO();
		String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
		ticket.setIdObservacion(elementos[0].trim());
		ticket.setIdPeticion(Integer.valueOf(elementos[1].trim()));
		ticket.setCodDemanda(elementos[2].trim());
		ticket.setDescTicket(elementos[3].trim());
		
		return ticket;
	}
	
	@SuppressWarnings("resource")
	public static void eliminaReg(String idTicket){		
		ArrayList<String> registros = new ArrayList<String>();
		String linea;
		// Se guardan todos los registros del fichero menos el seleccionado
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_TIK);
			lector = new BufferedReader(new FileReader(fis.getFD()));
			while ((linea = lector.readLine()) != null) {
				String[] elementos = linea.split(Constantes.S_PUNTO_COMA);
				if(!idTicket.equals(elementos[0].trim())){
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
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_TIK);
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
	
	public static boolean actualizarRegistro(String idObservacion, TextField idPeticion, TextField codDemanda, TextField descTicket){
		boolean resultado = false;
		if(validaDatosObligatorios(idPeticion, codDemanda, descTicket)){
			eliminaReg(idObservacion);
			resultado = guardarRegistro(idPeticion, codDemanda, descTicket);
		}
		return resultado;
	}
	
	public static boolean guardarRegistro(TextField idPeticion,TextField codDemanda, TextField descTicket){
		boolean resultado = false;
		
		if(validaDatosObligatorios(idPeticion, codDemanda, descTicket)){
			StringBuffer registro = new StringBuffer();
			registro.append(obtieneId() + Constantes.S_PUNTO_COMA );
			registro.append(idPeticion.getText() + Constantes.S_PUNTO_COMA);
			registro.append(codDemanda.getText() + Constantes.S_PUNTO_COMA);
			registro.append(descTicket.getText());
	

			// Ingresa en el fichero el registro
			resultado = escribeFichero(registro.toString());
		}
		
		return resultado;
	}
	
	public static boolean validaDatosObligatorios(TextField idPeticion,TextField codDemanda,TextField descTicket){
		boolean resultado = true;
		
		if(null == idPeticion || null == idPeticion.getText() || idPeticion.getText().equals(Constantes.S_EMPTY)){
			MessagesBox.createAlert("Error", "Debe informar la demanda asociada.");
			resultado = false;
			return resultado;
		}
		
		if(null == codDemanda || null == codDemanda.getText() || codDemanda.getText().equals(Constantes.S_EMPTY)){
			MessagesBox.createAlert("Error", "Debe informar la demanda asociada.");
			resultado = false;
			return resultado;
		}
				
		if(null == descTicket || null == descTicket.getText() || descTicket.getText().equals(Constantes.S_EMPTY)){
			MessagesBox.createAlert("Error", "Debe informar observación.");
			resultado = false;
			return resultado;
		}
				
		return resultado;
	}
	
	public static boolean escribeFichero(String linea){
		boolean resultado = false;
		// Se escribe en fichero principal
		try {
			FileWriter fichero = new FileWriter(Constantes.RUTA_ARCHIVO_TIK, true);
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
	
	public void modificaTicket(TicketsVO ticket, TableView<TicketsVO> tableTicket){
    	try {
			// Nueva Pantalla detalle de compra
			final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/updateTicket.fxml");
			final FXMLLoader fxmlLoader = new FXMLLoader(clase);
			Parent root;
			root = (Parent)fxmlLoader.load();

			UpdateTicket controller = fxmlLoader.<UpdateTicket>getController();
			controller.setDataIni(ticket, tableTicket);

			final Scene scene = new Scene(root);
			Stage stageDetalle = new Stage();
			stageDetalle.setScene(scene);
			stageDetalle.setTitle("Modificar Ticket");					
			stageDetalle.show();
		} catch (IOException e1) {
			Logger log = PrintLog.getLogger();
			FileHandler fh = PrintLog.getHandler();
			log.addHandler(fh);
			log.info(e1.getMessage());
			PrintLog.closeHandler(fh);
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public static int obtieneId() {
		String linea;
		int id = 0; 
		try {
			BufferedReader lector = null;
			FileInputStream fis = new FileInputStream(Constantes.RUTA_ARCHIVO_TIK);
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
}
