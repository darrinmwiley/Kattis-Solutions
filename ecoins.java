import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ecoins {
	
	static int[][] dp;
	
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int N = file.nextInt();
			int G = file.nextInt();
			int[] a = new int[N];
			int[] b = new int[N];
			for(int i = 0;i<N;i++)
			{
				int x = file.nextInt();
				a[i] = x;
				int y = file.nextInt();
				b[i] = y;
			}
			dp = new int[G+1][G+1];
			for(int[] x:dp)
				Arrays.fill(x,Integer.MAX_VALUE/2);
			dp[0][0] = 0;
			for(int i = 0;i<G+1;i++)
			{
				for(int j = 0;j<G+1;j++)
				{
					for(int k = 0;k<N;k++)
					{
						if(i>=a[k]&&j>=b[k])
							dp[i][j] = Math.min(dp[i][j],1+dp[i-a[k]][j-b[k]]);
					}
				}
			}
			G*=G;
			boolean[] sq = new boolean[G+1];
			for(int i = 0;i*i<sq.length;i++)
				sq[i*i] = true;
			int min = Integer.MAX_VALUE;
			for(int i = 0;i<=G;i++)
			{
				if(sq[i]&&sq[G-i])
				{
					int s1 = (int)(Math.round(Math.sqrt(i)));
					int s2 = (int)(Math.round(Math.sqrt(G-i)));
					min = Math.min(min,dp[s1][s2]);
				}
			}
			//System.out.println(dp[3][4]);
			if(min>1000000)
				System.out.println("not possible");
			else
				System.out.println(min);
		}
	}
}
