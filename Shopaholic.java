import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Shopaholic {
	public static void main(String[] args) throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		StringTokenizer st = new StringTokenizer(file.readLine());
		PriorityQueue<Integer> que = new PriorityQueue<Integer>();
		for(int i = 0;i<N;i++)
			que.add(-Integer.parseInt(st.nextToken()));
		long sum = 0;
		while(que.size()>2)
		{
			que.poll();
			que.poll();
			sum+=que.poll();
		}
		System.out.println(-sum);
	}
}
