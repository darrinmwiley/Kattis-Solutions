/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/memorymatch
TAGS: casework
EXPLANATION:
the problem falls into 3 possible cases.

1) you've seen at least 1 of every card type, in this case you can flip them all
2) you've seen both of all but one card type, in this case you can flip them all
3) in any other case, you can only flip cards that you've seen both locations for
END ANNOTATION
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
 
public class PolygonGame {
    
    PrintWriter pout;
    
    long startTimeMillis;
    long benchmark;
    
    boolean DEBUG_FLAG = false;
    
    node[] nodes;
    int[] color;
    int WHITE = 0;
    int GREY = 1;
    int BLACK = 2;
    int ccc = 0;
    
    double epsilon = .00001;
    
    int[] p;
/*
4 6
0 6
11 6
11 0
2 0
1 3 4 6
1 3 6 0
6 0 11 5
10 6 10 0
3 6 11 2
8 6 10 0
 */
    
    public static void main(String[] args) throws Exception
    {
        long entryTime = System.currentTimeMillis();
        PolygonGame t = new PolygonGame();
        t.benchmark = t.startTimeMillis = entryTime;
        t.pout = new PrintWriter(System.out);
        t.run();
        t.pout.flush();
        t.pout.close();
    }
    
    public void run()
    {
            Scanner file = new Scanner(System.in);
            int N = file.nextInt();
            int M = file.nextInt();
            double[] X = new double[N];
            double[] Y = new double[N];
            for(int i = 0;i<N;i++)
            {
                X[i] = file.nextDouble();
                Y[i] = file.nextDouble();
            }
            segment[] segs = new segment[N+M];
            for(int i = 0;i<N;i++)
            {
                segs[i] = new segment(X[i],Y[i],X[(i+1)%N],Y[(i+1)%N]);
            }
            LinkedList<poly> shapes = new LinkedList<poly>();
            shapes.add(new poly(X,Y));
            for(int i = 0;i<M;i++)
            {
                /*for(poly p:shapes)
                {
                    System.out.println(p);
                }
                System.out.println();*/
                double x1 = file.nextDouble();
                double y1 = file.nextDouble();
                double x2 = file.nextDouble();
                double y2 = file.nextDouble();
                segment seg = new segment(x1,y1,x2,y2);
                int sz = shapes.size();
                for(int j = 0;j<sz;j++)
                {
                    poly p = shapes.removeFirst();
                    shapes.addAll(p.clip(seg));
                }
            }
            for(poly p:shapes)
            {
                print(p);
            }
            print(" ");
            double max = 0;
            for(poly p:shapes)
            {
                print(p.area());
                max = Math.max(max, p.area());
            }
            pout.println(max);
    }
    
    private class pt{
        
            double x;
            double y;
            public pt(double xx, double yy)
            {
                x = xx;
                y = yy;
            }
        
    }
    
    private class poly{
        
        double[] X;
        double[] Y;
        segment[] segments;
        int N;
        
        public poly(double[] X, double[] Y){
            this.X = X;
            this.Y = Y;
            N = X.length;
            segments = new segment[N];
            for(int i = 0;i<N;i++)
                segments[i] = new segment(X[i],Y[i],X[(i+1)%N],Y[(i+1)%N]);
        }
        
        public poly(LinkedList<Double> x, LinkedList<Double> y)
        {
            N = x.size();
            X = new double[N];
            Y = new double[N];
            for(int i = 0;i<N;i++)
            {
                X[i] = x.removeFirst();
                Y[i] = y.removeFirst();
            }
            segments = new segment[N];
            for(int i = 0;i<N;i++)
                segments[i] = new segment(X[i],Y[i],X[(i+1)%N],Y[(i+1)%N]);
        }
        
        public double area()
        {
            double sum = 0;
            for(int i = 0;i<N;i++)
            {
                sum += X[i] * Y[(i+1)%N] - Y[i] * X[(i+1)%N];
            }
            return Math.abs(sum/2);
        }
        
        public String toString()
        {
            String ans = "(";
            for(int i = 0;i<N;i++)
            {
                ans += X[i]+" "+Y[i]+",";
            }
            return ans.substring(0,ans.length() - 1)+")";
        }
        
