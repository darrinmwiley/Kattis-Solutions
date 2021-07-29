/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/primalrepresentation
TAGS: sieve 
EXPLANATION:
sieve primes under sqrt(2<<32), the size of these is approximately n/lgn = 2^16/11 = 6000
with 10,000 inputs we can try dividing by each of the 6000 primes as much as possible, 
any number left after trying all on one side of the root must be a prime from the other side or 1
6000 primes * 10000 queries = 60M operations
END ANNOTATION
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class hopscotch {
	
	StringTokenizer st;
	BufferedReader file;
	PrintWriter pout;
	
	long[] inv;
	long[] invfact;
	long[] factmod;
	
	int m;
	
	public static void main(String[] args) throws Exception
	{
        new hopscotch().run();
	}	
	
	public void run() throws Exception
	{	
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);
		
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		long X = Integer.parseInt(st.nextToken());
		long Y = Integer.parseInt(st.nextToken());
		m = 1000000007;
		inv = new long[N+1];
		invfact = new long[N+1];
		factmod = new long[N+1];
		inv[1] = 1;
		invfact[0] = invfact[1] = 1;
		factmod[0] = factmod[1] = 1;
		for(int i = 2; i < N+1; ++i) {
		    inv[i] = m - (m/i) * inv[m%i] % m;
		    invfact[i] = invfact[i-1] * inv[i] % m;
		    factmod[i] = factmod[i-1] * i % m;
		}
		
		long[] waysX = new long[N+1];
		long[] waysY = new long[N+1];
		for(int i = 1;i<waysX.length;i++)
		{
			if(X*i <= N)
			{
				waysX[i] = choose(N - X*i + i - 1, N - X*i);
			}
			if(Y*i <= N)
			{
				waysY[i] = choose(N - Y*i + i - 1, N - Y*i);
			}
		}
		
		long ans = 0;
		
		for(int i = 1;i<=N;i++)
		{
			ans += waysX[i] * waysY[i] % m;
			if(ans >= m)
				ans -= m;
		}
		
		pout.println(ans);
		
		pout.flush();
		pout.close();
	}
	
	long choose(long n, long k)
	{
		return factmod[(int)n] * invfact[(int)k] % m * invfact[(int)(n - k)] % m;
	}
}
