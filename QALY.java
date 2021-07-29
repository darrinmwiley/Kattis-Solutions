import java.util.Scanner;

public class QALY {
	public static void main(String[] args) throws Exception {
		Scanner file = new Scanner(System.in);
		int n = file.nextInt();
		double ans = 0.0;
		for(int i = 0;i<n;i++)
		{
			double a = file.nextDouble();
			double b = file.nextDouble();
			ans += a*b;
		}
		System.out.println(ans);
	}
}
