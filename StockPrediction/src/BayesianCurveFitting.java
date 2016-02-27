import java.util.ArrayList;

import Jama.Matrix;

public class BayesianCurveFitting {
	public int M=4;
    public int n;
    public int dayPredict;

    public ArrayList<Double> price;
    public double beta = 11.1;
    public double alpha = 0.005;
    
    public BayesianCurveFitting(ArrayList<Double> input){
    	price = input;
        dayPredict = price.size();
    }
    
    public Matrix Phi(int n)
    {
        double[][] temp = new double[M+1][1];
        for (int i=0;i<M+1;i++)
        {
            temp[i][0] = Math.pow(n, i);
        }

        return new Matrix(temp);
    } 
    
    public Matrix PhiT()
    {
        double [][] temp = new double[1][M+1];
        for(int i=0; i<=M;i++)
        {
            temp[0][i] = Math.pow(dayPredict, i);
        }
        
        return new Matrix(temp);
    }
    
    public Matrix getS_inv()
    {
    	/*generate sum of beta*phixn*phix */
        Matrix sum = new Matrix(M+1, 1);
        for(int i=1;i<=dayPredict-1;i++)
        {
            sum.plusEquals(Phi(i));
        }
        Matrix product = sum.times(PhiT());
        Matrix productBeta = product.timesEquals(beta);
        
        /*generate alpha*I*/
        double [][] alphaTemp = new double[M+1][M+1];
        for(int i=0;i<M+1;i++)
        {
            alphaTemp[i][i] = alpha;
        }
        Matrix alphai = new Matrix(alphaTemp);
        
        Matrix sInv = alphai.plus(productBeta);
        return sInv;
    }


    public Matrix getS(Matrix sInv){
        return sInv.inverse();
    }
    
    public double predict()
    {

        Matrix S = getS(getS_inv());
        Matrix tempSum = new Matrix(M+1,1);
        for(int i=1;i<=dayPredict-1;i++)
        {
        	Phi(i);
            double tn = price.get(i-1);
            tempSum.plusEquals(Phi(i).times(tn));
        }
        Matrix temp = PhiT().times(S).times(tempSum);
        double mX=beta* temp.get(0,0);
        return mX;
    }
}