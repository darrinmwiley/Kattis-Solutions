import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Areal {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		long zz = file.nextLong();
		System.out.println(4*Math.sqrt(zz));
	}
	
	public static void main(String[] args)
	{
		new Areal().run();
	}
}
