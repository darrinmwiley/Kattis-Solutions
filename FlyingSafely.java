import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FlyingSafely {

	public static void main(String[] args) throws IOException
	{
		new FlyingSafely().run();
	}

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int zz = Integer.parseInt(file.readLine());
		for(int z = 0;z<zz;z++)
		{
			StringTokenizer st = new StringTokenizer(file.readLine());
			int N = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			edge[] edges = new edge[P];
			for(int i = 0;i<P;i++)
			{
				st = new StringTokenizer(file.readLine());
				edges[i] = new edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			System.out.println(N-1);
		}
	}

	class edge{

		int a;
		int b;

		public edge(int a, int b)
		{
			this.a = a;
			this.b = b;
		}

	}

	class UnionFind{

		int[] ints;

		public UnionFind(int N)
		{
			ints = new int[N];
			Arrays.fill(ints, -1);
		}

		public int find(int a)
		{
			if(ints[a]<0)
				return a;
			return ints[a] = find(ints[a]);
		}

		public void union(int a, int b)
		{
			int aa = find(a);
			int bb = find(b);
			if(aa==bb)
				return;
			ints[aa]+=ints[bb];
			ints[bb] = aa;
		}

	}

}
