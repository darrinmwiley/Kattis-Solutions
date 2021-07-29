import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class TrainAddiction {
	
	public static void main(String[] args) throws Exception
	{
		new TrainAddiction().run();
	}
	
	BufferedReader file;
	StringTokenizer st;
	
	long MOD = 1000000007;
	int initial_size = 50;
	
/*
1
5 3 1 2 3	
 */

	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(file.readLine());
		for(int z = 0;z<T;z++)
		{
			st = new StringTokenizer(file.readLine());
			int X = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int[] in = new int[N];
			for(int i = 0;i<N;i++)
				in[i] = Integer.parseInt(st.nextToken());
			long[][] mat = new long[initial_size][initial_size];
			for(int i = 0;i<N;i++)
			{
				mat[mat.length - 1][mat.length - in[i]]++;
			}
			for(int i = 1;i<initial_size;i++)
				mat[i-1][i] = 1;
			long[][] dp = new long[initial_size][1];
			dp[0][0] = 1;
			for(int i = 1;i<dp.length;i++)
				for(int x: in)
					if(x <= i) {
						dp[i][0] += dp[i-x][0];
						dp[i][0] %= MOD;
					}
			long[][] exp = expo(mat, X);
			long[] ans = mult(exp, dp)[0];
			long ret = (ans[0]);
			if(ret == 0)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(ret);
		}
	}
	
	public long[][] expo(long[][] a, int x)
	{
		long[][] ret = new long[a.length][a.length];
		for(int i = 0;i<a.length;i++)
			ret[i][i] = 1;
		while(x != 0)
		{
			if(x%2 == 1)
				ret = mult(ret, a);
			x /= 2;
			a = mult(a, a);
		}
		return ret;
	}
	
	public long[][] clone(long[][] x)
	{
		long[][] ret = new long[x.length][x.length];
		for(int i = 0;i<ret.length;i++)
			ret[i] = x[i].clone();
		return ret;
	}
	
	public long[][] mult(long[][] a, long[][] b)
	{
		long[][] c = new long[a.length][b[0].length];
		for(int i = 0;i<c.length;i++)
		{
			for(int j = 0;j<c[i].length;j++)
			{
				for(int k = 0;k<a[i].length;k++)
				{
					c[i][j] += a[i][k] * b[k][j];
					c[i][j] %= MOD;
				}
			}
		}
		return c;
	}
	
}
