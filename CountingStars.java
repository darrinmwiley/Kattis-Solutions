import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class CountingStars {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int q = 0;
        while(file.hasNext())
        {
            int r = file.nextInt();
            int c = file.nextInt();
            boolean[][] b = new boolean[r][c];
            for(int i = 0;i<b.length;i++)
            {
                String next = file.next();
                for(int j =0;j<c;j++)
                    b[i][j] = next.charAt(j)=='-';
            }
            int count = 0;
            for(int i = 0;i<b.length;i++)
                for(int j = 0;j<b[i].length;j++)
                    if(b[i][j])
                    {
                        count++;
                        flood(i,j,b);
                    }
            System.out.printf("Case %d: %d%n",++q,count);
        }
    }

    public static void flood(int r, int c, boolean[][] bools)
    {
        Queue<Integer> que = new LinkedList<Integer>();
        bools[r][c] = false;
        que.add((r<<7)+c);
        int[][] d = new int[][]{{1,0,-1,0},{0,1,0,-1}};
        while(!que.isEmpty())
        {
            int x = que.poll();
            r = x>>7;
            c = x&127;
            for(int i = 0;i<4;i++)
            {
                int rr = r+d[0][i];
                int cc = c+d[1][i];
                if(Math.min(rr,cc)>=0&&rr<bools.length&&cc<bools[rr].length&&bools[rr][cc])
                {
                    bools[rr][cc] = false;
                    que.add((rr<<7)+cc);
                }
            }
        }
    }
}
