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

public class porpoises {

	StringTokenizer st;
	BufferedReader file;
	PrintWriter pout;

	public static void main(String[] args) throws Exception
	{
        new porpoises().run();
	}

	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);

		int zz = Integer.parseInt(file.readLine());
		long[][] fib = new long[][] {{1,1},{1,0}};
		for(int z = 0;z<zz;z++)
		{
			StringTokenizer st = new StringTokenizer(file.readLine());
			String cas = st.nextToken();
			long l = Long.parseLong(st.nextToken());
			long[][] p = powMod(fib, l, 1000000000);
			pout.println(cas+" "+p[0][1]);
		}

		pout.flush();
		pout.close();
	}

	public long[][] powMod(long[][] a, long p, long m)
	{
		if(p == 1)
			return a;
		if(p % 2 == 0)
		{
			long[][] half = powMod(a, p/2, m);
			return multMod(half, half, m);
		}else
			return multMod(a, powMod(a,p-1, m), m);

	}

	public long[][] multMod(long[][] a, long[][] b, long m)
	{
		long[][] ans = new long[a.length][b.length];
		//row a * col b
		for(int i = 0;i<a.length;i++)
		{
			for(int j = 0;j<b.length;j++)
			{
				long sum = 0;
				for(int k = 0;k<a[i].length;k++)
				{
					sum += a[i][k] * b[k][j] % m;
					if(sum >= m)
						sum -= m;
				}
				ans[i][j] = sum;
			}
		}
		return ans;
	}

}
