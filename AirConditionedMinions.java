

import java.util.PriorityQueue;
import java.util.Scanner;

public class AirConditionedMinions {

	int ans = 0;

	public static void main(String[] args) throws Exception
	{
		new AirConditionedMinions().run();
	}

	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		boolean[] covered = new boolean[N];
		PriorityQueue<event> end = new PriorityQueue<event>();
		int[] starts = new int[N];
		int[] ends = new int[N];
		for(int i = 0;i<N;i++)
		{
			starts[i] = file.nextInt();
			ends[i] = file.nextInt();
			end.add(new event(ends[i],i));
		}
		while(!end.isEmpty())
		{
			event e = end.poll();
			if(!covered[e.i])
				cover(e.x,starts,ends,covered);
		}
		System.out.println(ans);
	}

	public void cover(int x, int[] starts, int[] ends, boolean[] covered)
	{
		for(int i = 0;i<starts.length;i++)
			if(starts[i]<=x&&ends[i]>=x)
				covered[i] = true;
		ans++;
	}

	private class event implements Comparable<event>{

		int x;
		int i;

		public event(int x, int i)
		{
			this.x = x;
			this.i = i;
		}

		@Override
		public int compareTo(event o) {
			return Integer.compare(x,o.x);
		}

	}

}
