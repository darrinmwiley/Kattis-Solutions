import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class crophard {

	long[] x;
	long[] y;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		//System.out.println(mod3(-2));
		new crophard().run();
	}

	public void run() throws NumberFormatException, IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(new File("asdf.txt")));
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int zz = Integer.parseInt(file.readLine());
		for(int z = 0;z<zz;z++)
		{
			StringTokenizer st = new StringTokenizer(file.readLine());
			int N = Integer.parseInt(st.nextToken());
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			long C = Long.parseLong(st.nextToken());
			long D = Long.parseLong(st.nextToken());
			long x0 = Long.parseLong(st.nextToken());
			long y0 = Long.parseLong(st.nextToken());
			long M = Long.parseLong(st.nextToken());
			x = new long[N];
			y = new long[N];
			x[0] = x0;
			y[0] = y0;
			for(int i = 1;i<N;i++)
			{
				x0 = (A*x0+B) % M;
				y0 = (C*y0+D) % M;
				x[i] = x0;
				y[i] = y0;
			}
			//#points, sum of x mod 3, sum of y mod 3
			long[][][] dp = new long[4][3][3];
			dp[0][0][0] = 1;
			for(int q = 0;q<N;q++)
			{
				for(int i = 3;i>=1;i--)
				{
					for(int j = 2;j>=0;j--)
					{
						for(int k = 2;k>=0;k--)
						{
							int xm = mod3(x[q]);
							int ym = mod3(y[q]);
							int xb = ((j-xm)+3)%3;
							int yb = ((k-ym)+3)%3;
							dp[i][j][k]+=dp[i-1][xb][yb];
						}
					}
				}
			}
			//System.out.println(Arrays.toString(x));
			//System.out.println(Arrays.toString(y));
			System.out.printf("Case #%d: %d%n",z+1,dp[3][0][0]);
		}
		out.close();
	}

	public static int mod3(long N)
	{
		if(N>=0)
			return (int)(N%3);
		return (int)((3+(N%3))%3);
	}

}
