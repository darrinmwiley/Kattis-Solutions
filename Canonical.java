import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

class Canonical
{
	
	public static void main (String[] args) throws java.lang.Exception
	{
		new Canonical().solve();
	}
	
	public void solve() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int[] ints = new int[N];
		for(int i = 0;i<ints.length;i++)
		{
			ints[i] = file.nextInt();
		}
		int max = ints[ints.length - 1] + ints[ints.length - 2];
		int[] dp = new int[max];
		int[] greedy = new int[max];
		TreeSet<Integer> set = new TreeSet<Integer>();
		for(int x: ints)
			set.add(x);
		Arrays.fill(dp, Integer.MAX_VALUE/4);
		dp[0] = 0;
		for(int i = 1;i<dp.length;i++)
		{
			greedy[i] = greedy[i - set.floor(i)] + 1;
			for(int j: ints)
			{
				if(i >= j)
				{
					dp[i] = Math.min(dp[i-j]+1, dp[i]);
				}
			}
		}
		for(int i = 0;i<dp.length;i++)
		{
			if(dp[i] != greedy[i])
			{
				System.out.print("non-");
				break;
			}
		}
		System.out.println("canonical");
	}
}