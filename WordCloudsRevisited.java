import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.System.*;

public class WordCloudsRevisited {
    
    public void run()
    {   
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int maxwid = file.nextInt();
        int[] ht = new int[N+1];
        int[] wid = new int[N+1];
        for(int i = 0;i<N;i++)
        {
            wid[i] = file.nextInt();
            ht[i] = file.nextInt();
        }
        ht[ht.length-1] = 0;
        wid[wid.length-1] = 0;
        int[] dp = new int[N+1];
        Arrays.fill(dp,1000000);
        dp[dp.length-1] = ht[dp.length-1];
        for(int i = dp.length-2;i>-1;i--)
        {
            int maxHt = ht[i];
            int currwid = wid[i];
            for(int x = i+1;x<dp.length;x++)
            {
                if(currwid>maxwid)
                    break;
                dp[i] = Math.min(dp[i],maxHt+dp[x]);
                currwid+=wid[x];
                maxHt = Math.max(maxHt,ht[x]);
            }
        }
        System.out.println(dp[0]);
    }
    
    public static void main(String[] args)
    {
        new WordCloudsRevisited().run();
    }
    
}