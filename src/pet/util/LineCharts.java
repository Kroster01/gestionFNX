package pet.util;

import java.util.Comparator;

import pet.vo.SprintVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class LineCharts {
	
    //Comparator for int, by Number
    static Comparator<? super SprintVO> comparadorAgile = new Comparator<SprintVO>() {
        public int compare(SprintVO o1, SprintVO o2) {
            return Integer.valueOf(o2.getNSprint()) - Integer.valueOf(o1.getNSprint());
        }
    };
	
	//
	public static void createLineChartTrh(Pane paneTendencias, TableView<SprintVO> tableSprint){
		
	    // Create empty series
	    ObservableList<SprintVO> peticiones = tableSprint.getItems();
	    FXCollections.sort(peticiones, comparadorAgile);
	    SprintVO selSprint = tableSprint.getSelectionModel().getSelectedItem();
		ObservableList<XYChart.Series> seriesList = FXCollections.observableArrayList();
	    ObservableList<XYChart.Data> totHisto = FXCollections.observableArrayList();
	    ObservableList<XYChart.Data> totHistComp = FXCollections.observableArrayList();
	    int lastSprint = 0;
	    int cantSprint = 0;
	    
	    
		for (SprintVO sprintVO : peticiones) {	
			if(selSprint.getCelula().equals(sprintVO.getCelula())){
				XYChart.Data totalH = new XYChart.Data(Integer.valueOf(sprintVO.getNSprint()), Integer.valueOf(sprintVO.getNHUSprint()));
				XYChart.Data totalHCom = new XYChart.Data(Integer.valueOf(sprintVO.getNSprint()), Integer.valueOf(sprintVO.getNHUComplete()));
				if(lastSprint < Integer.valueOf(sprintVO.getNSprint())){
					lastSprint = Integer.valueOf(sprintVO.getNSprint());
				}

				cantSprint++;
				totHisto.add(totalH);
				totHistComp.add(totalHCom);
			}
		}

		XYChart.Series seriesA = new XYChart.Series("Total historias", totHisto);
	    seriesList.add(seriesA);

	    XYChart.Series seriesB = new XYChart.Series("Historias completadas", totHistComp);
	    seriesList.add(seriesB);
	    
	    // Create axes
	    Axis<Number> xAxis = new NumberAxis("Sprint", lastSprint - cantSprint, lastSprint + 1, 1);
	    Axis<Number> yAxis = new NumberAxis("Cantidad historias", 0, 15, 1);
	    
		LineChart velocidad = new LineChart(xAxis, yAxis, seriesList);
		velocidad.setTitle("Throughput");
	    velocidad.setStyle("-fx-background-color:  F5EEEC;");
	    velocidad.setLayoutX(877);
	    velocidad.setLayoutY(75);
	    velocidad.setPrefWidth(770);
	    velocidad.setPrefHeight(419);

	    Node lineA = seriesA.getNode().lookup(".chart-series-line");
	    lineA.setStyle("-fx-stroke: #1324EC;");
	    Node lineB = seriesB.getNode().lookup(".chart-series-line");
	    lineB.setStyle("-fx-stroke: #13EC13;");
	    
	    paneTendencias.getChildren().add(velocidad);
	}
	
	public static void createLineChartVel(Pane paneTendencias, TableView<SprintVO> tableSprint){
		
	    // Create empty series
	    ObservableList<SprintVO> peticiones = tableSprint.getItems();
	    FXCollections.sort(peticiones, comparadorAgile);
	    SprintVO selSprint = tableSprint.getSelectionModel().getSelectedItem();
		ObservableList<XYChart.Series> seriesList = FXCollections.observableArrayList();
	    ObservableList<XYChart.Data> totHisto = FXCollections.observableArrayList();
	    ObservableList<XYChart.Data> totHistComp = FXCollections.observableArrayList();
	    int lastSprint = 0;
	    int cantSprint = 0;
	    
	    
		for (SprintVO sprintVO : peticiones) {	
			if(selSprint.getCelula().equals(sprintVO.getCelula())){
				XYChart.Data totalH = new XYChart.Data(Integer.valueOf(sprintVO.getNSprint()), Integer.valueOf(sprintVO.getNPuntos()));
				XYChart.Data totalHCom = new XYChart.Data(Integer.valueOf(sprintVO.getNSprint()), Integer.valueOf(sprintVO.getNPuntosComplete()));
				if(lastSprint < Integer.valueOf(sprintVO.getNSprint())){
					lastSprint = Integer.valueOf(sprintVO.getNSprint());
				}
				cantSprint++;
				totHisto.add(totalH);
				totHistComp.add(totalHCom);
			}
		}

		XYChart.Series seriesA = new XYChart.Series("Total puntos", totHisto);
	    seriesList.add(seriesA);

	    XYChart.Series seriesB = new XYChart.Series("Puntos completados", totHistComp);
	    seriesList.add(seriesB);
	    
	    // Create axes
	    Axis<Number> xAxis = new NumberAxis("Sprint", lastSprint - cantSprint, lastSprint + 1, 1);
	    Axis<Number> yAxis = new NumberAxis("Cantidad Historias", 0, 50, 5);
	    
		LineChart velocidad = new LineChart(xAxis, yAxis, seriesList);
		velocidad.setTitle("Velocidad");
	    velocidad.setStyle("-fx-background-color:  F5EEEC;");
	    velocidad.setLayoutX(21);
	    velocidad.setLayoutY(75);
	    velocidad.setPrefWidth(770);
	    velocidad.setPrefHeight(419);

	    Node lineA = seriesA.getNode().lookup(".chart-series-line");
	    lineA.setStyle("-fx-stroke: #1324EC;");
	    Node lineB = seriesB.getNode().lookup(".chart-series-line");
	    lineB.setStyle("-fx-stroke: #13EC13;");
	    
	    paneTendencias.getChildren().add(velocidad);
	}

}
