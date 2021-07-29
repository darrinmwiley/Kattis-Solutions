import java.util.Arrays;
import java.util.Scanner;

public class FroshWeek {
	
	public static void main(String[] args)
	{
		new FroshWeek().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		int[] tasks = new int[N];
		int[] intervals = new int[M];
		for(int i = 0;i<N;i++)
			tasks[i] = file.nextInt();
		for(int j =0;j<M;j++)
			intervals[j] = file.nextInt();
		Arrays.sort(tasks);
		Arrays.sort(intervals);
		int ptr = 0;
		for(int i:intervals)
		{
			if(ptr !=tasks.length && tasks[ptr] <= i)
			{
				ptr++;
			}
		}
		System.out.println(ptr);
	}
	

	
}
