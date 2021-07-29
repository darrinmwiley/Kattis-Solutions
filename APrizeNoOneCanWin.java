import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class APrizeNoOneCanWin {

	StringTokenizer st;
	BufferedReader file;

	public static void main(String[] args) throws Exception
	{
		new APrizeNoOneCanWin().run();
	}

	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		int N = nextInt();
		int X = nextInt();
		int[] ints = readInts(N);
		Arrays.sort(ints);
		for(int i = 1;i<ints.length;i++)
		{
			if(ints[i]+ints[i-1] > X)
			{
				System.out.println(i);
				return;
			}
		}
		System.out.println(ints.length);
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

	public int[] readInts(int N)
	{
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
		{
			ints[i] = nextInt();
		}
		return ints;
	}

	public long[] readLongs(int N)
	{
		long[] ints = new long[N];
		for(int i = 0;i<N;i++)
		{
			ints[i] = nextLong();
		}
		return ints;
	}

}
