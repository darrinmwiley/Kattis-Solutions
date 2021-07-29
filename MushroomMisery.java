import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

public class MushroomMisery {

	int N;
	int L;
	double[] x,y,r;
	int[][] d = new int[][] {{1,0,-1,0},{0,1,0,-1}};
	long in = 0;

	int INSIDE = 1337;
	int INTERSECT = 420;
	int OUTSIDE = 69;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new MushroomMisery().run();
	}

/*
10
2
3 9 5
5 5 6

10
1
5 5 6
 */

	public String gen(int N, int L)
	{
		String ans = "";
		ans += L+"\n"+N+"\n";
		for(int i = 0;i<N;i++)
		{
			int x = (int)(Math.random() * L + 2);
			int y = (int)(Math.random() * L + 2);
			ans +=  x+" "+y+ " "+(int)(Math.random() * Math.min(x-2,y-2)+1)+"\n";
		}
		return ans;
	}

	public long run(String test, boolean magic)
	{
		in = 0;
		Scanner file = new Scanner(test);
		L = file.nextInt();
		N = file.nextInt();
		x = new double[N];
		y = new double[N];
		r = new double[N];
		for(int i = 0;i<N;i++)
		{
			x[i] = file.nextDouble();
			y[i] = file.nextDouble();
			r[i] = file.nextDouble();
		}
		boolean[] active = new boolean[N];
		Arrays.fill(active, true);
		test(0,L,L,0,active, magic);
		return in;
	}

	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		L = file.nextInt();
		N = file.nextInt();
		x = new double[N];
		y = new double[N];
		r = new double[N];
		for(int i = 0;i<N;i++)
		{
			x[i] = file.nextDouble();
			y[i] = file.nextDouble();
			r[i] = file.nextDouble();
		}
		boolean[] active = new boolean[N];
		Arrays.fill(active, true);
		test(0,L,L,0,active, true);
		System.out.println(in);
	}

	public void test(double tlx, double tly, double brx, double bry, boolean[] active, boolean magic)
	{
		long wid = (long)(brx - tlx);
		long ht = (long)(tly - bry);
		if(wid == 0 || ht == 0)
			return;
		int cl = OUTSIDE;
		boolean[] act = new boolean[N];
		Arrays.fill(act, true);
		for(int i = 0;i<N;i++)
		{
			if(active[i])
			{
				int c = classify(i, tlx, tly, brx, bry);
				if(magic)
					if(c == OUTSIDE)
						act[i] = false;
				cl = Math.max(cl,c);
			}else
				act[i] = false;
		}
		if(cl == INSIDE) {
			in += (long)(brx - tlx)*(tly - bry);
			return;
		}
		//System.out.println(tlx+" "+tly+" "+brx+" "+bry+" "+undumb(cl));
		if(cl == INTERSECT)
		{
			if(wid * ht == 1)
				in++;
			else {
				int midx = (int)(brx + tlx)/2;
				int midy = (int)(bry + tly)/2;
				test(tlx, tly, midx, midy,act,magic);
				test(midx, tly, brx, midy,act,magic);
				test(tlx, midy, midx, bry,act,magic);
				test(midx, midy, brx, bry,act,magic);
			}
		}
	}

	public String undumb(int x)
	{
		if(x == 69)
			return "outside";
		if(x == 420)
			return "intersect";
		return "inside";
	}

	public int classify(int circle, double tlx, double tly, double brx, double bry)
	{
		double mx = (tlx+brx)/2;
		double my = (tly+bry)/2;
		boolean tl = inside(circle, tlx,tly);
		boolean tr = inside(circle,brx,tly);
		boolean bl = inside(circle, tlx, bry);
		boolean br = inside(circle, brx, bry);
		boolean mid = inside(circle, mx, my);
		if(tl && tr && bl && br)
		{
			return INSIDE;
		}
		if(tl || tr || bl || br || mid)
			return INTERSECT;
		if(!tl && !tr && !bl && !br)
		{
			for(int i = 0;i<4;i++)
			{
				if(inside(x[circle]+r[circle]*d[0][i],y[circle]+r[circle]*d[1][i],tlx,tly,brx,bry))
				{
					return INTERSECT;
				}
			}
		}
		return OUTSIDE;
	}

	//circ
	public boolean inside(int circle, double xx, double yy)
	{
		return (x[circle]-xx)*(x[circle]-xx)+(y[circle]-yy)*(y[circle]-yy)<=(r[circle])*(r[circle]);
	}

	//rect
	public boolean inside(double x, double y, double tlx, double tly, double brx, double bry)
	{
		boolean ret = x >= tlx && x <= brx && y <= tly && y >= bry;
		return ret;
	}

}
