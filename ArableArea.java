import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ArableArea {

    double BAD = -500;
    long time;

    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new ArableArea().run();
    }

    private class event implements Comparable<event>{

    	int time;
    	int lineId;

    	public event(int t, int d)
    	{
    		time = t;
    		lineId = d;
    	}

		@Override
		public int compareTo(event arg0) {
			return Integer.compare(time, arg0.time);
		}

    }

    public void run() throws NumberFormatException, IOException
    {
        time = System.currentTimeMillis();
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        while(true)
        {
            int N = Integer.parseInt(file.readLine());
            if(N == 0)
                break;
            int[] x = new int[N];
            int[] y = new int[N];
            for(int i = 0;i<N;i++)
            {
                StringTokenizer st = new StringTokenizer(file.readLine());
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
            }
            line[] lines = new line[N];
            for(int i = 0;i<lines.length;i++)
                lines[i] = new line(i,x[i],y[i],x[(i+1)%N],y[(i+1)%N]);
            ArrayList<line> meaningfulLines = new ArrayList<line>();
            for(line l:lines)
            	if(!l.vertical)
            		meaningfulLines.add(l);
            PriorityQueue<event> starts = new PriorityQueue<event>();
            PriorityQueue<event> ends = new PriorityQueue<event>();
            for(line l:meaningfulLines){
            	starts.add(new event(l.x1, l.id));
            	ends.add(new event(l.x2, l.id));
            }
            int ans = 0;
            ArrayList<line> active = new ArrayList<line>();
            for(int i = -100;i<100;i++)
            {
                while(!ends.isEmpty() && ends.peek().time == i)
                {
                	line l = lines[ends.poll().lineId];
                	int index = search(active, l,i-1);
                	//System.out.println("removing "+l);
                	active.remove(index);
                	//System.out.println("successful");
                }
                while(!starts.isEmpty() && starts.peek().time == i)
                {
                	line l = lines[starts.poll().lineId];
                	//System.out.println("adding "+l);
                	int index = search(active, l,i);
                	active.add(index,l);
                	//System.out.println("successful");
                }
                for(int j = 0;j<active.size();j+=2)
                {
                    line bottom = active.get(j+1);
                    line top = active.get(j);
                    ans += top.between(bottom, i, i+1);
                }
            }
            out.println(ans);
        }
        //out.println((System.currentTimeMillis()-time)/1000.0+" seconds");
        out.flush();
        out.close();
    }

    public int search(ArrayList<line> list, line line, int x)
    {
    	int L = -1;
    	int R = list.size();
    	int M = (L+R)/2;
    	while(R-L>1)
    	{
    		M = (L+R)/2;
    		line test = list.get(M);
    		int comp = line.compareTo(test, x);
    		if(comp == 0)
    			return M;
    		if(comp < 0)
    			L = M;
    		else
    			R = M;
    	}
    	return R;
    }

