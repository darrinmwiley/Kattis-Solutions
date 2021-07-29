import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BallsAndNeedles {

	ArrayList<node> nodes;
	ArrayList<node> floor;
	HashMap<String, Integer> map;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new BallsAndNeedles().run();	
	}

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		nodes = new ArrayList<node>(N);
		floor = new ArrayList<node>(N);
		map = new HashMap();
		for(int i = 0;i<N;i++)
		{
			StringTokenizer st = new StringTokenizer(file.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int z1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int z2 = Integer.parseInt(st.nextToken());
			if(!map.containsKey(x1+","+y1+","+z1))
			{
				node n = new node(x1,y1,z1);
				n.id = nodes.size();
				map.put(x1+","+y1+","+z1,nodes.size());
				nodes.add(n);
			}
			if(!map.containsKey(x2+","+y2+","+z2))
			{
				node n = new node(x2,y2,z2);
				n.id = nodes.size();
				map.put(x2+","+y2+","+z2,nodes.size());
				nodes.add(n);
			}
			if(!map.containsKey(x1+","+y1))
			{
				node n = new node(x1,y1);
				n.id = floor.size();
				map.put(x1+","+y1,floor.size());
				floor.add(n);
			}
			if(!map.containsKey(x2+","+y2))
			{
				node n = new node(x2,y2);
				n.id = floor.size();
				map.put(x2+","+y2,floor.size());
				floor.add(n);
			}
			int a = map.get(x1+","+y1+","+z1);
			int b = map.get(x2+","+y2+","+z2);
			nodes.get(a).con(b);
			nodes.get(b).con(a);
			int c = map.get(x1+","+y1);
			int d = map.get(x2+","+y2);
			if(c!=d)
			{
				floor.get(c).con(d);
				floor.get(d).con(c);
			}
		}
		boolean loops = (loops(nodes));
		boolean fl = (loops(floor));
		if(loops)
			System.out.println("True closed chains");
		else
			System.out.println("No true closed chains");
		if(fl)
			System.out.println("Floor closed chains");
		else
			System.out.println("No floor closed chains");

	}

	public boolean loops(ArrayList<node> nodes2)
	{
		boolean[] vis = new boolean[nodes2.size()];
		int[] pred = new int[nodes2.size()];
		Arrays.fill(pred,-1);
		for(int i = 0;i<vis.length;i++)
		{
			if(!vis[i])
			{
				Queue<Integer> que = new LinkedList<Integer>();
				que.add(i);
				while(!que.isEmpty())
				{
					int x = que.poll();
					if(vis[x])
						continue;
					vis[x] = true;
					node n = nodes2.get(x);
					for(int q:n.con)
					{
						if(vis[q]&&pred[x]!=q)
							return true;
						if(pred[x]!=q)
						{
							pred[q] = x;
							que.add(q);
						}
					}
				}
			}
		}
		return false;
	}

	private class node{

		int id;
		String name;
		ArrayList<Integer> con;

		public node(int x, int y, int z)
		{
			name = x+","+y+","+z;
			con = new ArrayList<Integer>();
		}

		public node(int x, int y)
		{
			name = x+","+y;
			con = new ArrayList<Integer>();
		}

		public void con(int N)
		{
			con.add(N);
		}
	}

}
