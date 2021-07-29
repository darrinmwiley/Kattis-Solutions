import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PrimalPartitions {
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new PrimalPartitions().run();		
	}
	
	int[] ints;
	int[] sieve;
	int N;
	int K;
	
	public void fillSieve()
	{
		sieve = new int[1000000];
		for(int i = 2;i<sieve.length;i++)
		{
			if(sieve[i] == 0)
			{
				for(int j = i;j<sieve.length;j+=i)
				{
					sieve[j] = i;
				}
			}
		}
		sieve[1] = 0;
	}
	
	public void run()
	{
		fillSieve();
		Scanner file = new Scanner(System.in);
		N = file.nextInt();
		K = file.nextInt();
		String str = file.nextLine();
		StringTokenizer st = new StringTokenizer(file.nextLine());
		ints = new int[N];
		for(int i = 0;i<ints.length;i++)
			ints[i] = Integer.parseInt(st.nextToken());
		int L = 0;
		int R = 1000000;
		int M = (R+L)/2;
		while(R-L > 1)
		{
			M = (R+L)/2;
			boolean poss = test(M);
			if(poss)
			{
				L = M;
			}else {
				R = M;
			}
		}
		System.out.println(L);
	}
	
	//5 3
	//10 11 12 13 14
	
	public boolean test(int M)
	{
		int k = K-1;
		int gcd = ints[0];
		for(int i = 0;i<ints.length-1;i++)
		{
			if(sieve[gcd] < M) {
				return false;	
			}
			int nextGCD = gcd(gcd, ints[i+1]);
			if(sieve[nextGCD] < M)
			{
				if(k != 0)
				{
					k--;
					gcd = ints[i+1];
				}
				else {
					return false;
				}
			}else {
				gcd = nextGCD;
			}
		}
		return sieve[gcd] >= M;
	}
	
	public int gcd(int a, int b)
	{
		return BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
	}
	
}
