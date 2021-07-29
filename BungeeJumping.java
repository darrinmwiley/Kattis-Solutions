import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BungeeJumping {

	public static void main(String[] args) throws FileNotFoundException
	{
		new BungeeJumping().run();
	}

	public void run() throws FileNotFoundException
	{
		Scanner file = new Scanner(System.in);
		double k = file.nextDouble();
		double l = file.nextDouble();
		double s = file.nextDouble();
		double w = file.nextDouble();
		while(file.hasNext())
		{
			if(w==0)
				return;
			double origP = w*9.81*s;
			double P = .5*k*(s-l)*(s-l);
			double K = origP-P;
			//System.out.println(P+" "+origP+" "+K);
			double x = Math.sqrt(K*2/w);
			//System.out.println(x);
			if(s<l)
			{
				K = origP;
				x = Math.sqrt(K*2/w);
			}
			if(Double.isNaN(x))
				System.out.println("Stuck in the air.");
			else if(x<=10)
				System.out.println("James Bond survives.");
			else
				System.out.println("Killed by the impact.");
			k = file.nextDouble();
			l = file.nextDouble();
			s = file.nextDouble();
			w = file.nextDouble();
		}
	}
}
