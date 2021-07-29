import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class AlmostPerfect {
	
	public static void main(String[] args)
	{
		new AlmostPerfect().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(file.hasNext())
		{
			int n = file.nextInt();
			int sqrt = (int)(Math.sqrt(n));
			int amt = 1;
			for(int i = 2;i<=sqrt;i++)
			{
				if(i * i == n)
				{
					amt+=i;
				}else if(n% i == 0)
					amt+=i+n/i;
			}
			boolean perfect = amt == n;
			boolean almost = Math.abs(amt-n) <=2;
			if(perfect)
			{
				System.out.println(n+" perfect");
			}else if(almost)
			{
				System.out.println(n+" almost perfect");
			}else{
				System.out.println(n+" not perfect");
			}
		}
	}
}
