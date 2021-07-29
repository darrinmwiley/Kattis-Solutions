package kattis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zbrka {

	long mod = 1000000007;
	
	public static void main(String[] args)
	{
		new zbrka().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int C = file.nextInt();
		System.out.println(ans(N,C));
	}
	
	long ans(int N, int C)
	{
		if(C == 0)
			return 1;
		if(N*N-N-N+1<C)
			return 0;
		long[][] dp = new long[N][C+1];//index in subarray, confusion
		long[][] sum = new long[N][C+2];
		dp[0][0] = 1;
		for(int i = 0;i<sum[1].length;i++)
			sum[0][i] = 1;
		sum[0][0] = 0;
		for(int i = 1;i<N;i++)
		{
			for(int j = 0;j<C+1;j++)
			{
				int back = Math.min(i,j);
				dp[i][j] = (sum[i-1][j+1]+ mod - sum[i-1][j-back])%mod;
				if(j!=0)
					sum[i][j+1] = (sum[i][j]+dp[i][j])%mod;
				else
					sum[i][j+1] = dp[i][j]%mod;
			}
		}
		return(dp[N-1][C]);
	}
	
}

