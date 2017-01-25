import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class Dependencies {
	public static void main(String[] args)
	{
		new Dependencies().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		file.nextLine();
		TreeMap<String,Integer> map = new TreeMap<String,Integer>();
		node[] nodes = new node[N];
		for(int i = 0;i<N;i++)
		{
			nodes[i] = new node(i);
		}
		for(int i = 0;i<N;i++)
		{
			String[] line = file.nextLine().replace(":","").split(" ");
			String s = line[0];
			if(map.get(s)==null)
			{
				map.put(s,map.size());
				nodes[map.get(s)].name = s;
			}
				
			for(int j = 1;j<line.length;j++)
			{
				String t = line[j];
				if(map.get(t)==null)
				{
					map.put(t,map.size());
					nodes[map.get(t)].name = t;
				}	
				nodes[map.get(t)].add(map.get(s));
				nodes[map.get(s)].indeg++;
			}
		}
		String str = file.next();
		boolean[] bools = new boolean[N];
		Queue<Integer> que = new LinkedList();
		que.add(map.get(str));
		bools[map.get(str)] = true;
		int newsize = 1;
		while(!que.isEmpty())
		{
			int i = que.poll();
			node n = nodes[i];
			for(int x:n.out)
			{
				if(!bools[x])
				{
					bools[x] = true;
					newsize++;
					que.add(x);
				}
			}
		}
		for(node n:nodes)
		{
			if(!bools[n.id])
			{
				for(int i:n.out)
				{
					nodes[i].indeg--;
				}
			}
		}
		node[] nodes2 = new node[newsize];
		int spot = 0;
		for(int i = 0;i<nodes.length;i++)
		{
			if(bools[i])
				nodes2[spot++] = nodes[i];
		}
		Queue<Integer> zero = new LinkedList<Integer>();
		for(int i = 0;i<nodes2.length;i++)
		{
			if(nodes2[i].indeg==0)
				zero.add(nodes2[i].id);
		}
		int done = 0;
		while(done!=newsize)
		{
			int next = zero.poll();
			node nx = nodes[next];
			System.out.println(nx.name);
			done++;
			for(int i:nx.out)
			{
				nodes[i].indeg--;
				if(nodes[i].indeg==0)
					zero.offer(i);
			}
		}
	}
	
	private class node
	{
		int indeg;
		int id;
		ArrayList<Integer> out;
		String name;
		public node(int q)
		{
			out = new ArrayList();
			indeg = 0;
			id = q;
		}
		
		public void add(int n)
		{
			out.add(n);
		}
		
		public String toString()
		{
			return name+" "+indeg;
		}
	}
}
