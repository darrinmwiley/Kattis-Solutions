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
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
 
public class divisors {
	
	StringTokenizer st;
	BufferedReader file;
	PrintWriter pout;
	HashMap<Integer, Integer> primeToIndex;
	ArrayList<Integer> primes;
	int[][] fact = new int[432][];
	int[] sieve;
	
	public static void main(String[] args) throws Exception
	{
        new divisors().run();
	}	
	
	public void run() throws Exception
	{	
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);
		
		String line;
		
		primes = new ArrayList<Integer>();
		primeToIndex = new HashMap<>();
		
		sieve = new int[432];
		Arrays.fill(sieve, -1);
		
		for(int i = 2;i<sieve.length;i++)
		{
			if(sieve[i] == -1)
			{
				primes.add(i);
				primeToIndex.put(i, primes.size() - 1);
				for(int j = i;j<sieve.length;j+=i)
				{
					sieve[j] = i;
				}
			}
		}
		
		fact = new int[432][primes.size()];
		for(int i = 2;i<432;i++)
			fact[i] = fact(i);
		
		while((line = file.readLine()) != null)
		{
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			System.out.println(factors(ncr(N,K)));
		}
		
		pout.flush();
		pout.close();
	}
	
	public long factors(int[] ints)
	{
		long ans = 1;
		for(int x:ints)
			ans *= x+1;
		return ans;
	}
	
	public int[] ncr(int N, int K)
	{
		int[] n = fact[N];
		int[] k = fact[K];
		int[] nk = fact[N - K];
		int[] ans = new int[n.length];
		for(int i = 0;i<n.length;i++)
		{
			ans[i] = n[i] - k[i] - nk[i];
		}
		return ans;
	}
	
	public int[] fact(int N)
	{
		int[] ans = new int[primes.size()];
		for(int i = 2;i<=N;i++)
		{
			int[] pf = pf(i);
			for(int j = 0;j<ans.length;j++)
			{
				ans[j] += pf[j];
			}
		}
		return ans;
	}
	
	public int[] pf(int N)
	{
		int[] ans = new int[primes.size()];
		while(N != 1)
		{
			int p = sieve[N];
			ans[primeToIndex.get(p)]++;
			N /= p;
		}
		return ans;
	}
	
	
	
}
