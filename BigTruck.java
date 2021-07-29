import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BigTruck {

	public static void main(String[] args) throws IOException
	{
		new BigTruck().run();
	}

	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] items = new int[N];
		st = new StringTokenizer(file.readLine());
		for(int i = 0;i<N;i++)
			items[i] = Integer.parseInt(st.nextToken());
		node[] nodes = new node[N];
		for(int i = 0;i<nodes.length;i++)
			nodes[i] = new node(i,items[i]);
		int M = Integer.parseInt(file.readLine());
		for(int i = 0;i<M;i++)
		{
			st = new StringTokenizer(file.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			nodes[a].con(b,c);
			nodes[b].con(a,c);
		}
		PriorityQueue<node> que = new PriorityQueue<node>();
		boolean[] vis = new boolean[nodes.length];
		nodes[0].fp = 0;
		que.add(nodes[0]);
		while(!que.isEmpty())
		{
			node n = que.poll();
			if(vis[n.id])
				continue;
			for(int i = 0;i<n.con.size();i++)
			{
				node next = nodes[n.con.get(i)];
				if(!vis[next.id]&&(next.fp>n.fp+n.cost.get(i)||next.fp==n.fp+n.cost.get(i)&&next.items+n.mi>next.mi))
				{
					next.fp = n.fp+n.cost.get(i);
					next.mi = next.items+n.mi;
					que.add(next);
				}
			}
		}
		int fp = nodes[nodes.length-1].fp;
		if(fp==Integer.MAX_VALUE)
			System.out.println("impossible");
		else
			System.out.printf("%d %d%n",fp,nodes[nodes.length-1].mi);
	}

	private class node implements Comparable<node>{

		int fp = Integer.MAX_VALUE;
		int mi;
		ArrayList<Integer> con;
		ArrayList<Integer> cost;
		int id;
		int items;
		public node(int d, int t)
		{
			id = d;
			mi = items = t;
			con = new ArrayList<Integer>();
			cost = new ArrayList<Integer>();
		}

		public int compareTo(node n)
		{
			if(fp==n.fp)
				return Integer.compare(items,n.items);
			return Integer.compare(fp,n.fp);
		}

		public void con(int N, int C)
		{
			con.add(N);
			cost.add(C);
		}
	}
}
