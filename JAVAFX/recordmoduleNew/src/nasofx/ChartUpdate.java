/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class ChartUpdate extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        NumberAxis xAxis = new NumberAxis(0, 50_000, 5000);
        xAxis.setAutoRanging(false);
        NumberAxis yAxis = new NumberAxis(-1, 1, 25);
        yAxis.setAutoRanging(false);
        LineChart<Number, Number> graph = new LineChart<>(xAxis, yAxis);
        graph.setAnimated(false);
        graph.setCreateSymbols(false);
        graph.setLegendVisible(false);
        Series<Number, Number> series = new Series<>();
        stage.setScene(new Scene(graph));

    //   GeometryFactory gf = new GeometryFactory();

        long t0 = System.nanoTime();
    //    Coordinate[] coordinates = new Coordinate[100_000];
   //     for (int i = 0; i < coordinates.length; i++) {
  //          coordinates[i] = new Coordinate(i, Math.sin(Math.toRadians(i / 100)));
  //      }
   //     Geometry geom = new LineString(new CoordinateArraySequence(coordinates), gf);
      //  Geometry simplified = Douglas_Peucker_Algorithm.simplify(geom, 0.00001);
        List<Data<Number, Number>> update = new ArrayList<Data<Number, Number>>();
       
     //   for (Coordinate each : simplified.getCoordinates()) {
       //     update.add(new Data<>(each.x, each.y));
      //  }
        long t1 = System.nanoTime();

      //  System.out.println(String.format("Reduces points from %d to %d in %.1f ms", coordinates.length, update.size(),
    //            (t1 - t0) / 1e6));
        ObservableList<Data<Number, Number>> list = FXCollections.observableArrayList(update);
        series.setData(list);
        graph.getData().add(series);

        stage.show();

    }

    
}