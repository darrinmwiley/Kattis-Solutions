import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Pizza2 {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int R = file.nextInt();
		int C = file.nextInt();
		System.out.println(100.0*(R-C)*(R-C)/(R*R));
	}
	
	public static void main(String[] args)
	{
		new Pizza2().run();
	}
}

