import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Walkway {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(file.hasNextInt())
		{
			int N = file.nextInt();
			if(N==0)
				return;
			node[] nodes = new node[1001];
			for(int i = 0;i<nodes.length;i++)
				nodes[i] = new node(i);
			for(int i = 0;i<N;i++)
			{
				int a = file.nextInt();
				int b = file.nextInt();
				int h = file.nextInt();
				nodes[a].add(b,(a+b)*h*.01);
				nodes[b].add(a,(a+b)*h*.01);
			}
			int start = file.nextInt();
			int end = file.nextInt();
			PriorityQueue<node> que = new PriorityQueue<node>();
			nodes[start].fp = 0;
			que.add(nodes[start]);
			/*for(int i = 0;i<nodes.length;i++)
			{
				if(nodes[i].con.isEmpty())
					continue;
				System.out.println(i+":");
				System.out.println(nodes[i].con);
				System.out.println(nodes[i].wt);
				System.out.println();
			}*/
			
			while(!que.isEmpty())
			{
				node n = que.poll();
				if(n.id==end)
					break;
				for(int x = 0;x<n.con.size();x++)
				{
					node y = nodes[n.con.get(x)];
					if(n.fp+n.wt.get(x)<y.fp)
					{
						y.fp = n.fp+n.wt.get(x);
						que.remove(y);
						que.add(y);
					}
				}
			}
			System.out.printf("%.2f%n",nodes[end].fp);
		}	
	}
	
	public static void main(String[] args)
	{
		new Walkway().run();
	}
	
	private class node implements Comparable<node>{
		int id;
		ArrayList<Integer> con;
		ArrayList<Double> wt;
		double fp;
		public node(int id)
		{
			this.id = id;
			con = new ArrayList();
			wt = new ArrayList();
			fp = 1000000000;
		}
		
		public void add(int i, double w)
		{
			con.add(i);
			wt.add(w);
		}
		
		@Override
		public int compareTo(node arg0) {
			return (int) Math.signum(fp-arg0.fp);
		}
	}
}

