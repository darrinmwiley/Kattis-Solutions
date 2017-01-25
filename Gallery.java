import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Gallery {
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int K = file.nextInt();
		int[][] vals = new int[N][2];
		int sum = 0;
		for(int i = 0;i<vals.length;i++)
		{
			vals[i][0] = file.nextInt();
			vals[i][1] = file.nextInt();
			sum+=vals[i][0]+vals[i][1];
		}
		int[][][] dp = new int[K+1][N+1][3];
		for(int i = 1;i<dp.length;i++)
			for(int j = 0;j<N;j++)
				for(int k = 0;k<3;k++)
				dp[i][j][k] = 1000000;
		for(int i = 1;i<dp.length;i++)
			for(int j = 1;j<dp[i].length;j++)
			{
				dp[i][j][0] = Math.min(dp[i][j-1][0],Math.min(vals[j-1][0]+dp[i-1][j-1][1],vals[j-1][1]+dp[i-1][j-1][2]));
				dp[i][j][1] = Math.min(dp[i][j-1][0], vals[j-1][0]+dp[i-1][j-1][1]);
				dp[i][j][2] = Math.min(dp[i][j-1][0], vals[j-1][1]+dp[i-1][j-1][2]);
			}
		System.out.println(sum-Math.min(dp[K][N][0],Math.min(dp[K][N][1],dp[K][N][2])));
	}
}
