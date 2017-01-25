import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class VisuAlgo {
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int n = file.nextInt();
		Vertex[] v = new Vertex[n];
		for(int i = 0;i<n;i++)
			v[i] = new Vertex(i);
		int m = file.nextInt();
		for(int i = 0;i<m;i++)
			v[file.nextInt()].add(v[file.nextInt()],file.nextInt());
		int beg = file.nextInt();
		int end = file.nextInt();
		v[beg].shortestPath = 0;
		PriorityQueue<Vertex> que = new PriorityQueue<Vertex>();
		que.add(v[beg]);
		while(!que.isEmpty())
		{
			Vertex x = que.poll();
			for(int i = 0;i<x.list.size();i++)
			{
				long dist = x.shortestPath+x.edges.get(i);
				long curr = x.list.get(i).shortestPath;
				Vertex y = x.list.get(i);
				if(dist==curr)
				{
					y.previous.add(x);
				}else if(dist<curr)
				{
					y.previous.clear();
					y.previous.add(x);
					y.shortestPath = dist;
					que.remove(y);
					que.add(y);
				}
			}
		}
		System.out.println(v[end].numPaths());
	}
	
	public static void main(String[] args)
	{
		new VisuAlgo().run();
	}
}
class Vertex implements Comparable<Vertex>{
	
	long id;
	long shortestPath;
	long numPaths;
	ArrayList<Vertex> previous;
	ArrayList<Vertex> list;
	ArrayList<Long> edges;
	
	public Vertex(long id)
	{
		list = new ArrayList<Vertex>();
		edges = new ArrayList<Long>();
		previous = new ArrayList<Vertex>();
		this.id = id;
		shortestPath = Long.MAX_VALUE;
	}
	
	public void add(Vertex v, long i)
	{
		list.add(v);
		edges.add(i);
	}
	
	public int compareTo(Vertex v)
	{
		return (int)(Math.signum(shortestPath-v.shortestPath));
	}
	
	public void addAncestor(Vertex v)
	{
		numPaths+=v.numPaths();
	}
	
	public long numPaths()
	{
		if(shortestPath == Long.MAX_VALUE)
			return 0;
		if(previous.isEmpty())
			return 1;
		if(numPaths!=0)
			return numPaths;
		long sum = 0;
		for(Vertex v:previous)
			sum+=v.numPaths();
		numPaths = sum;
		return sum;
	}
	
}
