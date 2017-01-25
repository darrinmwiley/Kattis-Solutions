import java.io.IOException;
import java.util.Arrays;
import java.util.*;
import java.util.Scanner;

public class LetsMeet {
	
	int S;
	int L;
	int S1;
	int S2;
	boolean[][] adj;
	
	public void go() throws IOException {
		Scanner file = new Scanner(System.in);
		S = file.nextInt();
		L = file.nextInt();
		int[] deg = new int[S];
		adj = new boolean[S][S];
		for(int i = 0;i<L;i++)
		{
			int a = file.nextInt();
			int b = file.nextInt();
			deg[a]++;
			deg[b]++;
			adj[a][b] = adj[b][a] = true;
		}
		S1 = file.nextInt();
		S2 = file.nextInt();
		int index = 0;
		boolean[][] use = pre();
		if(S1==S2)
		{
			System.out.println(0);
			return;
		}
		if(!use[S1][S2]){
			System.out.println("never meet");
			return;
		}	
		TreeMap<Integer,int[]> map = new TreeMap<>();
		TreeMap<Integer,Integer>map2 = new TreeMap<>();
		for(int i = 0;i<use.length;i++)
			for(int j = 0;j<use.length;j++)
			{
				if(use[i][j]&&i==j)
					map2.put(map2.size(),i);
				if(use[i][j]&&i!=j)
					map.put(map.size(),new int[]{i,j});
				if(i==S1&&j==S2)
					index = map.size()-1;
			}
		
		double[][] trans = new double[map.size()][map.size()*2];
		double[][] abs = new double[S*(S-1)][S];
		for(int i = 0;i<trans.length;i++)
		{
			trans[i][trans.length+i] = trans[i][i] = 1;
			trans[i][i] = 1;
			for(int j = 0;j<trans.length;j++)
			{
				int[] locA = map.get(i);
				int[] locB = map.get(j);
				if(adj[locA[0]][locB[0]]&&adj[locA[1]][locB[1]])
					trans[i][j] = -1.0/(deg[locA[0]]*deg[locA[1]]);
			}
		}
		for(int i = 0;i<map.size();i++)
		{
			for(int j = 0;j<S;j++)
			{
				int[] loc = map.get(i);
				if(adj[loc[0]][j]&&adj[loc[1]][j])
					abs[i][j] = 1.0/(deg[loc[0]]*deg[loc[1]]);
			}
		}
		//print(trans);
		//System.out.println();
		//print(abs);
		for(int i = 0;i<trans.length;i++)
			for(int j = i+1;j<trans.length;j++)
			{
				double fact = -trans[j][i]/trans[i][i];
				for(int k = i;k<trans[i].length;k++)
					trans[j][k]+=trans[i][k]*fact;
			}
		for(int i = trans.length-1;i>=0;i--)
			for(int j = i-1;j>=0;j--)
			{
				double fact = -trans[j][i]/trans[i][i];
				for(int k = i;k<trans[j].length;k++)
					trans[j][k]+=trans[i][k]*fact;
			}
		double ans = 0.0;
		int row = index;
		for(int i = 0;i<trans.length;i++)
			ans+=trans[row][trans.length+i]/trans[row][row];
		System.out.println(ans);
		//print(trans);
	}
	
	public boolean[][] pre()
	{
		boolean[] even = new boolean[adj.length];
		boolean[] odd = new boolean[adj.length];
		boolean[] vis = new boolean[adj.length];
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(S1);
		que.add(0);
		even[S1] = vis[S1] = true;
		while(!que.isEmpty())
		{
			int spot = que.poll();
			int time = que.poll();
			for(int x = 0;x<adj[spot].length;x++)
			{
				if(adj[spot][x])
				{
					if(time%2!=0)
					{
						if(!even[x])
						{
							even[x] = true;
							vis[x] = true;
							que.add(x);
							que.add(time+1);
						}
					}else if(!odd[x]){
						odd[x] = true;
						vis[x] = true;
						que.add(x);
						que.add(time+1);
					}
				}
					
			}	
		}
		boolean[][] ret = new boolean[odd.length][even.length];
		for(int i = 0;i<ret.length;i++)
			for(int j = i;j<ret.length;j++)
			{
				if(vis[i]&&vis[j]&&even[i]==even[j])
				{
					ret[i][j] = ret[j][i] = true;
				}
			}
		return ret;
	}
	
	public void print(boolean[][] mat)
	{
		for(boolean[] x:mat){
			System.out.println(Arrays.toString(x));
		}
			
	}
	
	public void print(double[][] mat)
	{
		for(double[] x:mat){
			for(double d:x)
				System.out.printf("%.3f ",d);
			System.out.println();
		}
			
	}
	
	public static void main(String[] args) {
		try {
			new LetsMeet().go();
		} catch (IOException e) {
			
		}
	}
	
}
