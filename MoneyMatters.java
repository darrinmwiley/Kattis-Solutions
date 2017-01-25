import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MoneyMatters {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] sums = new int[N];
		for(int i = 0;i<N;i++)
			sums[i] = Integer.parseInt(br.readLine());
		ArrayList[] con = new ArrayList[N];
		for(int i = 0;i<con.length;i++)
			con[i] = new ArrayList<Integer>();
		for(int i = 0;i<M;i++)
		{
			String line = br.readLine();
			st = new StringTokenizer(line);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			con[a].add(b);
			con[b].add(a);
		}
		boolean[] vis = new boolean[N];
		for(int i = 0;i<vis.length;i++)
		{
			if(sum(i,vis,con,sums)!=0)
			{
				System.out.println("IMPOSSIBLE");
				return;
			}
		}
		System.out.println("POSSIBLE");
	}
	
	public static int sum(int start,boolean[] vis, ArrayList[] con, int[] sums)
	{
		if(vis[start])
			return 0;
		vis[start] = true;
		int amt = sums[start];
		ArrayList<Integer> ints = con[start];
		for(int i:ints)
		{
			amt+=sum(i,vis,con,sums);
		}
		return amt;
	}
}
