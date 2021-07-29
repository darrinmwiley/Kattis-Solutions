import java.math.BigInteger;
import java.util.Scanner;

public class RationalRatio {

	public static void main(String[] args)
	{
		new RationalRatio().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		String input = file.next();
		int repeating = file.nextInt();
		String[] strs = input.split("\\.");
		int before = strs[0].length();
		int after = strs[1].length()-repeating;
		String beforeValue = strs[0];
		String afterValue = strs[1].substring(0,after);
		if(afterValue.isEmpty())
			afterValue="0";
		String repeatingValue = strs[1].substring(after);
		BigInteger num1 = new BigInteger(beforeValue).multiply(tenx(after)).add(new BigInteger(afterValue));
		frac value1 = new frac(num1,BigInteger.ONE);
		BigInteger num2 = new BigInteger(repeatingValue);
		BigInteger denom2 = tenx(repeating).subtract(BigInteger.ONE);
		frac value2 = new frac(num2,denom2);
		frac ansnum = value1.add(value2);
		frac anstot = ansnum.divide(tenx(after));
		System.out.println(anstot);
	}

	public BigInteger tenx(int n)
	{
		String x = "1";
		for(int i = 0;i<n;i++)
			x+="0";
		return new BigInteger(x);
	}

	private class frac{

		BigInteger num;
		BigInteger denom;

		public frac(BigInteger num,BigInteger denom)
		{
			this.num = num;
			this.denom = denom;
			reduce();
		}

		public frac add(frac f)
		{
			BigInteger lcm = denom.multiply(f.denom).divide(denom.gcd(f.denom));
			BigInteger multa = lcm.divide(denom);
			BigInteger multb = lcm.divide(f.denom);
			return new frac(num.multiply(multa).add(f.num.multiply(multb)),lcm);
		}

		public void reduce()
		{
			BigInteger gcd = num.gcd(denom);
			num = num.divide(gcd);
			denom = denom.divide(gcd);
		}

		public frac multiply(frac f)
		{
			return new frac(num.multiply(f.num),denom.multiply(f.denom));
		}

		public frac divide(BigInteger x)
		{
			return new frac(num,denom.multiply(x));
		}

		public String toString()
		{
			return num+"/"+denom;
		}

	}

}
