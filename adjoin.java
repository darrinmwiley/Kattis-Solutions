import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class adjoin {
	
	int[] groups;
	int[] r;
	int[] p;
	int[] h;
	int[] f;
	int[] g;
	int[] e;
	int[] d;
	int G = -1;
	node[] nodes;
	
	public static void main(String[] args) throws IOException
	{
		new adjoin().run();
	}
	
	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		nodes = new node[N];
		for(int i =0;i<N;i++)
			nodes[i] = new node(i);
		for(int i = 0;i<M;i++)
		{
			st = new StringTokenizer(file.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].con(b);
			nodes[b].con(a);
		}
		group();
		p();
		h();
		f();
		g();
		e();
		r();
		d();
		Arrays.sort(r);
		Arrays.sort(d);
		int ans = d[d.length-1];
		try{
			ans = Math.max(ans,1+r[r.length-1]+r[r.length-2]);
		}catch(Exception ex){}
		try{
			ans = Math.max(ans,2+r[r.length-2]+r[r.length-3]);
		}catch(Exception ex){}
		System.out.println(ans);
	}
	
	public void group()
	{
		boolean[] vis = new boolean[nodes.length];
		groups = new int[nodes.length];
		for(int i = 0;i<nodes.length;i++)
		{
			if(!vis[i])
			{
				Queue<Integer> que = new LinkedList<Integer>();
				que.add(i);
				G++;
				while(!que.isEmpty())
				{
					int x = que.poll();
					if(vis[x])
						continue;
					vis[x] = true;
					groups[x] = G;
					for(int y:nodes[x].con)
						if(!vis[y])
							que.offer(y);
				}
			}
		}
	}
	
	public void p()
	{
		boolean[] vis = new boolean[nodes.length];
		p = new int[nodes.length];
		Arrays.fill(p,-1);
		for(int i = 0;i<nodes.length;i++)
		{
			if(!vis[i])
			{
				Stack<Integer> st = new Stack<Integer>();
				st.add(i);
				while(!st.isEmpty())
				{
					int x = st.pop();
					if(vis[x])
						continue;
					vis[x] = true;
					for(int y:nodes[x].con)
					{
						if(!vis[y])
						{
							p[y] = x;
							st.add(y);
						}
					}
				}
			}
		}
	}
	
	public void h()
	{
		h = new int[nodes.length];
		Arrays.fill(h, -1);
		for(int i = 0;i<nodes.length;i++)
			if(h[i]==-1)
				h(i);
	}
	
	public int h(int start)
	{
		if(h[start]!=-1)
			return h[start];
		int max = 0;
		for(int x:nodes[start].con)
			if(p[start]!=x)
				max = Math.max(max,1+h(x));
		return h[start] = max;
	}
	
	public void f()
	{
		f = new int[nodes.length];
		Arrays.fill(f, -1);
		for(int i = 0;i<nodes.length;i++)
			if(f[i]==-1)
				f(i);
	}
	
	public int f(int start)
	{
		if(f[start]!=-1)
			return f[start];
		if(p[start]==-1)
			return f[start] = 0;
		int siblings = nodes[p[start]].con.size()-1;
		if(p[p[start]]!=-1)
			siblings--;
		if(siblings==0)
			return f[start] = 1;
		int max = 0;
		for(int x:nodes[p[start]].con)
			if(x!=start&&x!=p[p[start]])
				max = Math.max(max,2+h[x]);
		return f[start] = max;
	}
	
	public void g()
	{
		g = new int[nodes.length];
		Arrays.fill(g,-1);
		for(int i = 0;i<nodes.length;i++)
			if(g[i]==-1)
				g(i);
	}
	
	public int g(int start)
	{
		if(g[start]!=-1)
			return g[start];
		if(p[start]==-1)
			return g[start] = 0;
		return g[start] = Math.max(f[start],g(p[start])+1);
	}
	
	public void e()
	{
		e = new int[nodes.length];
		for(int i = 0;i<nodes.length;i++)
			e[i] = Math.max(g[i],h[i]);
	}
	
	public void r()
	{
		r = new int[G+1];
		Arrays.fill(r,100000);
		for(int i = 0;i<nodes.length;i++)
			r[groups[i]] = Math.min(r[groups[i]],e[i]);
	}
	
	public void d()
	{
		d = new int[G+1];
		for(int i = 0;i<nodes.length;i++)
				d[groups[i]] = Math.max(d[groups[i]],e[i]);
	}
	
	private class node{
		
		int id;
		ArrayList<Integer> con;
		
		public node(int d)
		{
			id = d;
			con = new ArrayList<>();
		}
		
		public void con(int N)
		{
			con.add(N);
		}
		
	}
}
