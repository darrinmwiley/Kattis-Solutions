import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Veci {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int x = file.nextInt();
		for(int i = x+1;i<=999999;i++)
			if(eq(dig(i),dig(x)))
			{
				System.out.println(i);
				return;
			}
		System.out.println(0);
	}
	
	public int[] dig(int x)
	{
		int[] ret = new int[10];
		while(x!=0)
		{
			ret[x%10]++;
			x/=10;
		}
		return ret;
	}
	
	public boolean eq(int[] a, int[] b)
	{
		if(a.length!=b.length)
			return false;
		for(int i = 0;i<a.length;i++)
			if(a[i]!=b[i])
				return false;
		return true;
	}
	
	public static void main(String[] args)
	{
		new Veci().run();
	}
}

