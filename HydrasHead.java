import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HydrasHead {

	public static void main(String[] args)
	{
		new HydrasHead().run();
	}

	public void run()
	{
		int[][] ints = new int[500][500];
		for(int[] in:ints)
			Arrays.fill(in, Integer.MAX_VALUE/4);
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(2);
		que.add(0);
		que.add(1);
		while(!que.isEmpty())
		{
			int H = que.poll();
			int T = que.poll();
			int c = que.poll();
			if(val(H,T))
			{
				if(ints[H][T] > c)
				{
					ints[H][T] = c;
					if(T != 1)
					{
						que.add(H);
						que.add(T-1);
						que.add(c+1);
					}
					que.add(H-1);
					que.add(T+2);
					que.add(c+1);
					que.add(H+2);
					que.add(T);
					que.add(c+1);
				}
			}
		}
		Scanner file = new Scanner(System.in);
		while(true) {
			int a = file.nextInt();
			int b = file.nextInt();
			if(a==0 && b == 0)
			{
				return;
			}else {
				int ans = ints[a][b];
				if(ans == Integer.MAX_VALUE/4)
					System.out.println(-1);
				else
					System.out.println(ans);
			}
		}
	}

	public boolean val(int H, int T)
	{
		return Math.min(H,T) >=0 && H < 500 && T < 500;
	}

}
