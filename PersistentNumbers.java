import java.math.BigInteger;
import java.util.Scanner;

public class PersistentNumbers {
	public static final void main(final String[] args)throws Exception
	{
		final Scanner file = new Scanner(System.in);
		final BigInteger stop = BigInteger.valueOf(-1);
		L:
		while(true)
		{
			BigInteger target = new BigInteger(file.nextLine());
			if(target.equals(stop)) break;
			else
			{
				if(target.equals(BigInteger.ZERO)) System.out.println("10");
				else if(target.compareTo(BigInteger.valueOf(10)) < 0) System.out.println("1" + target);
				else
				{
					int[] hist = new int[10];
					IL:
					while(!target.equals(BigInteger.ONE))
					{
						for(int i = 9; i >= 2; i--)
						{
							if(target.mod(BigInteger.valueOf(i)).equals(BigInteger.ZERO))
							{
								target = target.divide(BigInteger.valueOf(i));
								hist[i]++;
								continue IL;
							}
						}
						System.out.println("There is no such number.");
						continue L;
					}
					for(int i = 0; i < hist.length; i++)
					{
						System.out.print(new String(new char[hist[i]]).replace('\0', (char) ('0' + i)));
					}
					System.out.println();
				}
			}
		}
	}
}
