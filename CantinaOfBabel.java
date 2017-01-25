import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class CantinaOfBabel {
	node[] nodes;
	boolean[] vis;
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int n = file.nextInt();
		nodes = new node[n];
		HashMap<node,HashSet<String>> understands = new HashMap<node,HashSet<String>>();
		HashMap<node,String> speaks = new HashMap<node,String>();
		for(int i = 0;i<n;i++)
		{
			nodes[i] = new node(i,file.next());
			String[] line = file.nextLine().trim().split(" ");
			speaks.put(nodes[i],line[0]);
			HashSet<String> u = new HashSet<String>();
			for(int j = 0;j<line.length;j++)
				u.add(line[j]);
			understands.put(nodes[i],u);
		}
		for(int i = 0;i<n;i++)
			for(int j = 0;j<n;j++)
				if(i!=j&&understands.get(nodes[j]).contains(speaks.get(nodes[i])))
						nodes[i].add(nodes[j]);
		vis = new boolean[n];
		int x = 1;
		for(int i = 0;i<nodes.length;i++)
			x = mark(nodes[i],x);
		Arrays.sort(nodes);
		for(int i = 0;i<nodes.length;i++)
			nodes[i].edges.clear();
		for(int i = 0;i<n;i++)
			for(int j = 0;j<n;j++)
				if(i!=j&&understands.get(nodes[j]).contains(speaks.get(nodes[i])))
						nodes[j].add(nodes[i]);
		int max = 0;
		vis = new boolean[n];
		for(int i = 0;i<nodes.length;i++)
			max = Math.max(max,groupSize(nodes[i]));
		System.out.println(n-max);
	}
	
	public int groupSize(node n)
	{
		if(vis[n.id])
			return 0;
		vis[n.id] = true;
		int sum = 1;
		for(node x:n.edges)
			sum+=groupSize(x);
		return sum;
	}
	
	public int mark(node n, int x)
	{
		if(vis[n.id])
			return x;
		n.num = x++;
		vis[n.id] = true;
		for(node y:n.edges)
			x = mark(y,x);
		n.denom = x++;
		return x;
	}
	
	public static void main(String[] args)
	{
		new CantinaOfBabel().run();
	}
}
class node implements Comparable<node>{
	public static HashMap<String,Integer> map;
	public int id,num,denom;
	public ArrayList<node> edges;
	public node(int id,String name)
	{
		if(map==null)
			map = new HashMap<String,Integer>();
		map.put(name,id);
		this.id = id;
		edges = new ArrayList<node>();
	}
	public void add(node n)
	{
		edges.add(n);
	}
	public void remove(node n)
	{
		edges.remove(n);
	}
	
	public int compareTo(node n)
	{
		return n.denom-denom;
	}
}