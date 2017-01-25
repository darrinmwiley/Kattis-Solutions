import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Anagram {
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		BigInteger[] fact = new BigInteger[101];
		fact[0] = BigInteger.ONE;
		for(int i = 1;i<fact.length;i++)
			fact[i] = new BigInteger(i+"").multiply(fact[i-1]);
		while(file.hasNext())
		{
			String next = file.next();
			BigInteger ans = fact[next.length()];
			for(int i = 0;i<26;i++)
			{
				ans = ans.divide(fact[count((char)(97+i),next)]);
				ans = ans.divide(fact[count((char)(65+i),next)]);
			}
			System.out.println(ans);
		}
		
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
