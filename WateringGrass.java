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
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

//
public class WateringGrass {
	
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
		WateringGrass t = new WateringGrass();
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
		while(true)
		{
			try {
				int N = file.nextInt();
				int L = file.nextInt();
				int W = file.nextInt();
				int[] X = new int[N];
				int[] R = new int[N];
				for(int i = 0;i<N;i++)
				{
					X[i] = file.nextInt();
					R[i] = file.nextInt();
				}
				interval[] intervals = new interval[N];
				for(int i = 0;i<N;i++)
				{
					double x = Math.sqrt(R[i]*R[i]-W*W/4.0);
					intervals[i] = new interval(X[i]-x,X[i]+x);
				}
				int ans = solve(intervals, L);
				System.out.println(ans);
			}catch(Exception ex) {return;}
		}
	}
	
	public int solve(interval[] intervals, int L)
	{
		Arrays.sort(intervals);
		Queue<interval> list = new LinkedList<interval>();
		double location = 0;
		Comparator<interval> latestEnd = new Comparator<interval>() {
			public int compare(interval a, interval b)
			{
				return Double.compare(b.end,a.end);
			}
		};
		TreeSet<interval> active = new TreeSet<interval>(latestEnd);
		for(interval i: intervals)
			list.add(i);
		int ans = 0;
		while(location < L)
		{
			while(!list.isEmpty() && list.peek().start <= location)
			{
				active.add(list.poll());
			}
			if(active.isEmpty() || active.first().end <= location)
			{
				return -1;
			}
			location = active.first().end;
			ans++;
		}
		return ans;
	}
	
	private class interval implements Comparable<interval>{
		
		double start, end;
		
		public interval(double start, double end)
		{
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(interval o) {
			return Double.compare(start,o.start);
		}
		
		public String toString()
		{
			return start+" "+end;
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