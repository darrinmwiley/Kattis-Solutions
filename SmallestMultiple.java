import java.math.BigInteger;
import java.util.Scanner;

public class SmallestMultiple {

	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(file.hasNext())
		{
			String[] line = file.nextLine().split(" ");
			BigInteger[] ints = new BigInteger[line.length];
			for(int i = 0;i<ints.length;i++)
				ints[i] = new BigInteger(line[i]);
			BigInteger lcm = ints[0];
			for(int i = 1;i<ints.length;i++)
			{
				lcm = lcm(lcm,ints[i]);
			}
			System.out.println(lcm);
		}
	}

	public BigInteger lcm(BigInteger a, BigInteger b)
	{
		return a.multiply(b).divide(a.gcd(b));
	}

	//2 3 5
	//1 2 3 4
	//399 772 163 959 242

	public static void main(String[] args)
	{
		new SmallestMultiple().run();
	}

}
