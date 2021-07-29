/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/primesieve
TAGS: sieve 
EXPLANATION:
Sieve implementation with optimizations for time and memory.
1) use bitset instead of boolean array, this is more memory efficient
2) instead of starting your inner loop at i*2, start it at i*i. Any multiple composite multiple of i smaller than i*i must have some prime factor smaller than i (so it will already be marked)
3) use fast i/o
4) you can probably get away with way less optimizations if you just use c++ 
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
 
public class reseto {
	
	StringTokenizer st;
	BufferedReader file;
	
	public static void main(String[] args) throws Exception
	{
        new reseto().run();
	}	
	
	public void run() throws Exception
	{	
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pout = new PrintWriter(System.out);
		
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		BitSet bits = new BitSet(N+1);
		bits.set(0);
		bits.set(1);
		int root = (int)(Math.sqrt(N));
		for(int i = 2;i<N+1;i++)
		{
			if(!bits.get(i))
			{
				K--;
				if(K == 0)
				{
					System.out.println(i);
					return;
				}
				if(i <= root) {
					for(int j = i*i;j<N+1;j+=i)
					{
						if(!bits.get(j))
						{
							K--;
							if(K == 0)
							{
								System.out.println(j);
								return;
							}
							bits.set(j);
						}
					}
				}
			}
		}
		pout.flush();
		pout.close();
	}
	
}
