import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ZebrasOcelots {

	public static void main(String[] args) throws FileNotFoundException
	{
		new ZebrasOcelots().run();
	}

	public void run() throws FileNotFoundException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		long ans = 0;
		for(int i = 0;i<N;i++)
		{
			ans*=2;
			if(file.next().equals("O"))
				ans++;
		}
		System.out.println(ans);
	}
}
