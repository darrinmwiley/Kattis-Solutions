import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Freckles {
	public static final void main(final String[] arg)throws Exception {
		new Freckles().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		file.nextLine();
		for(int z = 0;z<zz;z++)
		{
			file.nextLine();
			int N = file.nextInt();
			double[] x = new double[N];
			double[] y = new double[N];
			for(int i = 0;i<N;i++)
			{
				x[i] = file.nextDouble();
				y[i] = file.nextDouble();
			}
			ArrayList<edge> list = new ArrayList<edge>();
			for(int i = 0;i<N;i++)
				for(int j = i+1;j<N;j++)
				{
					list.add(new edge(i,j,dist(x[i],y[i],x[j],y[j])));
				}
			Collections.sort(list);
			unionFind uf = new unionFind(N);
			int edges = 0;
			double cost = 0;
			for(int i = 0;i<list.size();i++)
			{
				edge e = list.get(i);
				if(uf.find(e.a)!=uf.find(e.b))
				{
					uf.union(e.a, e.b);
					cost+=e.cost;
					edges++;
				}
			}
			System.out.printf("%.2f%n",cost);
		}
	}
	
	public double dist(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
	
	private class edge implements Comparable<edge>{
		
		int a;
		int b;
		double cost;
		
		public edge(int a, int b, double c)
		{
			this.a = a;
			this.b = b;
			this.cost = c;
		}
		
		public int compareTo(edge e)
		{
			return Double.compare(cost,e.cost);
		}
	}
	
	private class unionFind{
		
		int[] ints;
		
		public unionFind(int N)
		{
			ints = new int[N];
			Arrays.fill(ints, -1);
		}
		
		public int find(int x)
		{
			if(ints[x]<0)
				return x;
			return ints[x] = find(ints[x]);
		}
		
		public void union(int a, int b)
		{
			int pa = find(a);
			int pb = find(b);
			if(pa==pb)
				return;
			ints[pa]+=ints[pb];
			ints[pb] = pa;
		}
	}
}
