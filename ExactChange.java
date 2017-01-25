import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class ExactChange {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
    loop:
        for(int z = 0;z<zz;z++)
        {
            int[] dp = new int[10001];
            Arrays.fill(dp,1000);
            dp[0] = 0;
            int X = file.nextInt();
            int M = file.nextInt();
            int[] V = new int[M];
            for(int i = 0;i<V.length;i++)
                V[i] = file.nextInt();
            for(int i:V)
                for(int j = dp.length-1;j>=i;j--)
                    dp[j] = Math.min(dp[j],dp[j-i]+1);
            for(int i = X;i<=10000;i++)
                if(dp[i]<100)
                {
                    System.out.println(i+" "+dp[i]);
                    continue loop;
                }
        }
    }
}