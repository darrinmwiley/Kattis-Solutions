import java.util.*;
import java.io.*;
import java.math.*;

import static java.lang.System.*;

public class RestaurantOrders {

    public void go() throws IOException {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = file.readLine())!=null)
        {
            int N = Integer.parseInt(line.trim());
            int[] ints = new int[N];
            StringTokenizer st = new StringTokenizer(file.readLine());
            for(int i = 0;i<N;i++)
            {
                ints[i] = Integer.parseInt(st.nextToken());
            }
            for(int i = 0;i<ints.length/2;i++)
            {
                int save = ints[i];
                ints[i] = ints[ints.length-1-i];
                ints[ints.length-1-i] = save;
            }
            byte[][] dp = new byte[N+1][30001];
            dp[0][0] = 1;
            for(int i = 1;i<dp.length;i++)
                for(int j = 0;j<dp[i].length;j++)
                {
                    byte a = dp[i-1][j];
                    byte b = 0;
                    if(j>=ints[i-1])
                        b = dp[i][j-ints[i-1]];
                    dp[i][j] = (byte) Math.min(2,a+b);
                }
            int M = Integer.parseInt(file.readLine().trim());
            st = new StringTokenizer(file.readLine());
            for(int i = 0;i<M;i++)
            {
                int goal = Integer.parseInt(st.nextToken());
                if(dp[N][goal]==2){
                    System.out.println("Ambiguous");
                    continue;
                }
                if(dp[N][goal]==0)
                {
                    System.out.println("Impossible");
                    continue;
                }
                int r = N;
                int c = goal;
                while(c!=0)
                {
                    if(c>=ints[r-1]&&dp[r][c-ints[r-1]]==1){
                        System.out.print(N-r+1+" ");
                        c-=ints[r-1];
                    }
                    else
                        r--;
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new RestaurantOrders().go();
    }
}
