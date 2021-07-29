import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;

public class Xentopia {

	public static void main(String[] args)
	{
		new Xentopia().run();
	}

	long[][][] d;
	boolean[][][] vis;

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		int k1 = file.nextInt();//red
		int k2 = file.nextInt();//blue
		node[] nodes = new node[N];
		d = new long[N][k1+1][k2+1];
		for(int i = 0;i<N;i++)
			nodes[i] = new node(i);
		for(int i = 0;i<M;i++)
		{
			int U = file.nextInt()-1;
			int V = file.nextInt()-1;
			int X = file.nextInt();
			int C = file.nextInt();
			nodes[U].addEdge(V, X, C);
			nodes[V].addEdge(U, X, C);
		}
		for(node n:nodes)
			n.sort();
		int S = file.nextInt()-1;
		int T = file.nextInt()-1;
		for(int i = 0;i<d.length;i++)
			for(int j = 0;j<=k1;j++)
				Arrays.fill(d[i][j],Long.MAX_VALUE);//this might be too small
		Queue<state> que = new PriorityQueue<state>();
		que.add(new state(S,0,0,0));
		long cutoff = Long.MAX_VALUE;
		vis = new boolean[N][k1+1][k2+1];
		while(!que.isEmpty())
		{
			state s = que.poll();
			int n = s.n;
			int b = s.b;
			int r = s.r;
			long c = s.c;
			if(c>=cutoff)
				continue;
			if(n==T&&r==k1&&b==k2)
				cutoff = Math.min(cutoff, c);
			if(!good(n,r,b,c))
				continue;
			vis[n][r][b] = true;
			d[n][r][b] = c;
			for(edge e:nodes[n].edges)
				if(good(e.dest,r+e.red,b+e.blue,c+e.cost))
					que.add(new state(e.dest,r+e.red,b+e.blue,c+e.cost));
		}
		long ans = d[T][k1][k2];
		if(ans==Long.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(d[T][k1][k2]);
	}
	//n,red,blue
	public boolean good(int n, int r, int b, long c)
	{
		if(r>=d[n].length) {
			return false;
		}

		if(b>=d[n][r].length) {
			return false;
		}
		if(vis[n][r][b]) {
			return false;
		}
		if(c>=d[n][r][b]) {
			return false;
		}
		return true;
	}

	private class state implements Comparable<state>{
		int n,b,r;
		long c;
		public state(int n, int r, int b, long c)
		{
			this.n = n;
			this.b = b;
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(state o) {
			return Long.compare(c, o.c);
		}
	}

	private class node{

		int id;
		ArrayList<edge> edges;

		public node(int id)
		{
			this.id = id;
			edges = new ArrayList<edge>();
		}

		public void addEdge(int dest, int cost, int color)
		{
			edges.add(new edge(dest,cost,color));
		}

		public void sort()
		{
			Collections.sort(edges);
		}

	}

	private class edge implements Comparable<edge>{

		int dest;
		int cost;
		int red;
		int white;
		int blue;

		public edge(int dest, int cost, int color){
			this.dest = dest;
			this.cost = cost;
			if(color==0)
				white = 1;
			if(color == 1)
				red = 1;
			if(color ==2)
				blue = 1;
		}

		@Override
		public int compareTo(edge arg0) {
			if(cost==arg0.cost)
			{
				return Integer.compare(red+blue, arg0.red+arg0.blue);
			}
			return Integer.compare(cost,arg0.cost);
		}

	}

}
