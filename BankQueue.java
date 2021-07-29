import java.util.Arrays;
import java.util.Scanner;

public class BankQueue {
	
	int[] amts;
	boolean[] used;
	int[] times;
	
	public static void main(String[] args)
	{
		new BankQueue().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		used = new boolean[N];
		amts = new int[N];
		times = new int[N];
		for(int i = 0;i<N;i++)
		{
			amts[i] = file.nextInt();
			times[i] = file.nextInt();
		}
		int ans = 0;
		for(int i = M-1;i>=0;i--)
		{
			int next = pick(i);
			if(next!=-1)
			{
				ans+=amts[next];
				used[next] = true;
			}
		}
		System.out.println(ans);
	}
	
	public int pick(int earliest)
	{
		int best = -1;
		for(int i = 0;i<amts.length;i++)
		{
			if(!used[i]&&times[i]>=earliest&&(best==-1||amts[i]>amts[best]))
			{
				best = i;
			}
		}
		return best;
	}
}
