import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class WatchWhereYouStep {

	node[] nodes;
	int[] comp;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new WatchWhereYouStep().run();
	}

	public void run() throws NumberFormatException, IOException//bfs by shortest connection number
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		int E = 0;
		nodes = new node[N];
		comp = new int[N];
		for(int i =0 ;i<N;i++)
			nodes[i] = new node(i);
		for(int i = 0;i<N;i++)
		{
			StringTokenizer st = new StringTokenizer(file.readLine());
			for(int j = 0;j<N;j++)
			{
				if(st.nextToken().equals("1"))
				{
					E++;
					nodes[i].con.add(j);
					nodes[j].rev.add(i);
				}
			}
		}
		Stack<node> st = new Stack<node>();
		for(int i = 0;i<nodes.length;i++)
			if(!nodes[i].vis)
				dfs(st, i);
		int cc = 1;
		while(!st.isEmpty())
		{
			node n = st.pop();
			if(comp[n.id] == 0)
			{
				dfs2(n.id,cc++);
			}
		}
		int[] amt = new int[cc-1];
		for(int i = 0;i<nodes.length;i++)
		{
			amt[comp[i]-1]++;
		}
		int ans = 0;
		for(int i = 0;i<amt.length;i++)
		{
			for(int j = i+1;j<amt.length;j++)
			{
				ans += amt[i]*amt[j];
			}
			ans += amt[i] * (amt[i]-1);
		}
		System.out.println(ans - E);
	}

	public void dfs2(int v, int c)
	{
		if(comp[v]!=0)
			return;
		node n = nodes[v];
		comp[v] = c;
		n.vis2 = true;
		for(int x:n.rev)
			if(!nodes[x].vis2)
				dfs2(x, c);
	}

	public void dfs(Stack<node> st,int v)
	{
		node n = nodes[v];
		n.vis = true;
		for(int x:n.con)
			if(!nodes[x].vis)
				dfs(st, x);
		st.add(n);
	}

	private class node{

		ArrayList<Integer> con;
		ArrayList<Integer> rev;
		int id;
		boolean vis = false;
		boolean vis2 = false;

		public node(int id)
		{
			this.id = id;
			con = new ArrayList<Integer>();
			rev = new ArrayList<Integer>();
		}

		public String toString()
		{
			return id+1+"";
		}

	}

}
