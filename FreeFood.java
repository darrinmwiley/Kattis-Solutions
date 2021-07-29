import java.util.Scanner;

public class FreeFood {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt(), m = 365;
		int[] arr = new int[m+1];
		for (int i = 0; i < n; i++) {
			arr[input.nextInt()-1]++; arr[input.nextInt()]--;
		}
		int cnt = 0;
		int ans = 0;
		for (int i = 0; i < m; i++) {
			cnt += arr[i];
			if (cnt > 0) ans++;
		}
		System.out.println(ans);
	}
}
