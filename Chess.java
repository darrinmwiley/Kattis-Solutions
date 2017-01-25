import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Chess {
	int min = 100;
	public static void main(String[] args)
	{
		new Chess().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		file.nextInt();
		node[] nodes = new node[N];
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i = 0;i<M;i++)
		{
			String a = file.next();
			String b = file.next();
			if(!map.containsKey(a))
			{
				nodes[map.size()]=new node(map.size(),a);
				map.put(a,map.size());
			}
			if(!map.containsKey(b))
			{
				nodes[map.size()]=new node(map.size(),b);
				map.put(b,map.size());
			}
			nodes[map.get(a)].out.add(map.get(b));
			nodes[map.get(b)].indeg++;
		}
		HashMap<String,Integer> rank = new HashMap<String,Integer>();
		Queue<Integer> que = new LinkedList();
		for(node n:nodes)
		{
			if(n.indeg==0)
				que.add(n.id);
		}
		while(!que.isEmpty())
		{
			int x = que.poll();
			node n = nodes[x];
			rank.put(n.ic,rank.size());
			for(int i:n.out)
			{
				nodes[i].indeg--;
				if(nodes[i].indeg==0)
					que.add(i);
			}
		}
		Queue<Integer> list = new LinkedList<Integer>();
		for(int i =0;i<N;i++)
		{
			String next = file.next();
			if(rank.containsKey(next))
				list.add(rank.get(next));
		}
		ArrayList<Integer> lst = new ArrayList<Integer>();
		while(!list.isEmpty())
		{
			insert(list.poll(),lst);
		}
		//System.out.println(lst);
		System.out.println((N-lst.size())*2);
	}
	
	public void insert(int i, ArrayList<Integer> list)
	{
		//System.out.println("inserting "+i+" to "+list);
		if(list.isEmpty())
			list.add(i);
		else if(i>list.get(list.size()-1))
			list.add(i);
		else if(i<list.get(0))
			list.set(0,i);
		else
		{
			int low = 0;
			int high = list.size();
			int m = (low+high)/2;
			while(high-low>1)
			{
				if(list.get(m)>i)
					high = m;
				else
					low = m;
				m = (low+high)/2;
			}
			if(list.get(high-1)<i&&list.get(high)>i)
				list.set(high,i);
		}
	}
	
	private class node
	{
		int id, indeg;
		String ic;
		ArrayList<Integer> out;
		public node(int d, String c)
		{
			id = d;
			ic = c;
			out = new ArrayList();
		}
	}
}
