import java.util.Arrays;
import java.util.Scanner;

public class OpeningCeremony {
	
	public static void main(String[] args)
	{
		new OpeningCeremony().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
			ints[i] =file.nextInt();
		int best = N;
		Arrays.sort(ints);
		for(int i = 0;i<N;i++)
			best = Math.min(best,ints[i]+N-i-1);
		System.out.println(best);
	}
	
}
