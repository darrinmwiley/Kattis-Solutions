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
 
public class pseudoprime {
	
	StringTokenizer st;
	BufferedReader file;
	ArrayList<Integer> primes;
	PrintWriter pout;
	
	public static void main(String[] args) throws Exception
	{
        new pseudoprime().run();
	}	
	
	public void run() throws Exception
	{	
		pout = new PrintWriter(System.out);
		primes = new ArrayList<Integer>();
		boolean[] sieve = new boolean[1<<16+1];
		Arrays.fill(sieve, true);
		for(int i = 2;i<sieve.length;i++)
			if(sieve[i]) {
				primes.add(i);
				for(int j = i*2;j<sieve.length;j+=i)
					sieve[j] = false;
			}
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while((line = file.readLine())!= null)
		{
			StringTokenizer st = new StringTokenizer(line);
			long p = Integer.parseInt(st.nextToken());
			long a = Integer.parseInt(st.nextToken());
			if(a==0 && p == 0)
			{
				break;
			}
			if(BigInteger.valueOf(p).isProbablePrime(200))
			{
				pout.println("no");
				continue;
			}
			long powmod = powmod(a,p,p);
			if(powmod == a)
			{
				pout.println("yes");
			}else
				pout.println("no");
		}
		pout.flush();
		pout.close();
	}
	
	public long powmod(long a, long p, long m)
	{
		if(p == 0)
			return 1;
		if((p&1) == 0)
		{
			long half = powmod(a, p/2, m);
			return half * half % m;
		}else {
			return powmod(a, p-1, m) * a % m;
		}
	}
	
}
