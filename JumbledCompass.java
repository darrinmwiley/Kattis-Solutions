import java.math.BigInteger;
import java.util.Scanner;

public class JumbledCompass {

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int a = file.nextInt();
		int b = file.nextInt();

		int clockDist = b >= a ? b - a : b - a + 360;
		int counterDist = a >= b ? a - b : a - b + 360;

		System.out.println(counterDist < clockDist ? -counterDist : clockDist);
	}


	public static void main(String[] args)
	{
		new JumbledCompass().run();
	}

}
