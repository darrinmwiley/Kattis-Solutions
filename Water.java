import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Water {
	
	node[] nodes;
	int[][] res;
	boolean[][] con;
	
	public static void main(String[] args) throws Exception {
		new Water().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int P = file.nextInt();
		int K = file.nextInt();
		nodes = new node[N];
		for(int i = 0;i<N;i++)
			nodes[i] = new node(i);
		res = new int[N][N];
		con = new boolean[N][N];
		for(int i = 0;i<P;i++)
		{
			int a = file.nextInt() - 1;
			int b = file.nextInt() - 1;
			int c = file.nextInt();
			connect(a,b,c);
		}
		int sum = 0;
		while(true)
		{
			int aug = aug();
			if(aug == 0)
				break;
			sum += aug;
		}
		System.out.println(sum);
		for(int i = 0;i<K;i++)
		{
			int a = file.nextInt() - 1;
			int b = file.nextInt() - 1;
			int c = file.nextInt();
			if(!con[a][b])
				connect(a,b,c);
			else
			{
				res[a][b] += c;
				res[b][a] += c;
			}
			while(true)
			{
				int aug = aug();
				if(aug == 0)
					break;
				sum += aug;
			}
			System.out.println(sum);
		}
	}
	
	public int aug()
	{
		//System.out.println("aug");
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(0);
		boolean[] vis = new boolean[nodes.length];
		int[] pred = new int[nodes.length];
		Arrays.fill(pred, -1);
		que.add(0);
		vis[0] = true;
		while(!que.isEmpty())
		{
			int x = que.poll();
			node node = nodes[x];
			for(int y:node.con)
			{
				if(res[x][y] != 0 && !vis[y])
				{
					pred[y] = x;
					vis[y] = true;
					que.add(y);
				}
			}
		}
		if(vis[1])
		{
			return traceback(pred);
		}else {
			return 0;
		}
	}
	
	public int traceback(int[] pred)
	{
		int current = 1;
		int min = Integer.MAX_VALUE;
		while(pred[current] != -1)
		{
			min = Math.min(min, res[pred[current]][current]);
			current = pred[current];
		}
		
		current = 1;
		while(pred[current] != -1)
		{
			res[pred[current]][current] -= min;
			res[current][pred[current]] += min;
			current = pred[current];
		}
		return min;
	}
	
	public void connect(int a, int b, int c)
	{
		nodes[a].con.add(b);
		nodes[b].con.add(a);
		res[a][b]+=c;
		res[b][a]+=c;
		con[a][b] = con[b][a] = true;
	}
	
	private class node{
		
		public ArrayList<Integer> con;
		int id;
		
		public node(int x)
		{
			id = x;
			con = new ArrayList<Integer>();
		}
		
	}
	
}
