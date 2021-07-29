import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrainSorting {

	public static void main(String[] args) throws Exception
	{
		new TrainSorting().run();
	}

	/*D sketch -
	  try every possible starting point, find length of LIS and LDS starting at that point.
	*/
	public void run() throws Exception{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(file.readLine());
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
		{
			ints[i] = Integer.parseInt(file.readLine());
		}
		int[] lis = new int[N];
		int[] lds = new int[N];
		Arrays.fill(lis, 1);
		Arrays.fill(lds, 1);
		for(int i = ints.length - 1;i>=0;i--)
		{
			for(int j = i-1;j>=0;j--)
			{
				if(ints[i] > ints[j])
				{
					lis[j] = Math.max(lis[j], lis[i] + 1);
				}
				if(ints[i] < ints[j])
				{
					lds[j] = Math.max(lds[j], lds[i] + 1);
				}
			}
		}
		int ans = 0;
		for(int i = 0;i<ints.length;i++)
		{
			ans = Math.max(ans, lis[i] + lds[i] - 1);
		}
		System.out.println(ans);
	}



}
