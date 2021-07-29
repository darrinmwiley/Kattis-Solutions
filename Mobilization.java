import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Mobilization {

    public static void main(String[] args) throws IOException
    {
        new Mobilization().run();
    }

    public void run() throws IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(file.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        unit[] units = new unit[N];
        unit top = null;
        for(int i = 0;i<N;i++)
        {
            st = new StringTokenizer(file.readLine());
            double C = Double.parseDouble(st.nextToken());
            double scale = B/C;
            double x = Double.parseDouble(st.nextToken())*scale;
            double y = Double.parseDouble(st.nextToken())*scale;
            units[i] = new unit(x,y);
            if(top == null || (units[i].y > top.y || units[i].y == top.y && units[i].x < top.x))
            {
                top = units[i];
            }
        }
        ArrayList<unit> list = new ArrayList<unit>();
        list.add(top);
        Arrays.sort(units);
        int tpi = -1;
        for(int i = 0;i<units.length;i++)
        {
            if(units[i] == top)
                tpi = i;
        }
        for(int i = tpi+1;i<units.length;i++)
        {
            while(true)
            {
                if(list.size() < 2)
                {
                    break;
                }
                unit mid = list.get(list.size() - 1);
                unit first = list.get(list.size() - 2);
                unit next = units[i];
                if(ccw(first.x, first.y, mid.x, mid.y, next.x, next.y))
                    list.remove(list.size() - 1);
                else
                    break;

            }
            list.add(units[i]);
        }
        double best = 0;
        for(int i = 0;i<list.size() - 1;i++)
        {
            unit a = list.get(i);
            unit b = list.get(i+1);
            if(a.x == b.x)
                continue;
            double M = (a.y - b.y)/(a.x - b.x);
            double BB = a.y - M*a.x;
            double xCrit = -BB/(2*M);
            double yCrit = M*xCrit+BB;
            if(xCrit > Math.min(a.x, b.x) && xCrit < Math.max(a.x,b.x))
            	best = Math.max(best, xCrit * yCrit);
        }
        for(unit x:units)
        {
            best = Math.max(best, x.x*x.y);
        }
        System.out.println(best);
    }

    public static boolean ccw(double x1, double y1, double x2, double y2, double x3, double y3)
    {
        return (y3-y1)*(x2-x1)>=(y2-y1)*(x3-x1);
    }

    private class unit implements Comparable<unit>{

        double x;
        double y;

        public unit(double x, double y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(unit o) {
            return Double.compare(x, o.x);
        }

    }


}
