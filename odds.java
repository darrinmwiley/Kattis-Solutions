/*
Group members:
Biranchi Padhi
Darrin Wiley
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class odds{
	
	BufferedReader file;
	PrintWriter pout;
	StringTokenizer st;

	public static void main(String[] args) throws Exception
	{
		new odds().run();
	}
	
	public void run() throws Exception{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringTokenizer st;
		while((line = file.readLine()) != null)
		{
			if(line.equals("0 0 0 0"))
				return;
			st = new StringTokenizer(line);
			char a = st.nextToken().charAt(0);
			char b = st.nextToken().charAt(0);
			char c = st.nextToken().charAt(0);
			char d = st.nextToken().charAt(0);
			int total = 0;
			int win = 0;
			for(int aa = 1;aa<=6;aa++)
			{
				for(int bb = 1;bb<=6;bb++)
				{
					for(int cc = 1;cc<=6;cc++)
					{
						for(int dd = 1;dd<=6;dd++)
						{
							if(consistent(a,b,c,d,aa,bb,cc,dd))
							{
								//System.out.print(aa+" "+bb+" "+cc+" "+dd+" consistent");
								total++;
								if(win(aa,bb,cc,dd)) {
									win++;
									//System.out.println(" win");
								}else {
									//System.out.println(" lose");
								}
							}
						}
					}
				}
			}
			int div = BigInteger.valueOf(total).gcd(BigInteger.valueOf(win)).intValue();
			total /= div;
			win /= div;
			if(total == 1)
				System.out.println(win);
			else {
				System.out.println(win+"/"+total);
			}
		}
	}
	
	//given, test
	public boolean consistent(char a, char b, char c, char d, int aa, int bb, int cc, int dd)
	{
		if(a != '*' && a-'0' != aa)
			return false;
		if(b != '*' && b-'0' != bb)
			return false;
		if(c != '*' && c-'0' != cc)
			return false;
		if(d != '*' && d-'0' != dd)
			return false;
		return true;
	}
	
	public boolean win(int a, int b, int c, int d)
	{
		if(a < b)
		{
			int x = a;
			a = b;
			b = x;
		}
		if(c < d)
		{
			int x = c;
			c = d;
			d = x;
		}
		if(c == 2 && d == 1)
			return false;
		if(a == 2 && b == 1)
		{
			return true;
		}
		if(c == d)
		{
			if(a != b)
				return false;
		}
		if(a == b)
		{
			if(c == d)
			{
				if(a > c)
					return true;
				return false;
			}
			return true;
		}
		if(a > c)
		{
			return true;
		}
		if(a == c && b > d)
			return true;
		return false;
	}
	
}