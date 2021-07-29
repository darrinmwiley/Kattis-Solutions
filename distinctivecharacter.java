import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class distinctivecharacter {
	
	int INF = 100;
	
	public static void main(String[] args) throws Exception
	{
		new distinctivecharacter().run();		
	}
	
	public void run() throws Exception
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] fp = new int[1<<M];
		Arrays.fill(fp, INF);
		Queue<state> que = new LinkedList<state>();
		for(int i = 0;i<N;i++)
		{
			int x = Integer.parseInt(file.readLine(), 2);
			fp[x] = 0;
			que.add(new state(x, 0));
		}
		while(!que.isEmpty())
		{
			state state = que.poll();
			for(int i = 0;i<M;i++)
			{
				int next = state.location^(1<<i);
				if(fp[next] == INF)
				{
					fp[next] = state.cost + 1;
					que.add(new state(next, state.cost + 1));
				}
			}
		}
		int bestLocation = 0;
		for(int i = 1;i<fp.length;i++)
		{
			if(fp[i] > fp[bestLocation])
			{
				bestLocation = i;
			}
		}
		String ans = (Integer.toBinaryString(bestLocation));
		while(ans.length() != M)
		{
			ans = "0"+ans;
		}
		System.out.println(ans);
	}
	
	private class state{
		
		int location;
		int cost;
		
		public state(int l, int c)
		{
			this.location = l;
			this.cost = c;
		}
		
	}
	
}
