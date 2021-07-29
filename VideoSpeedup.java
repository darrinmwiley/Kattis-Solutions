import java.util.Scanner;

public class VideoSpeedup {

	public void run() throws Exception
	{
		Scanner input = new Scanner(System.in);
		int n = input.nextInt(), pp = input.nextInt(), k = input.nextInt();
		double p = pp/100.0;
		double b4 = 0;
		int[] a = new int[n+1];
		for (int i = 1; i <= n; i++)
			a[i] = input.nextInt();
		for (int i = 1; i <= n; i++) {
			b4 += (double)(a[i]-a[i-1])*(1+i*p-p);
		}
		System.out.printf("%.9f\n", b4 + (k-a[n])*(1+n*p));

		input.close();
	}

	public static void main(String[] args) throws Exception
	{
		new VideoSpeedup().run();
	}

}
