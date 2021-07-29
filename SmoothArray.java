import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SmoothArray {
	
	public static void main(String[] args) throws IOException
	{
		int passed = 0;
		/*while(true)
		{
			passed++;
			if(passed%100==0)
				System.out.println(passed);
			String s = genCase();
			int a = new SmoothArray().run1(s);
			int b = run2(s);
			if(a!=b){
				System.out.println(s);
				System.out.println(a+" should be "+b);
				new Scanner(System.in).nextLine();
			}
			
		}*/
		new SmoothArray().run();
	}
	
	public static String genCase()
	{
		String ans = "";
		ans+="6 3 10";
		for(int i = 0;i<6;i++){
			ans+="\n";
			ans+=(int)(Math.random()*11);
		}
		return ans;
	}
	
	public static int run2(String input)
	{
		Scanner file = new Scanner((input));
		int N = file.nextInt();
		int K = file.nextInt();
		int S = file.nextInt();
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
		{
			ints[i] = file.nextInt();
		}
		int[][] occurrence = new int[S+1][K];
		for(int i = 0;i<ints.length;i++)
			occurrence[ints[i]][i%K]++;
		int[][] cost = new int[K][S+1];
		int extra = N%K;
		int usual = N/K;
		int[][] dp = new int[K+1][S+1];
		for(int i = 0;i<K;i++)
		{
			for(int j = 0;j<=S;j++)
			{
				if(K-i <= extra){
					cost[i][j] = usual+1-occurrence[j][i];
				}
				else{
					cost[i][j] = usual - occurrence[j][i];
				}
			}
		}
		for(int[] in:dp)
			Arrays.fill(in,10000);
		dp[0][0] = 0;
		for(int i = 1;i<dp.length;i++){
			for(int j = 0;j<dp[0].length;j++)
			{
				int index = i-1;
				int mod = index%K;
				for(int k = 0;k<=S;k++)
				{
					if(j>=k)
						dp[i][j] = Math.min(dp[i][j],dp[i-1][j-k]+cost[i-1][k]);
				}
			}
		}
		return (dp[dp.length-1][S]);
	}
	
	public int run1(String s)
	{
		Scanner file = new Scanner(s);
		int N = file.nextInt();
		int K = file.nextInt();
		int S = file.nextInt();
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
		{
			ints[i] = file.nextInt();
		}
		int[][] occurrence = new int[S+1][K];
		for(int i = 0;i<ints.length;i++)
			occurrence[ints[i]][i%K]++;
		int[][] cost = new int[K][S+1];
		int extra = N%K;
		int usual = N/K;
		int[][] dp = new int[K+1][S+1];
		int[][] min = new int[K+1][S+1];
		for(int i = 0;i<K;i++)
		{
			for(int j = 0;j<=S;j++)
			{
				if(K-i <= extra){
					dp[i+1][j] = usual+1+dp[i][j];
					cost[i][j] = usual+1-occurrence[j][i];
				}
				else{
					dp[i+1][j] = usual+dp[i][j];
					cost[i][j] = usual - occurrence[j][i];
				}
			}
		}
		for(int[] in:min)
			Arrays.fill(in, 10000);
		Arrays.fill(dp[0],10000);
		Arrays.fill(min[0],0);
		dp[0][0] = 0;
		for(int i = 1;i<dp.length;i++){
			for(int j = 0;j<dp[0].length;j++)
			{
				dp[i][j] =  min[i-1][j]+numGroups(i-1,K,N);
				min[i][j] = Math.min(min[i][j],dp[i][j]);
				int index = i-1;
				int mod = index%K;
				for(int k = mod;k<N;k+=K)
				{
					if(j>=ints[k])
						dp[i][j] = Math.min(dp[i][j],dp[i-1][j-ints[k]]+cost[i-1][ints[k]]);
					min[i][j] = Math.min(min[i][j],dp[i][j]);
				}
			}
		}
		return dp[dp.length-1][S];
	}
	
	public void run() throws IOException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int K = file.nextInt();
		int S = file.nextInt();
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
		{
			ints[i] = file.nextInt();
		}
		int[][] occurrence = new int[S+1][K];
		for(int i = 0;i<ints.length;i++)
			occurrence[ints[i]][i%K]++;
		int[][] cost = new int[K][S+1];
		int extra = N%K;
		int usual = N/K;
		int[][] dp = new int[K+1][S+1];
		int[][] min = new int[K+1][S+1];
		for(int i = 0;i<K;i++)
		{
			for(int j = 0;j<=S;j++)
			{
				if(K-i <= extra){
					cost[i][j] = usual+1-occurrence[j][i];
				}
				else{
					cost[i][j] = usual - occurrence[j][i];
				}
			}
		}
		for(int[] in:min)
			Arrays.fill(in, 10000);
		Arrays.fill(dp[0],10000);
		Arrays.fill(min[0],0);
		dp[0][0] = 0;
		for(int i = 1;i<dp.length;i++){
			for(int j = 0;j<dp[0].length;j++)
			{
				dp[i][j] =  min[i-1][j]+numGroups(i-1,K,N);
				min[i][j] = Math.min(min[i][j],dp[i][j]);
				if(j!=0)
					min[i][j] = Math.min(min[i][j],min[i][j-1]);
				int index = i-1;
				int mod = index%K;
				for(int k = mod;k<N;k+=K)
				{
					if(j>=ints[k])
						dp[i][j] = Math.min(dp[i][j],dp[i-1][j-ints[k]]+cost[i-1][ints[k]]);
					min[i][j] = Math.min(min[i][j],dp[i][j]);
				}
			}
		}
		//for(int[] in:min)
		//	System.out.println(Arrays.toString(in));
		//System.out.println();
		//for(int[] in:dp)
		//	System.out.println(Arrays.toString(in));
		System.out.println(dp[dp.length-1][S]);
	}
	
	public int numGroups(int i, int k, int N)
	{
		int usual = N/k;
		int extra = N%k;
		if(k-i<=extra)
			return usual+1;
		return usual;
	}
	
}
