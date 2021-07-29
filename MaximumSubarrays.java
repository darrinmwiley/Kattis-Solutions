import java.util.Arrays;
import java.util.Scanner;

public class MaximumSubarrays {

	static long INF = 1000000000000000L;

	public static void main(String[] args)
	{
		new MaximumSubarrays().run();
	}

	public void run()
	{
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
		{
			arr[i] = in.nextInt();
		}
		long[][] dp = new long[K+1][2];
		for (long[] ar : dp)
		{
			Arrays.fill(ar, -INF-1000);
		}
		dp[0][0] = 0;
		dp[0][1] = 0;
		for (int i = arr.length-1; i >= 0; i--)
		{
			long[][] oldDp = new long[K+1][2];
			for (int k = 0; k <= K; k++)
			{
				for (int s = 0; s < 2; s++)
				{
					oldDp[k][s] = dp[k][s];
				}
			}
			dp[0][0] = 0;
			dp[0][1] = 0;
			for (int k = 1; k <= K; k++)
			{
				dp[k][0] = Math.max(arr[i] + oldDp[k][1], Math.max(arr[i] + oldDp[k-1][0], oldDp[k][0]));
				dp[k][1] = Math.max(arr[i] + oldDp[k][1], Math.max(arr[i] + oldDp[k-1][0], oldDp[k-1][0]));
			}
		}
		System.out.println(dp[K][0]);
	}

	public long kmss(int k, int i, boolean inside, int[] arr, long[][][] dp)
	{
		if (k == 0)
		{
			return 0;
		}
		if (i == arr.length && k > 0)
		{
			return -INF;
		}
		if (dp[k][i][inside ? 1 : 0] != -INF-1000)
		{
			return dp[k][i][inside ? 1 : 0];
		}
		long max = arr[i] + kmss(k, i+1, true, arr, dp);
		max = Math.max(max, arr[i] + kmss(k-1, i+1, false, arr, dp));
		if (inside)
		{
			max = Math.max(max, kmss(k-1, i+1, false, arr, dp));
		}
		else
		{
			max = Math.max(max, kmss(k, i+1, false, arr, dp));
		}
		dp[k][i][inside ? 1 : 0] = max;
		return max;
	}
}
