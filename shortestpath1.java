import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class shortestpath1 {

	public static void main(String[] args) throws IOException
	{
		new shortestpath1().run();
	}

	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		while(N!=0)
		{
			node[] nodes = new node[N];
			for(int i = 0;i<nodes.length;i++)
				nodes[i] = new node(i);
			for(int i = 0;i<M;i++)
			{
				st = new StringTokenizer(file.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				nodes[a].con(b,c);
				//nodes[b].con(a,c);
			}
			PriorityQueue<node> que = new PriorityQueue<node>();
			boolean[] vis = new boolean[nodes.length];
			nodes[S].fp = 0;
			que.add(nodes[S]);
			while(!que.isEmpty())
			{
				node n = que.poll();
				if(vis[n.id])
					continue;
				for(int i = 0;i<n.con.size();i++)
				{
					node next = nodes[n.con.get(i)];
					if(!vis[next.id]&&next.fp>n.fp+n.cost.get(i))
					{
						next.fp = n.fp+n.cost.get(i);
						que.add(next);
					}
				}
			}
			for(int i = 0;i<Q;i++)
			{
				int x = Integer.parseInt(file.readLine());
				int ans = nodes[x].fp;
				if(ans==Integer.MAX_VALUE)
					System.out.println("Impossible");
				else
					System.out.println(ans);
			}
			st = new StringTokenizer(file.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			if(N!=0)
				System.out.println();
		}
	}

	private class node implements Comparable<node>{

		int fp = Integer.MAX_VALUE;
		ArrayList<Integer> con;
		ArrayList<Integer> cost;
		int id;
		public node(int d)
		{
			id = d;
			con = new ArrayList<Integer>();
			cost = new ArrayList<Integer>();
		}

		public int compareTo(node n)
		{
			return Integer.compare(fp,n.fp);
		}

		public void con(int N, int C)
		{
			con.add(N);
			cost.add(C);
		}
	}
}
