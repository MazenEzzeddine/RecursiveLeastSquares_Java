package org.example;

import com.opencsv.exceptions.CsvException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.ParseException;



/**
 * Hello world!
 *
 */
public class App extends Application



{
    static CSVPlot2 csvp  = null;
    static  double[] pred = null;

    public static void main( String[] args ) throws IOException, CsvException, URISyntaxException, ParseException {
        System.out.println( "Hello World!" );

        int num_vars;
        num_vars =5;

        RLS rls = new RLS(5, 0.95);





        csvp = new CSVPlot2();
        csvp.loadFile();
        double[][] Xx = new double[1][5];
        pred = new double[csvp.datax.size() - num_vars];

        RealMatrix X;

        for (int i = 0; i< csvp.datax.size() - num_vars; i++){
            for(int j=0; j<5; j++) {
                Xx[0][j] = csvp.datay.get(i + j);
            }
            X = new Array2DRowRealMatrix(Xx);
            //rls.get_error();
            pred[i] = (rls.w.transpose().multiply(X.transpose())).getEntry(0,0);

            double y1 = csvp.datay.get(i+num_vars);
            rls.add_obs(X.transpose(), y1);
            System.out.println("index, pred, actual " +  (i+num_vars + 1) +  "," + pred[i]  +  "," + y1);

        }

        URL resource = App.class.getClassLoader().getResource("file.csv");
        File file = Paths.get(resource.toURI()).toFile();

       /* try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
        }*/

        Application.launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("LineChart Experiments");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("X");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        lineChart.setStyle("-fx-font-size: " + 5 + "px;");
        lineChart.setStyle("font-weight: " + 5 + "px;");




        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("actual data");
        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("predicted data");

        Double dy;

        for (int i= 0; i < csvp.datax.size() - 5; i++) {

            dy = csvp.datay.get(i);
           // System.out.println(d + " "+ dy);
            dataSeries1.getData().add(new XYChart.Data(i, dy));

        }

        for(int i =0; i< csvp.datax.size() -5; i++ )
            dataSeries2.getData().add(new XYChart.Data(i, pred[i]));

        lineChart.getData().add(dataSeries2);
        lineChart.getData().add(dataSeries1);


        VBox vbox = new VBox(lineChart);

        Scene scene = new Scene(vbox, 2000, 2000);

        primaryStage.setScene(scene);
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1500);

        primaryStage.show();
    }
}
