import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CoastLength {
	
	char[][] chars;
	boolean[][] bools;
	
	public static void main(String[] args) throws IOException
	{
		new CoastLength().run();
	}
	
	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		chars = new char[r][c];
		for(int i = 0;i<r;i++)
			chars[i] = file.readLine().trim().toCharArray();
		bools = new boolean[r][c];
		for(int i = 0;i<r;i++)
		{
			flood(i,0);
			flood(i,c-1);
		}
		for(int i = 0;i<c;i++)
		{
			flood(0,i);
			flood(r-1,i);
		}
		int[][] d = new int[][]{{1,0,-1,0},{0,1,0,-1}};
		int sum = 0;
		for(int i = 0;i<r;i++)
			for(int j = 0;j<c;j++)
			{
				for(int k = 0;k<4;k++)
				{
					int rr = i+d[0][k];
					int cc = j+d[1][k];
					if(val(rr,cc)&&bools[rr][cc]&&chars[i][j]=='1')
						sum++;
				}
			}
		for(int i = 0;i<r;i++)
		{
			if(chars[i][0]=='1')
				sum++;
			if(chars[i][c-1]=='1')
				sum++;
		}
		for(int i = 0;i<c;i++)
		{
			if(chars[0][i]=='1')
				sum++;
			if(chars[r-1][i]=='1')
				sum++;
		}
		System.out.println(sum);
	}
	
	public void flood(int r, int c)
	{
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(r);
		que.add(c);
		int[][] d = new int[][]{{1,0,-1,0},{0,1,0,-1}};
		while(!que.isEmpty())
		{
			int R = que.poll();
			int C = que.poll();
			if(bools[R][C]||(chars[R][C]!='0'))
				continue;
			bools[R][C] = true;
			for(int i = 0;i<4;i++)
			{
				int rr = R+d[0][i];
				int cc = C+d[1][i];
				if(val(rr,cc)&&chars[rr][cc]=='0'&&!bools[rr][cc])
				{
					que.add(rr);
					que.add(cc);
				}
			}
		}
	}
	
	public boolean val(int r, int c)
	{
		return Math.min(r,c)>=0&&r<bools.length&&c<bools[0].length;
	}
}
