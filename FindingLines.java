import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FindingLines {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new FindingLines().run();		
	}

	long[] x;
	long[] y;

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		if(N == 1)
		{
			System.out.println("possible");
			return;
		}
		int P = Integer.parseInt(file.readLine());
		int need = (int)Math.ceil(N*P/100.0);
		x = new long[N];
		y = new long[N];
		StringTokenizer st;
		for(int i = 0;i<N;i++)
		{
			st = new StringTokenizer(file.readLine());
			x[i] = Long.parseLong(st.nextToken());
			y[i] = Long.parseLong(st.nextToken());
		}
		int it = 1000;
		for(int i = 0;i<it;i++)
		{
			int a = (int)(Math.random()*N);
			int b = (int)(Math.random()*N);
			if(a == b)
			{
				i--;
				continue;
			}
			if(check(a,b,need))
			{
				System.out.println("possible");
				return;
			}
		}
		System.out.println("impossible");
	}

	public boolean check(int a, int b, int need)
	{
		long x1 = x[a];
		long y1 = y[a];
		long x3 = x[b];
		long y3 = y[b];
		int n = 0;
		if(x[a] == x[b])
		{
			for(int i = 0;i<x.length;i++)
			{
				if(x[i] == x[a])
				{
					n++;
				}
			}
			return n >= need;
		}else {
			n+=2;
			for(int i =0 ;i<x.length;i++)
			{
				if(i!=a && i != b)
				{
					long x2 = x[i];
					long y2 = y[i];
					if(col(x1,y1,x2,y2,x3,y3))
					{
						n++;
					}
				}
			}
			return n >= need;
		}
	}

    public boolean col(long x1, long y1, long x2, long y2, long x3, long y3)
    {
        return (y3-y1)*(x2-x1)==(y2-y1)*(x3-x1);
    }

}
