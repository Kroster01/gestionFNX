package pet.util;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pet.vo.SprintVO;

import com.sun.javafx.charts.Legend;

public class LineChartsRally {
	
    //Comparator for int, by Number
    static Comparator<? super SprintVO> comparadorAgile = new Comparator<SprintVO>() {
        public int compare(SprintVO o1, SprintVO o2) {
            return Integer.valueOf(o2.getNSprint()) - Integer.valueOf(o1.getNSprint());
        }
    };
    
	//
	public static void createLineChartCycle(Pane paneTendencias, TableView<SprintVO> tableSprint){
		
	    // Create empty series
	    ObservableList<SprintVO> peticiones = tableSprint.getItems();
	    FXCollections.sort(peticiones, comparadorAgile);
	    SprintVO selSprint = tableSprint.getSelectionModel().getSelectedItem();
		ObservableList<XYChart.Series> seriesList = FXCollections.observableArrayList();
	    ObservableList<XYChart.Data> cycles = FXCollections.observableArrayList();
	    int lastSprint = 0;
	    int cantSprint = 0;
	    double maxCycle = 0;
	    int cont = 0;
	    
	    
		for (SprintVO sprintVO : peticiones) {
			if(cont == 10){
				break;
			}
			if(selSprint.getCelula().equals(sprintVO.getCelula())){
				if(null != sprintVO.getCycleTime() && !sprintVO.getCycleTime().equals(Constantes.S_EMPTY) && !sprintVO.getCycleTime().equals("null")){
					if(maxCycle < Double.valueOf(sprintVO.getCycleTime())){
						maxCycle = Double.valueOf(sprintVO.getCycleTime());
					}
					XYChart.Data cycle = new XYChart.Data(Integer.valueOf(sprintVO.getNSprint()), Double.valueOf(sprintVO.getCycleTime()));
					if(lastSprint < Integer.valueOf(sprintVO.getNSprint())){
						lastSprint = Integer.valueOf(sprintVO.getNSprint());
					}

					cantSprint++;
					cycles.add(cycle);					
				}

			}
			cont++;
		}

		XYChart.Series seriesA = new XYChart.Series("Cycle Time", cycles);
	    seriesList.add(seriesA);
	    
	    // Create axes
	    Axis<Number> xAxis = new NumberAxis("Sprint", lastSprint - cantSprint, lastSprint + 1, 1);
		xAxis.setTickLabelFill(Color.WHITE);
//		xAxis.tickLabelFontProperty().set(Font.font(15));
	    Axis<Number> yAxis = new NumberAxis("Días", 0, maxCycle, 1);
		yAxis.setTickLabelFill(Color.WHITE);
	    
		LineChart velocidad = new LineChart(xAxis, yAxis, seriesList);
		velocidad.setLegendSide(Side.TOP);
		velocidad.lookup(".chart").setStyle("-fx-background-color: #043042;");
		velocidad.lookup(".chart-plot-background").setStyle("-fx-background-color: #043042;");
		velocidad.lookup(".chart-vertical-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		velocidad.lookup(".chart-horizontal-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		velocidad.lookup(".chart-title").setStyle("-fx-text-fill: white; -fx-font-size: 2em;");

		velocidad.setTitle("Cycle Time");
	    velocidad.setLayoutX(1117);
	    velocidad.setLayoutY(450);
	    velocidad.setPrefWidth(539);
	    velocidad.setPrefHeight(293);

	    paneTendencias.getChildren().add(velocidad);
	}
	
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
	    int maxTrp = 0;
	    int cont = 0;
	    
	    
		for (SprintVO sprintVO : peticiones) {
			if(cont == 10){
				break;
			}
			if(selSprint.getCelula().equals(sprintVO.getCelula())){
				if(maxTrp < Integer.valueOf(sprintVO.getNHUSprint())){
					maxTrp = Integer.valueOf(sprintVO.getNHUSprint());
				}
				XYChart.Data totalH = new XYChart.Data(Integer.valueOf(sprintVO.getNSprint()), Integer.valueOf(sprintVO.getNHUSprint()));
				XYChart.Data totalHCom = new XYChart.Data(Integer.valueOf(sprintVO.getNSprint()), Integer.valueOf(sprintVO.getNHUComplete()));
				if(lastSprint < Integer.valueOf(sprintVO.getNSprint())){
					lastSprint = Integer.valueOf(sprintVO.getNSprint());
				}

				cantSprint++;
				totHisto.add(totalH);
				totHistComp.add(totalHCom);
			}
			cont++;
		}

		XYChart.Series seriesA = new XYChart.Series("Total historias", totHisto);
	    seriesList.add(seriesA);

	    XYChart.Series seriesB = new XYChart.Series("Historias completadas", totHistComp);
	    seriesList.add(seriesB);
	    
	    // Create axes
	    Axis<Number> xAxis = new NumberAxis("Sprint", lastSprint - cantSprint, lastSprint, 1);
		xAxis.setTickLabelFill(Color.WHITE);
//		xAxis.tickLabelFontProperty().set(Font.font(15));
	    Axis<Number> yAxis = new NumberAxis("Cantidad historias", 0, maxTrp + 1, 1);
		yAxis.setTickLabelFill(Color.WHITE);
	    
		LineChart velocidad = new LineChart(xAxis, yAxis, seriesList);
		velocidad.setLegendSide(Side.TOP);
		velocidad.lookup(".chart").setStyle("-fx-background-color: #043042;");
		velocidad.lookup(".chart-plot-background").setStyle("-fx-background-color: #043042;");
		velocidad.lookup(".chart-vertical-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		velocidad.lookup(".chart-horizontal-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		velocidad.lookup(".chart-title").setStyle("-fx-text-fill: white; -fx-font-size: 2em;");
		
		velocidad.setTitle("Throughput");
	    velocidad.setLayoutX(565);
	    velocidad.setLayoutY(450);
	    velocidad.setPrefWidth(539);
	    velocidad.setPrefHeight(293);
	    
	    for(Node n : velocidad.getChildrenUnmodifiable()){
	    	   if(n instanceof Legend){
	    	      for(Legend.LegendItem legendItem : ((Legend)n).getItems()){
	    	    	  legendItem.getSymbol().setStyle("-fx-background-radius: 0");
	    	      }
	    	   }
	    	}
	    	    
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
	    int maxVel = 0;
	    int cont = 0;
	    
		for (SprintVO sprintVO : peticiones) {
			if(cont == 10){
				break;
			}
			if(selSprint.getCelula().equals(sprintVO.getCelula())){
				if(maxVel < CnvString.convertInt(sprintVO.getNPuntos())){
					maxVel = CnvString.convertInt(sprintVO.getNPuntos());
				}
				
				XYChart.Data totalH = new XYChart.Data(Integer.valueOf(sprintVO.getNSprint()), CnvString.convertInt(sprintVO.getNPuntos()));
				XYChart.Data totalHCom = new XYChart.Data(Integer.valueOf(sprintVO.getNSprint()), CnvString.convertInt(sprintVO.getNPuntosComplete()));
				if(lastSprint < Integer.valueOf(sprintVO.getNSprint())){
					lastSprint = Integer.valueOf(sprintVO.getNSprint());
				}
				cantSprint++;
				totHisto.add(totalH);
				totHistComp.add(totalHCom);
			}
			cont++;
		}

		XYChart.Series seriesA = new XYChart.Series("Total puntos", totHisto);
	    seriesList.add(seriesA);

	    XYChart.Series seriesB = new XYChart.Series("Puntos completados", totHistComp);
	    seriesList.add(seriesB);
	    
	    // Create axes
	    Axis<Number> xAxis = new NumberAxis("Sprint", lastSprint - cantSprint, lastSprint, 1);
		xAxis.setTickLabelFill(Color.WHITE);
//		xAxis.tickLabelFontProperty().set(Font.font(15));
	    Axis<Number> yAxis = new NumberAxis("Cantidad Historias", 0, maxVel + 5, 5);
		yAxis.setTickLabelFill(Color.WHITE);
	    
		LineChart velocidad = new LineChart(xAxis, yAxis, seriesList);
		velocidad.setLegendSide(Side.TOP);
		velocidad.lookup(".chart").setStyle("-fx-background-color: #043042;");
		velocidad.lookup(".chart-plot-background").setStyle("-fx-background-color: #043042;");
		velocidad.lookup(".chart-vertical-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		velocidad.lookup(".chart-horizontal-grid-lines").setStyle("-fx-stroke: #015683; -fx-stroke-width: 1;");
		velocidad.lookup(".chart-title").setStyle("-fx-text-fill: white; -fx-font-size: 2em;");
		velocidad.setTitle("Velocidad");
	    velocidad.setLayoutX(13);
	    velocidad.setLayoutY(450);
	    velocidad.setPrefWidth(539);
	    velocidad.setPrefHeight(293);
	    
	    for(Node n : velocidad.getChildrenUnmodifiable()){
	    	   if(n instanceof Legend){
	    	      for(Legend.LegendItem legendItem : ((Legend)n).getItems()){
	    	    	  legendItem.getSymbol().setStyle("-fx-background-radius: 0");
	    	      }
	    	   }
	    	}
	    
	    paneTendencias.getChildren().add(velocidad);
	}

}
