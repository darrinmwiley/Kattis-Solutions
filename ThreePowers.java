import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class ThreePowers {
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		ArrayList<BigInteger> list = new ArrayList<BigInteger>();
		list.add(BigInteger.ONE);
		for(int i = 0;i<66;i++)
			list.add(list.get(list.size()-1).multiply(new BigInteger("3")));
		while(file.hasNextBigInteger())
		{
			BigInteger next = file.nextBigInteger().subtract(BigInteger.ONE);
			if(next.equals(new BigInteger("-1")))
				return;
			else if(next.equals(BigInteger.ZERO))
				System.out.println("{ }");
			else{
				String ans = "{ ";
				for(int i = 0;i<68;i++)
					if(next.testBit(i))
						ans+=(list.get(i)+", ");
				ans = ans.substring(0,ans.length()-2);
				System.out.println(ans+" }");
			}
			
		}
	}
}
