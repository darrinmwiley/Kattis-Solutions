

import java.awt.Polygon;
import java.math.BigInteger;
import java.util.Scanner;

public class Jabuke {

	public static void main(String[] args) throws Exception
	{
		new Jabuke().run();
	}
	
	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int ax = file.nextInt();
		int ay = file.nextInt();
		int bx = file.nextInt();
		int by = file.nextInt();
		int cx = file.nextInt();
		int cy = file.nextInt();
		System.out.println(area(ax,ay,bx,by,cx,cy));
		int[] x = new int[]{ax,bx,cx};
		int[] y = new int[]{ay,by,cy};
		Polygon p = new Polygon(x,y,3);
		line[] lines = new line[3];
		for(int i = 0;i<lines.length;i++)
        {
            int x1 = x[i];
            int x2 = x[(i+1)%3];
            int y1 = y[i];
            int y2 = y[(i+1)%3];
            lines[i] = new line(x1,y1,x2,y2);
        }
		int inside = 0;
		int M = file.nextInt();
	loop:
		for(int i = 0;i<M;i++)
        {
            int X = file.nextInt();
            int Y = file.nextInt();
            for(line l:lines)
                if(l.contains(X, Y))
                {
                   	inside++;
                    continue loop;
                }
            boolean cont = p.contains(X, Y);
            if(cont)
                inside++;
        }
		System.out.println(inside);
	}
	
	public double area(double ax, double ay, double bx, double by, double cx, double cy)
	{
		return Math.abs(ax*(by-cy)+bx*(cy-ay)+cx*(ay-by))/2;
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


