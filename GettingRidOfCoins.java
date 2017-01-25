import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class GettingRidOfCoins {

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(System.in);      
        String col = "abcdefgh";
        String row = "87654321";
        int N = file.nextInt();
        for(int z = 0;z<N;z++)
        {
            int[][] d = new int[8][8];
            for(int i = 0;i<8;i++)
                Arrays.fill(d[i],64);
            String next = file.next();
            int c = col.indexOf(next.charAt(0));
            int r = row.indexOf(next.charAt(1));
            d[r][c] = 0;
            d(r,c,d);
            int max = 0;
            for(int i = 0;i<8;i++)
                for(int j = 0;j<8;j++)
                    max = Math.max(max,d[i][j]);
            System.out.print(max+" ");
            for(int i =0 ;i<8;i++)
                for(int j = 0;j<8;j++)
                {
                    if(d[i][j]==max)
                    {
                        System.out.print(col.charAt(j)+""+row.charAt(i)+" ");
                    }
                }
            System.out.println();
        }
    }
    
    public static void d(int r, int c, int[][] d)
    {
        int[][] dir = new int[][]{{-2,-2,-1,-1,1,1,2,2},{-1,1,-2,2,-2,2,-1,1}};
        for(int i = 0;i<8;i++)
        {
            int rr = r+dir[0][i];
            int cc = c+dir[1][i];
            if(Math.min(rr,cc)>=0&&Math.max(rr,cc)<8&&d[rr][cc]>d[r][c]+1)
            {
                d[rr][cc] = d[r][c]+1;
                d(rr,cc,d);
            }
        }
    }
}