import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class LeftBeehind {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(file.hasNext())
		{
			int a = file.nextInt();
			int b = file.nextInt();
			if(a+b==0)
				return;
			if(a+b==13)
				System.out.println("Never speak again.");
			else if(a>b)
				System.out.println("To the convention.");
			else if(b>a)
				System.out.println("Left beehind.");
			else
				System.out.println("Undecided.");
		}
		
	}
	
	public static void main(String[] args)
	{
		new LeftBeehind().run();
	}
}

