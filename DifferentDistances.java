import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class DifferentDistances {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(file.hasNext())
		{
			double x1 = file.nextDouble();
			if(x1==0)
				return;
			double y1 = file.nextDouble();
			double x2 = file.nextDouble();
			double y2 = file.nextDouble();
			double p = file.nextDouble();
			System.out.println(Math.pow(Math.pow(Math.abs(x1-x2), p)+Math.pow(Math.abs(y1-y2),p),1/p));
		}
	}
	
	public static void main(String[] args)
	{
		new DifferentDistances().run();
	}
}

