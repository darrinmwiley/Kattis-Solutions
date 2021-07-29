import java.util.Scanner;

public class EuclidianTSP {

	public static void main(String[] args)
	{
		new EuclidianTSP().run();
	}
	
	public void run()
	{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		double p = in.nextDouble();
		double s = in.nextDouble();
		double v = in.nextDouble();
		double c1 = ternarySearch(0.00000001, 100, n, p, s, v);
		System.out.println(formula(n, p, s, v, c1) + " " + c1);
		in.close();
	}
	
	public double ternarySearch(double low, double high, int n, double p, double s, double v)
	{
		while (Math.abs(high - low) > 0.0000000001)
		{
			double leftMid = low + (high-low) / 3;
			double rightMid = low + (high-low) / 3 * 2;
			double leftCheck = formula(n, p, s, v, leftMid);
			double rightCheck = formula(n, p, s, v, rightMid);
			if (leftCheck < rightCheck)
			{
				high = rightMid;
			}
			else
			{
				low = leftMid;
			}
		}
		return high;
	}
	
	public double formula(int n, double p, double s, double v, double c)
	{
		return n * Math.pow((Math.log(n) / Math.log(2)), c * Math.sqrt(2)) / (p * 1000000000.0)
				+ s * (1 + 1/c) / v;
	}
}
