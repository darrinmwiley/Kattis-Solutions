import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CuttingTheNecklace{

	long[] ints;
	long[] sum;

	public static void main(String[] args) throws IOException
	{
		new CuttingTheNecklace().run();
	}

	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ints = new long[M];
		st = new StringTokenizer(file.readLine());
		for(int i = 0;i<ints.length;i++)
			ints[i] = Long.parseLong(st.nextToken());
		sum = new long[ints.length+1];
		sum[0] = 0;
		for(int i = 0;i<ints.length;i++)
		{
			sum[i+1] = sum[i]+ints[i];
		}
		long totalSum = sum(0,ints.length - 1);
		if(totalSum % N != 0)
		{
			System.out.println("NO");
			return;
		}
		long goal = totalSum / N;
		int[] length = new int[M];
		for(int i = 0;i<M;i++)
		{
			int R = M;
			int L = 0;
			int mid = (R+L)/2;
			while(R-L > 1)
			{
				mid = (R+L)/2;
				long sum = sum(i, (i+mid-1)%M);
				if(sum == goal) {
					length[i] = mid;
					break;
				}
				if(sum < goal)
				{
					L = mid;
				}else {
					R = mid;
				}
			}
		}
		boolean[] visited = new boolean[M];
		for(int i = 0;i<ints.length;i++)
		{
			int index = i;
			int groups = 0;
			while(groups != N)
			{
				if(visited[index] || length[index] == 0)
				{
					break;
				}
				visited[index] = true;
				groups++;
				index += length[index];
				index %= M;
			}
			if(index == i && groups == N)
			{
				System.out.println("YES");
				return;
			}
		}
		System.out.println("NO");
	}

	public long sum(int L, int R)
	{
		if(L <= R)
		{
			return sum[R+1] - sum [L];
		}
		if(R+1 == L)
			return sum(0,ints.length - 1);
		return sum(0,ints.length - 1) - sum(R+1, L-1);
	}
}
