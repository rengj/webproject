import java.util.ArrayList;

public class predict {
	public static void main(String[] argv)
    {
        String name = new String("Twitter.csv");
        ArrayList<Double> predictValue = new ArrayList<Double>();
        DataImport dataImport = new DataImport();

       for(int k=0;k<10;k++)
       {
           ArrayList<Double> price1 = new ArrayList<>();
           for (int i = 0+k; i <=dataImport.readCSV(name).size()-10+k; i++) {
               price1.add(dataImport.readCSV(name).get(i));
           }
           BayesianCurveFitting curveFitting1 = new BayesianCurveFitting(price1);
           predictValue.add(curveFitting1.predict());
       }
        for(int i=0;i<10;i++) {
            System.out.println(predictValue.get(i));
        }

    }
}
