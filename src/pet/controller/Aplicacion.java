package pet.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Aplicacion implements Initializable{

	 @FXML
	    private BorderPane contenedor;

	    @FXML
	    private Button entregadas;
	    
	    @FXML
	    private Button celulas;
	    
	    @FXML
	    private Button rally;

	    @FXML
	    private Button rutas;
	    
	    @FXML
	    private Button fenix;
	    
	    @FXML
	    private Button incurridos;

	    @FXML
	    private ScrollPane contentScroll;

	    @FXML
	    private Pane mainPane;
	    
	    @FXML
	    private Pane fondo;

	    @FXML
	    private Button principal;
	    
	    @FXML
	    private Button prevision;
	    
	    @FXML
	    void clickPrevision() {
	    	
	    	// Cambia el color del boton correspondiente
	    	entregadas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	principal.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	rutas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	fenix.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	celulas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	incurridos.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	prevision.setStyle("-fx-background-color: royalblue");
	    	rally.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	
    		final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/prevision.fxml");
    		final FXMLLoader fxmlLoader = new FXMLLoader(clase);
    		Parent root;
    		try {
    			root = (Parent)fxmlLoader.load();	
    			contenedor.getChildren().setAll(root);
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
	    }

	    @FXML
	    void clickRutas() {
	    	
	    	// Cambia el color del boton correspondiente
	    	rutas.setStyle("-fx-background-color: royalblue");
	    	principal.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	entregadas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	fenix.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	celulas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	incurridos.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	rally.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	prevision.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	
    		final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/rutas.fxml");
    		final FXMLLoader fxmlLoader = new FXMLLoader(clase);
    		Parent root;
    		try {
    			root = (Parent)fxmlLoader.load();	
    			contenedor.getChildren().setAll(root);
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
	    }
	    
	    @FXML
	    void clickCelulas() {
	    	
	    	// Cambia el color del boton correspondiente
	    	rutas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	principal.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	entregadas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	fenix.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	incurridos.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	celulas.setStyle("-fx-background-color: royalblue");
	    	rally.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	prevision.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	
    		final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/agile.fxml");
    		final FXMLLoader fxmlLoader = new FXMLLoader(clase);
    		Parent root;
    		try {
    			root = (Parent)fxmlLoader.load();	
    			contenedor.getChildren().setAll(root);
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
	    }
	    
	    @FXML
	    void clickIncurridos() {
	    	
	    	// Cambia el color del boton correspondiente
	    	rutas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	principal.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	entregadas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	fenix.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	incurridos.setStyle("-fx-background-color: royalblue");
	    	celulas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	rally.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	prevision.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	
    		final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/incurridos.fxml");
    		final FXMLLoader fxmlLoader = new FXMLLoader(clase);
    		Parent root;
    		try {
    			root = (Parent)fxmlLoader.load();	
    			contenedor.getChildren().setAll(root);
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
	    }

	    @FXML
	    void clickEjecucion() {
	    	
	    	// Cambia el color del boton correspondiente
	    	principal.setStyle("-fx-background-color: royalblue");
	    	entregadas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	rutas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	fenix.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	celulas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	incurridos.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	rally.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	prevision.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	
    		final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/ejecucion.fxml");
    		final FXMLLoader fxmlLoader = new FXMLLoader(clase);
    		Parent root;
    		try {
    			root = (Parent)fxmlLoader.load();	
    			contenedor.getChildren().setAll(root);
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
	    }
	    
	    @FXML
	    void clickFenix() {
	    	
	    	// Cambia el color del boton correspondiente
	    	principal.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	entregadas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	rutas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	celulas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	incurridos.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	fenix.setStyle("-fx-background-color: royalblue");
	    	rally.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	prevision.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	
    		final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/fenix.fxml");
    		final FXMLLoader fxmlLoader = new FXMLLoader(clase);
    		Parent root;
    		try {
    			root = (Parent)fxmlLoader.load();	
    			contenedor.getChildren().setAll(root);
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
	    }

	    @FXML
	    void clickEntregadas() {
	    	
	    	// Cambia el color del boton correspondiente
	    	entregadas.setStyle("-fx-background-color: royalblue");
	    	principal.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	rutas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	fenix.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	celulas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	incurridos.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	rally.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	prevision.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	
    		final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/entregadas.fxml");
    		final FXMLLoader fxmlLoader = new FXMLLoader(clase);
    		Parent root;
    		try {
    			root = (Parent)fxmlLoader.load();	
    			contenedor.getChildren().setAll(root);
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
	    }
	    
	    @FXML
	    void clickRally() {
	    	
	    	// Cambia el color del boton correspondiente
	    	entregadas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	principal.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	rutas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	fenix.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	celulas.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	incurridos.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	rally.setStyle("-fx-background-color: royalblue");
	    	prevision.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color");
	    	
    		final URL clase = this.getClass().getClassLoader().getResource("pet/controller/fxml/rally.fxml");
    		final FXMLLoader fxmlLoader = new FXMLLoader(clase);
    		Parent root;
    		try {
    			root = (Parent)fxmlLoader.load();	
    			contenedor.getChildren().setAll(root);
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
	    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		//fondo.setStyle("-fx-background-image: pet/images/fondo.jpg");
		
		BackgroundImage myBI= new BackgroundImage(new Image("pet/images/fondo.jpg",200,200,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		//then you set to your node
		fondo.setBackground(new Background(myBI));
		
		clickEjecucion();
		
	}

}
