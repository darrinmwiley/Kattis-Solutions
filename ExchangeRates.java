import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ExchangeRates {

	static int[][] dp;

	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		while(N!=0)
		{
			double[] rates = new double[N];
			for(int i = 0;i<N;i++)
				rates[i] = file.nextDouble();
			int[][] dp = new int[N+1][2];
			dp[0][0] = 100000;
			for(int i = 1;i<N+1;i++)
			{
				dp[i][0] = dp[i-1][0];
				dp[i][1] = dp[i-1][1];
				dp[i][0] = Math.max(dp[i][0],(int)Math.floor(dp[i][1]*rates[i-1]*.97));
				dp[i][1] = Math.max(dp[i][1],(int)Math.floor(dp[i][0]/rates[i-1]*.97));
			}
			System.out.printf("%.2f%n",dp[N][0]/100.0);
			N = file.nextInt();
		}
	}
}
