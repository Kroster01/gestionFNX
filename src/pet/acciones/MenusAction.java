package pet.acciones;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import pet.controller.IngresaTicket;
import pet.controller.ListaTickets;
import pet.controller.ResumenAgile;
import pet.util.PrintLog;
import pet.vo.PeticionesVO;
import pet.vo.SprintVO;

public class MenusAction {
	
	public static void creaMenuCopiar(final TableView<PeticionesVO> tablePeticiones, MenuItem item, ContextMenu menu){
		item = new MenuItem("Copiar");
		item.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	            ObservableList<PeticionesVO> rowList = (ObservableList<PeticionesVO>) tablePeticiones.getSelectionModel().getSelectedItems();
	            StringBuilder clipboardString = new StringBuilder();
	            for (Iterator<PeticionesVO> it = rowList.iterator(); it.hasNext();) {
	                PeticionesVO peticion = (PeticionesVO) it.next();
	        		int column = tablePeticiones.getSelectionModel().getSelectedCells().get(0).getColumn();
	        		switch (column) {
	        		case 1:
	                    clipboardString.append(peticion.getIdPeticion());
	        			break;
	        		case 2:
	                    clipboardString.append(peticion.getCodDemanda());
	        			break;
	        		case 3:
	                    clipboardString.append(peticion.getDescripcion());
	        			break;
	        		case 4:
	                    clipboardString.append(peticion.getResponsable());
	        			break;
	        		case 5:
	                    clipboardString.append(peticion.getDirCentro());
	        			break;
	        		case 6:
	                    clipboardString.append(peticion.getDirRtcOsi());
	        			break;
	        		case 7:
	                    clipboardString.append(peticion.getDirRtcPap());
	        			break;
	        		default:
	        			break;
	        		}
	               // clipboardString.append('\n');
	            }
	            final ClipboardContent content = new ClipboardContent();

	            content.putString(clipboardString.toString());
	            Clipboard.getSystemClipboard().setContent(content);
	        }
	    });
	    menu.getItems().add(item);
	    tablePeticiones.setContextMenu(menu);
	}
	
	public static void creaMenuAsociarTicket(final TableView<PeticionesVO> tablePeticiones, MenuItem item, ContextMenu menu){
		item = new MenuItem("Ingresar observación");
		item.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	    		try {
					// Nueva Pantalla detalle de compra
					final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/ingresaTicket.fxml");
					final FXMLLoader fxmlLoader = new FXMLLoader(clase);
					Parent root;
					root = (Parent)fxmlLoader.load();
					
					IngresaTicket controller = fxmlLoader.<IngresaTicket>getController();
					controller.setDataIni(tablePeticiones);
		
					final Scene scene = new Scene(root);
					Stage stageDetalle = new Stage();
					stageDetalle.setScene(scene);
					stageDetalle.setTitle("Asociar observación");					
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
	    });
	    menu.getItems().add(item);
	    tablePeticiones.setContextMenu(menu);
	}
	
	public static void creaMenuVerTicket(final TableView<PeticionesVO> tablePeticiones, MenuItem item, ContextMenu menu){
		item = new MenuItem("Ver Bitácora");
		item.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	try {
					// Nueva Pantalla detalle de compra
					final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/displayTickets.fxml");
					final FXMLLoader fxmlLoader = new FXMLLoader(clase);
					Parent root;
					root = (Parent)fxmlLoader.load();

					ListaTickets controller = fxmlLoader.<ListaTickets>getController();
					controller.setDataIni(tablePeticiones);
		
					final Scene scene = new Scene(root);
					Stage stageDetalle = new Stage();
					stageDetalle.setScene(scene);
					stageDetalle.setTitle("Observaciones asociadas");					
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
	    });
	    menu.getItems().add(item);
	    tablePeticiones.setContextMenu(menu);
	}
	
	public static void creaMenuAbreResumen(final TableView<SprintVO> tableSprint, MenuItem item, ContextMenu menu){
		item = new MenuItem("Ver detalle Sprint");
		item.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	    		try {
	    			// Nueva Pantalla detalle de compra
	    			final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/resumenAgile.fxml");
	    			final FXMLLoader fxmlLoader = new FXMLLoader(clase);
	    			Parent root;
	    			root = (Parent)fxmlLoader.load();
	    			
	    			ResumenAgile controller = fxmlLoader.<ResumenAgile>getController();
					controller.setDataIni(tableSprint);

	    			final Scene scene = new Scene(root);
	    			Stage stageDetalle = new Stage();
	    			stageDetalle.setScene(scene);
	    			stageDetalle.setTitle("Detalle Sprint");					
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
	    });
	    menu.getItems().add(item);
	    tableSprint.setContextMenu(menu);
	}
	
	

}
