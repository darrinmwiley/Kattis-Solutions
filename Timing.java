import java.util.*;
import java.io.*;

public class Timing {

	double[][] IDENTITY;

	public void run()
	{
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int zzz = in.nextInt();
		for (int zz = 0; zz < zzz; zz++)
		{
			int numForts = in.nextInt();
			int numLinks = in.nextInt();
			int time = in.nextInt();

			IDENTITY = new double[numForts][numForts];
			for (int i = 0; i < numForts; i++)
			{
				IDENTITY[i][i] = 1;
			}

			double[][] stats = new double[1][numForts];
			for (int i = 0; i < numForts; i++)
			{
				stats[0][i] = in.nextDouble();
			}

			boolean[][] edges = new boolean[numForts][numForts];
			double[][] forts = new double[numForts][numForts];
			double[] sums = new double[numForts];
			for (int i = 0; i < numLinks; i++)
			{
				int u = in.nextInt();
				int v = in.nextInt();
				double cost = in.nextDouble();
				sums[u] += cost;
				edges[u][v] = true;
				edges[v][u] = true;
				forts[u][v] = cost;
			}

			for (int i = 0; i < numForts; i++)
			{
				forts[i][i] = 1 - sums[i];
				edges[i][i] = true;
			}

			stats = multiply(stats, exp(forts, time));

//			System.out.println(Arrays.toString(stats[0]));

			double min = Double.POSITIVE_INFINITY;
			for (int i = 0; i < numForts; i++)
			{
				double strength = 0;
				for (int e = 0; e < numForts; e++)
				{
					if (edges[i][e])
					{
						strength += stats[0][e];
					}
				}
				min = Math.min(strength, min);
			}

			System.out.println(min);
		}
		in.close();
	}

	public double[][] exp(double[][] mat, int n)
	{
		if (n == 0)
		{
			return IDENTITY;
		}
		double[][] squared = multiply(mat, mat);
		if (n % 2 == 0)
		{
			return exp(squared, n/2);
		}
		return multiply(exp(squared, n/2), mat);
	}

	public double[][] multiply(double[][] a, double[][] b)
	{
		double[][] c = new double[a.length][b[0].length];
		for (int i = 0; i < c.length; i++)
		{
			for (int j = 0; j < c[0].length; j++)
			{
				for (int k = 0; k < b.length; k++)
				{
					c[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return c;
	}

	public static void main(String[] args)
	{
		new Timing().run();
	}

}
