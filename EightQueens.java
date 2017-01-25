import java.awt.Point;
import java.util.Scanner;
import java.util.TreeMap;


public class EightQueens {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        Point[] pts = new Point[8];
        int c = 0;
        try{
            for(int i = 0;i<8;i++)
            {
                String next = file.next();
                for(int j = 0;j<next.length();j++)
                    if(next.charAt(j)=='*')
                        pts[c++] = new Point(i,j);
            }
            for(int i = 0;i<8;i++)
                for(int j = i+1;j<8;j++)
                {
                    Point a = pts[i];
                    Point b = pts[j];
                    if(a.x==b.x||a.y==b.y||Math.abs(a.x-b.x)==Math.abs(a.y-b.y))
                    {
                        System.out.println("invalid");
                        return;
                    }
                }   
        }catch(Exception ex){System.out.println("invalid");return;}
        System.out.println("valid");
    }
}