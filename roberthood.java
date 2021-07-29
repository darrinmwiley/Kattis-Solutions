package page;

import java.util.Arrays;
import java.util.Scanner;

public class roberthood {
	
	int[] highest;
	int[] lowest;
	int[] x;
	int[] y;
	
	public static void main(String[] args) throws Exception
	{
		new roberthood().run();
	}
	
	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		highest = new int[2001];
		lowest = new int[2001];
		Arrays.fill(highest, -1);
		Arrays.fill(lowest, -1);
		x = new int[N];
		y = new int[N];
		for(int i = 0;i<N;i++)
		{
			int xx = file.nextInt()+1000;
			int yy = file.nextInt();
			x[i] = xx;
			y[i] = yy;
			if(highest[xx] == -1)
				highest[xx] = i;
			if(lowest[xx] == -1)
				lowest[xx] = i;
			int chx = y[highest[xx]];
			int clx = y[lowest[xx]];
			if(yy>chx)
				highest[xx] = i;
			if(yy<clx)
				lowest[xx] = i;
		}
		int highSquare = 0;
		for(int i = 0;i<2001;i++)
		{
			for(int j = i;j<2001;j++)
			{
				int topi = highest[i];
				int topj = highest[j];
				int boti = lowest[i];
				int botj = lowest[j];
				highSquare = Math.max(highSquare,sqrdist(topi,topj));
				highSquare = Math.max(highSquare,sqrdist(topi,botj));
				highSquare = Math.max(highSquare,sqrdist(boti,botj));
				highSquare = Math.max(highSquare,sqrdist(boti,topj));
			}
		}
		System.out.println(Math.sqrt(highSquare));
	}
	
	public int sqrdist(int i, int j)
	{
		if(i==-1 || j == -1)
			return 0;
		return (x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]);
	}

}


