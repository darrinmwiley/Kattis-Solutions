import java.util.Scanner;

public class HeartRate {
	
	public static void main(String[] args)
	{
		new HeartRate().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int b = file.nextInt();
			double p = file.nextDouble();
			double calc = 60*b/p;
			double minFreq = (p/(b - 1));
			double min = 60/minFreq;
			//System.out.println(minFreq);
			double max = calc + (calc - min);
			System.out.println(min+" "+calc+" "+max);
		}
	}
	
}
