import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

class WeightOfWords
{

	public static void main (String[] args) throws java.lang.Exception
	{
		new WeightOfWords().solve();
	}

	public void solve() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int K = file.nextInt();
		boolean[][] dp = new boolean[N+1][K+1];
		dp[0][0] = true;
		for(int i = 0;i<dp.length-1;i++)
		{
			for(int j = 0;j<dp[i].length;j++)
			{
				if(dp[i][j])
				{
					for(int k = 0;k<26;k++)
					{
						if(j+k+1<dp[i].length)
						{
							dp[i+1][j+k+1] = true;
						}
					}
				}
			}
		}
		int r = dp.length - 1;
		int c = dp[r].length - 1;
		String ans ="";
		if(!dp[r][c])
		{
			System.out.println("impossible");
			return;
		}
	loop:
		while(r != 0)
		{
			for(int i = 0;i<26;i++)
			{
				if(c-i-1 >= 0 && dp[r-1][c-i-1])
				{
					ans = (char)('a'+i) + ans;
					r--;
					c-=i+1;
					continue loop;
				}
			}
		}
		System.out.println(ans);
	}
}
