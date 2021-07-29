import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NeighborhoodWatch {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(token.nextToken()), k = Integer.parseInt(token.nextToken());
		boolean[] set = new boolean[n];
		for (int i = 0; i < k; i++) 
			set[Integer.parseInt(input.readLine())-1]=true;
		int nxt = n;
		long ans = 0;
		for (int i = n-1; i >= 0; i--) {
			if (set[i]) nxt=i;
			ans += n-nxt;
		}
		System.out.println(ans);
	}
}
