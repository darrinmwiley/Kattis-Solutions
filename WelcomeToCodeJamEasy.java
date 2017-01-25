import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;


public class WelcomeToCodeJamEasy {

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();file.nextLine();
        String find = "welcome to code jam";
        for(int z = 0;z<zz;z++)
        {
            String str = file.nextLine();
            int[][] dp = new int[19][str.length()];
            int m = 0;
            for(int i = str.length()-1;i>=0;i--)
            {
                if(str.charAt(i)=='m')
                    m++;
                dp[0][i] = m;
            }
            for(int i = 1;i<dp.length;i++)
            {
                for(int j = str.length()-2;j>=0;j--)
                {
                    dp[i][j] = dp[i][j+1];
                    if(str.charAt(j)==find.charAt(find.length()-1-i))
                    {
                        dp[i][j]+=dp[i-1][j];
                        
                    }
                    dp[i][j]%=10000;
                }
            }
            System.out.printf("Case #%d: %04d%n",z+1,dp[18][0]);
        }
    }
}