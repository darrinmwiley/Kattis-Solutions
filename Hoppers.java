import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hoppers {
	
	public boolean colorable = true;
	int[] colors;
	int[] comp;
	boolean[] vis;
	int components = 1;
	node[] nodes;
	
	public static void main(String[] args) throws Exception {
		new Hoppers().run();
	}
	
	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		nodes = new node[N];
		for(int i = 0;i<N;i++)
			nodes[i] = new node(i);
		colors = new int[N];
		comp = new int[N];
		vis = new boolean[N];
		for(int i = 0;i<M;i++)
		{
			st = new StringTokenizer(file.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			nodes[a].con.add(b);
			nodes[b].con.add(a);
		}
		int cc = 0;
		for(int i = 0;i<nodes.length;i++)
		{
			if(comp[i] == 0)
			{
				dfs(i);
				cc++;
			}
		}
	loop:
		for(int i = 0;i<N;i++)
			for(int x:nodes[i].con)
			{
				if(colors[i] == colors[x])
				{
					colorable = false;
					break loop;
				}
			}
		if(colorable)
			System.out.println(cc);
		else
			System.out.println(cc-1);
	}
	
	public void dfs(int id)
	{
		int cno = components++;
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(id);
		que.add(1);//color
		vis[id] = true;
		while(!que.isEmpty())
		{
			int index = que.poll();
			int color = que.poll();
			colors[index] = color;
			node n = nodes[index];
			comp[index] = cno;
			for(int x:n.con)
			{
				if(!vis[x])
				{
					que.add(x);
					que.add(opposite(color));
					vis[x] = true;
				}
			}
		}
	}
	
	public int opposite(int x)
	{
		if(x==1)
			return 2;
		return 1;
	}
	
	private class node{
		
		int id;
		ArrayList<Integer> con;
		
		public node(int x)
		{
			id = x;
			con = new ArrayList<Integer>();
		}
		
	}
}
