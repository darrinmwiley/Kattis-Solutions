import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Alchemy7 {
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
    	new Alchemy7().run();
        //new Alchemy7().diff(8);
    	//Alchemy7 al = new Alchemy7();
    	//al.runbf();
    }
	
/*
5
13 3 10 -1 -1
13 8 2 2 -2
3 12 3 1 -1
13 3 9 2 -2
12 4 7 1 1
 */
	
	public long test(circle[] circles, int[] ans, boolean verbose)
	{
		long sum = 0;
		for(int i = 0;i<ans.length;i++)
		{
			boolean flag = true;
			for(int j = i+1;j<ans.length;j++)
			{
				if(inside(circles[ans[i]],circles[ans[j]]))
				{
					if(flag) {
						sum += circles[ans[i]].a;
						if(verbose)
						{
							System.out.println(ans[i]+" inside "+ans[j]+", adding "+circles[ans[i]].a);
						}
					}
					else {
						sum += circles[ans[i]].b;
						if(verbose)
						{
							System.out.println(ans[i]+" inside "+ans[j]+", adding "+circles[ans[i]].b);
						}
					}
					flag = !flag;
				}
			}
		}
		return sum;
	}
	
    
    int N;
    circle[] circles;
    int xx = 0;
    boolean[] used;
    int[] ans;
    boolean[] constraining;
    
    public void run() throws NumberFormatException, IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(file.readLine());
        StringTokenizer st;
        circles = new circle[N];
        for(int i = 0;i<N;i++)
        {
            st = new StringTokenizer(file.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            circles[i] = new circle(i,x,y,r,a,b);
        }
        
        ans = solve(circles);
        
        circle[] c = new circle[N];
        long sum = test(circles, ans, false);
        System.out.println(sum);
        for(int x: ans)
        {
        	System.out.print(x+1+" ");
        }
    }
    
    //assume a smaller than b
    public boolean inside(circle a, circle b)
    {
    	double centDist = dist(a.x,a.y,b.x,b.y);
    	return centDist + a.r < b.r;
    }
    
    public double dist(double x1, double y1, double x2, double y2)
    {
    	return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    
    public int[] solve(circle[] ccc)
    {
    	this.circles = ccc;
    	N = circles.length;
        constraining = new boolean[N];
    	xx = 0;
    	circle[] orig = circles.clone();
        Arrays.sort(circles);
        for(int i = 0;i<circles.length;i++)
        {
            for(int j = i+1;j<circles.length;j++)
            {
                int x1 = circles[i].x;
                int x2 = circles[j].x;
                int y1 = circles[i].y;
                int y2 = circles[j].y;
                int r1 = circles[i].r;
                int r2 = circles[j].r;
                int d1 = (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
                int d2 = (r1+r2)*(r1+r2);
                if(d1 < d2)
                {
                	//System.out.println(circles[i].id+" inside "+circles[j].id);
                	orig[circles[i].id].parents.add(circles[j].id);
                	orig[circles[j].id].children.add(circles[i].id);
                }
            }
        }
        circles = orig;
        for(int i = 0;i<circles.length;i++)
        {
        	circle c = circles[i];
        	String cla = c.classification;
        	if(cla.equals("oo"))
			{
    			//no restrictions
				//no condition
			}else if(cla.equals("o+"))
			{
				//largest even possible, optional 1 extra
				//condition: if ever left with even parents, block all parents
				if((c.parents.size() & 1) == 0)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
					constraining[i] = true;
				}
			}else if(cla.equals("+o"))
			{
				//largest odd possible, optional 1 extra
				//condition: if ever left with odd parents, block all parents
				if((c.parents.size() & 1) == 1)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
					constraining[i] = true;
				}
			}else if(cla.equals("o-"))
			{
				//1 or none	
				//condition: must be <= 1 parent left
			}else if(cla.equals("-o"))
			{
				//condition: parents block this
				//never flip	
				for(int par: c.parents)
				{
					circles[par].out.add(i);
					c.indegree++;
				}
			}else if(cla.equals("--"))
			{
				//condition: parents block this
				//never flip
				for(int par: c.parents)
				{
					circles[par].out.add(i);
					c.indegree++;
				}
			}else if(cla.equals("++"))
			{
				//condition: block all parents
				//flip as much as possible
				for(int par: c.parents)
				{
					c.out.add(par);
					circles[par].indegree++;
				}
			}else if(cla.equals("+--")) {
				//condition: if one parent left, block parent
				//flip once if possible, otherwise zero
				if(c.parents.size() == 1)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
					constraining[i] = true;
				}
			}else if(cla.equals("++-"))
			{
				//condition: must have odd number of parents left. Or potentially zero
				//condition: if ever left with odd number of parents, block all parents
				//largest odd possible, otherwise zero
				if((c.parents.size() & 1) == 1)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
					constraining[i] = true;
				}
			}else if(cla.equals("-++"))
			{
				//condition: must have even number of parents left
				//condition: if ever left with an even number of parents, block all parents
				//largest even possible, otherwise zero
				if((c.parents.size() & 1) == 0)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
					constraining[i] = true;
				}
			}else if(cla.equals("--+"))
			{
				//never flip
				//condition: parents block this
				for(int par: c.parents)
				{
					circles[par].out.add(i);
					c.indegree++;
				}
			}else if(cla.equals("+=-"))
			{
				//condition: if left with one parent, block parent
				//any odd number possible, otherwise zero
				if(c.parents.size() == 1)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
					constraining[i] = true;
				}
			}else if(cla.equals("-=+"))
			{
				//condition: only allowed if even parents left
				//any even number of times
			}
        }
        
        //i times:
        	//1) find the best thing to use that is allowed
        	//2) notify children
        	//3) block/unblock accordingly
        used = new boolean[N];
        ans = new int[N];
     loop:
        for(int x = 0;x<N;x++)
        {
        	//System.out.println(x+"--------------------------");
            /*for(circle c: circles)
            {
            	System.out.println(c.id);
            	System.out.println(c.out);
            	System.out.println(c.indegree);
            	System.out.println();
            }*/
        	for(int i = 0;i<circles.length;i++)
        	{
        		circle c = circles[i];
            	String cla = c.classification;
            	/*if(!used[i] && c.indegree != 0)
            	{
            		System.out.println(c.id+1+" blocked");
            	}*/
            	if(used[i] || c.indegree != 0)
            		continue;
            	if(cla.equals("oo"))
    			{
        			//no restrictions
    				//no condition
            		select(i);
            		continue loop;
    			}else if(cla.equals("o+"))
    			{
    				//largest even possible, optional 1 extra
    				//condition: if ever left with even parents, block all parents
    				select(i);
    				continue loop;
    			}else if(cla.equals("+o"))
    			{
    				//largest odd possible, optional 1 extra
    				//condition: if ever left with odd parents, block all parents
    				select(i);
    				continue loop;
    			}else if(cla.equals("o-"))
    			{
    				//1 or none	
    				//condition: must be <= 1 parent left
    				if(c.parents.size() <= 1) {
    					select(i);
    					continue loop;
    				}
    			}else if(cla.equals("-o"))
    			{
    				//condition: parents block this
    				//never flip	
    				select(i);
    				continue loop;
    			}else if(cla.equals("--"))
    			{
    				//condition: parents block this
    				//never flip
    				select(i);
    				continue loop;
    			}else if(cla.equals("++"))
    			{
    				//condition: block all parents
    				//flip as much as possible
    				select(i);
    				continue loop;
    			}else if(cla.equals("+--")) {
    				//condition: 1 or 0 parent left
    				//condition: if one parent left, block parent
    				//flip once if possible, otherwise zero
    				if(c.parents.size() <= 1) {
    					select(i);
    					continue loop;
    				}
    			}else if(cla.equals("++-"))
    			{
    				//condition: must have odd number of parents left. Or potentially zero
    				//condition: if ever left with odd number of parents, block all parents
    				//largest odd possible, otherwise zero
    				if((c.parents.size() & 1) == 1 || c.parents.isEmpty()) {
    					select(i);
    					continue loop;
    				}
    			}else if(cla.equals("-++"))
    			{
    				//condition: must have even number of parents left
    				//condition: if ever left with an even number of parents, block all parents
    				//largest even possible, otherwise zero
    				if((c.parents.size() & 1) == 0) {
    					select(i);
    					continue loop;
    				}
    			}else if(cla.equals("--+"))
    			{
    				//never flip
    				//condition: parents block this
    				select(i);
    				continue loop;
    			}else if(cla.equals("+=-"))
    			{
    				//condition: if left with one parent, block parent
    				//once if possible, otherwise zero
    				if((c.parents.size() & 1) == 1 || c.parents.isEmpty()) {
    					select(i);
    					continue loop;
    				}
    			}else if(cla.equals("-=+"))
    			{
    				//condition: only allowed if even parents left
    				//any even number of times
    				if((c.parents.size() & 1) == 0)
    				{
    					select(i);
    					continue loop;
    				}
    			}
        	}
        }
        return ans;
    }
    
    public void select(int n)
    {
    	//System.out.println("selecting "+(n+1));
    	used[n] = true;
    	ans[xx++] = n;
    	for(int x:circles[n].children)
    	{
    		circles[x].parents.remove(n);
    	}
    	for(int x: circles[n].out)
    	{
    		circles[x].indegree--;
    	}
    	update();
    }
    
    public void update()
    {
    	for(int i = 0;i<circles.length;i++)
        {
    		if(used[i])
    			continue;
        	circle c = circles[i];
        	String cla = c.classification;
        	if(cla.equals("oo"))
			{
    			//no restrictions
				//no condition
			}else if(cla.equals("o+"))
			{
				//largest even possible, optional 1 extra
				//condition: if ever left with even parents, block all parents
				if(!constraining[i] && (c.parents.size() & 1) == 0)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
				}
			}else if(cla.equals("+o"))
			{
				//largest odd possible, optional 1 extra
				//condition: if ever left with odd parents, block all parents
				if(!constraining[i] && (c.parents.size() & 1) == 1)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
				}
			}else if(cla.equals("o-"))
			{
				//1 or none	
				//condition: must be <= 1 parent left
			}else if(cla.equals("-o"))
			{
				//condition: parents block this
				//never flip	
			}else if(cla.equals("--"))
			{
				//condition: parents block this
				//never flip
			}else if(cla.equals("++"))
			{
				//condition: block all parents
				//flip as much as possible
			}else if(cla.equals("+--")) {
				//condition: if one parent left, block parent
				//flip once if possible, otherwise zero
				if(!constraining[i] && c.parents.size() == 1)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
				}
			}else if(cla.equals("++-"))
			{
				//condition: must have odd number of parents left. Or potentially zero
				//condition: if ever left with odd number of parents, block all parents
				//largest odd possible, otherwise zero
				if(!constraining[i] && (c.parents.size() & 1) == 1)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
				}
			}else if(cla.equals("-++"))
			{
				//condition: must have even number of parents left
				//condition: if ever left with an even number of parents, block all parents
				//largest even possible, otherwise zero
				if(!constraining[i] && (c.parents.size() & 1) == 0)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
				}
			}else if(cla.equals("--+"))
			{
				//never flip
				//condition: parents block this
			}else if(cla.equals("+=-"))
			{
				//condition: if left with one parent, block parent
				//once if possible, otherwise zero
				if(!constraining[i] && c.parents.size() == 1)
				{
					for(int par: c.parents)
					{
						c.out.add(par);
						circles[par].indegree++;
					}
				}
			}else if(cla.equals("-=+"))
			{
				//condition: only allowed if even parents left
				//any even number of times
			}
        }
    }
    
    private class circle implements Comparable<circle>{
        
        int id,x,y,r,a,b;
        String classification;
        ArrayList<Integer> out;//things this is blocking
        int indegree;
        HashSet<Integer> parents;
    	ArrayList<Integer> children;
        
        public circle(int id, int x, int y, int r, int a, int b)
        {
            this.id = id;
            this.x = x;
            this.y = y;
            this.r = r;
            this.a = a;
            this.b = b;
            this.children = new ArrayList<Integer>();
            this.parents = new HashSet<Integer>();
            this.out = new ArrayList<Integer>();
            if(a == 0 && b == 0)
			{
				//no restrictions
				classification = "oo";
			}else if(a == 0 && b > 0)
			{
				//largest even possible, optional 1 extra
				classification = "o+";
			}else if(a > 0 && b == 0)
			{
				//largest odd possible, optional 1 extra
				classification = "+o";
			}else if(a == 0 && b < 0)
			{
				//1 or none	
				classification = "o-";
			}else if(a < 0 && b == 0)
			{
				classification = "-o";
				//never flip	
			}else if(a < 0 && b < 0)
			{
				//never flip	
				classification = "--";
			}else if(a > 0 && b > 0)
			{
				//flip as much as possible	
				classification = "++";
			}else if(a > 0 && b < 0 && a+b < 0)
			{
				//flip once if possible, otherwise zero	
				classification = "+--";
			}else if(a > 0 && b < 0 && a+b > 0)
			{
				//largest odd possible, otherwise zero
				classification = "++-";
			}else if(a < 0 && b > 0 && a+b > 0)
			{
				//largest even possible, otherwise zero
				classification = "-++";
			}else if(a < 0 && b > 0 && a+b < 0)
			{
				//never flip
				classification = "--+";
			}else if(a > 0 && a+b == 0)
			{
				//once if possible, otherwise zero
				classification = "+=-";
			}else if(a < 0 && a+b == 0)
			{
				//any even number of times
				classification = "-=+";
			}
        }
        
        public String toString()
        {
        	return "("+x+","+y+","+r+","+a+","+b+")";
        }

        @Override
        public int compareTo(circle o) {
        	return Integer.compare(r, o.r);
        }    
    }
}