import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Terraces {
	
	StringTokenizer st;
	BufferedReader file;
	node[][] nodes;
	node[] sort;
	int[][] d = new int[][] {{1,0,-1,0},{0,1,0,-1}};
	int R;
	int C;
	
	public static void main(String[] args) throws Exception
	{
		new Terraces().run();
	}	
	
	public void run() throws Exception
	{
		//this is fast IO - faster than Scanner/System.out.println
		file = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pout = new PrintWriter(System.out);
		
		C = nextInt();
		R = nextInt();
		nodes = new node[R][C];
		sort = new node[R*C];
		for(int i = 0;i<R;i++)
		{
			for(int j = 0;j<C;j++)
			{
				nodes[i][j] = new node(i,j,nextInt());
				sort[i*C+j] = nodes[i][j];
			}
		}
		
		Arrays.sort(sort);
		
		for(int i = 0;i<sort.length;i++)
		{
			if(!sort[i].vis)
				dfs(sort[i].r, sort[i].c, true);
		}
		
		int ans = 0;
		for(int i = 0;i<sort.length;i++)
		{
			if(sort[i].good)
				ans++;
		}
		pout.println(ans);
		
		pout.flush();
		pout.close();
	}
	
	public void dfs(int r, int c, boolean good)
	{
		nodes[r][c].good = good;
		nodes[r][c].vis = true;
		for(int i = 0;i<4;i++)
		{
			int rr = r+d[0][i];
			int cc = c+d[1][i];
			if(valid(rr,cc) && !nodes[rr][cc].vis && nodes[rr][cc].height >= nodes[r][c].height)
			{
				dfs(rr,cc,nodes[rr][cc].height > nodes[r][c].height? false : good);
			}
		}
	}
	
	public boolean valid(int r, int c)
	{
		return Math.min(r,c) >= 0 && r < R && c < C;
	}
	
	private class node implements Comparable<node>{

		int r, c;
		int height;
		boolean good;
		public boolean vis;
		
		public node(int r, int c, int h)
		{
			this.r = r;
			this.c = c;
			this.height = h;
		}
		
		@Override
		public int compareTo(node arg0) {
			return Integer.compare(height, arg0.height);
		}
		
		public String toString()
		{
			return height+"";
		}
		
	}
	
	//don't worry about this, just a helper method
	public void newst()
	{
		try {
			st = new StringTokenizer(file.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//consumes the entire next line of input
	public String readLine() throws IOException
	{
		return file.readLine();
	}
	
	//get's the next word of input
	public String next()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return st.nextToken();
	}
	
	//tries to parse the next piece of input as an int
	public int nextInt()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return Integer.parseInt(st.nextToken());
	}
	
	//tries to parse the next piece of input as a long
	public long nextLong()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return Long.parseLong(st.nextToken());
	}
	
}