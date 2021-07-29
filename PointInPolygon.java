import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class PointInPolygon {
	
	public static void main(String[] args) throws IOException
	{
		new PointInPolygon().run();
	}
	
	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		while(N!=0)
		{
			int[] x = new int[N];
			int[] y = new int[N];
			for(int i = 0;i<N;i++)
			{
				st = new StringTokenizer(file.readLine());
				x[i] = Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st.nextToken());
			}
			Polygon p = new Polygon(x,y,N);
			int M = Integer.parseInt(file.readLine());
			line[] lines = new line[N];
			for(int i = 0;i<lines.length;i++)
			{
				int x1 = x[i];
				int x2 = x[(i+1)%N];
				int y1 = y[i];
				int y2 = y[(i+1)%N];
				lines[i] = new line(x1,y1,x2,y2);
			}
		loop:
			for(int i = 0;i<M;i++)
			{
				st = new StringTokenizer(file.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				for(line l:lines)
					if(l.contains(X, Y))
					{
						System.out.println("on");
						continue loop;
					}
				boolean cont = p.contains(X, Y);
				if(cont)
					System.out.println("in");
				else
					System.out.println("out");
			}
			N = Integer.parseInt(file.readLine());
		}
	}
	
	private class line
	{
		int x1, x2, y1, y2;
		public line(int x1, int y1, int x2, int y2)
		{
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}
		
		public boolean contains(int x, int y)
		{
			if(x==x1&&y==y1||x==x2&&y==y2)
				return true;
			int minx = Math.min(x1,x2);
			int maxx = Math.max(x1,x2);
			int miny = Math.min(y1,y2);
			int maxy = Math.max(y1,y2);
			if(x<minx||x>maxx||y<miny||y>maxy)
				return false;
			frac a = new frac(y2-y1,x2-x1);
			frac b = new frac(y2-y,x2-x);
			return a.equals(b);
			//return dist(x,y,x1,y1)+dist(x,y,x2,y2)==dist(x1,y1,x2,y2);
		}
		
		public int dist(int x1, int y1, int x2, int y2)
		{
			return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
		}
	}
	
	class frac
	{
		int num;
		int denom;
		public frac(int n, int d)
		{
			num = n;
			denom = d;
		}
		
		public void reduce()
		{
			int gcf = new BigInteger(num+"").gcd(new BigInteger(""+denom)).intValue();
			num/=gcf;
			denom/=gcf;
		}
		
		public boolean equals(frac f)
		{
			if(num==0&&f.num==0)
				return true;
			if(denom==0&&f.denom==0)
				return true;
			reduce();
			f.reduce();
			return num==f.num&&denom==f.denom;
		}
	}
}
