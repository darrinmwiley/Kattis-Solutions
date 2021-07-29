import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Treehouses {

	public static void main(String[] args)
	{
		new Treehouses().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int E = file.nextInt();
		int P = file.nextInt();
		double[] x = new double[N];
		double[] y = new double[N];
		for(int i = 0;i<N;i++)
		{
			x[i] = file.nextDouble();
			y[i] = file.nextDouble();
		}
		double[][] dist = new double[N][N];
		for(int i = 0;i<N;i++)
		{
			for(int j = 0;j<N;j++)
			{
				dist[i][j] = dist(x[i],y[i],x[j],y[j]);
			}
		}
		for(int i = 0;i<E;i++)
		{
			for(int j = 0;j<E;j++)
			{
				dist[i][j] = 0;
			}
		}
		for(int i = 0;i<P;i++)
		{
			int a = file.nextInt()-1;
			int b = file.nextInt()-1;
			dist[a][b] = dist[b][a] = 0;
		}
		PriorityQueue<edge> que = new PriorityQueue<edge>();
		for(int i = 0;i<N;i++)
			for(int j = i+1;j<N;j++)
					que.add(new edge(i,j,dist[i][j]));
		double cost = 0;
		int con = 0;
		UnionFind uf = new UnionFind(N);
		while(con != N-1)
		{
			edge e = que.poll();
			if(uf.find(e.a) != uf.find(e.b))
			{
				cost += e.d;
				uf.union(e.a, e.b);
				con++;
			}
		}
		System.out.println(cost);
	}

	public double dist(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}

	private class UnionFind{

		public int[] p;

		public UnionFind(int n)
		{
			p = new int[n];
			Arrays.fill(p,-1);
		}

		public int find(int x)
		{
			if(p[x] < 0)
				return x;
			return p[x] = find(p[x]);
		}

		public void union(int a, int b)
		{
			int pa = find(a);
			int pb = find(b);
			if(pa != pb)
			{
				p[pa] += p[pb];
				p[pb] = pa;
			}
		}
	}

	private class edge implements Comparable<edge>{

		int a,b;
		double d;

		public edge(int a, int b, double d)
		{
			this.a = a;
			this.b = b;
			this.d = d;
		}

		public int compareTo(edge e)
		{
			return Double.compare(d, e.d);
		}

	}

}
