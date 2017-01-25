import java.util.*;
import java.io.*;
import java.math.*;

import static java.lang.System.*;

public class AmazingRace {
    
    public void go() {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int T = file.nextInt();
        int[] p = new int[N+2];
        int[] t = new int[N+2];
        int[] d = new int[N+2];
        int[][] cost = new int[N+2][N+2];
        for(int i = 0;i<N;i++)
        {
            p[i] = file.nextInt();
            t[i] = file.nextInt();
            d[i] = file.nextInt();
            if(d[i] == -1)
                d[i] = T;
        }
        d[N+1] = T;
        for(int i = 0;i<N+2;i++)
        {
            for(int j = 0;j<N+2;j++)
            {
                cost[i][j] = file.nextInt();
                if(i!=j)
                    cost[i][j]+=t[j];
            }
        }
        int[][] dp = new int[1<<N+2][N+2];
        for(int[] n:dp)
            Arrays.fill(n, 1441);
        int start = 1<<N;
        dp[start][N] = 0;
        int score = 0;
        int best = 0;
        for(int i = start+1;i<dp.length;i++)
        {
            for(int j = 0;j<N+2;j++)
            {
                if((i&1<<j)!=0)
                    for(int k = 0;k<N+2;k++)
                        dp[i][j] = Math.min(dp[i][j],dp[i^1<<j][k]+cost[k][j]);
                if(dp[i][j]>d[j])
                    dp[i][j] = 1441;
                else if(j==N+1)
                {
                    if(getScore(i,p)==score)
                    {
                        best = smallest(best,i,N);
                    }
                    else if(getScore(i,p)>score)
                    {
                        score = Math.max(score,getScore(i,p));
                        best = i;
                    }
                }
                    
            }   
        }
        System.out.println(score);
        if(score!=0)
            for(int i = 0;i<N;i++)
            {
                if((best&1<<i)!=0)
                    System.out.print(i+1+" ");
            }
        System.out.println();
    }
    
    public int smallest(int a, int b, int N)
    {
        String bina = Integer.toBinaryString(a);
        String binb = Integer.toBinaryString(b);
        if(bina.endsWith(binb))
            return b;
        else if(binb.endsWith(bina))
            return a;
        for(int i = 0;i<N;i++)
        {
            if((a&1<<i)!=0&&(b&1<<i)==0)
                return a;
            if((b&1<<i)!=0&&(a&1<<i)==0)
                return b;
        }
        return a;
    }
    
    public int getScore(int i, int[] p)
    {
        int ret = 0;
        for(int j = 0;j<p.length;j++)
            if((1<<j&i)!=0)
                ret+=p[j];
        return ret;
    }
    
    public static void main(String[] args) {
        new AmazingRace().go();
    }
}