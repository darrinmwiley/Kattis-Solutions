package page;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class Rectangular {
	
	int MOD = 1000000007;
	
	public static void main(String[] args) throws Exception {
		new Rectangular().run();	
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int R = file.nextInt();
		int C = file.nextInt();
		int K = file.nextInt();	
		
		//we need number of ways to choose N values from K choices such that at least one is x
		
		//equal to (number of ways to choose N values) - (number of ways to choose N values such that none are x)
        // K^N - (K-1)^N
		
		long[] pows = new long[5001];
		for(int i = 1;i<pows.length;i++)
			pows[i] = (modpow(i,N)+MOD-modpow(i-1,N))%MOD;
		
        long[] dp = new long[5002];
        for(int i = 2;i<=5001;i++)
        {
        	for(int j = 1;j<i;j++)
        	{
        		dp[i] += pows[j] * pows[i-j];
        		dp[i] %= MOD;
        	}
        }
		
		long ans = 0;
		for(int wid = 1;wid<=C;wid++)
		{
			int minHt = (int)(Math.ceil(K/(wid+0.0)));
			for(int ht = minHt;ht<=R;ht++)
			{
				int horzChoices = R + 2 - ht;
				int vertChoices = C + 2 - wid;
				long horzWays = dp[horzChoices];
				long vertWays = dp[vertChoices];
				ans += horzWays * vertWays;
				ans %= MOD;
			}
		}
		System.out.println(ans);
	}
	
	long modpow(long a, long b) {
		if (b == 0) return 1;
		return modpow(a*a%MOD, b/2) * ((b&1)==1?a:1) % MOD;
	}
}
