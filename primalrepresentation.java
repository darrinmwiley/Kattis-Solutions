/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/reseto
TAGS: sieve 
EXPLANATION:
Sieve implementation with optimizations for time and memory.
standard sieve, keep a counter variable to keep track of how many you've marked.
END ANNOTATION
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class primalrepresentation {
	
	StringTokenizer st;
	BufferedReader file;
	ArrayList<Integer> primes;
	PrintWriter pout;
	
	public static void main(String[] args) throws Exception
	{
        new primalrepresentation().run();
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
			int x = Integer.parseInt(line);
			pout.println(rep(x));
		}
		pout.flush();
		pout.close();
	}
	
	public String rep(int x)
	{
		StringBuilder ret = new StringBuilder();
		if(x < 0) 
			ret.append("-1 ");
		x = Math.abs(x);
		for(int i: primes)
		{
			if(x == 1)
				break;
			int ct = 0;
			while(x % i == 0)
			{
				ct++;
				x/=i;
			}
			if(ct != 0)
			{
				if(ct == 1)
				{
					ret.append(i+" ");
				}else {
					ret.append(i+"^"+ct+" ");
				}
			}
		}
		if(x != 1)
		{
			ret.append(x);
		}
		return ret.toString().trim();
	}
	
}
