import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class InverseFactorial {
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		String next = file.next();
		String[] facts = "1 2 6 24 120 720".split(" ");
		for(int i = 0;i<facts.length;i++)
			if(next.equals(facts[i]))
			{
				System.out.println(i+1);
				return;
			}
		int log = next.length()-1;
		double x = 0;
		int i = 1;
		while(x<log)
		{
			x+=Math.log10(i++);
		}
		System.out.println(i-1);
	}
	
	public static int count(char ch, String str)
	{
		int c = 0;
		for(int i = 0;i<str.length();i++)
			if(str.charAt(i)==ch)
				c++;
		return c;
	}
}
