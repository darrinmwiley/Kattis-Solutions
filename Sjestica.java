import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Sjestica {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt()-3;
		System.out.println((N*N*N*N+6*N*N*N+11*N*N+6*N)/24);
	}
	
	public static void main(String[] args)
	{
		new Sjestica().run();
	}
}

