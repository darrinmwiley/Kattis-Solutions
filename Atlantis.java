import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Atlantis {

	public static void main(String[] args) throws Exception
	{
		new Atlantis().run();
	}
	
	public void run() throws Exception
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		item[] items = new item[N];
		StringTokenizer st;
		for(int i = 0;i<N;i++)
		{
			st = new StringTokenizer(file.readLine());
			int t = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			items[i] = new item(t,h);
		}
		Arrays.sort(items);
		System.out.println(schedule(items));
	}
	
	//items comes sorted by height descending
	public int schedule(item[] items)
	{
		PriorityQueue<Long> que = new PriorityQueue<Long>();
		int completed = 0;
		for(int i = 0;i<items.length;i++)
		{
			long t = items[i].height - (i == items.length - 1? 0: items[i+1].height);
			que.add(items[i].time);
			while(t != 0 && !que.isEmpty())
			{
				long next = que.poll();
				if(next > t)
				{
					que.add(next - t);
					t = 0;
				}else {
					t -= next;
					completed++;
				}
			}
		}
		return completed;
	}
	
	private class item implements Comparable<item>{
		
		long time;
		long height;
	
		public item(int t, int h)
		{
			time = t;
			height = h;
		}

		@Override
		public int compareTo(item o) {
			return Long.compare(o.height, height);
		}
		
	}
}
