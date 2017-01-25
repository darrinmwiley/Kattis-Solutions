import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

public class ants {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int L = file.nextInt();
			int N = file.nextInt();
			int maxmax = 0;
			int minmax = 0;
			for(int i = 0;i<N;i++)
			{
				int x = file.nextInt();
				maxmax = Math.max(maxmax,Math.max(x,L-x));
				minmax = Math.max(minmax,Math.min(x,L-x));
			}
			System.out.println(minmax+" "+maxmax);
		}
	}
	
	public static void main(String[] args)
	{
		new ants().run();
	}
}

