import java.util.Arrays;
import java.util.Scanner;

public class HumanCannonball {

	public void run(){
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		for(int i = 0;i<N;i++)
		{
			double v = file.nextDouble();
	        double theta = file.nextDouble();
	        double x = file.nextDouble();
	        double h1 = file.nextDouble();
	        double h2 = file.nextDouble();
	        double T = x/(v*Math.cos(Math.toRadians(theta)));
	        double y = v*T*Math.sin(Math.toRadians(theta))-9.81*T*T/2;
	        if(h2-y>=1&&y-h1>=1)
	        	System.out.println("Safe");
	        else
	        	System.out.println("Not Safe");
		}
       
	}
	
    public static void main(String[] args)
    {
       new HumanCannonball().run();
    } 
}