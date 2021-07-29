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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//
public class FlightCollisions {
	
	//this is used for actual solution output. It is very fast but will not show up on the console until the run() method terminates (or until you call pout.flush()).
	PrintWriter pout;
	
	//this is used for timing your code
	long startTimeMillis;
	long benchmark;
	
	int oo = Integer.MAX_VALUE;
	
	//set this to false to remove all debug and timing output for submission.
	boolean DEBUG_FLAG = true;
	
	//this is just the entry point of your program. 
	//it will instantiate an instance of your solution class, set it's relevent variables, and then call its run() method to do the solving.
	//this also prints all output in pout's buffer for you after run() is complete.
	public static void main(String[] args) throws Exception
	{
		long entryTime = System.currentTimeMillis();
		FlightCollisions t = new FlightCollisions();
		t.benchmark = t.startTimeMillis = entryTime;
		t.pout = new PrintWriter(System.out);
		t.run();
		t.pout.flush();
		t.pout.close();
	}	
	
	//solution code goes here
	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		drone[] drones = new drone[N];
		for(int i = 0;i<N;i++)
		{
			int x = file.nextInt();
			int v = file.nextInt();
			drones[i] = new drone(x,v,i);
		}
		for(int i = 0;i<N;i++)
		{
			drones[i].id = i;
		}
		for(int i = 0;i<drones.length-1;i++)
		{
			drones[i].next = drones[i+1];
		}
		for(int i = 1;i<drones.length;i++)
		{
			drones[i].prev = drones[i-1];
		}
		PriorityQueue<event> que = new PriorityQueue<event>();
		for(int i = 0;i<drones.length - 1;i++)
		{
			fraction intersect = intersect(drones[i], drones[i+1]);
			if(intersect != null) {
				event e = new event(drones[i], drones[i+1], intersect);
				que.add(e);
			}
		}
		while(!que.isEmpty())
		{
			event e = que.poll();
			drone a = e.a;
			drone b = e.b;
			if(a.x > b.x)
			{
				drone c = a;
				a = b;
				b = c;
			}
			if(a.crashed || b.crashed)
			{
				continue;
			}
			a.crashed = true;
			b.crashed = true;
			if(a.prev != null)
				a.prev.next = b.next;
			if(b.next != null)
				b.next.prev = a.prev;
			if(a.prev != null && b.next != null)
			{
				fraction intersect = intersect(a.prev, b.next);
				if(intersect != null) {
					event ev = new event(a.prev, b.next, intersect);
					que.add(ev);
				}
			}
		}
		ArrayList<Integer> flying = new ArrayList<Integer>();
		for(drone x: drones)
		{
			if(!x.crashed)
			{
				flying.add(x.id + 1);
			}
		}
		Collections.sort(flying);
		pout.println(flying.size());
		for(int x: flying)
			pout.print(x+" ");
		pout.println();
		pout.flush();
	}
	
	private class fraction implements Comparable<fraction>{
		
		BigInteger num, denom;
		
		public fraction(int num, int denom)
		{
			this.num = BigInteger.valueOf(num);
			this.denom = BigInteger.valueOf(denom);
		}

		@Override
		public int compareTo(fraction o) {
			return num.multiply(o.denom).compareTo(o.num.multiply(denom));
		}
		
		
	}
	
	private class event implements Comparable<event>{
		drone a,b;
		fraction time;
		public event(drone a, drone b, fraction t)
		{
			this.a = a;
			this.b =b;
			this.time = t;
		}
		@Override
		public int compareTo(event o) {
			return time.compareTo(o.time);
		}
	}
	
	public fraction intersect(drone a, drone b)
	{
		if(a.x > b.x)
		{
			drone c = a;
			a = b;
			b = c;
		}
		if(a.v <= b.v)
		{
			return null;
		}
		//WLOG a is left of b
		return new fraction(b.x-a.x,a.v-b.v);
	}
	
	private class drone implements Comparable<drone>{
		
		int x,v, id;
		
		drone next;
		drone prev;
		
		boolean crashed;
		
		public drone(int x, int v, int id)
		{
			this.x =x ;
			this.v =v ;
			this.id = id;
		}

		@Override
		public int compareTo(drone arg0) {
			return Integer.compare(x, arg0.x);
		}
		
		public String toString()
		{
			return "("+id+","+x+","+v+")";
		}
	}
	
	public void reverse(char[] chars, int start, int finish)
	{
		for(int i = 0;i<=(finish-start)/2;i++)
		{
			char save = chars[start+i];
			chars[start+i] = chars[finish - i];
			chars[finish - i] = save;
		}
	}
	
	//prints out o if DEBUG_FLAG is true, does nothing otherwise
	//If you use pout for all real output and print() for all debugging output, you won't have to run through your code deleting prints after you get your solution working. 
	//Instead you can just set DEBUG_FLAG to false;
	public void print(Object o)
	{
		if(DEBUG_FLAG)
			System.out.println(o);
	}
	
	//call time to print a debug of how long a certain segment of your code has taken in milliseconds.
	//does nothing if DEBUG_FLAG is false
	public void time(String s)
	{
		if(DEBUG_FLAG)
		{
			long time = System.currentTimeMillis();
			System.out.println(s+": "+(time - startTimeMillis)+" ("+(time - benchmark)+" millis from last benchmark)");
			benchmark = time;
		}
	}
	
	//A fast bare-bones implementation of java.util.Scanner for fast input
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