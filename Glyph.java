import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Glyph {
	
	int N;
	int[] x;
	int[] y;
	double[] cos = new double[9];
	double[] sin = new double[9];
	double root3 = Math.sqrt(3);
	double epsilon = .0000005;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new Glyph().run();		
	}
	
	public void run() throws IOException
	{
		for(int i = 3;i<=8;i++)
		{
			cos[i] = Math.cos(Math.toRadians(360.0/i));
			sin[i] = Math.sin(Math.toRadians(360.0/i));
		}
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(file.readLine());
		x = new int[N];
		y = new int[N];
		StringTokenizer st;
		for(int i = 0;i<N;i++)
		{
			st = new StringTokenizer(file.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		int best = -1;
		double score = 0;
		for(int i = 3;i<=8;i++)
		{
			double inner = innerR(i);
			double outer = outerR(i);
			double ratio = inner*inner/(outer*outer);
			if(ratio > score)
			{
				best = i;
				score = ratio;
			}
		}
		System.out.println(best+" "+score);
	}

/*
9
-4 -1
-4 4
-3 -6
-3 4
0 -4
2 -3
2 3
5 1
7 0
 */
	
/*
4
1 0
0 1
-1 0
0 -1
 */
	
	public double outerR(int P)
	{
		double L = 0;
		double R = 2000000000;
		double M = (L+R)/2;
		while(R-L>epsilon)
		{
			M = (L+R)/2;
			int contained = countContained(P, M);
			if(contained == N)
				R = M;
			else
				L = M;
		}
		return M;
	}
	
	public double innerR(int P)
	{
		double L = 0;
		double R = 2000000000;
		double M = (L+R)/2;
		while(R-L>epsilon)
		{
			M = (L+R)/2;
			int contained = countContained(P, M);
			if(contained == 0)
				L = M;
			else
				R = M;
		}
		return M;
	}
	
	public int countContained(int P, double R)
	{
		Area a = makeShape(P,R);
		int ret = 0;
		for(int i = 0;i<N;i++)
		{
			if(a.contains(x[i],y[i]))
				ret++;
		}
		return ret;
	}
	
	public Area makeShape(int P, double R)
	{
		double[] xp = new double[P];
		double[] yp = new double[P];
		double X = R;
		double Y = 0;
		for(int i = 0;i<P;i++)
		{
			xp[i] = X;
			yp[i] = Y;
			double Xp = X*cos[P] - Y*sin[P];
			double Yp = Y*cos[P] + X*sin[P];
			X = Xp;
			Y = Yp;
		}
		return makeArea(xp,yp);
	}

	public static Area makeArea(double[] xp, double[] yp) {
		Path2D path = new Path2D.Double(); path.moveTo(xp[0], yp[0]); 
		for(int i = 1;i<xp.length;i++)
			path.lineTo(xp[i], yp[i]); path.closePath();
		return new Area(path);
	}
}
