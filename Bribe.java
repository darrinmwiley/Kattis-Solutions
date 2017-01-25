import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Bribe {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            int N = file.nextInt();
            int C = file.nextInt();
            int M = file.nextInt();
            int[] c = new int[N];
            double[] p = new double[N];
            for(int i = 0;i<N;i++)
            {
                c[i] = file.nextInt();
                p[i] = file.nextInt()/100.0;
            }
            double[][] dp = new double[C+1][1<<N];
            for(int i = 0;i<dp[0].length;i++)
                if(money(i,M,c))
                    dp[0][i] = 1;
            for(int i = 1;i<dp.length;i++)
            {
                for(int j = dp[i].length-1;j>=0;j--)
                {
                    if(money(j,M,c))
                        for(int k = 0;k<N;k++)
                        {
                            if((j&1<<k)==0)
                            {
                                dp[i][j] = Math.max(dp[i][j],dp[i-1][j|1<<k]*p[k]+dp[i][j|1<<k]*(1-p[k]));
                            }
                        }
                }
            }
            double best = 0;
            for(double d:dp[C])
                best = Math.max(best,d);
            System.out.println(best);
        }
    }
    
    public boolean money(int hash, int M, int[] cost)
    {
        int sum = 0;
        for(int i = 0;i<cost.length;i++)
        {
            if((hash&1<<i)>0)
                sum+=cost[i];
        }
        return sum<=M;
    }
    
    /*
     * dp[i][j] = Math.max(dp[i][j],dp[i^1<<k][j-c[k]]*p[k]+dp[i][j-c[k]]*(1-p[k]));
1
4 2 10
1 80
7 50
4 20
5 20
     */
    
    public static void main(String[] args)
    {
        new Bribe().run();
    }
    
}