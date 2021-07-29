import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class PickingUpTheDice {

	ArrayList<Integer> primes;

    public static void main(String[] args) throws IOException
    {
        new PickingUpTheDice().run();  
    }

    static int lsb(int n)
    {
        return (int)((Math.log10(n & -n)) / Math.log10(2));
    }

    public void run() throws IOException
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int goal = file.nextInt();
        int[] dice = new int[N];
        int total = 0;
        for(int i = 0;i<N;i++){
        	dice[i] = file.nextInt();
        	total+=dice[i];
        }
        long[][] dp = new long[25][145];
        dp[0][0] = 1;
        for(int i = 1;i<dp.length;i++)
        {
        	for(int j = 0;j<145;j++)
        	{
        		for(int k = 1;k<=6;k++)
        			if(j>=k)
        				dp[i][j] += dp[i-1][j-k];
        	}
        }
        for(int i = 0;i<dp.length;i++)
        {
        	for(int j = 0;j<145;j++)
        	{
        		for(int k = 0;k<24-i;k++)
        		{
        			dp[i][j]*=6;
        		}
        	}
        }
        int[] sum = new int[1<<N];
        int[] set = new int[1<<N];
        sum[0] = 0;
        set[0] = 0;
        for(int i = 1;i<sum.length;i++)
        {
        	int lsp = i&-i;
        	int lsb = lsb(i);
        	sum[i] = sum[i-lsp] + dice[lsb];
        	set[i] = set[i-lsp]+1;
        }
        long max = -1;
        int best = -1;
        for(int i = 0;i<sum.length;i++)
        {
        	int diceSum = sum[i];
        	int numTaken = set[i];
        	int remainingSum = total-diceSum;
        	int needed = goal-remainingSum;
        	if(needed<0)
        		continue;
        	long chance = dp[numTaken][needed];
        	if(chance>max)
        	{
        		max = chance;
        		best = numTaken;
        	}
        }
        System.out.println(best);
    }
}
