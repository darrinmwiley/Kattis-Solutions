import java.util.*;
import java.io.*;

public class UsingDigits{
	
	BufferedReader file;
	PrintWriter pout;
	StringTokenizer st;
	
	int[][] mat;
	int[][] d = new int[][] {{-1,0},{0,1}};
	int[][] jadd = new int[][] {{-1,0},{0,1}};
	
	public static void main(String[] args) throws Exception
	{
		new UsingDigits().run();
	}
	
	public void run() throws Exception{
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);
		st = new StringTokenizer(file.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		char[] key = file.readLine().toCharArray();
		mat = new int[R][C];
		for(int i = 0;i<mat.length;i++)
		{
			String line = file.readLine();
			for(int j = 0;j<mat[i].length;j++)
			{
				mat[i][j] = line.charAt(j)-'0';
			}
		}
		int[][][] fp = new int[R][C][key.length+1];
		for(int i = 0;i<R;i++)
		{
			for(int j = 0;j<C;j++)
			{
				Arrays.fill(fp[i][j], Integer.MAX_VALUE/4);
			}
		}
		boolean[][][] vis = new boolean[R][C][key.length+1];
		
		PriorityQueue<state> que = new PriorityQueue<state>();
		que.add(new state(fp.length - 1,0,0,mat[mat.length - 1][0]));
		while(!que.isEmpty())
		{
			state curr = que.poll();
			int nextJump = 0;
			if(curr.nextJump < key.length)
				nextJump = key[curr.nextJump]-'0';
			int r = curr.R;
			int c = curr.C;
			int cost = curr.cost;
			//System.out.println(r+" "+c+" "+nextJump+" "+cost);
			if(vis[r][c][curr.nextJump])
			{
				continue;
			}
			vis[r][c][curr.nextJump] = true;
			for(int i = 0;i<2;i++)
			{
				//normal
				int rr = r+d[0][i];
				int cc = c+d[1][i];
				if(val(rr,cc))
				{
					int nextCost = cost + mat[rr][cc];
					if(nextCost < fp[rr][cc][curr.nextJump])
					{
						fp[rr][cc][curr.nextJump] = nextCost;
						que.add(new state(rr,cc,curr.nextJump, nextCost));
					}
				}
				if(curr.nextJump < key.length)
				{
					int jr = r+nextJump*d[0][i]+jadd[0][i];
					int jc = c+nextJump*d[1][i]+jadd[1][i];
					if(val(jr,jc))
					{
						int nextCost = cost + mat[jr][jc];
						if(nextCost < fp[jr][jc][curr.nextJump+1])
						{
							fp[jr][jc][curr.nextJump+1] = nextCost;
							que.add(new state(jr,jc,curr.nextJump+1, nextCost));
						}
					}
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0;i<key.length+1;i++)
		{
			min = Math.min(min, fp[0][mat[0].length-1][i]);
		}
		System.out.println(min);
	}
	
	public boolean val(int r, int c)
	{
		return Math.min(r,c) >= 0 && r < mat.length && c < mat[r].length;
	}
	
	private class state implements Comparable<state>{
		
		int R,C,nextJump, cost;
		public state(int r, int c, int nj, int cost)
		{
			R = r;
			C = c;
			nextJump = nj;
			this.cost = cost;
		}
		@Override
		public int compareTo(state arg0) {
			return Integer.compare(cost, arg0.cost);
		}
		
	}
	
}