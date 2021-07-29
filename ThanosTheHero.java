import java.io.IOException;
import java.util.Scanner;

public class ThanosTheHero {

	public static void main(String[] args) throws IOException
	{
		new ThanosTheHero().run();
	}

	public void run() throws IOException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		long[] ints = new long[N];
		for(int i = 0;i<N;i++)
			ints[i] = file.nextInt();
		long tot = 0;
		for(int i = ints.length - 2;i>=0;i--)
		{
			if(ints[i] >= ints[i+1])
			{
				tot += ints[i] - ints[i+1] + 1;
				ints[i] = ints[i+1] -1;
			}
		}
		if(ints[0] <0)
			System.out.println(1);
		else
			System.out.println(tot);
	}

}
