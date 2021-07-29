import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Apparatus {

	StringTokenizer st;
	BufferedReader file;

	public static void main(String[] args) throws Exception
	{
		new Apparatus().run();
	}

	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		int N = nextInt();
		int M = nextInt();
		char[][] switches = new char[N][M];
		char[][] observations = new char[N][M];
		for(int i = 0;i<M;i++)
		{
			char[] chars = next().toCharArray();
			char[] chars2 = next().toCharArray();
			for(int j = 0;j<N;j++)
			{
				switches[j][i] = chars[j];
				observations[j][i] = chars2[j];
			}
		}
		HashMap<String, Integer> map1 = new HashMap<String,Integer>();
		HashMap<String, Integer> map2 = new HashMap<String,Integer>();
		for(int i = 0;i<N;i++)
		{
			String a = new String(switches[i]);
			String b = new String(observations[i]);
			if(!map1.containsKey(a))
			{
				map1.put(a, 0);
			}
			if(!map2.containsKey(b))
			{
				map2.put(b, 0);
			}
			map1.put(a, map1.get(a)+1);
			map2.put(b, map2.get(b)+1);
		}
		long MOD = 1000003;
		long[] fact = new long[1001];
		fact[0] = 1;
		for(int i = 1;i<fact.length;i++)
		{
			fact[i] = (i * fact[i-1]) % MOD;
		}
		if(M == 0)
		{
			System.out.println(fact[N]);
			return;
		}
		long ans = 1;
		for(String i: map1.keySet())
		{
			if(!map1.get(i).equals(map2.get(i)))
			{
				System.out.println(0);
				return;
			}else {
				ans = (ans * fact[map1.get(i)]) % MOD;
			}
		}
		System.out.println(ans);
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
