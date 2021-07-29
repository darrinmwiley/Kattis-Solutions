import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Promotions
{
	
	node[] nodes;
	boolean[] vis;
	int A,B,E,P;
	
	public static void main (String[] args) throws java.lang.Exception
	{
		new Promotions().solve();
	}
	
	public void solve() throws Exception
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		nodes = new node[E];
		vis = new boolean[E];
		for(int i = 0;i<E;i++)
		{
			nodes[i] = new node(i);
		}
		for(int i = 0;i<P;i++)
		{
			st = new StringTokenizer(file.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			createEdge(a,b);
		}
		for(int i = 0;i<E;i++)
		{
			Arrays.fill(vis,  false);
			dfs(i,i);
		}
		int certainA = 0;
		int certainB = 0;
		int impossible = 0;
		for(int i = 0;i<nodes.length;i++)
		{
			node n = nodes[i];
			if(E - n.after <= A)
			{
				certainA++;
			}
			if(E - n.after <= B)
			{
				certainB++;
			}
			if(1 + n.before > B)
			{
				impossible++;
			}
		}
		System.out.println(certainA);
		System.out.println(certainB);
		System.out.println(impossible);
	}
	
	public void dfs(int s, int v)
	{
		vis[v] = true;
		for(int x: nodes[v].neighbors)
		{
			if(!vis[x])
			{
				nodes[x].before++;
				nodes[s].after++;
				dfs(s, x);
			}		
		}
	}
	
	public void createEdge(int a, int b)
	{
		nodes[a].neighbors.add(b);
	}
	
	private class node{
		
		ArrayList<Integer> neighbors;
		int id;
		int before = 0;
		int after = 0;
		
		public node(int id)
		{
			this.id = id;
			neighbors = new ArrayList<Integer>();
		}
		
	}
}