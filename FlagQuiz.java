import java.util.Arrays;
import java.util.Scanner;

public class FlagQuiz {

	public static void main(String[] args)
	{
		new FlagQuiz().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		String line = file.nextLine();
		int N = file.nextInt();
		file.nextLine();
		String[][] strs = new String[N][];
		int[][] dist = new int[N][N];
		String[] inp = new String[N];
		for(int i = 0;i<N;i++){
			inp[i] = file.nextLine();
			strs[i] = inp[i].split(", ");
		}
		for(int i = 0;i<N;i++)
			for(int j = i+1;j<N;j++)
				dist[i][j] = dist[j][i] = dist(strs[i],strs[j]);
		int[] maxDist = new int[N];
		for(int i = 0;i<N;i++)
			for(int j = 0;j<N;j++)
				maxDist[i] = Math.max(maxDist[i],dist[i][j]);
		int min = Integer.MAX_VALUE;
		for(int x:maxDist)
			min = Math.min(min,x);
		for(int i = 0;i<N;i++)
			if(maxDist[i]==min)
				System.out.println(inp[i]);
	}

	public int dist(String[] a, String[] b)
	{
		int c = 0;
		for(int i = 0;i<a.length;i++)
		{
			if(!a[i].equals(b[i]))
				c++;
		}
		return c;
	}

}
