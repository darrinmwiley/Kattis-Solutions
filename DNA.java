import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DNA {
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new DNA().run();		
	}
	
	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		char[] chars = file.next().toCharArray();
		int[][] dp = new int[chars.length][2];
		if(chars[0] == 'A') {
			dp[0][0] = 0;
			dp[0][1] = 1;
		}
		else {
			dp[0][0] = 1;
			dp[0][1] = 0;
		}
		for(int i = 1;i<dp.length;i++)
		{
			// i 0
			if(chars[i] == 'A')
			{
				dp[i][0] = dp[i-1][0];
			}else {
				dp[i][0] = Math.min(dp[i-1][0],dp[i-1][1])+1;
			}
			if(chars[i] == 'B')
			{
				dp[i][1] = dp[i-1][1];
			}else {
				dp[i][1] = Math.min(dp[i-1][1], dp[i-1][0])+1;
			}
		}
		//for(int i = 0;i<dp.length;i++)
		System.out.println(Math.min(dp[dp.length - 1][0],dp[dp.length - 1][1]+1));
	}
	
}
