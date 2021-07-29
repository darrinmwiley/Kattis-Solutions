import java.util.Arrays;
import java.util.Scanner;

public class Alphabet {
	public static void main(String[] args)
	{
		new Alphabet().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(file.hasNext())
		{
			String line = file.next();
			char[] chars = line.toCharArray();
			int[] dp = new int[chars.length];
			Arrays.fill(dp, 1);
			for(int i = 0;i<dp.length;i++)
				for(int j = 0;j<i;j++)
				{
					if(chars[i]>chars[j])
						dp[i] = Math.max(dp[i],dp[j]+1);
				}
			int max =0;
			for(int x:dp)
				max = Math.max(max,x);
			System.out.println(26-max);
		}
	}
}
