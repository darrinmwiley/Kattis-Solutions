import java.awt.Point;
import java.util.Scanner;

public class Bazen {
	
	public void run()
	{
		double rt = Math.sqrt(2);
		Scanner file = new Scanner(System.in);
		int x = file.nextInt();
		int y = file.nextInt();
		double closest = 0;
		double ansX = 0;
		double ansY = 0;
		boolean flip = false;
		if(x==0)
		{
			int save = x;
			x = y;
			y = save;
			flip = true;
		}
		//code in degenerates
		if(y==0)
		{
			for(double z = 0;z<=250;z+=.0001)
			{
				double a = 250-x;
				double b = (250-z)*rt;
				double c = dist(x,y,z,250-z);
				double calc = calc(a,b,c);
				if(Math.abs(calc-15625)<Math.abs(closest-15625))
				{
					closest = calc;
					ansX = z;
					ansY = 250-z;
				}
				a = x;
				b = z;
				c = dist(0,z,x,y);
				calc = calc(a,b,c);
				if(Math.abs(calc-15625)<Math.abs(closest-15625))
				{
					closest = calc;
					ansX = 0;
					ansY = z;
				}
			}
		}
		if(x+y==250)
		{
			for(double z = 0;z<=250;z+=.0001)
			{
				double a = 250-z;
				double b = dist(0,250,x,y);
				double c = dist(x,y,0,z);
				double calc = calc(a,b,c);
				if(Math.abs(calc-15625)<Math.abs(closest-15625))
				{
					closest = calc;
					ansX = 0;
					ansY = z;
				}
				a = dist(z,0,x,y);
				b = dist(x,y,250,0);
				c = 250-z;
				calc = calc(a,b,c);
				if(Math.abs(calc-15625)<Math.abs(closest-15625))
				{
					closest = calc;
					ansX = z;
					ansY = 0;
				}
			}
		}
		if(flip)
		{
			System.out.printf("%.2f %.2f",ansY,ansX);
		}else
			System.out.printf("%.2f %.2f",ansX,ansY);
	}
	
	public double calc(double a, double b, double c)
	{
		double s = (a+b+c)/2;
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}
	
	public double dist(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2,2));
	}
	
	public static void main(String[] args)
	{
		new Bazen().run();
	}
}
