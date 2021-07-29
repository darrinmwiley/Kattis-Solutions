mport java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Dejavu {

    public static void main(String[] args) throws FileNotFoundException
    {
        new Dejavu().run();
    }

    public void run() throws FileNotFoundException
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        point[] pts = new point[N];
        int[] x = new int[100001];
        int[] y = new int[100001];
        for(int i = 0;i<N;i++)
        {
            int a = file.nextInt();
            int b = file.nextInt();
            x[a]++;
            y[b]++;
            pts[i] = new point(a,b);
        }
        int[] sumx = new int[x.length];
        int[] sumy = new int[y.length];
        for(int i = 1;i<sumx.length;i++)
        {
            sumx[i] = sumx[i-1]+x[i];
            sumy[i] = sumy[i-1]+y[i];
        }
        point[] sortx = new point[pts.length];
        point[] sorty = new point[pts.length];
        for(point p: pts)
        {
            int spot = sumx[p.x-1]++;
            sortx[spot] = p;
        }
        for(point p: pts)
        {
            int spot = sumy[p.y-1]++;
            sorty[spot] = p;
        }
        int[] pos = new int[100001];
        //left to right
        for(point p: sortx)
        {
            p.left = pos[p.y];
            pos[p.y]++;
        }
        for(point p:pts)
            p.right = pos[p.y]-1-p.left;
        reset(pos);
        for(point p: sorty)
        {
            p.above = pos[p.x];
            pos[p.x]++;
        }
        for(point p:pts)
            p.below = pos[p.x]-1-p.above;
        long ans = 0;
        for(point p:pts)
        {
            ans+= p.above*p.left+p.above*p.right+p.below*p.left+p.below*p.right;
        }
        System.out.println(ans);
    }

    public void reset(int[] pts)
    {
        Arrays.fill(pts,0);
    }

    private class point{

        int x, y;
        long above,below,left,right;
        public point(int a, int b)
        {
            x = a;
            y = b;
        }

        public String toString()
        {
            return "("+x+","+y+")";
        }

    }

}
