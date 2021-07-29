

import java.util.Arrays;
import java.util.Scanner;

public class TrackSmoothing {

	long[][] dp;
	boolean[][] spots;

	public static void main(String[] args) throws Exception
	{
		new TrackSmoothing().run();
	}

	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int R = file.nextInt();
			int N = file.nextInt();
			double[] x = new double[N];
			double[] y = new double[N];
			for(int i = 0;i<N;i++)
			{
				x[i] = file.nextDouble();
				y[i] = file.nextDouble();
			}
			double sum = 0;
			for(int i = 0;i<N;i++)
			{
				sum+=dist(x[i],y[i],x[(i+1)%N],y[(i+1)%N]);
			}

			double circ = 2*Math.PI*R;
			if(circ>sum)
				System.out.println("Not possible");
			else
				System.out.println((sum-circ)/sum);
		}
	}

	double dist(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
}
