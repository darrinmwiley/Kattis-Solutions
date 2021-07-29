import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BuggyRobot2 {

	public static void main(String[] args) throws Exception {
		new BuggyRobot2().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int R = file.nextInt();
		int C = file.nextInt();
		file.nextLine();
		char[][] chars = new char[R][C];
		for(int i = 0;i<R;i++)
			chars[i] = file.nextLine().toCharArray();
		char[] command = file.nextLine().toCharArray();
		int[][][] dp = new int[command.length+1][R][C];
		int[] start = find(chars,'R');
		int[] exit = find(chars,'E');
		for(int i = 0;i<dp.length;i++)
			for(int j = 0;j<dp[i].length;j++)
				for(int k = 0;k<dp[i][j].length;k++)
					dp[i][j][k] = 5000;
		dp[0][start[0]][start[1]] = 0;
		for(int i = 0;i<dp.length;i++)
		{
			Queue<Integer> que = new LinkedList<Integer>();//r,c,cost
			for(int r = 0;r<dp[i].length;r++) {
				for(int c = 0;c<dp[i][r].length;c++)
				{
					if(dp[i][r][c] != 5000)
					{
						que.add(r);
						que.add(c);
					}
				}
			}
			while(!que.isEmpty())
			{
				int r = que.poll();
				int c = que.poll();
				int cost = dp[i][r][c];
				int[][] d = new int[][] {{-1,0,1,0},{0,1,0,-1}};
				for(int k = 0;k<4;k++)
				{
					int rr = r+d[0][k];
					int cc = c+d[1][k];
					if(valid(rr,cc,chars) && dp[i][rr][cc] > cost+1)
					{
						que.add(rr);
						que.add(cc);
						dp[i][rr][cc] = cost+1;
					}
				}
			}

			for(int r = 0;r<dp[i].length;r++)
			{
				for(int c = 0;c<dp[i][r].length;c++)
				{
					if(i!=dp.length-1)
					{
						if(dp[i][r][c] != 5000)
						{
							char ch = command[i];
							dp[i+1][r][c] = Math.min(dp[i+1][r][c],dp[i][r][c] + 1);//delete
							if(ch == 'R')
							{
								if(valid(r,c+1,chars))
								{
									dp[i+1][r][c+1] = Math.min(dp[i+1][r][c+1],dp[i][r][c]);
								}else
									dp[i+1][r][c] = Math.min(dp[i+1][r][c],dp[i][r][c]);
							}
							if(ch == 'L')
							{
								if(valid(r,c-1,chars))
								{
									dp[i+1][r][c-1] = Math.min(dp[i+1][r][c-1],dp[i][r][c]);
								}else
									dp[i+1][r][c] = Math.min(dp[i+1][r][c],dp[i][r][c]);
							}
							if(ch == 'U')
							{
								if(valid(r-1,c,chars))
								{
									dp[i+1][r-1][c] = Math.min(dp[i+1][r-1][c],dp[i][r][c]);
								}else
									dp[i+1][r][c] = Math.min(dp[i+1][r][c],dp[i][r][c]);
							}
							if(ch == 'D')
							{
								if(valid(r+1,c,chars))
								{
									dp[i+1][r+1][c] = Math.min(dp[i+1][r+1][c],dp[i][r][c]);
								}else
									dp[i+1][r][c] = Math.min(dp[i+1][r][c],dp[i][r][c]);
							}
						}
					}
				}
			}

		}
		int cost = 5000;
		for(int i = 0;i<dp.length;i++)
		{
			cost = Math.min(cost,dp[i][exit[0]][exit[1]]);
		}
		System.out.println(cost);
	}

	public boolean valid(int R, int C, char[][] chars)
	{
		return Math.min(R,C)>=0 && R<chars.length && C < chars[R].length && chars[R][C] != '#';
	}

	public int[] find(char[][] chars, char ch)
	{
		for(int i = 0;i<chars.length;i++)
			for(int j = 0;j<chars[i].length;j++)
				if(chars[i][j] == ch)
					return new int[] {i,j};
		return null;
	}
}
