import java.util.*;

import javax.print.attribute.SetOfIntegerSyntax;

import java.io.*;

public class fencebowling{
	
	BufferedReader file;
	PrintWriter pout;
	StringTokenizer st;
	
	double hpi = Math.PI/2;
	double epsilon = .000000001;
	
	public static void main(String[] args) throws Exception
	{
		new fencebowling().run();
	}
	
	long[] ints;
	
	public void run() throws Exception{
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);
		st = new StringTokenizer(file.readLine());
		int K = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		double ans = solve(K,L,W);
		System.out.println(Math.toDegrees(ans));
	}
	
	public double solve(int K, double len, double wid)
	{
		double L = 0;
		double R = hpi;
		//R = Math.toRadians(89.14062775635531)*2;//hpi;
		R = Math.toRadians(89.71352352295071 * 2);
		double M = (L+R)/2;
		while(R-L > epsilon)
		{
			M = (L+R)/2;
			ans x = countBounce(K, len, wid, M);
			int bounce = x.bounce;
			double remaining = x.remaining;
			double theta = x.theta;
			//System.out.print(Math.toDegrees(M)+" "+bounce+" "+remaining+" "+theta);
			if(bounce < K)
			{
				R = M;
				//System.out.println(" down");
				continue;
			}
			if(bounce > K)
			{
				L = M;
				//System.out.println(" up");
				continue;
			}
			double bw = bounceWidth(theta, remaining);
			//System.out.print(" "+bw);
			if(bw < wid/2)
			{
				R = M;
				//System.out.println(" down");
				continue;
			}
			if(bw > wid/2)
			{
				L = M;
				//System.out.println(" up");
				continue;
			}
		}
		return M;
	}
	
	public double bounceWidth(double theta, double remaining)
	{
		return (remaining*Math.sin(hpi-theta))/Math.sin(theta);
	}
	
	public double bounceLength(double theta, double wid, boolean flag)
	{
		if(flag)
		{
			return ((wid/2) * Math.sin(theta))/Math.sin(hpi-theta);
		}
		return (wid * Math.sin(theta))/Math.sin(hpi-theta);
	}
	
	public ans countBounce(int K, double len, double wid, double theta)
	{
		double remaining = len;
		double currentTheta = theta;
		int B = 0;
		while(B != K+1)
		{
			double bl = bounceLength(currentTheta, wid, B == 0);
			if(bl >= remaining) {
				return new ans(B, remaining, currentTheta);
			}
			else {
				B++;
				currentTheta = Math.atan(2*Math.tan(currentTheta));
				remaining -= bl;
			}
		}
		return new ans(B, -1, -1);
	}
	
	private class ans{
		
		int bounce;
		double remaining;
		double theta;
		
		public ans(int b, double r, double t)
		{
			bounce = b;
			remaining = r;
			theta = t;
		}
		
	}
	
	
}