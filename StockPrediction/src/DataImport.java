import Jama.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class DataImport
{
    public ArrayList<Double> readCSV(String fileName)
    {
        ArrayList<Double> price = new ArrayList<Double>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = null;
            double value=0;
            while ((line = reader.readLine()) != null) {
                //String item = line;
                String item [] = line.split(",");
            	value = Double.parseDouble(item[1]);
                price.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }
}