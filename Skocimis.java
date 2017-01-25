import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Skocimis {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int a = file.nextInt();
		int b = file.nextInt();
		int c = file.nextInt();
		System.out.println(Math.max(b-a,c-b)-1);
	}
	
	public static void main(String[] args)
	{
		new Skocimis().run();
	}
}

