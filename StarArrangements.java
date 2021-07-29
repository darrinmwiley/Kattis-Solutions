import java.math.BigInteger;
import java.util.Scanner;

public class StarArrangements {
	
	public static void main(String[] args)
	{
		new StarArrangements().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		System.out.println(N+":");
		for(int i = 2;i<N;i++)
		{
			if((N>=i+i-1)&&N%(i+i-1) == 0 || N%(i+i-1) == i)
			{
				System.out.println(i+","+(i-1));
			}if(N%i == 0)
			{
				System.out.println(i+","+i);
			}
		}
	}
}
