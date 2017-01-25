import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class RestaurantRatings {
    public void run() throws Exception
    {
        Scanner file = new Scanner(System.in);
        while(true)
        {
            int n = file.nextInt();
            if(n==0)
                return;
            int[] ratings = new int[n];
            int sum = 0;
            for(int i = 0;i<n;i++){
                ratings[i] = file.nextInt();
                sum+=ratings[i];
            }
            
            long[][] dp = new long[n+1][sum+1];
            dp[0][0] = 1;
            
            for(int c = 1;c<=n;c++)
            {
                for(int i = 0;i<=sum;i++)
                {
                    for(int j = 0;j<=i;j++)
                    {
                        dp[c][i] += dp[c-1][i-j];
                    }
                }
            }
            
            long ans = 0;
            for(int i = 0;i<sum;i++)
                ans+=dp[n][i];
            for(int x = 0;x<n-1;x++)
            {
                int s = 0;
                for(int i = 0;i<x;i++)
                    s+=ratings[i];
                for(int i = 0;i<ratings[x];i++)
                    ans+=dp[n-1-x][sum-i-s];
            }
            
            System.out.println(ans+1);  
        }
        
    }
    
    public static void main(String[] args) throws Exception
    {
        new RestaurantRatings().run();    
    }
}