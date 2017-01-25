import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

public class ants {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int N = file.nextInt();
			node[][] nodes = new node[121][121];
			for(int i = 0;i<121;i++)
				for(int j = 0;j<121;j++)
					nodes[i][j] = new node(i*121+j);
			int r = 60, c = 60;
			for(int i = 0;i<N;i++)
			{
				int R = r;
				int C = c;
				String next = file.next();
				switch(next)
				{
				case "S":r++;break;
				case "E":c++;break;
				case "W":c--;break;
				case "N":r--;break;
				}
				int x = R*121+C;
				int y = r*121+c;
				nodes[r][c].con.add(x);
				nodes[R][C].con.add(y);
			}
			PriorityQueue<node> que = new PriorityQueue();
			nodes[60][60].fp = 0;
			que.add(nodes[60][60]);
			while(!que.isEmpty())
			{
				node x = que.poll();
				if(x.id == r*121+c)
					break;
				for(int y:x.con)
				{
					int R = y/121;
					int C = y%121;
					if(nodes[R][C].fp>x.fp+1)
					{
						nodes[R][C].fp = x.fp+1;
						que.add(nodes[R][C]);
					}
				}
			}
			System.out.println(nodes[r][c].fp);
		}	
	}
	
	public static void main(String[] args)
	{
		new ants().run();
	}
	
	private class node implements Comparable<node>{
		int id;
		TreeSet<Integer> con;
		int fp;
		public node(int id)
		{
			this.id = id;
			con = new TreeSet();
			fp = 61;
		}
		@Override
		public int compareTo(node arg0) {
			return fp-arg0.fp;
		}
	}
}

