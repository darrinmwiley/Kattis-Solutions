import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class sumsets {
	
	sum[] sums;
	
	public static void main(String[] args) throws Exception
	{
		new sumsets().run();
	}
	
	public void run() throws Exception
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
			ints[i] = Integer.parseInt(file.readLine());
		Arrays.sort(ints);
		sums = new sum[N*(N-1)/2];
		int x = 0;
		for(int i = 0;i<ints.length;i++)
			for(int j = i+1;j<ints.length;j++)
			{
				sum s = new sum(i,j,ints[i]+ints[j]);
				sums[x++] = s;
			}
		Arrays.sort(sums);
		for(int i = ints.length-1;i>=0;i--)
			for(int j = 0;j<ints.length;j++)
				if(i!=j)
				{
					int diff = ints[i]-ints[j];
					int min = minIndex(diff);
					int max = maxIndex(diff);
					for(int k = 0;k<4;k++)
					{
						sum s = randomElement(min,max);
						if(s.sum == diff && s.i1 != i && s.i1 != j && s.i2 != i && s.i2 != j)
						{
							System.out.println(ints[i]);
							return;
						}
					}
				}
		System.out.println("no solution");
	}
	
	public int minIndex(int sum)
	{
		int L = -1;
		int R = sums.length;
		int M = 0;
		while(R-L > 1)
		{
			M = (L+R)/2;
			int s = sums[M].sum;
			if(s>=sum)
				R = M;
			else
				L = M;
		}
		return M;
	}
	
	public int maxIndex(int sum)
	{
		int L = -1;
		int R = sums.length;
		int M = 0;
		while(R-L > 1)
		{
			M = (L+R)/2;
			int s = sums[M].sum;
			if(s<=sum)
				L = M;
			else
				R = M;
		}
		return M;
	}
	
	public sum randomElement(int start, int end)
	{
		int index = start + (int)(Math.random()*(end-start+1));
		return sums[index];
	}
	
	private class sum implements Comparable<sum>{
		
		int i1;
		int i2;
		int sum;
		
		public sum(int i1, int i2, int sum)
		{
			this.i1 = i1;
			this.i2 = i2;
			this.sum = sum;
		}

		@Override
		public int compareTo(sum o) {
			return Integer.compare(sum, o.sum);
		}
		
		public String toString()
		{
			return String.format("(%d,%d,%d)", sum, i1, i2);
		}
		
	}
	
}
