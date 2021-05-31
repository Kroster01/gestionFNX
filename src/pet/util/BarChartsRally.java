package pet.util;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pet.vo.ResumenByCells;

public class BarChartsRally {
	

    

	public static void createBarChartCycle(Pane paneTendencias, ObservableList<ResumenByCells> sprintsCel){
		
		CategoryAxis xAxis    = new CategoryAxis();
		xAxis.setLabel("Células");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Días");
		
		BarChart barChart = new BarChart(xAxis, yAxis);
		barChart.setLegendSide(Side.TOP);
		barChart.lookup(".chart").setStyle("-fx-background-color: #043042;");
		barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: #043042;");
		barChart.lookup(".chart-vertical-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		barChart.lookup(".chart-horizontal-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		barChart.lookup(".chart-title").setStyle("-fx-text-fill: white; -fx-font-size: 2em;");
		barChart.setTitle("Cycle Time");
		barChart.setLayoutX(1117);
		barChart.setLayoutY(50);
		barChart.setPrefWidth(539);
		barChart.setPrefHeight(293);
		
		XYChart.Series dataSeries1 = new XYChart.Series();
		//	        dataSeries1.setName("Células");
		
		for (ResumenByCells resumenByCells : sprintsCel) {
			dataSeries1.getData().add(new XYChart.Data(resumenByCells.getCelula(), Integer.valueOf(resumenByCells.getCycleTime())));
		}
		
		barChart.getData().add(dataSeries1);
		VBox vbox = new VBox(barChart);
		
		paneTendencias.getChildren().add(barChart);
		
	}
	

	public static void createBarChartTrh(Pane paneTendencias, ObservableList<ResumenByCells> sprintsCel){
	
		CategoryAxis xAxis    = new CategoryAxis();
		xAxis.setTickLabelFill(Color.WHITE);
		xAxis.tickLabelFontProperty().set(Font.font(15));
		xAxis.setTickLabelRotation(20);
//		xAxis.setLabel("Devices");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setTickLabelFill(Color.WHITE);
//		yAxis.setLabel("Visits");
		
		BarChart barChart = new BarChart(xAxis, yAxis);
		barChart.setLegendSide(Side.TOP);
		barChart.lookup(".chart").setStyle("-fx-background-color: #043042;");
		barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: #043042;");
		barChart.lookup(".chart-vertical-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		barChart.lookup(".chart-horizontal-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		barChart.lookup(".chart-title").setStyle("-fx-text-fill: white; -fx-font-size: 2em;");
		barChart.setTitle("Throughput");
		barChart.setLayoutX(841);
		barChart.setLayoutY(50);
		barChart.setPrefWidth(815);
		barChart.setPrefHeight(393);
		
		XYChart.Series dataSeries1 = new XYChart.Series();
		dataSeries1.setName("Historias planificadas");
		XYChart.Series dataSeries2 = new XYChart.Series();
		dataSeries2.setName("Historias completas");
		XYChart.Series dataSeries3 = new XYChart.Series();
		dataSeries3.setName("CycleTime");
		
		for (ResumenByCells resumenByCells : sprintsCel) {
			dataSeries1.getData().add(new XYChart.Data(resumenByCells.getCelula(), Integer.valueOf(resumenByCells.getnHUSprint())));
			dataSeries2.getData().add(new XYChart.Data(resumenByCells.getCelula(), Integer.valueOf(resumenByCells.getnHUComplete())));
			dataSeries3.getData().add(new XYChart.Data(resumenByCells.getCelula(), Double.valueOf(resumenByCells.getCycleTime())));
		}
		
		barChart.getData().add(dataSeries1);
		barChart.getData().add(dataSeries2);
		barChart.getData().add(dataSeries3);
		
		
		
		VBox vbox = new VBox(barChart);
		
		paneTendencias.getChildren().add(barChart);
		
	
	}
	
	public static void createBarChartVel(Pane paneTendencias, ObservableList<ResumenByCells> sprintsCel){

		
		CategoryAxis xAxis    = new CategoryAxis();
		xAxis.setTickLabelFill(Color.WHITE);
		xAxis.tickLabelFontProperty().set(Font.font(15));
		xAxis.setTickLabelRotation(120);
//		xAxis.setLabel("Devices");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setTickLabelFill(Color.WHITE);
//		yAxis.setLabel("Visits");
		
		final BarChart barChart = new BarChart(xAxis, yAxis);
		barChart.setLegendSide(Side.TOP);
		barChart.lookup(".chart").setStyle("-fx-background-color: #043042;");
		barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: #043042;");
		barChart.lookup(".chart-vertical-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		barChart.lookup(".chart-horizontal-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		barChart.lookup(".chart-title").setStyle("-fx-text-fill: white; -fx-font-size: 2em;");
				
		barChart.setTitle("Velocidad");
		barChart.setLayoutX(13);
		barChart.setLayoutY(50);
		barChart.setPrefWidth(815);
		barChart.setPrefHeight(393);
		
		XYChart.Series dataSeries1 = new XYChart.Series();
		dataSeries1.setName("Puntos planif");
		XYChart.Series dataSeries2 = new XYChart.Series();
		dataSeries2.setName("Puntos complete");
		XYChart.Series dataSeries3 = new XYChart.Series();
		dataSeries3.setName("CycleTime");
		
		
		
		for (ResumenByCells resumenByCells : sprintsCel) {
			dataSeries1.getData().add(new XYChart.Data(resumenByCells.getCelula(), Integer.valueOf(resumenByCells.getnPuntos())));
			dataSeries2.getData().add(new XYChart.Data(resumenByCells.getCelula(), Integer.valueOf(resumenByCells.getnPuntosComplete())));
			dataSeries3.getData().add(new XYChart.Data(resumenByCells.getCelula(), Double.valueOf(resumenByCells.getCycleTime())));
		}
		
		
		barChart.getData().add(dataSeries1);
		barChart.getData().add(dataSeries2);
		barChart.getData().add(dataSeries3);

		VBox vbox = new VBox(barChart);
		
		paneTendencias.getChildren().add(barChart);
		
	
	}

}
