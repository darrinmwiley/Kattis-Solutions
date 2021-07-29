import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RussianDolls {

	public static void main(String[] args)
	{
		new RussianDolls().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt()*2;
		while(N!=0)
		{
			doll[] dolls = new doll[N];
			for(int i = 0;i<N;i++)
			{
				dolls[i] = new doll(file.nextInt(),file.nextInt(),file.nextInt());
			}
			boolean[][] con = new boolean[N][N];
			for(int i = 0;i<con.length;i++)
			{
				for(int j = 0;j<con.length;j++)
				{
					if(i==j)
						continue;
					if(!dolls[i].fitsIn(dolls[j])&&!dolls[j].fitsIn(dolls[i]))
					{
						con[i][j] = con[j][i] = true;
					}
				}
			}
			boolean[] visited = new boolean[N];
			ArrayList<SCC> sccs = new ArrayList<SCC>();

			for(int i = 0;i<visited.length;i++)
			{
				if(visited[i])
					continue;
				else {
					ArrayList<Integer> list = new ArrayList<Integer>();
					dfs(i,visited,con,list);
					sccs.add(new SCC(list));
				}
			}
			for(SCC scc:sccs)
			{
				scc.calculateSets(con);
			}
			/*System.out.println(Arrays.toString(dolls));
			System.out.println(sccs.size());
			for(SCC scc:sccs)
			{
				System.out.println(scc);
			}*/
			int[] x = new int[sccs.size()];
			int[] y = new int[sccs.size()];
			for(int i = 0;i<sccs.size();i++)
			{
				x[i] = sccs.get(i).asize;
				y[i] = sccs.get(i).bsize;
			}
			boolean[][] dp = new boolean[sccs.size()+1][N/2+1];
			dp[0][0] = true;
			char[][] trace = new char[dp.length][dp[0].length];

			for(int i = 1;i<dp.length;i++)
			{
				for(int j = 0;j<dp[0].length;j++)
				{
					if(j>=x[i-1])
						if(dp[i-1][j-x[i-1]])
						{
							dp[i][j] = true;
							trace[i][j] = 'a';
						}
					if(j>=y[i-1])
						if(dp[i-1][j-y[i-1]])
						{
							dp[i][j] = true;
							trace[i][j] = 'b';
						}
				}
			}
			ArrayList<Integer> boris = traceBack(sccs,trace);
			ArrayList<Integer> natasha = new ArrayList<Integer>();
			for(int i = 0;i<N;i++)
				if(!boris.contains(i))
					natasha.add(i);
			//System.out.println(N+" "+boris.size()+" "+natasha.size());
			//System.out.println(boris);
			//System.out.println(natasha);

			doll[] b = new doll[N/2];
			doll[] n = new doll[N/2];

			for(int i = 0;i<N/2;i++)
			{
				b[i] = dolls[boris.get(i)];
				n[i] = dolls[natasha.get(i)];
			}
			Arrays.sort(b);
			Arrays.sort(n);
			for(doll d:b)
				System.out.println(d);
			System.out.println("-");
			for(doll d:n)
				System.out.println(d);
			System.out.println();
			N = file.nextInt()*2;
		}
	}

	public ArrayList<Integer> traceBack(ArrayList<SCC> list, char[][] trace)
	{
		ArrayList<Integer> ret = new ArrayList<>();
		int r = trace.length-1;
		int c = trace[0].length-1;
		while(r!=0)
		{
			if(trace[r][c]=='a')
			{
				ret.addAll(list.get(r-1).aset);
				c-=list.get(r-1).asize;
			}else if(trace[r][c]=='b'){
				ret.addAll(list.get(r-1).bset);
				c-=list.get(r-1).bsize;
			}
			r--;
		}
		return ret;
	}

	public void dfs(int current,boolean[] visited,boolean[][] con, ArrayList<Integer> scc)
	{
		if(visited[current])
			return;
		visited[current] = true;
		scc.add(current);
		for(int i = 0;i<con.length;i++)
		{
			if(con[current][i]&&!visited[i])
			{
				dfs(i,visited,con,scc);
			}
		}
	}

	private class SCC{

		ArrayList<Integer> nodes;
		ArrayList<Integer> aset;
		ArrayList<Integer> bset;
		public int asize;
		public int bsize;
		public SCC(ArrayList<Integer> list)
		{
			nodes = list;
			aset = new ArrayList<>();
			bset = new ArrayList<>();
		}

		public void calculateSets(boolean[][] con)
		{
			helper(nodes.get(0),true,new boolean[con.length],con);
			asize = aset.size();
			bsize = bset.size();
		}

		public void helper(int current,boolean flag, boolean[] used,boolean[][] con)
		{
			if(flag)
				aset.add(current);
			else
				bset.add(current);
			used[current] = true;
			for(int x:nodes)
				if(!used[x]&&con[current][x])
					helper(x,!flag,used,con);
		}

		public String toString()
		{
			return aset+" "+bset+" "+asize+" "+bsize;
		}

	}

	private class doll implements Comparable<doll>{

		int h,d,w;

		public doll(int h, int d, int w)
		{
			this.h = h;
			this.w = w;
			this.d = d;
		}

		public int hollowHeight()
		{
			return h-2*w;
		}

		public int hollowDiameter()
		{
			return d-2*w;
		}

		public boolean fitsIn(doll doll)
		{
			return d<=doll.hollowDiameter()&&h<=doll.hollowHeight();
		}

		@Override
		public int compareTo(doll arg0) {
			return Integer.compare(arg0.h,h);
		}

		public String toString()
		{
			return h+" "+d+" "+w;
		}
	}

}
