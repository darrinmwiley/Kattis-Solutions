import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Recon {
	
	public static void main(String[] args) throws Exception
	{
		new Recon().run();		
	}
	
	int N;
	int[] pos;
	int[] vel;
	
	public void run() throws Exception
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(file.readLine());
		StringTokenizer st;
		pos = new int[N];
		vel = new int[N];
		for(int i = 0;i<N;i++)
		{
			st = new StringTokenizer(file.readLine());
			pos[i] = Integer.parseInt(st.nextToken());
			vel[i] = Integer.parseInt(st.nextToken());
		}
		double R = 1000000;
		double L = 0;
		double M = (L+R)/2;
		double best = 100000;
		for(int i = 0;i<1000;i++)
		{
			M = (L+R)/2;
			double dist = getDistance(M);
			double dist2 = getDistance(M+.0000001);
			if(dist == dist2)
			{
				System.out.println(dist);
				return;
			}
			if(dist<dist2)
			{
				R = M;
			}else {
				L = M;
			}
		}
		System.out.println(getDistance(M));
	}
	
	public double getDistance(double time)
	{
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		for(int i = 0;i<N;i++)
		{
			double x = pos[i] + vel[i]*time;
			min = Math.min(min,x);
			max = Math.max(max,x);
		}
		//System.out.println(time+" "+max+" "+min);
		return max - min;
	}
}
