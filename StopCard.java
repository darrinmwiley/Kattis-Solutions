import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class StopCard {
	
	public static void main(String[] args) throws Exception
	{
		new StopCard().run();
	}
	
	HashSet<String> bad = new HashSet<String>();
	BufferedReader file;
	StringTokenizer st;
	
	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] ints = new int[N];
		st = new StringTokenizer(file.readLine());
		for(int i = 0;i<N;i++)
			ints[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(ints);
		if(C == 0)
		{
			double sum = 0;
			for(int x:ints)
				sum += x;
			System.out.println(sum/N);
			return;
		}
		double ans = 0;
		for(int i = 0;i<ints.length - 1;i++)
		{
			int numSmaller = i;
			int numLarger = ints.length - i - 1;
			double pLargest = 1.0/N;
			for(int j = 0;j<C-1;j++)
			{
				pLargest = pLargest * (numSmaller - j)/(N-1-j+0.0);
			}
			pLargest *= C;
			for(int j = i+1;j<ints.length;j++)
			{
				
				double pFirst = 1.0/(numLarger);
				ans += pLargest * pFirst * ints[j];
			}
		}
		double pNoMax = 1;
		for(int i = 0;i<C;i++)
			pNoMax *= (N-1.0-i)/(N-i);
		double pMax = 1-pNoMax;
		double nonMaxAve = 0;
		for(int i = 0;i<ints.length-1;i++)
			nonMaxAve += ints[i];
		nonMaxAve /= N-1;
		ans += nonMaxAve * pMax;
		System.out.println(ans);
	}	
	
}
