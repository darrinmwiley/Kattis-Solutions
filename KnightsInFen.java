import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class KnightsInFen {

    HashMap<Integer,Integer> map = null;
    int min = 11;
    public static void main(String[] args)
    {
        new KnightsInFen().run();
    }

    /*      [0, 0, 0, 0,  ]
            [1, 0, 1, 0, 0]
            [1, 1, 0, 1, 1]
            [1, 0, 0, 0, 0]
            [0, 0, 0, 1, 0]
    */

    public void run()
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        file.nextLine();
        char[][] chars;
        char[][] solved = new char[][]{"11111".toCharArray(),"01111".toCharArray(),"00 11".toCharArray(),"00001".toCharArray(),"00000".toCharArray()};
        map = new HashMap<Integer,Integer>();
        explore(0,solved,2,2);
        for(int i = 0;i<N;i++)
        {
            int r = -1;
            int c = -1;
            min = 11;

            chars = new char[5][5];
            for(int j = 0;j<5;j++)
            {
                String next = file.nextLine();
                if(next.contains(" ")){
                    r = j;
                    c = next.indexOf(" ");
                }
                chars[j] = next.toCharArray();
            }
            bruteForce(0,chars,r,c);
            if(min==11)
                System.out.println("Unsolvable in less than 11 move(s).");
            else
                System.out.printf("Solvable in %d move(s).%n",min);
        }
    }

    public void explore(int depth, char[][] chars,int r, int c)
    {
        int hash = hash(chars);
        if(map.get(hash)==null)
        {
            map.put(hash, depth);
        }
        if(depth==5)
            return;
        int[][] d = new int[][]{{-2,-2,-1,-1,1,1,2,2},{-1,1,-2,2,-2,2,-1,1}};
        for(int i = 0;i<8;i++)
        {
            int rr = r+d[0][i];
            int cc = c+d[1][i];
            if(val(rr,cc))
            {
                chars[r][c] = chars[rr][cc];
                chars[rr][cc] = ' ';
                explore(depth+1,chars,rr,cc);
                chars[rr][cc] = chars[r][c];
                chars[r][c] = ' ';
            }
        }
    }

    public void bruteForce(int depth, char[][] chars, int r, int c)
    {
        int hash = hash(chars);

        if(map.get(hash)!=null)
        {
            min = Math.min(map.get(hash)+depth,min);
        }
        if(depth==5)
            return;
        int[][] d = new int[][]{{-2,-2,-1,-1,1,1,2,2},{-1,1,-2,2,-2,2,-1,1}};
        for(int i = 0;i<8;i++)
        {
            int rr = r+d[0][i];
            int cc = c+d[1][i];
            if(val(rr,cc))
            {
                chars[r][c] = chars[rr][cc];
                chars[rr][cc] = ' ';
                bruteForce(depth+1,chars,rr,cc);
                chars[rr][cc] = chars[r][c];
                chars[r][c] = ' ';
            }
        }
    }

    public char[][] unhash(int x)
    {
        char[][] chars = new char[5][5];
        int c = x%8;
        x/=8;
        int r = x%8;
        x/=8;
        for(int i = 0;i<chars.length;i++)
            for(int j = 0;j<chars.length;j++)
            {
                chars[chars.length-1-i][chars.length-1-j] = (char)(48+x%2);
                x/=2;
            }
        chars[r][c] = ' ';
        return chars;
    }

    public boolean val(int r, int c)
    {
        return Math.min(r,c)>=0&&Math.max(r,c)<5;
    }

    public int hash(char[][] chars)
    {
        int r = 0;
        int c = 0;
        int hash = 0;
        for(int i = 0;i<chars.length;i++)
            for(int j = 0;j<chars.length;j++){
                if(chars[i][j]==' ')
                {
                    r = i;
                    c = j;
                }
                if(chars[i][j]=='1')
                    hash++;
                hash*=2;
            }
        hash*=4;
        hash+=r;
        hash*=8;
        hash+=c;
        return hash;
    }
}
