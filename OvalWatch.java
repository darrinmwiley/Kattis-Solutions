import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class OvalWatch {
	public static void main(String[] args) throws IOException {
		new OvalWatch().run();
	}

	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		event[] events = new event[K*2];
		for(int i = 0;i<K;i++)
		{
			st = new StringTokenizer(file.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			events[i*2] = new event(b,a,a+1);
			events[i*2+1] = new event(b,a+1,a);
		}
		TreeSet<event>[] cols = new TreeSet[N];
		for(int i = 0;i<N;i++)
			cols[i] = new TreeSet<event>();
		for(event e:events)
		{
			cols[e.source].add(e);
		}
		int[] ans = new int[N];
		for(int i = 0;i<N;i++)
		{
			int r = 0;
			int c = i;
			while(true)
			{
				event lower = cols[c].ceiling(new event(r,0,0));
				if(lower == null)
				{
					ans[i] = c;
					break;
				}
				r = lower.time+1;
				c = lower.dest;
			}
		}
		StringBuilder output = new StringBuilder("");
		for(int x:ans)
			output.append(x+" ");
		System.out.println(output.toString().trim());
	}

	private class event implements Comparable<event>
	{
		int time;
		int dest;
		int source;

		public event(int t,int s, int d)
		{
			time = t;
			source = s;
			dest = d;
		}
		@Override
		public int compareTo(event o) {
			return Integer.compare(time,o.time);
		}

		public String toString()
		{
			return source+"-"+dest+","+time;
		}


	}
}
