import java.math.BigInteger;
import java.util.Scanner;

public class RationalSequence {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int N = file.nextInt();
			String[] next = file.next().split("/");
			int num = Integer.parseInt(next[0]);
			int denom = Integer.parseInt(next[1]);
			if(denom==1)
			{
				System.out.printf("%d %d/%d%n",z+1,1,num+1);
				continue;
			}
			long ancestorNum;
			long ancestorDenom;
			int up = num/denom;
			ancestorNum = num-denom*up;
			ancestorDenom = denom-ancestorNum;
			ancestorNum+=ancestorDenom;
			ancestorDenom+=up*ancestorNum;
			System.out.printf("%d %d/%d%n",z+1,ancestorNum,ancestorDenom);
		}
	}
	
	public long gcd(long a, long b)
	{
		return new BigInteger(a+"").gcd(new BigInteger(b+"")).longValue();
	}
	
	public static void main(String[] args)
	{
		new RationalSequence().run();
	}
}

