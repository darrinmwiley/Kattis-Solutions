import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AliceDigital {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new AliceDigital().run();
	}

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(file.readLine());
		for(int z = 0;z<t;z++)
		{
			st = new StringTokenizer(file.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] ints = new int[N];
			st = new StringTokenizer(file.readLine());
			for(int i = 0;i<N;i++)
			{
				ints[i] = Integer.parseInt(st.nextToken());
			}
			int max = M;
			for(int i = 0;i<N;i++)
			{
				if(ints[i] == M)
				{
					max = Math.max(max, test(ints, i));
				}
			}
			System.out.println(max);
		}
	}

	public int test(int[] ints, int index)
	{
		int sum = ints[index];
		for(int i = index-1;i>=0;i--)
		{
			if(ints[i] <= ints[index])
				break;
			sum += ints[i];
		}
		for(int i = index+1;i<ints.length;i++)
		{
			if(ints[i] <= ints[index])
				break;
			sum += ints[i];
		}
		return sum;
	}

}
