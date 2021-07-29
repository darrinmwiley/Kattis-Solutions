import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class NonPrimeFactors {
	
	static int totient_ans(int n, int[] sieve) {
		int ans = 1, pcnt = 0, nn = n;
		while (n>1) {
			int p = sieve[n]; pcnt++;
			int tcnt = 0;
			while (n%p==0) {
				tcnt++; n/=p;
			}
			ans *= (tcnt+1);
		}
		return ans-pcnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(input.readLine());
		int m = 2000007;
		int[] sieve = new int[m];
		sieve[0] = sieve[1] = 1;
		for (int i = 2; i < m; i++)
			if (sieve[i] == 0)
				for (int j = i; j < m; j+=i)
					sieve[j]=i;
		for (int i = 0; i < n; i++)
			out.println(totient_ans(Integer.parseInt(input.readLine()), sieve));
		out.close();
	}
}
