import java.math.BigInteger;
import java.util.Scanner;

public class PlayingSlots {
	
	public static void main(String[] args)
	{
		new PlayingSlots().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		double[] x = new double[N];
		double[] y = new double[N];
		double avex = 0;
		double avey = 0;
		for(int i = 0;i<N;i++)
		{
			x[i] = file.nextDouble();
			y[i] = file.nextDouble();
			avex += x[i];
			avey += y[i];
		}
		avex /= N;
		avey /= N;
		for(int i = 0;i<N;i++)
		{
			x[i] -=avex;
			y[i] -=avey;
		}
		double minDist = 200000;
		for(double i = 0;i<Math.PI*2;i+=.0001)
		{
			double[][] pts = new double[N][2];
			for(int j = 0;j<N;j++)
			{
				pts[j] = rotate(x[j],y[j],i);
			}
			double minx = 200000;
			double maxx = -200000;
			for(int j = 0;j<N;j++)
			{
				minx = Math.min(minx, pts[j][0]);
				maxx = Math.max(maxx, pts[j][0]);
			}
			minDist = Math.min(minDist, maxx - minx);
		}
		System.out.println(minDist);
	}
	
	//radians
	public double[] rotate(double x, double y, double theta)
	{
		double[][] mat = new double[][]{{Math.cos(theta),Math.sin(theta)},{-Math.sin(theta), Math.cos(theta)}};
		double[] mult = new double[]{x,y};
		double nx = x*Math.cos(theta) - y* Math.sin(theta);
		double ny = y*Math.cos(theta) + x* Math.sin(theta);
		return new double[]{nx,ny};
	}
}
