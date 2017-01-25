import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Knapsack {
	
	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while((line = file.readLine())!=null)
		{
			StringTokenizer st = new StringTokenizer(line);
			int C = (int)(Double.parseDouble(st.nextToken()));
			int N = Integer.parseInt(st.nextToken());
			int[] v = new int[N];
			int[] w = new int[N];
			for(int i = 0;i<N;i++)
			{
				st = new StringTokenizer(file.readLine());
				v[i] = Integer.parseInt(st.nextToken());
				w[i] = Integer.parseInt(st.nextToken());
			}
			int[][] dp = new int[N+1][C+1];
			for(int i = 1;i<dp.length;i++)
				for(int j = 1;j<dp[i].length;j++)
				{
					dp[i][j] = dp[i-1][j];
					if(j>=w[i-1])
						dp[i][j] = Math.max(dp[i][j],dp[i-1][j-w[i-1]]+v[i-1]);
				}
			int r = N;
	        int c = C;
	        Stack<Integer> s = new Stack<Integer>(); 
	        while(r!=0)
	        {
	            if(c>=w[r-1]&&dp[r][c]-dp[r-1][c-w[r-1]]==v[r-1])
	            {
	                s.add(r-1);
	                c-=w[r-1];
	            }
	            r--;
	        }
	        System.out.println(s.size());
	        while(!s.isEmpty())
	        	System.out.print(s.pop()+" ");
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		new Knapsack().run();
	}
}

