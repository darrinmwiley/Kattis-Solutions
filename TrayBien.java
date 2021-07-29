

import java.util.Arrays;
import java.util.Scanner;

public class TrayBien {

	long[][] dp;
	boolean[][] spots;

	public static void main(String[] args) throws Exception
	{
		new TrayBien().run();
	}

	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int M = file.nextInt();
		int N = file.nextInt();
		int[] badrows = new int[N];
		int[] badcols = new int[N];
		for(int i = 0;i<N;i++)
		{
			badrows[i] = (int)(file.nextDouble());
			badcols[i] = (int)(file.nextDouble());
		}
		//System.out.println(Arrays.toString(badrows));
		//System.out.println(Arrays.toString(badcols));
		spots = new boolean[M+1][3];
		for(int i = 0;i<N;i++)
		{
			spots[badrows[i]][badcols[i]] = true;
		}
		int[][] trans = new int[][]{
			{3,2,1,1,2,1,1,1},
			{2,0,1,0,1,0,1,0},
			{1,1,0,0,1,1,0,0},
			{1,0,0,0,1,0,0,0},
			{2,1,1,1,0,0,0,0},
			{1,0,1,0,0,0,0,0},
			{1,1,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0},
		};
		dp = new long[M+1][8];
		int start = getStartState();
		dp[0][start] = 1;
		//System.out.println(start);
		//for(boolean[] in:spots)
		//	System.out.println(Arrays.toString(in));
		for(int i = 0;i<dp.length-1;i++)
		{
			for(int j = 0;j<8;j++)
			{
				for(int k = 0;k<8;k++)
				{
					if(canTransition(i,k))
					{
						//if(i==1&&j==6&&k==1)
						//	System.out.println("here");
						int after = getStateAfterTransition(i,k);
						//if(dp[i][j]!=0&&trans[j][k]!=0)
							//System.out.println(i+" "+j+" "+k+" "+after+" "+dp[i][j]+"*"+trans[j][k]);
						dp[i+1][after]+=dp[i][j]*trans[j][k];
					}
				}
			}
		}
		//for(int i = 0;i<dp.length;i++)
		//	System.out.println(Arrays.toString(dp[i]));
		System.out.println(dp[dp.length-1][0]);
	}

	public int getStartState()
	{
		int sum = 0;
		if(spots[0][0])
			sum++;
		if(spots[0][1])
			sum+=2;
		if(spots[0][2])
			sum+=4;
		return sum;
	}

	public boolean canTransition(int index, int state)
	{
		String b = Integer.toBinaryString(state);
		while(b.length()!=3)
			b = "0"+b;
		b = new StringBuffer(b).reverse().toString();
		//if(index==1&&state==1)
		//	System.out.println(b);
		char[] bin = b.toCharArray();
		for(int i = 0;i<3;i++)
		{
			if(spots[index+1][i] && bin[i]=='1' )
			{
				//System.out.print(spots[index+1][i]);
				return false;
			}
		}
		return true;
	}

	public int getStateAfterTransition(int index, int state)
	{
		String b = Integer.toBinaryString(state);
		while(b.length()!=3)
			b = "0"+b;
		b = new StringBuffer(b).reverse().toString();
		char[] bin = b.toCharArray();
		int ret = 0;
		for(int i = 2;i>=0;i--)
		{
			ret*=2;
			if(spots[index+1][i] || bin[i]=='1' )
				ret++;
		}
		return ret;
	}
}
