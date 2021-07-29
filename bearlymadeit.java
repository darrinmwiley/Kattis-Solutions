/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/almostunionfind
TAGS: union find, DSU
EXPLANATION:
This is a simple modification on the Union Find data structure. 

We will make our Union Find variable size, and add an auxiliary array called point, where point[i] is the location of element i in the modified Union Find. 

To implement union(a,b), we just do a standard union of (point[a], point[b])

To implement move(a,b):
1) remove a from the Union Find by subtracting one from it's parent size and setting point[a] to null
2) add a new element to the union find, and set point[a] to this new index.
3) perform our modified union on a and b now.

Each of these operations take constant time, so overall the time and space complexity is O(n)
END ANNOTATION
*/
/*countercase found
0 0 16 0
25
6 8 2
6 0 2
8 0 1
14 0 1
16 0 3
6 4 1
0 0 1
4 0 1
8 8 1
10 8 2
12 8 1
0 8 1
2 8 2
4 8 1
4 4 2
2 4 1
12 6 1
12 4 2
12 2 1
12 0 1
0 6 2
0 4 1
8 4 1
8 2 1
2 0 2


 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bearlymadeit {
    
    int N;
    double[] cx;
    double[] cy;
    double[] r;
    
    double epsilon = .000001;
    
    double[][] dist;
    node[] nodes;
    
    public void run() throws IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(file.readLine());
        double xb = Integer.parseInt(st.nextToken());
        double yb = Integer.parseInt(st.nextToken());
        double xm = Integer.parseInt(st.nextToken());
        double ym = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(file.readLine());
        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> y = new ArrayList<Double>();
        x.add(xb);
        y.add(yb);
        x.add(xm);
        y.add(ym);
        cx = new double[N];
        cy = new double[N];
        r = new double[N];
        for(int i = 0;i<N;i++)
        {
        		st = new StringTokenizer(file.readLine());
        		cx[i] = Integer.parseInt(st.nextToken());
        		cy[i] = Integer.parseInt(st.nextToken());
        		r[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0;i<N;i++)
        {
        		for(int j = i+1;j<N;j++)
        		{
        			double d = dist(cx[i], cy[i], cx[j], cy[j]);
        			//System.out.println(i+" "+j+" "+d);
        			if(Math.abs(d-r[i]-r[j])<epsilon)
        			{
        				x.add((cx[i]+cx[j])/2.0);
        				y.add((cy[i]+cy[j])/2.0);
        			}else if(d < r[i] + r[j] && d > Math.abs(r[i] - r[j])){
	        			double a = (r[i]*r[i]-r[j]*r[j]+d*d)/(d*2);
	        			//a = 554138.68913;
	        			//System.out.println(d+" "+a);
	        			double h = Math.sqrt(r[i]*r[i]-a*a);
	        			double p2x = cx[i]+a*(cx[j] - cx[i])/d;
	        			double p2y = cy[i]+a*(cy[j] - cy[i])/d;
	        			//System.out.println(p2x+" "+p2y + " "+h);
	        			double x1 = p2x+h*(cy[j] - cy[i])/d;
	        			double y1 = p2y-h*(cx[j] - cx[i])/d;
	        			double x2 = p2x-h*(cy[j] - cy[i])/d;
	        			double y2 = p2y+h*(cx[j] - cx[i])/d;
	        			//System.out.println(h+" "+cy[j] +" "+cy[i]+" "+d);
	        			//System.out.println(cx[i]+" "+cy[i]+" "+cx[j]+" "+cy[j]);
	        			//System.out.println(i+" "+j+" "+d+" "+a+" "+h+" "+x1+" "+y1+" "+x2+" "+y2);
	        			x.add(x1);
	        			y.add(y1);
	        			x.add(x2);
	        			y.add(y2);
	        			//System.out.println("("+cx[i]+" "+cy[i]+" "+r[i]+")"+" "+"("+cx[j]+" "+cy[j]+" "+r[j]+")"+" "+"("+x1+","+y1+") "+"("+x2+","+y2+") ");
        			}
        		}
        }
        //System.out.println((x));
        nodes = new node[x.size()];
        for(int i = 0;i<nodes.length;i++)
        {
        		nodes[i] = new node(i,x.get(i),y.get(i));
        }
        dist = new double[nodes.length][nodes.length];
        for(int i = 0;i<nodes.length;i++)
        {
        		for(int j = i+1;j<nodes.length;j++)
        		{
        			if(canMove(x.get(i), y.get(i),x.get(j),y.get(j))) {
        				//System.out.println("("+x.get(i)+","+y.get(i)+")"+"->"+"("+x.get(j)+","+y.get(j)+") "+i+"->"+j);
        				con(i,j);
        			}
        			dist[i][j] = dist[j][i] = dist(x.get(i), y.get(i),x.get(j),y.get(j));
        		}
        }
        //for(int i = 0;i<x.size();i++)
        //{
        //	System.out.print("("+x.get(i)+","+y.get(i)+") ");
        //}
        //System.out.println();
        boolean[] vis = new boolean[nodes.length];
        double[] fp = new double[nodes.length];
        node[] pred = new node[nodes.length];
        Arrays.fill(fp, Double.POSITIVE_INFINITY);
        PriorityQueue<state> que = new PriorityQueue<state>();
        state begin = new state(0,0);
        que.add(begin);
        while(!que.isEmpty())
        {
        		state s = que.poll();
        		if(vis[s.location] || fp[s.location] <= s.cost)
        			continue;
        		vis[s.location] = true;
        		fp[s.location] = s.cost;
        		node curr = nodes[s.location];
        		for(int next: curr.con)
        		{
        			if(!vis[next] && s.cost + dist[curr.id][next] < fp[next])
        			{
        				pred[next] = curr;
        				que.add(new state(next, s.cost+dist[curr.id][next]));
        			}
        		}
        }
        String ans = "";
        
        for(node n: nodes)
        {
        //	System.out.println(n.id+" "+n.x+" "+n.y);
        }
        //System.out.println(ans);
        if(Double.isInfinite(fp[1]))
        {
        	System.out.println("impossible");
        }else
        	System.out.println(fp[1]);
    }
    
    private class state implements Comparable<state>{
    	
    		int location;
    		double cost;
    		
    		public state(int l, double c)
    		{
    			this.location = l;
    			this.cost = c;
    		}

		@Override
		public int compareTo(state o) {
			return Double.compare(cost, o.cost);
		}
    	
    }
    
    public void con(int a, int b)
    {
    		nodes[a].con.add(b);
    		nodes[b].con.add(a);
    }
    
    private class node{
    	
    		int id;
    		double x, y;
    		ArrayList<Integer> con;
    	
    		public node(int id, double x, double y) {
    			this.id = id;
    			this.x = x;
    			this.y = y;
    			con = new ArrayList<Integer>();
    		}
    	
    }
    
    public boolean canMove(double x1, double y1, double x2, double y2)
    {
    	if(Math.abs(x1 - x2) < epsilon)
    		return canMoveY(x1, y1, x2, y2);
    	else
    		return canMoveX(x1,y1,x2,y2);
    }
    
    public boolean canMoveX(double x1, double y1, double x2, double y2)
    {
    	if(x1 == 0)
    	{
    		//new Scanner(System.in).nextLine();
    	}
    	//System.out.println("testing "+x1+" "+y1+" "+x2+" "+y2);
		PriorityQueue<event> que = new PriorityQueue<event>();
		for(int i = 0;i<N;i++)
		{
			segment seg = new segment(x1, y1, x2, y2);
			double[] interval = getInterval(seg, cx[i], cy[i], r[i])[0];
			if(interval != null)
			//	System.out.println("interval: "+Arrays.toString(interval));
			if(interval != null)
			{
				que.add(new event(interval[0], true));
				que.add(new event(interval[1], false));
			}
		}
		int open = 0;
		boolean flag = false;
		while(!que.isEmpty())
		{
			event e = que.poll();
			//System.out.print(e+" ");
			if(e.open)
			{
				open++;
			}
			if(!e.open)
			{
				open--;
				if(open == 0)
				{
					//System.out.println();
					if(Math.abs(e.x-Math.max(x1, x2)) < epsilon) {
						//System.out.println("TRUE TRUE TRUE TRUE TRUE");
						return true;	
					}
					else
						//System.out.println("FALSE FALSE FALSE FALSE FALSE");
						return false;
				}
			}
		}
		//System.out.println("FALSE FALSE FALSE FALSE FALSE");
		return false;
    }
    
    public boolean canMoveY(double x1, double y1, double x2, double y2)
    {
    	//System.out.println("testing "+x1+" "+y1+" "+x2+" "+y2);
    		PriorityQueue<event> que = new PriorityQueue<event>();
    		for(int i = 0;i<N;i++)
    		{
    			segment seg = new segment(x1, y1, x2, y2);
    			double[] interval = getInterval(seg, cx[i], cy[i], r[i])[1];
    			if(interval != null)
    			{
    				que.add(new event(interval[0], true));
    				que.add(new event(interval[1], false));
    			}
    		}
    		int open = 0;
    		boolean flag = false;
    		while(!que.isEmpty())
    		{
    			event e = que.poll();
    			//System.out.print(e+" ");
    			if(e.open)
    			{
    				open++;
    			}
    			if(!e.open)
    			{
    				open--;
    				if(open == 0)
    				{
    					//System.out.println();
    					if(Math.abs(e.x - Math.max(y1, y2)) < epsilon) {
    						return true;	
    					}
    					else
    						return false;
    				}
    			}
    		}
    		return false;
    }
    
    private class event implements Comparable<event>{
    	
    		double x;
    		boolean open;
    		
    		public event(double x, boolean open)
    		{
    			this.x = x;
    			this.open = open;
    		}
		
    		@Override
		public int compareTo(event o) {
    			if(Math.abs(x - o.x) < epsilon)
    			{
    				return Boolean.compare(o.open, open);
    			}
			return Double.compare(x, o.x);
		}	
    		
    	public String toString()
    	{
    		return "("+x+" "+open+")";
    	}
    	
    }
    
    public double[][] getInterval(segment seg, double cx, double cy, double r)
    {
    		segment oseg = seg;
    		seg = new segment(seg.x1 - cx, seg.y1 - cy, seg.x2 - cx, seg.y2 - cy);
    		double det = r*r*(seg.A*seg.A+seg.B*seg.B)-seg.C*seg.C;
    		if(det <= 0)
    			return new double[][] {null, null};
    		double denom = seg.A*seg.A+seg.B*seg.B;
    		double x1 = cx +(seg.A*seg.C+seg.B*Math.sqrt(det))/denom;
    		double x2 = cx +(seg.A*seg.C-seg.B*Math.sqrt(det))/denom;
    		
    		double y1 = cy +(seg.B*seg.C-seg.A*Math.sqrt(det))/denom;
    		double y2 = cy +(seg.B*seg.C+seg.A*Math.sqrt(det))/denom;
    		
    		double minx = cx + Math.min(seg.x1,seg.x2);
    		double maxx = cx + Math.max(seg.x1,seg.x2);
    		
    		double miny = cy + Math.min(seg.y1, seg.y2);
    		double maxy = cy + Math.max(seg.y1, seg.y2);
    		
    		//System.out.println(oseg+" "+cx+" "+cy+" "+r);
    		//System.out.println(seg.A+" "+seg.B+" "+seg.C);
    		//System.out.println(x1+" "+x2);
    		//System.out.println("@"+det+" "+denom+" "+x1+" "+x2);
    		double[] xans = new double[] {Math.max(minx, Math.min(x1, x2)), Math.min(maxx, Math.max(x1, x2))};
    		double[] yans = new double[] {Math.max(miny, Math.min(y1, y2)), Math.min(maxy, Math.max(y1, y2))};
    		
    		//System.out.println(Arrays.toString(xans));
    		//System.out.println();
    		
    		return new double[][] {xans, yans};
    }
    
    private class segment{
    	
    		double x1, y1, x2, y2;
    		double A,B,C;
    		
    		public segment(double x1, double y1, double x2, double y2)
    		{
    			this.x1 = x1;
    			this.y1 = y1;
    			this.x2 = x2;
    			this.y2 = y2;
    			A = y2 - y1;
    			B = x1 - x2;
    			C = A*x1+B*y1;
    		}
    		
    		public String toString()
    		{
    			return "("+x1+" "+y1+" "+x2+" "+y2+")";
    		}
    	
    }
    
    public double dist(double x1, double y1, double x2, double y2)
    {
    	return Math.sqrt((x1-x2)*(x1 - x2)+(y1 - y2)*(y1 - y2));
    }
            
    
    public static void main(String[] args) throws IOException
    {
        new bearlymadeit().run();
    }
} 