import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class gcds {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new gcds().run();
	}
	
	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
			ints[i] = Integer.parseInt(file.readLine());
		int[][] gcd = fillgcd();
		boolean[][] dp = new boolean[N][101];
		for(int i = 0;i<ints.length;i++)
				dp[i][ints[i]] = true;
		for(int i = 0;i<ints.length-1;i++)
			for(int j = 1;j<101;j++)
				if(dp[i][j])
					dp[i+1][gcd[j][ints[i+1]]] = true;
		boolean[] shad = new boolean[101];
		for(int i =0;i<ints.length;i++)
			for(int j = 1;j<101;j++)
				if(dp[i][j])
					shad[j]=true;
		int c = 0;
		for(boolean b:shad)
		{
			if(b)
				c++;
		}
		System.out.println(c);
	}	
	
	public int[][] fillgcd(){
		int[][] gcd = new int[101][101];
		for(int i = 1;i<101;i++)
			for(int j = 1;j<101;j++)
				gcd[i][j] = new BigInteger(i+"").gcd(new BigInteger(j+"")).intValue();
		return gcd;
	}
	
}