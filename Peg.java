import java.util.Scanner;
import java.util.TreeMap;


public class Peg {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        char[][] chars = new char[7][7];
        for(int i = 0;i<7;i++)
            chars[i] = file.nextLine().toCharArray();
        int[][] d = new int[][]{{1,0,-1,0},{0,1,0,-1}};
        int ret = 0;
        for(int i =0 ;i<7;i++)
            for(int j = 0;j<7;j++)
            {
                for(int k = 0;k<4;k++)
                {
                    int r1 = i+d[0][k];
                    int r2 = i+d[0][k]*2;
                    int c1 = j+d[1][k];
                    int c2 = j+d[1][k]*2;
                    if(val(r1,c1)&&val(r2,c2)&&chars[i][j]=='.'&&chars[r1][c1]=='o'&&chars[r2][c2]=='o')
                        ret++;
                }
            }
        System.out.println(ret);
    }
    
    public static boolean val(int r, int c)
    {
        return Math.max(r,c)<7&&Math.min(r,c)>=0;
    }
}