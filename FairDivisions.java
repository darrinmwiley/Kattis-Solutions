import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FairDivisions{

	HashSet<String> ans = new HashSet<String>();

	public static void main(String[] args) throws IOException
	{
		new FairDivisions().run();
	}

	public void run() throws IOException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int[] x = new int[N];
		int[] y = new int[N];
		for(int i = 0;i<N;i++)
		{
			x[i] = file.nextInt();
			y[i] = file.nextInt();
		}
		if(N % 2 != 0)
		{
			System.out.println(0);
		}
		for(int i = 0;i<N;i++)
		{
			PriorityQueue<event> que = new PriorityQueue<event>();
			int cx = x[i];
			int cy = y[i];
			int total = 1;
			boolean[] blue = new boolean[N];
			blue[i] = true;
			for(int j = 0;j<N;j++)
			{
				if(i == j)
					continue;
				int nx = x[j];
				int ny = y[j];
				if(ny > cy || (ny == cy && nx > cx))
				{
					que.add(new event(nx-cx,ny-cy, 1, j));
				}else {
					que.add(new event(cx-nx,cy-ny, -1, j));
					blue[j] = true;
					total++;
				}
			}
			if(total == N/2)
			{
				ans.add(Arrays.toString(blue));
			}
			while(!que.isEmpty())
			{
				event current = que.poll();
				total += current.delta;
				blue[current.id] = !blue[current.id];
				while(!que.isEmpty() &&que.element().compareTo(current) == 0)
				{
					event equal = que.poll();
					total += equal.delta;
					blue[equal.id] = !blue[equal.id];
				}
				if(total == N/2)
				{
					ans.add(Arrays.toString(blue));
					ans.add(Arrays.toString(negate(blue)));
				}
			}
		}
		System.out.println(ans.size());
	}

	public boolean[] negate(boolean[] bools)
	{
		boolean[] ans = new boolean[bools.length];
		for(int i = 0;i<ans.length;i++)
			ans[i] = !bools[i];
		return ans;
	}

	private class event implements Comparable<event>{

		int x, y, id;
		int delta;

		public event(int x, int y, int d, int id)
		{
			this.x = x;
			this.y = y;
			this.delta = d;
			this.id = id;
		}

		@Override
		public int compareTo(event o) {
			int a = (int)Math.signum(x*o.y - y*o.x);
            return a;
		}

	}
}
