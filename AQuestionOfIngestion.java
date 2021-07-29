

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

public class AQuestionOfIngestion {

    public static void main(String[] args) throws Exception
    {
        new AQuestionOfIngestion().run();
    }

    int[][] dp;

    public void run() throws Exception
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int C = file.nextInt();
        if(N == 0)
        {
        		System.out.println(0);
        		return;
        }
        int[] cal = new int[N];
        for(int i = 0;i<N;i++)
        		cal[i] = file.nextInt();
        dp = new int[N+1][C+1];
        for(int i = 0;i<N+1;i++)
        {
        		for(int j = 0;j<C+1;j++)
        		{
        			dp[i][j] = -Integer.MAX_VALUE/8;
        		}
        }
        dp[0][C] = 0;
        for(int i = 0;i<N;i++)
        {
        		for(int j = 0;j<C+1;j++)
        		{
        			int cc = cal[i];
        			set(i+1, j*2/3, dp[i][j] + Math.min(cc, j));
        			set(i+1, j, dp[i][j]);
        			set(i+2, j, dp[i][j]+Math.min(cc,j));
        			set(i+3, C, dp[i][j]+Math.min(cc,j));
        		}
        }
        int max = 0;
        for(int i = 0;i<dp[0].length;i++)
        		max = Math.max(max, dp[N][i]);
        System.out.println(max);
    }

    public void set(int r, int c, int x)
    {
    		if(r < dp.length && c < dp[r].length)
    		{
    			dp[r][c] = Math.max(dp[r][c], x);
    		}
    }
}
