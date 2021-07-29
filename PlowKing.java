import java.util.Scanner;

public class PlowKing {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		long m = input.nextLong();
		long ans = 0;
		
		long i = 1;
		int cur = 1;
		while (cur < n) {
			ans += i;
			i+=cur++;
			if (m-i+1<=n-cur) {
				for (int d = 0; d < n-cur; d++)
					ans += m-d;
				break;
			}
		}
		
		System.out.println(ans);
	}
}
