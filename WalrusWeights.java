import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class WalrusWeights {

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(System.in);
        boolean[] dp = new boolean[2001];
        dp[0] = true;
        int N = file.nextInt();
        for(int i = 0;i<N;i++)
        {
            int v = file.nextInt();
            for(int j = 2000;j>=v;j--)
                if(dp[j-v])
                    dp[j] = true;
        }
        for(int i = 0;i<1000;i++)
        {
            if(dp[1000+i]){
                System.out.println(1000+i);
                return;
            }
            if(dp[1000-i])
            {
                System.out.println(1000-i);
                return;
            }
        }
    }
}