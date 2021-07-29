import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ZigZag {
	static int mxn = 1000000;
	static class MAX_BIT {
		int[] tree = new int[2*mxn];
		void change(int i, int x) {
			for (; i <= mxn; i |= i+1) tree[i]=Math.max(tree[i],x);
		}
		int query(int i) {
			int ans = 0;
			for (; i>=0; i = (i&(i+1))-1) ans = Math.max(ans,tree[i]);
			return ans;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(input.readLine());
		int[] a = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(input.readLine());
		}
		int[][] dp = new int[n][2];
		MAX_BIT ba, bb;
		ba = new MAX_BIT(); bb = new MAX_BIT();
		int ans = 0;
		for (int i = 0; i < n; i++) {
			dp[i][0] = dp[i][1] = 1;
			dp[i][1] = Math.max(dp[i][1], ba.query(a[i]-1)+1);
			dp[i][0] = Math.max(dp[i][0], bb.query(mxn-a[i]-1)+1);
			ba.change(a[i], dp[i][0]);
			bb.change(mxn-a[i], dp[i][1]);
			ans = Math.max(ans, dp[i][0]);
			ans = Math.max(ans, dp[i][1]);
			
		}
		System.out.println(ans);
	}
}
