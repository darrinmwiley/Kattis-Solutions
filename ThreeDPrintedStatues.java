import java.util.Arrays;
import java.util.Scanner;

public class ThreeDPrintedStatues {

	int[] max = new int[17];

	public static void main(String[] args)
	{
		new ThreeDPrintedStatues().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		double log = Math.log(N)/Math.log(2);
		System.out.println((int)Math.ceil(log)+1);
	}
}
