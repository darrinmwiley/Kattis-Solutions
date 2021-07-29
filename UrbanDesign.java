import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class UrbanDesign {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		new UrbanDesign().run();
	}

	public void run() throws FileNotFoundException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		LineInf[] lines = new LineInf[N];
		for(int i = 0;i<N;i++)
		{
			LineInf x = new LineInf(file.nextInt(),file.nextInt(),file.nextInt(),file.nextInt());
			lines[i] = x;
		}
		int M = file.nextInt();
		for(int i = 0;i<M;i++)
		{
			int x1 = file.nextInt();
			int y1 = file.nextInt();
			int x2 = file.nextInt();
			int y2 = file.nextInt();
			int ct = 0;
			for(LineInf li:lines)
			{
				double[] nt = li.segInt(x1,y1,x2,y2);
				if(nt!=null)
					ct++;
			}
			if(ct%2==0)
				System.out.println("same");
			else
				System.out.println("different");
		}
	}
	
	public class LineInf {
		double A,B,C;//want line in form Ax+By = C
		
		public LineInf(double x1, double y1, double x2, double y2)
		{
			A = y2-y1;
			B = x1-x2;
			C = A*x1+B*y1;
		}
		
		private LineInf(double A, double B, double C)
		{
			this.A = A;
			this.B = B;
			this.C = C;
		}
		
		public double det(LineInf li)
		{
			return A*li.B-li.A*B;
		}
		
		public double[] intersection(LineInf li)
		{
			double det = det(li);
			if(det==0) return null; //zero or infinite solutions
			double x = (li.B*C-B*li.C)/det;
			double y = (A*li.C-li.A*C)/det;
			return new double[]{x,y};
		}
		
		public double dist(double x, double y)
		{
			LineInf perp = new LineInf(-B,A,-B*x+A*y);
			double[] z = intersection(perp);
			return Math.sqrt(Math.pow(x-z[0],2)+Math.pow(y-z[1],2));
		}
		
		//x1,y1,x2,y2 are the segment
		public double[] segInt(double x1, double y1, double x2, double y2,double x3, double y3, double x4, double y4)
		{
			double[] z = new LineInf(x1,y1,x2,y2).intersection(new LineInf(x3,y3,x4,y4));
			double ax = Math.sqrt(Math.pow(x1-z[0], 2)+Math.pow(y1-z[1], 2));
			double xb = Math.sqrt(Math.pow(x2-z[0], 2)+Math.pow(y2-z[1], 2));
			double ab = Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
			return Math.abs(ax+xb-ab)<.00001?z:null;
		}
		
		public double[] segInt(double x1, double y1, double x2, double y2)
		{
			double[] z = new LineInf(x1,y1,x2,y2).intersection(this);
			if(z==null)
				return null;
			double ax = Math.sqrt(Math.pow(x1-z[0], 2)+Math.pow(y1-z[1], 2));
			double xb = Math.sqrt(Math.pow(x2-z[0], 2)+Math.pow(y2-z[1], 2));
			double ab = Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
			return Math.abs(ax+xb-ab)<.00001?z:null;
		}
		
		public boolean ccw(double x1, double y1, double x2, 
							double y2, double x3, double y3)
		{
			return (y3-y1)*(x2-x1)>(y2-y1)*(x3-x1);
		}
	}
	
}
