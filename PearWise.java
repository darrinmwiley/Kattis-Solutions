import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class PearWise {
	
	public static void main(String[] args)
	{
		new PearWise().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		int[][] vote = new int[N][N];
		for(int i = 0;i<M;i++)
		{
			int K = file.nextInt();
			char[] ch = file.next().toCharArray();
			for(int j = 0;j<ch.length;j++)
			{
				for(int k = j+1;k<ch.length;k++)
				{
					int a = ch[j] - 'A';
					int b = ch[k] - 'A';
					vote[a][b] += K;
					vote[b][a] -= K;
				}
			}
		}
		for(int i = 0;i<N;i++)
		{
			if(canWin(vote, i))
			{
				System.out.println((char)(i+'A')+": can win");
			}else
				System.out.println((char)(i+'A')+": can't win");
		}
	}
	
	public boolean canWin(int[][] vote, int x)
	{
		boolean changed = false;
		boolean[] use = new boolean[vote.length];
		use[x] = true;
		while(true)
		{
			changed = false;
			for(int i = 0;i<vote.length;i++)
			{
				for(int j =0;j<vote.length;j++)
				{
					if(use[i] && !use[j] && vote[i][j] > 0)
					{
						changed = true;
						use[j] = true;
					}
				}
			}
			if(!changed)
				break;
		}
		int y = 0;
		for(boolean b:use)
			if(b)
				y++;
		return y == vote.length;
	}
}
