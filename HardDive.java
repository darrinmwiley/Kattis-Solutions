import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HardDive {
	
	int r, dx, dy, x, y;
	double p;
	double epsilon = .000001;
	int INSIDE = 1;
	int CONTAINS = 2;
	int INTERSECT = 3;
	int OUTSIDE = 4;
	int[][] d = new int[][]{{1,0,-1,0,0},{0,1,0,-1,0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new HardDive().run();
	}
	
	public void run() throws NumberFormatException, IOException//bfs by shortest connection number
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		boolean[] broken = new boolean[N];
		st = new StringTokenizer(file.readLine());
		for(int i = 0;i<B;i++)
		{
			int x = Integer.parseInt(st.nextToken());
			broken[x-1] = true;
		}
		char[] chars = new char[N];
		Arrays.fill(chars, '0');
		if(C % 2 == 1)
		{
			if(broken[0])
				chars[chars.length - 1] = '1';
			else
				chars[0] = '1';
			C--;
		}
		for(int i = 1;i<chars.length - 1;i++)
		{
			if(broken[i])
				continue;
			if(C==0)
				break;
			if(chars[i-1] == '0')
			{
				chars[i] = '1';
				C--;
				if(chars[i+1] == '0')
					C--;
			}
		}
		if(C==1)
		{
			chars[chars.length - 1] = '1';
			C--;
		}
		System.out.println(new String(chars));
	}	
}
