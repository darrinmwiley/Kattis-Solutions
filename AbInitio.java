import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AbInitio {

	StringTokenizer st;
	BufferedReader file;

	public static void main(String[] args) throws Exception
	{
		new AbInitio().run();
	}

	int V;
	int E;
	int Q;
	int size;
	boolean[][] G, transpose, complement, complementTranspose;
	long MOD = 1000000007;
	long[] pow;
	PrintWriter pout;

	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);
		V = nextInt();
		E = nextInt();
		Q = nextInt();
		size = V;
		int S = V+Q;
		G = new boolean[S][S];//edge u->v
		transpose = new boolean[S][S];//edge v->u
		complement = new boolean[S][S];//no edge u->v
		complementTranspose = new boolean[S][S];//no edge v->u
		for(int i = 0;i<S;i++)
		{
			Arrays.fill(complement[i], true);
			Arrays.fill(complementTranspose[i], true);
			complement[i][i] = false;
			complementTranspose[i][i] = false;
		}
		pow = new long[4001];
		pow[0] = 1;
		for(int i = 1;i<pow.length;i++)
		{
			pow[i] = (pow[i-1]*7)%MOD;
		}
		for(int i = 0;i<E;i++)
		{
			int a = nextInt();
			int b = nextInt();
			setEdge(a,b,true);
		}
		for(int i = 0;i<Q;i++)
		{
			int q = nextInt();
			if(q == 1)
			{
				addVertex();
			}else if(q == 2)
			{
				int x = nextInt();
				int y = nextInt();
				setEdge(x,y,true);
			}else if(q == 3)
			{
				int xi = nextInt();
				//delete all con to xi
				//be sure here
				for(int j = 0;j<size;j++)
				{
					if(G[xi][j])
						setEdge(xi,j,false);
					if(G[j][xi])
						setEdge(j,xi,false);
				}
			}else if(q == 4)
			{
				int x = nextInt();
				int y = nextInt();
				setEdge(x,y,false);
			}else if(q == 5)
			{
				transpose();
			}else if(q == 6)
			{
				complement();
			}
		}

		pout.println(size);
		for(int i = 0;i<size;i++)
		{
			hash(i);
		}
		pout.flush();
	}

	public void print()
	{
		for(int i = 0;i<size;i++)
		{
			for(int j = 0;j<size;j++)
			{
				System.out.print(G[i][j]?1:0);
			}
			System.out.println();
		}
		System.out.println();
	}

	public void hash(int row)
	{
		int x = 0;
		long hash = 0;
		for(int i = 0;i<size;i++)
		{
			if(G[row][i])
			{
				hash += pow[x++]*i;
			}
		}
		pout.println(x+" "+hash%MOD);
	}

	public void addVertex()
	{
		for(int j = 0;j<size;j++)
		{
			if(G[size][j])
				setEdge(size,j,false);
			if(G[j][size])
				setEdge(j,size,false);
		}
		size++;
	}

	public void setEdge(int u, int v, boolean b)
	{
		//sketch?
		G[u][v] = b;
		transpose[v][u] = b;
		complement[u][v] = !b;
		complementTranspose[v][u] = !b;
	}

	public void transpose()
	{
		boolean[][] save = transpose;
		transpose = G;
		G = save;
		save = complementTranspose;
		complementTranspose = complement;
		complement = save;
	}

	public void complement()
	{
		boolean[][] save = complement;
		complement = G;
		G = save;
		save = transpose;
		transpose = complementTranspose;
		complementTranspose = save;
	}

	public void newst()
	{
		try {
			st = new StringTokenizer(file.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readLine() throws IOException
	{
		return file.readLine();
	}

	public String next()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return st.nextToken();
	}

	public int nextInt()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return Integer.parseInt(st.nextToken());
	}

	public long nextLong()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return Long.parseLong(st.nextToken());
	}

	public int[] readInts(int N)
	{
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
		{
			ints[i] = nextInt();
		}
		return ints;
	}

	public long[] readLongs(int N)
	{
		long[] ints = new long[N];
		for(int i = 0;i<N;i++)
		{
			ints[i] = nextLong();
		}
		return ints;
	}

}
