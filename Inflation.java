import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Inflation {
	
	int r, dx, dy, x, y;
	double p;
	double epsilon = .000001;
	int INSIDE = 1;
	int CONTAINS = 2;
	int INTERSECT = 3;
	int OUTSIDE = 4;
	int[][] d = new int[][]{{1,0,-1,0,0},{0,1,0,-1,0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new Inflation().run();
	}
	
	public void run() throws NumberFormatException, IOException//bfs by shortest connection number
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		StringTokenizer st = new StringTokenizer(file.readLine());
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
		{
			ints[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(ints);
		double min = 1;
		for(int i = 0;i<ints.length;i++)
		{
			if(ints[i] > i+1)
			{
				System.out.println("impossible");
				return;
			}else
				min =Math.min(min,ints[i]/(i+1.0));
		}
		System.out.println(min);
	}
	
	public double area(double tlx, double tly, double brx, double bry)
	{
		 int classify = classify(tlx, tly, brx, bry);
		 //System.out.println(tlx+" "+tly+" "+brx+" "+bry+" "+classify);
		 double wid = brx - tlx;
		 double ht = tly - bry;
		 if(classify == CONTAINS)
			 return Math.PI*r*r;
		 else if(classify == INSIDE)
			 return (wid)*(ht);
		 else if(classify == OUTSIDE)
			 return 0;
		 else{
			 if(wid * ht < epsilon)
				 return wid*ht/2;
			 double mx = tlx + wid / 2;
			 double my = bry + ht / 2;
			 double tl = area(tlx, tly, mx,my);
			 double tr = area(mx, tly, brx, my);
			 double bl = area(tlx, my, mx, bry);
			 double br = area(mx,my,brx, bry);
			 return tl+tr+bl+br;
		 }
	}
	
	public int classify(double tlx, double tly, double brx, double bry)
	{
		boolean tl = inside(tlx,tly);
		boolean tr = inside(brx, tly);
		boolean bl = inside(tlx, bry);
		boolean br = inside(brx, bry);
		boolean mid = inside((tlx+brx)/2,(tly+bry)/2);
		if(tl&&tr&&bl&&br)
			return INSIDE;
		int circ = 0;
		for(int i = 0;i<5;i++)
		{
			if(inside(r*d[0][i],r*d[1][i],tlx, tly, brx, bry))
				circ++;
		}
		if(circ == 5)
			return CONTAINS;
		if(circ != 0 || tl || tr || bl || br || mid)
			return INTERSECT;
		return OUTSIDE;
	}
	
	public boolean inside(double x, double y)
	{
		return x*x + y*y <= r*r;
	}
	
	public boolean inside(double x, double y, double tlx, double tly, double brx, double bry)
	{
		return x >= tlx && x <= brx && y >= bry && y <= tly;
	}
}
