import java.util.Arrays;
import java.util.Scanner;

public class Sequences {
	
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		char[] chars = file.next().toCharArray();
		int M = 1000000007;
		int Q = 0;
		int[] zero = new int[chars.length+1];
		int[] dp = new int[chars.length+1];
		int[] powmod = new int[500001];
		powmod[0] = 1;
		for(int i = 1;i<powmod.length;i++)
			powmod[i] = powmod[i-1]*2%M;	
		for(int i = chars.length-1;i>=0;i--)
		{
			zero[i] = zero[i+1];
			if(chars[i]=='0')
			{
				zero[i]+=powmod[Q];
			}
			if(chars[i]=='?')
			{
				zero[i]*=2;
				zero[i]%=M;
				zero[i]+=powmod[Q++];
			}
			zero[i]%=M;
		}
		for(int i = chars.length-1;i>=0;i--)
		{
			dp[i]=dp[i+1];
			if(chars[i]=='1')
				dp[i] += zero[i+1];
			if(chars[i]=='?')
			{
				dp[i]*=2;
				dp[i]%=M;
				dp[i]+=zero[i+1];
			}
			dp[i]%=M;
		}
		System.out.println(dp[0]);
	}
}
