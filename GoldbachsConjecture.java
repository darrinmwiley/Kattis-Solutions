import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GoldbachsConjecture {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		boolean[] prime = new boolean[32001];
		Arrays.fill(prime,true);
		prime[0] = prime[1] = false;
		for(int i = 3;i<prime.length;i++)
		{
			if(prime[i])
			{
				for(int j = i*2;j<prime.length;j+=i)
					prime[j] = false;
			}
		}
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i = 0;i<prime.length;i++)
			if(prime[i])
				primes.add(i);
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int N = file.nextInt();
			int reps = 0;
			ArrayList<Integer> ans = new ArrayList<Integer>();
			for(int i:primes)
			{
				if(i>N/2)
					break;
				if(prime[N-i])
				{
					ans.add(i);
					reps++;
				}
			}
			System.out.println(N+" has "+reps+" representation(s)");
			for(int i:ans)
				System.out.println(i+"+"+(N-i));
			System.out.println();
		}
		
	}
}
