import java.awt.Point;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;


public class SquareFieldsHard {

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            int P = file.nextInt();
            int N = file.nextInt();
            Point[] pts = new Point[P];
            int miny = 64000;
            int minx = 64000;
            int maxy = 0;
            int maxx = 0;
            for(int i =0 ;i<P;i++)
            {
                int x = file.nextInt();
                int y = file.nextInt();
                maxx = Math.max(maxx,x);
                minx = Math.min(minx,x);
                maxy = Math.max(maxy,y);
                miny = Math.min(miny,y);
                pts[i] = new Point(x,y);
            }
            Arrays.sort(pts,new Comparator<Point>(){
                public int compare(Point a, Point b)
                {
                    return a.x-b.x;
                }
            });
            int min = Math.max(maxx-minx,maxy-miny);
            int fail = 0;
            int pass = min;
            int current = fail+pass/2;
            while(pass!=fail+1)
            {
                if(poss(pts,new boolean[P],current,N,0))
                {
                    //System.out.println(current+" pass");
                    pass = current; 
                }
                else{
                    //System.out.println(current+" fail");
                    fail = current;
                }
                current = fail+(pass-fail)/2;
                //System.out.println(pass+" "+fail+" "+current);
            }
            System.out.printf("Case #%d: %d%n",z+1,pass);
        }
    }
    
    public static boolean poss(Point[] pts, boolean[] vis, int sz,int N, int v)
    {
        //System.out.println(Arrays.toString(pts)+" "+Arrays.toString(vis)+" "+sz+" "+N+" "+v);
        if(v==vis.length)
            return true;
        if(N==0)
            return false;
        Point p = null;
        int i;
        for(i = 0;i<pts.length;i++)
        {
            if(!vis[i])
            {
                p = pts[i];
                break;
            }
        }
        int maxy = 0;
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(p.y);
        for(;i<pts.length;i++){
            if(pts[i].x-p.x>sz)
                break;
            else if(Math.abs(pts[i].y-p.y)<=sz)
            {
                set.add(pts[i].y);
                maxy = Math.max(pts[i].y,maxy);
            }
        }
        //System.out.println(set);
        int start = set.first();
        while(true)
        {
            //System.out.println("start is "+start+" "+N+" left");
            //System.out.println("check 2: "+(start+sz)+" "+set.higher(start+sz)+" "+maxy);
            Stack<Integer> stack = new Stack<Integer>();
            int newV = v;
            for(int j = 0;j<pts.length;j++)
            {
                if(!vis[j]&&p.x+sz>=pts[j].x&&start<=pts[j].y&&start+sz>=pts[j].y)
                {
                    stack.add(j);
                    newV++;
                    vis[j] = true;
                }
            }
            if(poss(pts,vis,sz,N-1,newV))
                return true;
            while(!stack.isEmpty())
                vis[stack.pop()] = false;
            
            if(start+sz==maxy)
                break;
            
            if(set.higher(start+sz)!=null)
                start = set.higher(start+sz)-sz;
            else
                break;
        }
        return false;
    }
}