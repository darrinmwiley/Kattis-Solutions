import java.util.ArrayList;
import java.util.Scanner;


public class HittingTheTargets {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        shape[] shapes = new shape[file.nextInt()];
        for(int i = 0;i<shapes.length;i++)
        {
            String type = file.next();
            if(type.equals("rectangle"))
                shapes[i] = new shape(file.nextInt(),file.nextInt(),file.nextInt(),file.nextInt());
            else
                shapes[i] = new shape(file.nextInt(),file.nextInt(),file.nextInt());
        }
        int M = file.nextInt();
        for(int i = 0;i<M;i++)
        {
            int x = file.nextInt();
            int y = file.nextInt();
            int hit = 0;
            for(shape s:shapes)
                if(s.inside(x,y))
                    hit++;
            System.out.println(hit);
        }
    }
}
class shape{
    
    int a, b, c, d, x, y, r;
    boolean rect;
    public shape(int a, int b, int c, int d)
    {
        this.a =a ;
        this.b = b;
        this.c = c;
        this.d = d;
        rect = true;
    }
    
    public shape(int x, int y, int r)
    {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    
    public boolean inside(int x, int y)
    {
        if(rect)
            return x>=a&&x<=c&&y>=b&&y<=d;
        return Math.hypot(x-this.x, y-this.y)<=r;
    }
    
}