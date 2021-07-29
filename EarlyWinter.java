import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class EarlyWinter {
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new EarlyWinter().run();		
	}
	
	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		for(int i = 0;i<N;i++)
		{
			if(file.nextInt() <= M) {
				System.out.println("It hadn't snowed this early in "+i+" years!");
				return;
			}
		}
		System.out.println("It had never snowed this early!");
	}
	
}