/*
8
0 0
5 2
0 2
5 4
0 4
5 6
6 6
6 0
4
1 0
2 2
2 4
1 3
4
-100 -100
99 100
100 100
100 99
4
-100 -100
-100 100
100 100
100 -100
3
-100 -100
99 100
100 99
12
0 0
3 0
3 3
1 3
1 4
3 4
3 5
0 5
0 2
2 2
2 1
0 1
3
-100 0
100 0
100 1
3
-100 0
100 0
100 2
0
 */

    private class line
    {
    	int id;
        int x1, x2, y1, y2;
        public HashMap<Integer,frac> terp;
        boolean vertical;//if vertical, y1 < y2
        public line(int id, int x1, int y1, int x2, int y2)
        {
        	this.id = id;
            terp = new HashMap<>();
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            swap();
            for(int i = this.x1;i<=this.x2;i++)
                terp.put(i,interpolateFirst(i));
        }

        public void swap()
        {
            if(x1 > x2)
            {
                int x3 = x1;
                int y3 = y1;
                x1 = x2;
                y1 = y2;
                x2 = x3;
                y2 = y3;
            }
            vertical = x1 == x2;
            if(vertical && y1 > y2)
            {
                int x3 = x1;
                int y3 = y1;
                x1 = x2;
                y1 = y2;
                x2 = x3;
                y2 = y3;
            }
        }

        public int between(line line, int x1, int x2)//assume you're the higher line
        {
            int left1 = (int)Math.floor(interpolate(x1).doubleValue());
            int left2 = (int)Math.ceil(line.interpolate(x1).doubleValue());
            int right1 = (int)Math.floor(interpolate(x2).doubleValue());
            int right2 = (int)Math.ceil(line.interpolate(x2).doubleValue());

            int topmin = Math.min(left1, right1);
            int botmax = Math.max(left2, right2);
            return Math.max(0,topmin-botmax);
        }

        public frac interpolateFirst(int x)//get Y value for said X, assumes not vertical
        {
            if(vertical)
                return null;
            if(x < x1 || x > x2)
            {
                return null;
            }
            double dist = x - x1;
            frac slope = new frac(BigInteger.valueOf(y2-y1),BigInteger.valueOf(x2-x1));
            return new frac(BigInteger.valueOf(y1),BigInteger.ONE).add(slope.multiply(new frac(BigInteger.valueOf(x-x1),BigInteger.ONE)));
        }

        public frac interpolate(int x)
        {
        	return terp.get(x);
        }

        public String toString()
        {
            return x1+" "+y1+" "+x2+" "+y2;
        }

		public int compareTo(line arg0, int x) {
			//System.out.println(this+" comparing to "+arg0+" at x = "+x+","+(x+1));
			//console.log(interpolate(x)+" "+arg0.interpolate(x)+" "+interpolate(x+1)+" "+arg0.interpolate(x+1));
			int a = Double.compare(interpolate(x).doubleValue(),arg0.interpolate(x).doubleValue());
            if(a != 0)
                return a;
            return Double.compare(interpolate(x+1).doubleValue(),arg0.interpolate(x+1).doubleValue());
		}
    }

    private class frac{

        BigInteger num;
        BigInteger denom;

        public frac(BigInteger num,BigInteger denom)
        {
            if(isNegative(num))
            {
                this.num = num.negate();
                this.denom = denom.negate();
            }else {
                this.num = num;
                this.denom = denom;
            }
            reduce();
        }

        public boolean isNegative(BigInteger x)
        {
            return x.compareTo(BigInteger.ZERO)<0;
        }

        boolean isNegative()
        {
            return isNegative(num)^isNegative(denom);
        }

        public BigInteger makePositive(BigInteger x)
        {
            if(x.compareTo(BigInteger.ZERO)<0)
                return x.negate();
            return x;
        }

        public frac(int num,int denom)
        {
            this(new BigInteger(num+""),new BigInteger(denom+""));
        }

        public frac add(frac f)
        {
            BigInteger lcm = denom.multiply(f.denom).divide(denom.gcd(f.denom));
            BigInteger multa = lcm.divide(denom);
            BigInteger multb = lcm.divide(f.denom);
            return new frac(num.multiply(multa).add(f.num.multiply(multb)),lcm);
        }

        public boolean equals(frac f)
        {
            return f.compareTo(this)==0;
        }

        public int compareTo(frac f)
        {
            BigInteger a = num.multiply(f.denom);
            BigInteger b = f.num.multiply(denom);
            int ret = a.compareTo(b);
            if(this.isNegative()^f.isNegative())
                return -ret;
            return ret;

        }

        public void reduce()
        {
            BigInteger gcd = num.gcd(denom);
            num = num.divide(gcd);
            denom = denom.divide(gcd);
        }

        public frac multiply(frac f)
        {
            return new frac(num.multiply(f.num),denom.multiply(f.denom));
        }

        public frac divide(BigInteger x)
        {
            return new frac(num,denom.multiply(x));
        }

        public String toString()
        {
            return num+"/"+denom;
        }

        public double doubleValue()
        {
            return num.doubleValue()/denom.doubleValue();
        }

    }
}
