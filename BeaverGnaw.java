import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class BeaverGnaw {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(true)
		{
			double D = file.nextDouble();
			double V = file.nextDouble();
			if(D==0)
				return;
			System.out.println(Math.cbrt((-3.0/2)*(4*V/Math.PI-2*D*D*D/3)));
		}
	}
	
	public static void main(String[] args)
	{
		new BeaverGnaw().run();
	}
}
