import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Convoy {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new Convoy().run();		
	}

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for(int i = 0;i<N;i++)
		{
			ints.add(Integer.parseInt(file.readLine()));
		}
		Collections.sort(ints);
		PriorityQueue<event> que = new PriorityQueue<event>();
		for(int i = 0;!ints.isEmpty() && i < K;i++)
		{
			int t = ints.remove(0);
			for(int j = 0;j<4;j++)
				if(!ints.isEmpty())
					ints.remove(ints.size() - 1);
			event e = new event(t, 5, t);
			que.add(e);
		}
		long maxT = 0;
		while(N>0)
		{
			event e = que.poll();
			N -= e.num;
			maxT = Math.max(maxT, e.time);
			event next = new event(e.time + e.next*2, 4, e.next);
			que.add(next);
		}
		System.out.println(maxT);
	}

	private class event implements Comparable<event>{

		long time, num, next;

		public event(long t, long n, long nx)
		{
			time = t;
			num = n;
			next = nx;
		}

		@Override
		public int compareTo(event o) {
			int a = Long.compare(time, o.time);
			if(a == 0)
				return Long.compare(o.num,num);
			return a;
		}
	}



}
