import java.util.Scanner;

public class HeirsDilemma {

	public static void main(String[] args)
	{
		new HeirsDilemma().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int L = file.nextInt();
		int H = file.nextInt();
		int ans = 0;
		for(int i = L;i<=H;i++)
		{
			if(test(i))
				ans++;
		}
		System.out.println(ans);
	}

	public boolean test(int x)
	{
		String s = x+"";
		for(int i = 0;i<s.length();i++)
		{
			for(int j = i+1;j<s.length();j++)
			{
				if(s.charAt(i) == s.charAt(j))
				{
					return false;
				}
			}
			int y = s.charAt(i)-'0';
			if(y==0)
				return false;
			if(x % y != 0)
				return false;
		}
		return true;
	}

}
