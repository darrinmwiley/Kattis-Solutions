import java.util.ArrayList;
import java.util.Scanner;

public class Doorman {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int max = file.nextInt();
		char[] chars = file.next().toCharArray();
		int m = 0;
		int w = 0;
		int first = 0;
		int second = 1;
		for(int i = 0;i<chars.length;i++)
		{
			if(m==w)
			{
				if(chars[first]=='M')
					m++;
				else
					w++;
				first = second++;
			}
			else if(m>w)
			{
				if(chars[first]=='W')
				{
					w++;
					first = second++;
				}else if(second<chars.length&&chars[second]=='W')
				{
					w++;
					second++;
				}else
				{
					m++;
					first = second++;
				}
			}else if(w>m)
			{
				if(chars[first]=='M')
				{
					m++;
					first = second++;
				}else if(second<chars.length&&chars[second]=='M')
				{
					m++;
					second++;
				}else
				{
					w++;
					first = second++;
				}
			}
			if(Math.abs(m-w)>max)
			{
				System.out.println(i);
				return;
			}
		}
		System.out.println(chars.length);
	}
	
	public static void main(String[] args)
	{
		new Doorman().run();
	}
}
