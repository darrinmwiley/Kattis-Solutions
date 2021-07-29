import java.util.Arrays;
import java.util.Scanner;

public class Cokolada {
	
	public static void main(String[] args)
	{
		new Cokolada().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		if(N==Integer.highestOneBit(N))
		{
			System.out.println(N+" 0");
			return;
		}
		int a = Integer.highestOneBit(N<<1);
		int l2a = (int)(Math.round(Math.log(a)/Math.log(2)));
		int b = Integer.lowestOneBit(N);
		int l2b = (int)(Math.round(Math.log(b)/Math.log(2)));
		System.out.println(a+" "+(l2a-l2b));
	}
	
}