        public ArrayList<poly> clip(segment seg)
        {
            print("poly: "+this+" "+seg.x1+" "+seg.y1+" "+seg.x2+" "+seg.y2);
            ArrayList<Integer> intersections = getIntersection(seg);
            ArrayList<poly> ret = new ArrayList<poly>();
            if(intersections.size() != 2) {
                ret.add(this);
                return ret;
            }
            else {
                if(intersections.get(0) < 0 && intersections.get(1) < 0)
                {
                    segment seg1 = segments[-intersections.get(0)-1];
                    LinkedList<Double> x = new LinkedList<Double>();
                    LinkedList<Double> y = new LinkedList<Double>();
                    double[] intersection1 = seg1.intersection(seg, false);
                    //System.out.println(Arrays.toString(intersection1));
                    x.add(intersection1[0]);
                    y.add(intersection1[1]);
                    segment seg2 = segments[-intersections.get(1)-1];
                    double[] intersection2 = seg2.intersection(seg, false);
                    int index = -intersections.get(0) % N;
                    while(true)
                    {
                        x.add(X[index]);
                        y.add(Y[index]);
                        if(index == -intersections.get(1) - 1) {
                            x.add(intersection2[0]);
                            y.add(intersection2[1]);
                            ret.add(new poly(x,y));
                            break;
                        }
                        index++;
                        if(index >= N)
                            index = 0;
                    }
                    x = new LinkedList<Double>();
                    y = new LinkedList<Double>();
                    x.add(intersection2[0]);
                    y.add(intersection2[1]);
                    index = -intersections.get(1) % N;
                    while(true)
                    {
                        x.add(X[index]);
                        y.add(Y[index]);
                        if(index == -intersections.get(0) - 1) {
                            x.add(intersection1[0]);
                            y.add(intersection1[1]);
                            ret.add(new poly(x,y));
                            break;
                        }
                        index++;
                        if(index >= N)
                            index = 0;
                    }
                }
                if(intersections.get(0) < 0 && intersections.get(1) >= 0)
                {
                    segment seg1 = segments[-intersections.get(0)-1];
                    LinkedList<Double> x = new LinkedList<Double>();
                    LinkedList<Double> y = new LinkedList<Double>();
                    segment seg2 = segments[-intersections.get(0) - 1];
                    double[] intersection2 = seg2.intersection(seg, false);
                    int index = intersections.get(1) % N;
                    while(true)
                    {
                        x.add(X[index]);
                        y.add(Y[index]);
                        if(index == -intersections.get(0) - 1) {
                            x.add(intersection2[0]);
                            y.add(intersection2[1]);
                            ret.add(new poly(x,y));
                            break;
                        }
                        index++;
                        if(index >= N)
                            index = 0;
                    }
                    x = new LinkedList<Double>();
                    y = new LinkedList<Double>();
                    x.add(intersection2[0]);
                    y.add(intersection2[1]);
                    index = -intersections.get(0) % N;
                    while(true)
                    {
                        x.add(X[index]);
                        y.add(Y[index]);
                        if(index == intersections.get(1)) {
                            ret.add(new poly(x,y));
                            break;
                        }
                        index++;
                        if(index >= N)
                            index = 0;
                    }
                }
                if(intersections.get(0) >= 0 && intersections.get(0) >= 0)
                {
                    LinkedList<Double> x = new LinkedList<Double>();
                    LinkedList<Double> y = new LinkedList<Double>();
                    segment seg2 = segments[-intersections.get(0)];
                    double[] intersection2 = seg2.intersection(seg, false);
                    int index = intersections.get(0) % N;
                    while(true)
                    {
                        x.add(X[index]);
                        y.add(Y[index]);
                        if(index == intersections.get(1)) {
                            ret.add(new poly(x,y));
                            break;
                        }
                        index++;
                        if(index >= N)
                            index = 0;
                    }
                    x = new LinkedList<Double>();
                    y = new LinkedList<Double>();
                    index = intersections.get(1) % N;
                    while(true)
                    {
                        x.add(X[index]);
                        y.add(Y[index]);
                        if(index == intersections.get(0)) {
                            ret.add(new poly(x,y));
                            break;
                        }
                        index++;
                        if(index >= N)
                            index = 0;
                    }
                }
            }
            print("list: "+ret);
            print(" ");
            return ret;
        }
        
