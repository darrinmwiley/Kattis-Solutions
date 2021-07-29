import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ResearchProductivityIndex {

	public static void main(String[] args)
	{
		new ResearchProductivityIndex().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
		{
			ints[i] = file.nextInt();
		}
		Arrays.sort(ints);
		double bestAnswer = 0.0;
		for(int i = 1;i<=ints.length;i++)
		{
			ArrayList<Integer> prob = new ArrayList<Integer>();
			for(int j = 0;j<i;j++)
				prob.add(ints[ints.length-1-j]);
			double[] dp = dp(prob);
			double ans = 0.0;
			for(int j = 1;j<dp.length;j++)
			{
				ans+=Math.pow(j, j/(i+0.0))*dp[j];
			}
			bestAnswer = Math.max(ans,bestAnswer);
		}
		System.out.println(bestAnswer);
	}

	public double[] dp(ArrayList<Integer> prob)
	{
		double[][] dp = new double[prob.size()+1][prob.size()+1];
		dp[0][0] = 1;
		for(int i = 0;i<dp.length;i++)
		{
			for(int j = 1;j<dp.length;j++)
			{
				double succ = prob.get(j-1)/100.0;
				double fail = 1.0-succ;
				dp[i][j] = dp[i][j-1] * fail;
				if(i != 0)
					dp[i][j] += dp[i-1][j-1]*succ;
			}
		}
		/*System.out.println(" ");
		for(double[] d:dp)
		{
			System.out.println(Arrays.toString(d));
		}*/
		double[] ret = new double[prob.size()+1];
		for(int i = 0;i<prob.size()+1;i++)
		{
			ret[i] = dp[i][dp.length-1];
		}
		return ret;
	}

/*
[10] [0.9, 0.1]
[10, 10] [0.81, 0.18000000000000002, 0.010000000000000002]
[10, 10, 10] [0.7290000000000001, 0.24300000000000005, 0.027000000000000003, 0.0010000000000000002]
[10, 10, 10, 10] [0.6561000000000001, 0.2916000000000001, 0.048600000000000004, 0.0036000000000000008, 1.0000000000000003E-4]
1.02503700453637
*/

}
