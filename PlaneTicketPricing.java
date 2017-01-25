import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class PlaneTicketPricing {
    public void run() throws Exception
    {
        Scanner file = new Scanner(System.in);
        int tickets = file.nextInt();
        int weeks = file.nextInt()+1;   
        int[][] dp = new int[weeks][tickets+1];
        int[][] options = new int[weeks][];
        int[][] needs = new int[weeks][];
        for(int i = 0;i<weeks;i++)
        {
            int n = file.nextInt();
            options[i] = new int[n];
            needs[i] = new int[n];
            for(int j = 0;j<n;j++){
                options[i][j] = file.nextInt();
            }
            for(int j = 0;j<n;j++){
                needs[i][j] = file.nextInt();
            }
        }
        for(int i = 0;i<=tickets;i++)
        {
            for(int j = 0;j<options[weeks-1].length;j++)
            {
                dp[weeks-1][i] = Math.max(dp[weeks-1][i],options[weeks-1][j]*Math.min(i,needs[weeks-1][j]));
            }
        }
        if(weeks==1)
        {
            int ans = dp[0][tickets];
            int best = Integer.MAX_VALUE;
            for(int i = 0;i<options[0].length;i++)
            {
                if(Math.min(tickets,needs[0][i])*options[0][i]==ans)
                {
                    if(options[0][i]<best)
                    {
                        best = options[0][i];
                    }
                }
            }
            System.out.println(ans);
            System.out.println(best);
            return;
        }
        for(int i = weeks-2;i>=0;i--)
        {
            for(int j = 0;j<=tickets;j++)
            {
                for(int k = 0;k<options[i].length;k++)
                {
                    dp[i][j] = Math.max(options[i][k]*Math.min(j,needs[i][k])+dp[i+1][j-Math.min(j,needs[i][k])],dp[i][j]);
                }
            }
        }
        int best = Integer.MAX_VALUE;
        int ans = dp[0][tickets];
        for(int i = 0;i<options[0].length;i++)
        {
            
            if(Math.min(tickets,needs[0][i])*options[0][i]+dp[1][tickets-Math.min(tickets,needs[0][i])]==ans)
            {
                if(options[0][i]<best)
                {
                    best = options[0][i];
                }
            }
        }
        System.out.println(ans);
        System.out.println(best);
    }
    
    public static void main(String[] args) throws Exception
    {
        new PlaneTicketPricing().run();
        
    }
}