import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class goodcoalition {
	
	StringTokenizer st;
	BufferedReader file;
	PrintWriter pout;
	
	public static void main(String[] args) throws Exception
	{
		new goodcoalition().run();
	}	
	
	double epsilon = 0;
	
	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		
		PrintWriter pout = new PrintWriter(System.out);
		
		int zz = nextInt();
		for(int z = 0;z<zz;z++)
		{
			double[] dp = new double[151];
			boolean[] poss = new boolean[151];
			poss[0] = true;
			int N = nextInt();
			int[] val = new int[N];
			double[] prob = new double[N];
			Arrays.fill(dp, -1000);
			dp[0] = 0;
			for(int i = 0;i<N;i++)
			{
				val[i] = nextInt();
				prob[i] = Math.log(nextInt()/100.0);
			}
			for(int i = 0;i<N;i++)
			{
				for(int j = 151;j>=val[i];j--)
				{
					if(poss[j - val[i]])
					{
						poss[j] = true;
						dp[j] = Math.max(dp[j], dp[j - val[i]] + prob[i]);
					}
				}
			}
			double max = -1000;
			for(int i = 76;i<=150;i++)
			{
				max = Math.max(max, dp[i]);
			}
			//System.out.println(Arrays.toString(dp));
			System.out.println(Math.exp(max)*100);
		}	
		
		pout.flush();
		pout.close();
	}
	
	public void newst()
	{
		try {
			st = new StringTokenizer(file.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readLine() throws IOException
	{
		return file.readLine();
	}
	
	public String next()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return st.nextToken();
	}
	
	public int nextInt()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return Integer.parseInt(st.nextToken());
	}
	
	public long nextLong()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return Long.parseLong(st.nextToken());
	}
	
}