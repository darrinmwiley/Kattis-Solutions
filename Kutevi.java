

import java.awt.Polygon;
import java.math.BigInteger;
import java.util.Scanner;

public class Kutevi {

	public static void main(String[] args) throws Exception
	{
		new Kutevi().run();
	}

	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		boolean[] possible = new boolean[360];
		possible[0] = true;
		for(int i = 0;i<N;i++)
		{
			possible[file.nextInt()] = true;
		}
		boolean goAgain = true;
		while(goAgain)
		{
			goAgain = false;
			for(int i = 0;i<360;i++)
			{
				if(possible[i])
				{
					for(int j = 0;j<360;j++)
					{
						if(possible[j])
						{
							int nextIndex = (j+i)%360;
							int prevIndex = (((j-i)+360)%360);
							if(!possible[nextIndex]||!possible[prevIndex])
							{
								goAgain = true;
								possible[nextIndex] = possible[prevIndex] = true;
							}
						}
					}
				}
			}
		}
		for(int i = 0;i<M;i++)
			System.out.println(possible[file.nextInt()]?"YES":"NO");
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
