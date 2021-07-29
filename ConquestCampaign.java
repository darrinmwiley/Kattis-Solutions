import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ConquestCampaign {
	
	public static void main(String[] args) throws IOException
	{
		new ConquestCampaign().run();
	}
	
	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		Queue<Integer> que = new LinkedList<Integer>();
		for(int i = 0;i<x;i++)
		{
			st = new StringTokenizer(file.readLine());
			que.add(Integer.parseInt(st.nextToken())-1);
			que.add(Integer.parseInt(st.nextToken())-1);
			que.add(1);
		}
		int[][] d = new int[][]{{1,0,-1,0},{0,1,0,-1}};
		boolean[][] shad = new boolean[r][c];
		int covered = 0;
		int maxday = 0;
	loop:
		while(!que.isEmpty())
		{
			int rr = que.poll();
			int cc = que.poll();
			int day = que.poll();
			if(shad[rr][cc])
				continue;
			covered++;
			shad[rr][cc] = true;
			maxday = day;
			if(covered == r*c)
			{
				break;
			}
			
			shad[rr][cc] = true;
			for(int i = 0;i<4;i++)
			{
				int nr = rr+d[0][i];
				int nc = cc+d[1][i];
				if(val(nr,nc,shad)&&!shad[nr][nc])
				{
					que.offer(nr);
					que.offer(nc);
					que.offer(day+1);
				}
			}
		}
		System.out.println(maxday);
	}
	
	public boolean val(int r, int c, boolean[][] ints)
	{
		return Math.min(r,c) >= 0 && r < ints.length && c < ints[r].length;
	}
	
}
