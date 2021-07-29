import java.awt.Point;
import java.util.Scanner;


public class Watersheds {
    char current = 'a';
    int[][] ints;
    char [][] chars;
    public void run(){
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        file.nextLine();
        for(int z = 0;z<zz;z++)
        {
            System.out.printf("Case #%d:%n",z+1);
            current = 'a';
            int r = file.nextInt();
            int c = file.nextInt();
            chars = new char[r][c];
            ints = new int[r][c];
            for(int i = 0;i<chars.length;i++)
                for(int j = 0;j<chars[0].length;j++)
                    ints[i][j] = file.nextInt();
            for(int i = 0;i<chars.length;i++){
                for(int j = 0;j<chars[i].length;j++)
                {
                    if(chars[i][j]==0)
                        System.out.print(trace(i,j)+" ");
                    else
                        System.out.print(chars[i][j]+" ");
                }
                System.out.println();
            }


        }
    }

    public char trace(int r, int c)
    {
        if(chars[r][c]!=0)
            return chars[r][c];
        Point p = getNext(r,c);
        if(p==null)
            return chars[r][c] = current++;
        return chars[r][c] = trace(p.x,p.y);
    }

    public Point getNext(int r, int c){
        int[][] d = new int[][]{{-1,0,0,1},{0,-1,1,0}};
        int best = -1;
        int elevation = ints[r][c];
        for(int i = 0;i<4;i++)
        {
            if(val(r+d[0][i],c+d[1][i])&&ints[r+d[0][i]][c+d[1][i]]<elevation)
            {
                best = i;
                elevation = ints[r+d[0][i]][c+d[1][i]];
            }
        }
        //if(best!=-1)
        //System.out.println(r+" "+c+" goes "+"north west east south".split(" ")[best]);
        if(best == -1)
            return null;
        return new Point(r+d[0][best],c+d[1][best]);
    }

    public boolean val(int r, int c)
    {
        return Math.min(r,c)>=0&&r<chars.length&&c<chars[r].length;
    }

    public static void main(String[] args)
    {
        new Watersheds().run();
    }

}
