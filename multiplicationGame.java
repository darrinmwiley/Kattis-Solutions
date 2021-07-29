package codejam;

import java.io.IOException;
import java.util.Scanner;

public class multiplicationGame {

	int[] pred;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new multiplicationGame().run();
	}
	
	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		while(file.hasNext())
		{
			long next = file.nextLong();
			long current = 9;
			boolean win = true;
			while(current<next)
			{
				current*=win?2:9;
				win = !win;
			}
			if(win)
				System.out.println("Stan wins.");
			else
				System.out.println("Ollie wins.");
		}
	}
	
	public boolean solve(int number, int goal)
	{
		for(int i = 2;i<=9;i++)
		{
			if(number*i>=goal)
				return true;
			if(!solve(number*i,goal))
				return true;
		}
		return false;
	}
	
}
