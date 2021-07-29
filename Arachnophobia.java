import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Arachnophobia {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new Arachnophobia().run();		
	}

	node[] nodes;

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		nodes = new node[N+1];
		for(int i = 0;i<nodes.length;i++)
		{
			nodes[i] = new node(i);
		}
		for(int i = 0;i<M;i++)
		{
			st = new StringTokenizer(file.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			con(a,b,c);
		}
		st = new StringTokenizer(file.readLine());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(file.readLine());
		int K = Integer.parseInt(st.nextToken());
		for(int i = 0;i<K;i++)
			con(N, Integer.parseInt(st.nextToken()),0);
		Comparator<node> comp = new Comparator<node>() {
			public int compare(node a, node b)
			{
				return Long.compare(a.spiderDist, b.spiderDist);
			}
		};
		PriorityQueue<node> que = new PriorityQueue<node>(comp);
		que.add(nodes[N]);
		nodes[N].spiderDist = 0;
		while(!que.isEmpty())
		{
			node n = que.poll();
			for(int i = 0;i<n.con.size();i++)
			{
				int con = n.con.get(i);
				int cost = n.cost.get(i);
				if(nodes[con].spiderDist > n.spiderDist + cost)
				{
					nodes[con].spiderDist = n.spiderDist + cost;
					que.remove(nodes[con]);
					que.add(nodes[con]);
				}
			}
		}
		int R = 1000000001;
		int L = -1;
		M = (L+R)/2;
		int bestAns = 0;
		while(R-L > 1)
		{
			M = (L + R)/2;
			fp(S,M);
			long fastestPath = nodes[E].fastestPath;
			if(fastestPath <= T)
			{
				bestAns = Math.max(bestAns, M);
				L = M;
			}else {
				R = M;
			}
		}
		System.out.println(bestAns);
	}

	public void fp(int S, int D)
	{
		for(node x:nodes)
		{
			x.fastestPath = Integer.MAX_VALUE/4;
		}
		nodes[S].fastestPath = 0;
		Comparator<node> comp = new Comparator<node>() {
			public int compare(node a, node b)
			{
				return Long.compare(a.fastestPath, b.fastestPath);
			}
		};
		PriorityQueue<node> que = new PriorityQueue<node>(comp);
		que.add(nodes[S]);
		while(!que.isEmpty())
		{
			node n = que.poll();
			if(n.spiderDist < D)
				continue;
			for(int i = 0;i<n.con.size();i++)
			{
				int con = n.con.get(i);
				int cost = n.cost.get(i);
				if(nodes[con].fastestPath > n.fastestPath + cost && nodes[con].spiderDist >= D)
				{
					nodes[con].fastestPath = n.fastestPath + cost;
					que.remove(nodes[con]);
					que.add(nodes[con]);
				}
			}
		}
	}

	public void con(int a, int b, int c)
	{
		nodes[a].con.add(b);
		nodes[b].con.add(a);
		nodes[a].cost.add(c);
		nodes[b].cost.add(c);
	}

	private class node
	{
		ArrayList<Integer> con;
		ArrayList<Integer> cost;
		long spiderDist = Long.MAX_VALUE/4;
		long fastestPath = Long.MAX_VALUE/4;

		public node(int id)
		{
			con = new ArrayList<Integer>();
			cost = new ArrayList<Integer>();
		}

		public String toString()
		{
			return fastestPath+" "+spiderDist;
		}
	}

}
