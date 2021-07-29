package page;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class connect {
	
	int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception
	{
		new connect().run();
	}
	
	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		int ar = file.nextInt();
		int ac = file.nextInt();
		int a1r = file.nextInt();
		int a1c = file.nextInt();
		int br = file.nextInt();
		int bc = file.nextInt();
		int b1r = file.nextInt();
		int b1c = file.nextInt();
		boolean[][] mat = new boolean[N+1][M+1];
		//System.out.println("setup done");
		int[][] cost = pdijkstra(ar,ac,a1r,a1c,br,bc,b1r,b1c,mat);
		//System.out.println("first dijkstra done");
		traceback(a1r,a1c,cost,mat);
		cost = pdijkstra(br,bc,b1r,b1c,mat);
		int min = 200000;
		if(cost!=null) {
			traceback(b1r,b1c,cost,mat);
			/*for(boolean[] b:mat) {
				for(boolean bo:b)
					if(bo)
						System.out.print("T");
					else
						System.out.print("F");
				System.out.println();
			}*/
			min = 0;
			for(boolean[] b:mat)
				for(boolean bo:b)
					if(bo)
						min++;	
		}
		//System.out.println("half done");
		boolean[][] mat2 = new boolean[N+1][M+1];
		int[][] cost2 = pdijkstra(br,bc,b1r,b1c,ar,ac,a1r,a1c,mat2);
		traceback(b1r,b1c,cost2,mat2);
		cost2 = pdijkstra(ar,ac,a1r,a1c,mat2);
		int min2 = 200000;
		if(cost2!=null)
		{
			traceback(a1r,a1c,cost2,mat2);
			min2 = 0;
			for(boolean[] b:mat2)
				for(boolean bo:b)
					if(bo)
						min2++;
			
		}
		//System.out.println(min-2+" "+(min2-2));
		//System.out.println("full done");
		min = Math.min(min, min2);
		if(min>100000||min<0)
			System.out.println("IMPOSSIBLE");
		else
			System.out.println(min-2);	
		
	}
	
	public int[][] pdijkstra(int r1, int c1, int r2, int c2, int badr1, int badc1, int badr2, int badc2, boolean[][] mat)
	{
		int[][] cost = new int[mat.length][mat[0].length];
		mat[badr1][badc1] = mat[badr2][badc2] = true;
		fill(cost,mat);
		//System.out.println("starting dijkstra");
		dijkstra(r1,c1,r2,c2,mat,cost,0);
		min = Integer.MAX_VALUE;
		return cost;
	}
	
	public int[][] pdijkstra(int r1, int c1, int r2, int c2, boolean[][] mat)
	{
		int[][] cost = new int[mat.length][mat[0].length];
		fill(cost,mat);
		cost[r1][c1] = cost[r2][c2] = Integer.MAX_VALUE/2;
		/*for(int[] in:cost)
		{
			for(int i:in)
			{
				if(i>100000)
					System.out.print("M, ");
				else
					System.out.print(i+", ");
			}
			System.out.println();
		}*/
		dijkstra(r1,c1,r2,c2,mat,cost,0);
		min = Integer.MAX_VALUE;
		if(cost[r2][c2]>100000)
			return null;
		return cost;
	}
	
	public void dijkstra(int r1, int c1, int r2, int c2,boolean[][] mat, int[][] cost, int c)
	{
		//System.out.println(r1+" "+c1+" "+r2+" "+c2);
		Stack<Integer> stack = new Stack<Integer>();
		Queue<Integer> que = new LinkedList<Integer>();
		que.offer(r1);
		que.offer(c1);
		que.offer(c);
		while(!que.isEmpty())
		{
			r1 = que.poll();
			c1 = que.poll();
			c = que.poll();
			if(!val(r1,c1,cost)||cost[r1][c1]<=c||c>=min)
				continue;
			cost[r1][c1] = c;
			if(r1==r2&&c1==c2)
				min = Math.min(min,c);
			int[][] d = new int[][] {{1,0,-1,0},{0,1,0,-1}};
			for(int i = 0;i<4;i++) {
				que.offer(r1+d[0][i]);
				que.offer(c1+d[1][i]);
				que.offer(c+1);
			}
		}
		
	}
	
	public void traceback(int r, int c,int[][] cost, boolean[][] answer)
	{
		answer[r][c] = true;
		int[][] d = new int[][] {{1,0,-1,0},{0,1,0,-1}};
		if(cost[r][c]!=0)
		{
			for(int i = 0;i<4;i++)
			{
				int rr = r+d[0][i];
				int cc = c+d[1][i];
				if(val(rr,cc,cost)&&cost[rr][cc]+1==cost[r][c])
				{
					traceback(rr,cc,cost,answer);
					return;
				}
			}
		}
	}
	
	public boolean val(int r, int c, int[][] mat)
	{
		return Math.min(r,c)>=0&&r<mat.length&&c<mat[r].length;
	}
	
	public void fill(int[][] ints,boolean[][] bools)
	{
		for(int i = 0;i<ints.length;i++)
		{
			for(int j = 0;j<ints[i].length;j++)
			{
				if(bools[i][j])
					ints[i][j] = -1;
				else
					ints[i][j] = Integer.MAX_VALUE/2;
			}
		}
	}
}


