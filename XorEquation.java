import java.util.ArrayList;
import java.util.Scanner;

public class XorEquation {

	public static void main(String[] args)
	{
		new XorEquation().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		String a = file.next();
		file.next();
		String b = file.next();
		file.next();
		String c = file.next();
		int ac = count(a);
		int bc = count(b);
		int cc = count(c);
		if(ac > bc)
		{
			String save = a;
			a = b;
			b = save;
			int sv = ac;
			ac = bc;
			bc = sv;
		}
		if(bc > cc)
		{
			String save = b;
			b = c;
			c = save;
			int sv = bc;
			bc = cc;
			cc = sv;
		}
		if(ac > bc)
		{
			String save = a;
			a = b;
			b = save;
			int sv = ac;
			ac = bc;
			bc = sv;
		}
		ArrayList<String> first = new ArrayList<String>();
		ArrayList<String> second = new ArrayList<String>();
		permute(a.length() > 1 && a.startsWith("?"),"",0,ac,first);
		permute(b.length() > 1 && b.startsWith("?"),"",0,bc,second);
		int ans = 0;
		for(String f:first)
		{
			for(String g:second)
			{
				int A = make(f,a);
				int B = make(g,b);
				int potentialC = A^B;
				if(fit(potentialC+"",c))
				{
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	public boolean fit(String s, String c)
	{
		if(s.length() != c.length())
			return false;
		for(int i = 0;i<s.length();i++)
		{
			if(c.charAt(i)!='?' && s.charAt(i) != c.charAt(i))
				return false;
		}
		return true;
	}

	public int make(String inject, String current)
	{
		int ans = 0;
		int index = 0;
		for(int i = 0;i<current.length();i++)
		{
			ans *= 10;
			if(current.charAt(i)=='?')
				ans += inject.charAt(index++)-'0';
			else
				ans += current.charAt(i) - '0';
		}
		return ans;
	}

	public int count(String s)
	{
		int c = 0;
		for(char ch:s.toCharArray())
			if(ch=='?')
				c++;
		return c;
	}

	public void permute(boolean zero, String curr, int index, int bits, ArrayList<String> list)
	{
		if(bits == index) {
			list.add(curr);
			return;
		}
		for(int i = 1;i<=9;i++)
			permute(false, curr+i,index+1,bits,list);
		if(!zero)
			permute(false,curr+0,index+1,bits,list);
	}
}
