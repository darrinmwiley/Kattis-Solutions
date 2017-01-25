import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Herman {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int R = file.nextInt();
		System.out.println(Math.PI*R*R);
		System.out.println(2*R*R);
	}
	
	public static void main(String[] args)
	{
		new Herman().run();
	}
}
