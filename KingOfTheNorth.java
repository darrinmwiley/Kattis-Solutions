import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KingOfTheNorth {
	
	int R;
	int C;
	int[] pred;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new KingOfTheNorth().run();
	}
	
	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[][] ints = new int[R][C];
		for(int i = 0;i<R;i++)
		{
			st = new StringTokenizer(file.readLine());
			for(int j = 0;j<C;j++)
			{
				ints[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(file.readLine());
		int cr = Integer.parseInt(st.nextToken());
		int cc = Integer.parseInt(st.nextToken());
		node[] nodes = new node[R*C*2+2];
		pred = new int[nodes.length];
		int source = 0;
		int sink = nodes.length-1;
		int first = 1;
		int second = R*C+1;
		for(int i = 0;i<nodes.length;i++)
		{
			nodes[i] = new node(i);
		}
		for(int i = 0;i<R;i++)
			for(int j = 0;j<C;j++)
			{
				int cap = ints[i][j];
				int index = index(i,j);
				nodes[first+index].con(second+index,cap);
				nodes[second+index].con(first+index,0);
			}
		int[][] d = new int[][]{{1,0,-1,0},{0,1,0,-1}};
		for(int i = 0;i<R;i++)
		{
			for(int j = 0;j<C;j++)
			{
				if(i==0||j==0||i==R-1||j==C-1)
				{
					nodes[second+index(i,j)].con(sink,100000);
					nodes[sink].con(second+index(i,j),0);
				}
				for(int k = 0;k<4;k++)
				{
					int a = second+index(i,j);
					if(!val(i+d[0][k],j+d[1][k]))
						continue;
					//System.out.println(i+d[0][k]+" "+(j+d[1][k]));
					int b = first+index(i+d[0][k],j+d[1][k]);
					//System.out.println(a+" "+b);
					nodes[a].con(b,100000);
					nodes[b].con(a,0);
				}
			}
		}
		int castle = first+index(cr,cc);
		nodes[castle].con(source,0);
		nodes[source].con(castle,500000);
		int ans = 0;
		while(true){
			int x = aug(nodes);
			//System.out.println(x);
			if(x!=-1)
			{
				ans+=x;
				traceBack(nodes,x);
				//System.out.println("finished traceback");
			}else break;
		}
		System.out.println(ans);
	}
	
	public boolean val(int i, int j)
	{
		return Math.min(i,j)>=0&&i<R&&j<C;
	}
	
	public int aug(node[] nodes)
	{
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(0);
		boolean[] vis = new boolean[nodes.length];
		vis[0] = true;
		Arrays.fill(pred,-1);
		while(!que.isEmpty())
		{
			int x = que.poll();
			if(x==nodes.length-1)
				break;
			for(int y:nodes[x].con)
			{
				if(!vis[y]&&nodes[x].cap(y)>0){
					pred[y] = x;
					vis[y] = true;
					que.add(y);
				}
			}
		}
		if(pred[nodes.length-1]==-1)
			return -1;
		int min = Integer.MAX_VALUE;
		int current = nodes.length-1;
		while(pred[current]!=-1)
		{
			min = Math.min(nodes[pred[current]].cap(current),min);
			current = pred[current];
		}
		return min;
	}
	
	public void traceBack(node[] nodes,int x)
	{
		int current = nodes.length-1;
		while(pred[current]!=-1){
			int pr = pred[current];
			nodes[current].incCap(pr,x);
			nodes[pr].incCap(current,-x);
			current = pr;
		}
	}
	
	public int index(int r, int c)
	{
		return C*r+c;
	}
	
	private class node{
		
		ArrayList<Integer> con;
		HashMap<Integer,Integer> cap;
		int id;
		
		public node(int x)
		{
			id = x;
			con = new ArrayList<Integer>();
			cap = new HashMap<Integer,Integer>();
		}
		
		public void con(int d, int c)
		{
			con.add(d);
			cap.put(d,c);//dangerous
		}
		
		public int cap(int y)
		{
			return cap.get(y);
		}
		
		public void incCap(int d, int x)
		{
			cap.put(d,cap.get(d)+x);
		}
		
	}
	
}