        public ArrayList<Integer> getIntersection(segment seg)
        {
            //System.out.println("poly: "+this+" "+seg.x1+" "+seg.y1+" "+seg.x2+" "+seg.y2);
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i = 0;i<segments.length;i++)
            {
                segment s = segments[i];
                if(s.intersection(seg, false) != null) {
                    list.add(-i-1);
                    //System.out.println(Arrays.toString(seg.intersection(s, false)));
                }
            }
            for(int i = 0;i<X.length;i++)
            {
                if(seg.contains(X[i], Y[i]))
                {
                    list.add(i);
                }
            }
            return list;
        }
    }
    
    private class segment {
        double A,B,C;//want line in form Ax+By = C
        double x1, y1,x2,y2;
        public segment(double x1, double y1, double x2, double y2)
        {
            A = y2-y1;
            B = x1-x2;
            C = A*x1+B*y1;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
        
        private segment(double A, double B, double C)
        {
            this.A = A;
            this.B = B;
            this.C = C;
        }
        
        public double det(segment li)
        {
            return A*li.B-li.A*B;
        }
        
        public boolean contains(double x, double y)
        {
            if(x >= Math.min(x1, x2)-epsilon && x-epsilon <= Math.max(x1, x2))
            {
                if(y >= Math.min(y1, y2)-epsilon && y-epsilon <= Math.max(y1, y2))
                {
                    return Math.abs(A*x+B*y-C) <= epsilon;
                }
            }
            return false;
        }
        
        boolean isEndpoint(double x, double y)
        {
            //System.out.println(x+" "+y);
            //System.out.println(Math.abs(x - x1));
            //System.out.println(Math.abs(y - y1));
            //System.out.println(Math.abs(x - x2));
            //System.out.println(Math.abs(y - y2));
            return Math.abs(x - x1)<epsilon && Math.abs(y - y1) < epsilon || Math.abs(x - x2) < epsilon && Math.abs(y - y2) < epsilon; 
        }
        
        public String toString()
        {
            return "("+x1+" "+y1+" "+x2+" "+y2+")";
        }
        
        public double[] intersection(segment li, boolean allowEndpoint)
        {
            double det = det(li);
            if(det==0) return null; //zero or infinite solutions
            double x = (li.B*C-B*li.C)/det;
            double y = (A*li.C-li.A*C)/det;
            //System.out.print(li.x1+" "+li.y1+" "+li.x2+" "+li.y2+" "+x1+" "+y1+" "+x2+" "+y2+" ");
            if(contains(x,y) && li.contains(x, y))
            {
                if(!allowEndpoint && isEndpoint(x,y)) {
                    //System.out.println("null1");
                    return null;
                }
                //System.out.println(x+" "+y);
                return new double[] {x,y};
            }
            //System.out.println("null2");
            return null;
        }
    }
    
    public void print(Object o)
    {
        if(DEBUG_FLAG)
            System.out.println(o);
    }
    
    public void time(String s)
    {
        if(DEBUG_FLAG)
        {
            long time = System.currentTimeMillis();
            System.out.println(s+": "+(time - startTimeMillis)+" ("+(time - benchmark)+" millis from last benchmark)");
            benchmark = time;
        }
    }
    
    private class node{
        
            ArrayList<Integer> con;
            int id;
            
            public node(int id)
            {
                this.id = id;
                con = new ArrayList<Integer>();
            }
        
    }
    
    private class Scanner 
    { 
        BufferedReader br; 
        StringTokenizer st; 
  
        public Scanner() 
        { 
            this(System.in);
        } 
        
        public Scanner(File f) throws FileNotFoundException
        {
            br = new BufferedReader(new FileReader(f));
        }
        
        public Scanner(InputStream is)
        {
            br = new BufferedReader(new
                    InputStreamReader(is)); 
        }
        
        public Scanner(String content)
        {
            br = new BufferedReader(new StringReader(content));
        }
  
        String next() 
        { 
            while (st == null || !st.hasMoreElements()) 
            { 
                try
                { 
                    st = new StringTokenizer(br.readLine()); 
                } 
                catch (IOException  e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
  
        int nextInt() 
        { 
            return Integer.parseInt(next()); 
        } 
  
        long nextLong() 
        { 
            return Long.parseLong(next()); 
        } 
  
        double nextDouble() 
        { 
            return Double.parseDouble(next()); 
        } 
  
        String nextLine() 
        { 
            try {
                return st.nextToken("");
            }catch(Exception ex) {
                String str = ""; 
                try
                { 
                    str = br.readLine(); 
                } 
                catch (IOException e) 
                { 
                    e.printStackTrace(); 
                } 
                return str; 
            }
        } 
    }
    
}