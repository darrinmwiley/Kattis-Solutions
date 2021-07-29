import java.util.*;
import java.io.*;

public class Alehouse{

	BufferedReader file;
	PrintWriter pout;
	StringTokenizer st;

	public static void main(String[] args) throws Exception
	{
		new Alehouse().run();
	}

	public void run() throws Exception{
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);
		st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		PriorityQueue<event> que = new PriorityQueue<event>();
		for(int i = 0;i<N;i++)
		{
			st = new StringTokenizer(file.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			event s = new event(start - K, true);
			event e = new event(end, false);
			que.add(s);
			que.add(e);
		}
		int active = 0;
		int max = 0;
		while(!que.isEmpty())
		{
			event e = que.poll();
			if(e.open) {
				active++;
				max = Math.max(max, active);
			}else
				active--;
		}
		System.out.println(max);
	}

	private class event implements Comparable<event>{

		int T;
		boolean open;

		public event(int T, boolean open)
		{
			this.T = T;
			this.open = open;
		}

		@Override
		public int compareTo(event arg0) {
			int comp = Integer.compare(T, arg0.T);
			if(comp == 0)
			{
				return Boolean.compare(arg0.open, open);
			}
			return comp;
		}

	}

}
