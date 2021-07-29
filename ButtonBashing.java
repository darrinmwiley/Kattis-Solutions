import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ButtonBashing {

	StringTokenizer st;
	BufferedReader file;

	int[][] d = new int[][] {{1,0,-1,0},{0,1,0,-1}};
	int R,C;

	public static void main(String[] args) throws Exception
	{
		new ButtonBashing().run();
	}

	public void run() throws Exception
	{
		//this is fast IO - faster than Scanner/System.out.println
		file = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pout = new PrintWriter(System.out);

		int zz = nextInt();
	loop:
		for(int z = 0;z<zz;z++)
		{
			int N = nextInt();
			int G = nextInt();
			int[] fp = new int[3601];
			int[] buttons = new int[N];
			for(int i = 0;i<N;i++)
				buttons[i] = nextInt();
			Arrays.fill(fp, 3601);
			Queue<Integer> que = new LinkedList<Integer>();
			que.add(0);
			que.add(0);
			while(!que.isEmpty())
			{
				int position = que.poll();
				int cost = que.poll();
				if(fp[position] == 3601)
				{
					fp[position] = cost;
					for(int i = 0;i<N;i++)
					{
						que.add(Math.max(Math.min(position + buttons[i], 3600), 0));
						que.add(cost + 1);
					}
				}
			}
			for(int i = G;i<fp.length;i++)
			{
				if(fp[i] != 3601)
				{
					System.out.println(fp[i]+" "+(i-G));
					continue loop;
				}
			}
		}

		pout.flush();
		pout.close();
	}

	boolean val(int r, int c)
	{
		return Math.min(r,c) >= 0 && r < R && c < C;
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
