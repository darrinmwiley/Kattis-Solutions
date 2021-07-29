import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class PresidentialElection
{
	
	public static void main (String[] args) throws java.lang.Exception
	{
		new PresidentialElection().solve();
	}
	
	public void solve() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int S = file.nextInt();
		int[] D = new int[S];
		int[] C = new int[S];
		int[] F = new int[S];
		int[] U = new int[S];
		boolean[] winnable = new boolean[S];
		int totalDelegates = 0;
		int winnableDelegates = 0;
		int winnableStates = 0;
		int totalVotes = 0;
		int[] toWin = new int[S];
		for(int i = 0;i<S;i++)
		{
			D[i] = file.nextInt();
			C[i] = file.nextInt();
			F[i] = file.nextInt();
			U[i] = file.nextInt();
			if(F[i] < C[i]+U[i])
			{
				winnableStates++;
				winnableDelegates+=D[i];
				winnable[i] = true;
				int minToWin = Math.max(0,(F[i]+C[i]+U[i])/2 + 1 - C[i]);
				toWin[i] = minToWin;
				totalVotes += minToWin;
			}
			totalDelegates+=D[i];
		}
		int needed = totalDelegates/2 + 1;
		int slack = winnableDelegates - needed;
		if(slack< 0)
		{
			System.out.println("impossible");
			return;
		}
		int[] W = new int[winnableStates];
		int[] V = new int[winnableStates];
		int index = 0;
		for(int i = 0;i<winnable.length;i++)
		{
			if(winnable[i])
			{
				W[index] = D[i];
				V[index++] = toWin[i];
			}
		}
		int[][] dp = new int[V.length + 1][slack+1];
		for(int i = 1;i<dp.length;i++)
		{
			for(int j = 1;j<dp[i].length;j++)
			{
				dp[i][j] = dp[i-1][j];
				if(j >= W[i-1])
				{
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-W[i-1]] + V[i-1]);
				}
			}
		}
		System.out.println(totalVotes - dp[dp.length - 1][dp[0].length - 1]);
	}
}