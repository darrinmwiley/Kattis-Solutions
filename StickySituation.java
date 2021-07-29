import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StickySituation {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new StickySituation().run();
	}

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		long[] ints = new long[N];
		StringTokenizer st = new StringTokenizer(file.readLine());
		for(int i = 0;i<N;i++)
			ints[i] = Long.parseLong(st.nextToken());
		Arrays.sort(ints);
		for(int i = 0;i<N-2;i++)
		{
			if(ints[i] + ints[i+1] > ints[i+2])
			{
				System.out.println("possible");
				return;
			}
		}
		System.out.println("impossible");
	}

}
