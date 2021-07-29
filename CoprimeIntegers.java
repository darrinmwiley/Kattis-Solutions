import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CoprimeIntegers {

	int[] sieve;
	int[] bits = new int[1024];

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new CoprimeIntegers().run();		
	}

	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		int A = file.nextInt();
		int B = file.nextInt();
		int C = file.nextInt();
		int D = file.nextInt();
		for(int i =0;i<bits.length;i++)
			bits[i] = Integer.bitCount(i);
		sieve = new int[10000001];
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
		long ans = 0;
		for(int i = C;i<=D;i++)
		{
			int add = cop(A,B,i);
			ans += add;
		}
		System.out.println(ans);
	}

	public int cop(int a, int b, int x)
	{
		int ans = 0;
		ArrayList<Integer> cop = pf(x);
		for(int i = 1;i<1<<cop.size();i++)
		{
			int fact = 1;
			for(int j = 0;j<cop.size();j++)
			{
				if((i&1<<j) != 0)
					fact *= cop.get(j);
			}
			int y = Math.max(0,b/fact - (a-1)/fact);
			if((bits[i] & 1) == 1)
				ans += y;
			else
				ans -= y;
		}
		return b - a + 1 - ans;
	}

	public ArrayList<Integer> pf(int N){
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(N != 1)
		{
			if(list.isEmpty() || list.get(list.size() - 1) != sieve[N])
				list.add(sieve[N]);
			N /= sieve[N];
		}
		return list;
	}

}
