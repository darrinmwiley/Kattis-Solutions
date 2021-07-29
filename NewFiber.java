import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class NewFiber {

	public static void main(String[] args) throws Exception
	{
		new NewFiber().run();
	}

	public void run() throws Exception
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] deg = new int[N];
		int sum = 0;
		for(int i = 0;i<M;i++)
		{
			st = new StringTokenizer(file.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			deg[a]++;
			deg[b]++;
			sum += 2;
		}
		need[] needs = new need[N];
		for(int i = 0;i<N;i++)
			needs[i] = new need(deg[i], i);
		Arrays.sort(needs);
		int cost = 0;
		int desiredSum = 2*(N-1);
		int index = 0;
	looperino:
		while(sum != desiredSum)
		{
			cost++;
			while(needs[index].need != 1)
			{
				if(sum == desiredSum)
					break looperino;
				needs[index].need--;
				sum--;
			}
			index++;
		}
		System.out.println(cost);
		System.out.println(N+" "+(N-1));
		LinkedList<need> list = new LinkedList<need>();
		Arrays.sort(needs);
		for(need n: needs)
			list.addFirst(n);
		while(list.size() != 1)
		{
			need small = list.removeFirst();
			need large = list.removeLast();
			large.need--;
			System.out.println(small.id+" "+large.id);
			if(large.need == 1)
				list.addFirst(large);
			else
				list.addLast(large);
		}
	}

	private class need implements Comparable<need>{

		int id;
		int need;
		public need(int need, int id)
		{
			this.id = id;
			this.need = need;
		}
		@Override
		public int compareTo(need o) {
			return o.need - need;
		}

	}
}
