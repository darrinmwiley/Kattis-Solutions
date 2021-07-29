import java.util.Scanner;
import java.math.BigInteger;

public class Nine {

	public static void main(String[] args)
	{
		new Nine().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			long numDigits = file.nextLong();

			BigInteger product = new BigInteger("9");
			product = product.modPow(new BigInteger(""+(numDigits - 1)), new BigInteger("1000000007"));
			product = product.multiply(new BigInteger("8")).mod(new BigInteger("1000000007"));
			System.out.println(product);
		}
	}

}
