import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Avoidland {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		int[] rows = new int[N+1];
		int[] cols = new int[N+1];
		for(int i = 0;i<N;i++)
		{
			StringTokenizer st = new StringTokenizer(file.readLine());
			rows[Integer.parseInt(st.nextToken())]++;
			cols[Integer.parseInt(st.nextToken())]++;
		}
		int[] sortRows = new int[N];
		int[] sortCols = new int[N];
		int r = 0;
		int c = 0;
		for(int i = 1;i<=N;i++)
		{
			if(rows[i]!=0){
				Arrays.fill(sortRows,r,r+rows[i],i);
				r+=rows[i];
			}
			if(cols[i]!=0){
				Arrays.fill(sortCols,c,c+cols[i],i);
				c+=cols[i];
			}	
		}
		int ans = 0;
		for(int i = 0;i<N;i++)
		{
			ans+=Math.abs(sortRows[i]-(i+1));
			ans+=Math.abs(sortCols[i]-(i+1));
		}
		System.out.println(ans);
	}
}
