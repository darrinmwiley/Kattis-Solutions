import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class HalfACookie {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(file.hasNext())
		{
			double R = file.nextDouble();
			double x = file.nextDouble();
			double y = file.nextDouble();
			double a = Math.sqrt(x*x+y*y);
			if(a>=R)
			{
				System.out.println("miss");
				continue;
			}
			double theta = Math.asin(Math.sqrt(R*R-a*a)/R);
			theta*=2;
			double deg = Math.toDegrees(theta);
			double A = Math.PI*R*R*deg/360-a*Math.sqrt(R*R-a*a);
			double B = Math.PI*R*R-A;
			System.out.println(Math.max(A, B)+" "+Math.min(A, B));
		}
	}
	
	public static void main(String[] args)
	{
		new HalfACookie().run();
	}
}

