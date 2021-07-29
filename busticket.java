import java.util.*;

import javax.print.attribute.SetOfIntegerSyntax;

import java.io.*;

public class busticket{
	
	BufferedReader file;
	PrintWriter pout;
	StringTokenizer st;
	
	public static void main(String[] args) throws Exception
	{
		new busticket().run();
	}
	
	long[] ints;
	
	public void run() throws Exception{
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);
		st = new StringTokenizer(file.readLine());
		long s = Long.parseLong(st.nextToken());
		long p = Long.parseLong(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		ints = new long[n];
		st = new StringTokenizer(file.readLine());
		for(int i = 0;i<n;i++)
		{
			ints[i] = Long.parseLong(st.nextToken());
		}
		long[] dp = new long[n+1];
		dp[0] = 0;
		for(int i = 1;i<dp.length;i++)
		{
			long singleCost = dp[i-1] + s;
			long firstCovered = ints[i-1] - m + 1;
			int floorIndex = floorIndex(firstCovered);
			long periodCost = dp[floorIndex + 1] + p;
			dp[i] = Math.min(singleCost, periodCost);
		}
		//System.out.println(Arrays.toString(dp));
		System.out.println(dp[dp.length-1]);
	}
	
	public int floorIndex(long x)
	{
		int L = -1;
		int R = ints.length;
		int M = (L+R)/2;
		while(R-L > 1)
		{
			M = (L+R)/2;
			long c = ints[M];
			if(c < x)
				L = M;
			else
				R = M;
		}
		return L;
	}
	
	
	
}