import java.math.BigInteger;
import java.util.Scanner;

public class DiagonalCut {

	public static void main(String[] args)
	{
		new DiagonalCut().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		BigInteger a = file.nextBigInteger();
		BigInteger b = file.nextBigInteger();
		if(a.compareTo(b) < 0)
		{
			BigInteger save = a;
			a = b;
			b = save;
		}
		BigInteger gcd = a.gcd(b);
		BigInteger div = a.divide(gcd);
		BigInteger div2 = b.divide(gcd);
		if(div.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO))
			System.out.println(0);
		else if(div2.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO))
			System.out.println(0);
		else
			System.out.println(a.gcd(b));
	}

/*
[10] [0.9, 0.1]
[10, 10] [0.81, 0.18000000000000002, 0.010000000000000002]
[10, 10, 10] [0.7290000000000001, 0.24300000000000005, 0.027000000000000003, 0.0010000000000000002]
[10, 10, 10, 10] [0.6561000000000001, 0.2916000000000001, 0.048600000000000004, 0.0036000000000000008, 1.0000000000000003E-4]
1.02503700453637
*/

}
