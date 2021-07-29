import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class hurricanedanger {
	
	StringTokenizer st;
	BufferedReader file;
	PrintWriter pout;
	
	public static void main(String[] args) throws Exception
	{
		new hurricanedanger().run();
	}	
	
	double epsilon = 0;
	
	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		
		PrintWriter pout = new PrintWriter(System.out);
		
		int zz = nextInt();
		for(int z = 0;z<zz;z++)
		{
			long x1 = nextInt();
			long y1 = nextInt();
			long x2 = nextInt();
			long y2 = nextInt();
			long C = nextInt();
			long minDist = Long.MAX_VALUE;
			ArrayList<String> danger = new ArrayList<String>();
			for(int i = 0;i<C;i++)
			{
				String s = next();
				int x = nextInt();
				int y = nextInt();
				long d = Math.abs((y2 - y1)*x - (x2 - x1)*y + x2*y1 - y2*x1);
				if(minDist == d)
				{
					danger.add(s);
				}
				if(d < minDist)
				{
					danger.clear();
					danger.add(s);
					minDist = d;
				}
			}
			for(String s:danger)
			{
				System.out.print(s+" ");	
			}
			System.out.println();
		}
		
		
		
		pout.flush();
		pout.close();
	}
	
	public void newst()
	{
		try {
			st = new StringTokenizer(file.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readLine() throws IOException
	{
		return file.readLine();
	}
	
	public String next()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return st.nextToken();
	}
	
	public int nextInt()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return Integer.parseInt(st.nextToken());
	}
	
	public long nextLong()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return Long.parseLong(st.nextToken());
	}
	
}