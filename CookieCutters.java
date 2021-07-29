import java.math.BigInteger;
import java.util.Scanner;

public class CookieCutters {

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		double[] x = new double[N];
		double[] y = new double[N];
		for(int i = 0;i<N;i++)
		{
			x[i] = file.nextDouble();
			y[i] = file.nextDouble();
		}
		int A = file.nextInt();
		double currentArea = area(x,y);
		double scale = 1.0/Math.sqrt(currentArea/A);
		for(int i = 0;i<N;i++)
		{
			x[i]*=scale;
			y[i]*=scale;
		}
		double minx = x[0];
		double miny = y[0];
		for(int i = 0;i<N;i++)
		{
			minx = Math.min(x[i],minx);
			miny = Math.min(y[i],miny);
		}
		for(int i = 0;i<N;i++)
		{
			System.out.println(x[i]-minx+" "+(y[i]-miny));
		}
	}

	public double area(double[] x, double[] y)
	{
		double sum = 0;
		for(int i = 0;i<x.length;i++)
		{
			sum+=x[i]*y[(i+1)%y.length];
			sum-=x[(i+1)%x.length]*y[i];
		}
		return Math.abs(sum/2);
	}

	public static void main(String[] args)
	{
		new CookieCutters().run();
	}

}
