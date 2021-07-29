import java.util.Scanner;

public class SmallSchedule {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		long Q = input.nextLong();
		long n = input.nextLong();
		long S = input.nextLong();
		long L = input.nextLong();
		
		long ans = (L/n)*Q; L%=n;
		if (L>0) {
			ans += Q;
			if (S>(n-L)*Q) S -= (n-L)*Q;
			else S = 0;
		}
		ans += (long)(Math.ceil(1.00*S/n));
		System.out.println(ans);
	}
}
