import java.util.Scanner;

public class SumSqDigitFunction {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int p = input.nextInt();
		while (p-->0) {
			int k, n, b;
			k=input.nextInt(); b = input.nextInt(); n = input.nextInt();
			long ans = 0;
			while (n>0) {
				ans += (n%b)*(n%b);
				n/=b;
			}
			System.out.println(k + " " + ans);
		}
	}
}
