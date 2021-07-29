import java.util.Scanner;

public class EulersNumber {

	public static void main(String[] args)
	{
		new EulersNumber().run();
	}

	public void run()
	{
		double[] fact = new double[10001];
		fact[0] = 1;
		for (int i = 1; i < fact.length; i++)
		{
			fact[i] = fact[i-1] / i;
		}
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		double sum = 0;
		for (int i = 0; i <= n; i++)
		{
			sum += fact[i];
		}
		System.out.printf("%.15f", sum);
	}

}
