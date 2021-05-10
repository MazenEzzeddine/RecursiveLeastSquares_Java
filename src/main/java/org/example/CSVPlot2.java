package org.example;



import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CSVPlot2  {


    // public ArrayList<Double,Double>  data


    public ArrayList<Double> datay = new ArrayList<Double>();
    public ArrayList<Double> datax = new ArrayList<Double>();





    void loadFile() throws IOException, ParseException {

        String inputFileName = "C:\\Users\\m.ezzeddine\\Desktop\\fx\\stock.csv";




        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        String fullLine;
        Date inputXPointValue;
        double targetXPointValue;

        int i = -1;
        int j=0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);



        try {
            br = new BufferedReader(new FileReader(inputFileName));

            while ((line = br.readLine()) != null) {
                i++;


                    String[] workFields = line.split(cvsSplitBy);

                    for(int k =0; j<workFields.length; j++) {


                        targetXPointValue = Double.parseDouble(workFields[j]);

                        datax.add((double)k);
                        datay.add(targetXPointValue);
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

