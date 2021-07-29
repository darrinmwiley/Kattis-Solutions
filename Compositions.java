import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Compositions
{
	
	public static void main (String[] args) throws java.lang.Exception
	{
		new Compositions().solve();
	}
	
	public void solve() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			file.nextInt();
			int N = file.nextInt();
			int M = file.nextInt();
			int K = file.nextInt();
			ArrayList<Integer> options = new ArrayList<Integer>();
			for(int i = 1;i<=N;i++)
			{
				options.add(i);
			}
			for(int i = 0;i<30;i++)
			{
				int x = M+i*K;
				if(x > N)
					break;
				options.remove(new Integer(x));
			}
			int[] dp = new int[N+1];
			dp[0] = 1;
			for(int i = 1;i<dp.length;i++)
			{
				for(int x: options)
					if(i>=x)
						dp[i] += dp[i-x];
			}
			System.out.println(z+1+" "+dp[N]);
		}
	}
}