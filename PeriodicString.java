import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PeriodicString {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		String s = input.next();
		for (int n = 1; n <= s.length(); n++) {
			String r = s.substring(0, n);
			boolean match = true;
			for (int i = 0; i < s.length(); i++)
				match &= (s.charAt(i) == r.charAt((i-i/n+n)%n));
			if (match) {
				System.out.println(n);
				break;
			}
		}
	}
}
