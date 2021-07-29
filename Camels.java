import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Camels {
	
	boolean needsRotation = false;
	int[] fenwick;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new Camels().run();
	}
	
	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(file.readLine());
		int[] A = new int[(int)N];
		int[] B = new int[(int)N];
		int[] C = new int[(int)N];
		int[] MA = new int[(int)N];
		int[] MB = new int[(int)N];
		StringTokenizer st = new StringTokenizer(file.readLine());
		for(int i = 0;i<N;i++)
		{
			A[i] = Integer.parseInt(st.nextToken())-1;
			MA[A[i]] = i;
		}
		st = new StringTokenizer(file.readLine());
		for(int i = 0;i<N;i++)
		{
			B[i] = Integer.parseInt(st.nextToken())-1;
			MB[B[i]] = i;
		}
		st = new StringTokenizer(file.readLine());
		for(int i = 0;i<N;i++)
		{
			C[i] = Integer.parseInt(st.nextToken())-1;
		}
		long a = count(MA,B);
		long b = count(MA,C);
		long c = count(MB,C);
		System.out.println(N*(N-1)/2-(a+b+c)/2);
	}
	
	public long count(int[] MA, int[] B)
	{
		fenwick = new int[MA.length+1];
		long ret = 0;
		int[] map = new int[MA.length];
        for(int i = 0;i<map.length;i++)
            map[i] = MA[B[i]];
        for(int i:map)
        {
            ret+=sum(i+1);
            add(1,1);
            add(i+1,-1);
        }
        return ret;
	}
	
	public int next(int N)
	{
		return N+(N&-N);
	}
	
	public int parent(int N)
	{
		return N-(N&-N);
	}
	
	public long sum(int N)
	{
		long ret = 0;
		while(N!=0)
		{
			//System.out.println(N);
			ret+=fenwick[N];
			N = parent(N);
		}
		return ret;
	}
	
	public void add(int N, int K)
	{
		while(N<fenwick.length)
		{
			//System.out.println(N);
			fenwick[N]+=K;
			N = next(N);
		}
	}
}
