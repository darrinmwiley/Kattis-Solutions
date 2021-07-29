import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class KnightJump {
	
	public static void main(String[] args) throws Exception {
		new KnightJump().run();
	}
	
	char[][] grid;
	int[][] d = new int[][] {{-2,-2,-1,-1,1,1,2,2},{-1,1,-2,2,-2,2,-1,1}};
	boolean[][] vis;
	int size;
	
	public void run() throws Exception
	{
		Scanner input = new Scanner(System.in);
		size = input.nextInt();
		input.nextLine();
		grid = new char[size][size];
		vis = new boolean[size][size];
		for(int i = 0;i<size;i++)
		{
			grid[i] = input.next().toCharArray();
		}
		int[] K = find('K');
		vis[K[0]][K[1]] = true;
		vertex start = new vertex(K[0],K[1], 0);
		Queue<vertex> que = new LinkedList<vertex>();
		que.add(start);
		while(!que.isEmpty())
		{
			vertex current = que.poll();
			if(current.r == 0 && current.c == 0)
			{
				System.out.println(current.cost);
				return;
			}
			for(int i = 0;i<8;i++)
			{
				int newR = current.r + d[0][i];
				int newC = current.c + d[1][i];
				if(isValid(newR, newC) && !vis[newR][newC] && grid[newR][newC] != '#')
				{
					vis[newR][newC] = true;
					que.add(new vertex(newR, newC, current.cost + 1));
				}
			}
		}
		System.out.println(-1);
	}
	
	public boolean isValid(int r, int c)
	{
		return Math.min(r,c) >= 0 && Math.max(r,c) < size;
	}
	
	public int[] find(char ch)
	{
		for(int i = 0;i<size;i++)
			for(int j = 0;j<size;j++)
				if(grid[i][j] == ch)
					return new int[] {i,j};
		return null;
	}
	
	private class vertex{
		
		int r, c, cost;
		public vertex(int r, int c, int cost)
		{
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
		
	}
}
