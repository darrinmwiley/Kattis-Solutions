import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Karte {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		String[] suits = "P K H T".split(" ");
		String[] numbers = "01 02 03 04 05 06 07 08 09 10 11 12 13".split(" ");
		String next = file.next();
		int[] missing = new int[4];
		for(int i = 0;i<suits.length;i++)
		{
			for(String s:numbers)
			{
				if(next.indexOf(suits[i]+s)!=next.lastIndexOf(suits[i]+s))
				{
					System.out.println("GRESKA");
					return;
				}
				if(next.indexOf(suits[i]+s)==-1)
					missing[i]++;
			}
		}
		System.out.printf("%d %d %d %d",missing[0],missing[1],missing[2],missing[3]);
	}
	
	public static void main(String[] args)
	{
		new Karte().run();
	}
}

