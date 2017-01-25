import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Ptice {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int len = file.nextInt();
		String a = "ABC";
		String b = "BABC";
		String c = "CCAABB";
		int x = 0;
		int y = 0;
		int z = 0;
		char[] next = file.next().toCharArray();
		for(int i = 0;i<next.length;i++)
		{
			if(a.charAt(i%a.length())==next[i])
				x++;
			if(b.charAt(i%b.length())==next[i])
				y++;
			if(c.charAt(i%c.length())==next[i])
				z++;
		}
		int max = Math.max(Math.max(x,y),z);
		System.out.println(max);
		if(x==max)
			System.out.println("Adrian");
		if(y==max)
			System.out.println("Bruno");
		if(z==max)
			System.out.println("Goran");
	}
	
	public static void main(String[] args)
	{
		new Ptice().run();
	}
}

