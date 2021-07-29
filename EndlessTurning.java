import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EndlessTurning {

	static int numPoints;

	public static void main(String[] args)
	{
		new EndlessTurning().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int R = file.nextInt();
		long N = file.nextLong();
		int X = file.nextInt();
		int Y = file.nextInt();
		LineInf[] lines = new LineInf[R];
		for(int i = 0;i<R;i++)
		{
			lines[i] = new LineInf(file.next(),file.nextInt(),file.nextInt(),file.nextInt(),file.nextInt());
			for(int j = 0;j<i;j++)
			{
				pt intersect = lines[i].intersection(lines[j]);
				if(intersect!=null)
				{
					lines[i].intersections.add(intersect);
					lines[j].intersections.add(intersect);
				}
			}
		}
		for(LineInf l:lines)
			Collections.sort(l.intersections);
		LineInf start = null;
		for(LineInf l:lines)
			if(l.contains(X, Y))
				start = l;
		if(N==0)
		{
			System.out.println(start.name);
			return;
		}
		ArrayList<pt> pts = new ArrayList<pt>();
		boolean[] vis = new boolean[numPoints];
		pt p = new pt(X,Y,start,null);
		pt firstIntersection = start.next(p);
		if(firstIntersection == null)
		{
			System.out.println(start.name);
			return;
		}
		vis[firstIntersection.id] = true;
		pts.add(p);
		pts.add(firstIntersection);
		int turns = 1;
		while(turns!=N)
		{
			pt current = pts.get(pts.size()-1);
			pt prev = pts.get(pts.size()-2);
			pt next = getNextIntersection(prev,current);
			if(next==null)
			{
				LineInf ln = current.a;
				if(prev.a==ln||prev.b==ln)
					ln = current.b;
				System.out.println(ln.name);
				return;
			}
			if(vis[next.id])
			{
				pts.remove(0);
				pt last = pts.get((int)((N-1)%pts.size()));
				pt secondToLast = pts.get((int)((N+pts.size()-2)%pts.size()));
				LineInf ln = last.a;
				if(secondToLast.a==ln||secondToLast.b==ln)
					ln = last.b;
				System.out.println(ln.name);
				return;
			}
			pts.add(next);
			vis[next.id] = true;
			turns++;
		}
		pt current = pts.get(pts.size()-1);
		pt prev = pts.get(pts.size()-2);
		LineInf ln = current.a;
		if(prev.a==ln||prev.b==ln)
			ln = current.b;
		System.out.println(ln.name);
	}

	public static boolean cw(double x1, double y1, double x2, double y2, double x3, double y3)
	{
		return (y3-y1)*(x2-x1)<(y2-y1)*(x3-x1);
	}

	public pt getNextIntersection(pt prev, pt current)
	{
		LineInf ln = current.a;
		if(prev.a==ln||prev.b==ln)
			ln = current.b;
		pt x = ln.next(current);
		pt y = ln.prev(current);
		if(x!=null&&cw(prev.x,prev.y,current.x,current.y,x.x,x.y))
			return x;
		if(y!=null&&cw(prev.x,prev.y,current.x,current.y,y.x,y.y))
			return y;
		return null;
	}

	private class pt implements Comparable<pt>{
		double x, y;
		LineInf a, b;
		int id;
		public pt(double c, double d,LineInf e, LineInf f)
		{
			id = numPoints++;
			x = c;
			y = d;
			a = e;
			b = f;
		}
		@Override
		public int compareTo(pt o) {
			if(x!=o.x)
				return Double.compare(x,o.x);
			else
				return Double.compare(y,o.y);
		}
		public String toString()
		{
			return print()+" "+"("+x+","+y+")";
		}

		public String print()
		{
			return name(a)+"/"+name(b);
		}

		public String name(LineInf x)
		{
			if(x==null)
				return "null";
			return x.name;
		}
	}

	private class LineInf {

		ArrayList<pt> intersections;
		String name;
		double A,B,C;//want line in form Ax+By = C
		boolean vert;
		public LineInf(String n,double x1, double y1, double x2, double y2)
		{
			name = n;
			intersections = new ArrayList<pt>();
			vert = x1==x2;
			A = y2-y1;
			B = x1-x2;
			C = A*x1+B*y1;
		}

		public pt next(pt x)//given a point on this line, find the next intersection to the right of that point
		{					//if line is vertical, it finds next intersection below
			if(vert)		//returns null if no intersection exists to the right/below
			{
				int L = -1;
				int R = intersections.size();
				int M = (L+R)/2;
				pt best = null;
				while(R-L>1)
				{
					pt y = intersections.get(M);
					if(y.y>x.y)
					{
						best = y;
						R = M;
					}else{
						L = M;
					}
					M = (R+L)/2;
				}
				return best;
			}
			int L = -1;
			int R = intersections.size();
			int M = (L+R)/2;
			pt best = null;
			while(R-L>1)
			{
				pt y = intersections.get(M);
				if(y.x>x.x)
				{
					best = y;
					R = M;
				}else{
					L = M;
				}
				M = (R+L)/2;
			}
			return best;
		}

		public pt prev(pt x)//given a point on this line, find the next intersection to the left of that point
		{					//if line is vertical, it finds next intersection above
			if(vert)		//returns null if no intersection exists to the left/above
			{
				int L = -1;
				int R = intersections.size();
				int M = (L+R)/2;
				pt best = null;
				while(R-L>1)
				{
					pt y = intersections.get(M);
					if(y.y<x.y)
					{
						best = y;
						L = M;
					}else{
						R = M;
					}
					M = (R+L)/2;
				}
				return best;
			}
			int L = -1;
			int R = intersections.size();
			int M = (L+R)/2;
			pt best = null;
			while(R-L>1)
			{
				pt y = intersections.get(M);
				if(y.x<x.x)
				{
					best = y;
					L = M;
				}else{
					R = M;
				}
				M = (R+L)/2;
			}
			return best;
		}

		public boolean contains(int x, int y)
		{
			return A*x+B*y==C;
		}

		public boolean isVertical()
		{
			return vert;
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

		public pt intersection(LineInf li)
		{
			double det = det(li);
			if(det==0) return null; //zero or infinite solutions
			double x = (li.B*C-B*li.C)/det;
			double y = (A*li.C-li.A*C)/det;
			return new pt(x,y,this,li);
		}

		public String toString()
		{
			return name+" "+intersections;
		}
	}
}
