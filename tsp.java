import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class tsp {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		double[] x = new double[zz];
		double[] y = new double[zz];
		for(int z = 0;z<zz;z++)
		{
			x[z] = file.nextDouble();
			y[z] = file.nextDouble();
		}
		int[] tour = new int[zz];
		boolean[] used = new boolean[zz];
		tour[0] = 0;
		used[0] = true;
		for(int i = 1;i<zz;i++)
		{
			int best = -1;
			for(int j = 0;j<zz;j++)
				if(!used[j]&&(best==-1||dist(x[tour[i-1]],y[tour[i-1]],x[j],y[j])<dist(x[tour[i-1]],y[tour[i-1]],x[best],y[best])))
					best = j;
			tour[i] = best;
			used[best] = true;
		}
		for(int i:tour)
			System.out.println(i);
	}
	
	public int dist(double x1, double y1, double x2, double y2)
	{
		return (int)Math.round(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)));
	}
	
	public static void main(String[] args)
	{
		new tsp().run();
	}
}

