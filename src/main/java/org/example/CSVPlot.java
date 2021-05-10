package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CSVPlot {

    private  XYChart.Series dataSeries2;


    public double[][] data;

    public ArrayList<Date> datax = new ArrayList<Date>();
    public ArrayList<Double> datay = new ArrayList<Double>();

    void loadFile() throws IOException, ParseException {

        String inputFileName = "C:\\Users\\m.ezzeddine\\Desktop\\fx\\ice_cream.csv";
        String outputNormFileName = "C:\\Users\\m.ezzeddine\\Desktop\\fx\\file2.csv";
        BufferedReader br = null;
        PrintWriter out = null;
        String line = "";
        String cvsSplitBy = ",";
        Date inputXPointValue;
        double targetXPointValue;
        int i = -1;
        int j=0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);



        try {
            Files.deleteIfExists(Paths.get(outputNormFileName));
            br = new BufferedReader(new FileReader(inputFileName));
            out = new
                    PrintWriter(new BufferedWriter(new FileWriter(outputNormFileName)));
            while ((line = br.readLine()) != null) {
                i++;
                if (i == 0) {
// Write the label line
                    out.println(line);
                }


            else
            {
                String[] workFields = line.split(cvsSplitBy);

                inputXPointValue = formatter.parse(workFields[0]);


                targetXPointValue = Double.parseDouble( workFields[1]);

                datax.add(inputXPointValue);
                datay.add(targetXPointValue);
                out.println(inputXPointValue);
                out.println(targetXPointValue);



            }

            } // End of WHILE
        } // End of TRY
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (IOException io)
        {// End of IF Else



        io.printStackTrace();
        System.exit(2);
    }
finally
    {
        if (br != null)
        {
            try
            {
                br.close();
                out.close();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
                System.exit(3);
            }
        }
    }
}


}

