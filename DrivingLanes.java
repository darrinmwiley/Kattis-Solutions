import java.util.Arrays;
import java.util.Scanner;

public class DrivingLanes {
	public static void main(String[] args) {
		new DrivingLanes().run();
	}
	
	public void run()
	{
		//LANES ARE 1 BASED
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		long K = file.nextLong();
		long r = file.nextLong();
		long[] straight = new long[N];
		for(int i = 0;i<straight.length;i++)
			straight[i] = file.nextInt();
		long[] curveS = new long[N-1];
		long[] curveC = new long[N-1];
		for(int i = 0;i<N-1;i++) {
			curveS[i] = file.nextInt();
			curveC[i] = file.nextInt();
		}
		long[][] dp = new long[N*2-1][M];
		for(int i = 0;i<dp.length;i++)
		{
			Arrays.fill(dp[i],Long.MAX_VALUE/4);
		}
		dp[0][0] = straight[0];
		int X = (int)(straight[0]/K);
		for(int k = 1;k<=X;k++)
		{
			if(k<dp[0].length)
				dp[0][k] = straight[0]+k*r;
		}
		for(int i = 0;i<dp.length;i++)
		{
			for(int j = 0;j<dp[i].length;j++)
			{
				if(i%2==1)
				{
					dp[i][j] = dp[i-1][j] + curveS[i/2]+curveC[i/2]*(j+1);
				}else {
					if(i != 0) {
						dp[i][j] = Math.min(dp[i][j],dp[i-1][j])+straight[i/2];
						int allowed = (int)(straight[i/2]/K);
						for(int k = 1;k<=allowed;k++)
						{
							if(j+k<dp[i].length)
								dp[i][j] = Math.min(dp[i][j],dp[i-1][j+k]+k*r+straight[i/2]);
							if(j-k>=0)
								dp[i][j] = Math.min(dp[i][j],dp[i-1][j-k]+k*r+straight[i/2]);
						}
					}
				}
			}
		}
		System.out.println(dp[dp.length-1][0]);
	}
}
