import java.util.Arrays;
import java.util.Scanner;

public class UxuhulVotingSystem {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            int P = file.nextInt();
            int[][] priests = new int[P][8];
            for(int i = 0;i<P;i++)
                for(int j =0;j<8;j++)
                    priests[i][j] = file.nextInt();
            int[][] dp = new int[P][8];
            for(int i = 0;i<dp.length;i++)
                Arrays.fill(dp[i],-1);
            for(int j = 0;j<8;j++)
            {
                int a = j^4;
                int b = j^2;
                int c = j^1;
                int aa = priests[P-1][a];
                int bb = priests[P-1][b];
                int cc = priests[P-1][c];
                if(aa<bb&&aa<cc)
                {
                    dp[P-1][j] = a;
                }
                if(bb<aa&&bb<cc)
                {
                    dp[P-1][j] = b;
                }
                if(cc<aa&&cc<bb)
                {
                    dp[P-1][j] = c;
                }
            }
            for(int i = P-2;i>-1;i--)
                for(int j = 0;j<8;j++)
                {
                    int a = j^4;
                    int b = j^2;
                    int c = j^1;
                    int aa = priests[i][dp[i+1][a]];
                    int bb = priests[i][dp[i+1][b]];
                    int cc = priests[i][dp[i+1][c]];
                    if(aa<=bb&&aa<=cc)
                    {
                        dp[i][j] = dp[i+1][a];
                    }
                    if(bb<=aa&&bb<=cc)
                    {
                        dp[i][j] = dp[i+1][b];
                    }
                    if(cc<=aa&&cc<=bb)
                    {
                        dp[i][j] = dp[i+1][c];
                    }
                }
            int ans = dp[0][0];
            System.out.println(String.format("%3s",Integer.toBinaryString(ans).replace("0","N").replace("1","Y")).replace(" ", "N"));
        }
    }
}