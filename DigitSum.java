import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class DigitSum {

    public static void main(String[] args)
    {
        new DigitSum().run();
    }

    public void run()
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            long a = file.nextLong();
            long b = file.nextLong();
            BigInteger c = digitSumDP(Math.max(a-1, 0));
            BigInteger d = digitSumDP(b);
            System.out.println(d.subtract(c));
        }
    }

    public BigInteger digitSumDP(long K)
    {
       String s = K+"";
       BigInteger[][] dp = new BigInteger[s.length()][2];
       for(int i = 0;i<dp.length;i++)
           Arrays.fill(dp[i],BigInteger.ZERO);
       dp[0][0] = new BigInteger("45"); // sum of all 1 digit numbers is 45
       dp[0][1] = new BigInteger(""+((K%10+1)*(K%10)/2)); //sum of all numbers less than last digit of K, using sum of arithmetic sequence
       for(int i = 1;i<dp.length;i++)
       {
          BigInteger D = new BigInteger(""+getDigit(s,i)); // i’th digit from the end of K
          for(int j = 0;j<D.intValue();j++)
             dp[i][1] = dp[i][1].add(new BigInteger(j+"").multiply(pow(i)).add(dp[i-1][0])); //analogous to A on slide 67
          dp[i][1] = dp[i][1].add(D.multiply(new BigInteger(""+(Long.parseLong(s.substring(s.length()-i))+1))).add(dp[i-1][1])); //analogous to B on slide 67
          for(int j = 0;j<10;j++)
             dp[i][0] = dp[i][0].add(new BigInteger(j+"").multiply(pow(i)).add(dp[i-1][0])); //sum if all of the remaining digits can be anything
       }
       return dp[s.length()-1][1];
    }

    public BigInteger pow(int i)
    {
        return BigInteger.TEN.pow(i);
    }

    public int getDigit(String s, int n)
    {
       return s.charAt(s.length()-1-n)-48;
    }

}
