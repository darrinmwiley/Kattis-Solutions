import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Chanukah {
	
	public static void main(String[] args) throws Exception
	{
		new Chanukah().run();
	}
	
	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		for(int i = 0;i<N;i++)
		{
			int a = file.nextInt();
			int b = file.nextInt();
			System.out.println(i+1+" "+(((b+1)*b)/2+b));
		}
	}
}
