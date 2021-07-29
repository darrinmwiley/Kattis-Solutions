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
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class detour {
	
	//this is used for actual solution output. It is very fast but will not show up on the console until the run() method terminates (or until you call pout.flush()).
	PrintWriter pout;
	
	//this is used for timing your code
	long startTimeMillis;
	long benchmark;
	
	//set this to false to remove all debug and timing output for submission.
	boolean DEBUG_FLAG = true;
	
	//this is just the entry point of your program. 
	//it will instantiate an instance of your solution class, set it's relevent variables, and then call its run() method to do the solving.
	//this also prints all output in pout's buffer for you after run() is complete.
	public static void main(String[] args) throws Exception
	{
		long entryTime = System.currentTimeMillis();
		detour t = new detour();
		t.benchmark = t.startTimeMillis = entryTime;
		t.pout = new PrintWriter(System.out);
		t.run();
		t.pout.flush();
		t.pout.close();
	}	
	
	node[] nodes;
	
	//solution code goes here
	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		nodes = new node[N];
		for(int i = 0;i<N;i++)
		{
			nodes[i] = new node(i);
		}
		for(int i = 0;i<M;i++)
		{
			int a = file.nextInt();
			int b = file.nextInt();
			int c = file.nextInt();
			con(a,b,c);
		}
		state start = new state(1, 0);
		PriorityQueue<state> que = new PriorityQueue<state>();
		que.add(start);
		long[] fp = new long[nodes.length];
		Arrays.fill(fp, Long.MAX_VALUE/4);
		boolean[] vis = new boolean[nodes.length];
		while(!que.isEmpty())
		{
			state st = que.poll();
			if(vis[st.location] || fp[st.location] <= st.cost)
			{
				continue;
			}
			vis[st.location] = true;
			fp[st.location] = st.cost;
			node curr = nodes[st.location];
			for(int i = 0;i<curr.con.size();i++)
			{
				node next = nodes[curr.con.get(i)];
				int cost = curr.cost.get(i);
				if(!vis[next.id] && st.cost + cost < fp[next.id])
				{
					state succ = new state(next.id, st.cost + cost);
					que.add(succ);
				}
			}
		}
		boolean[] vis2 = new boolean[nodes.length];
		int[] pred = new int[nodes.length];
		Arrays.fill(pred, -1);
		Queue<Integer> que2 = new LinkedList<Integer>();
		que2.add(0);
		vis2[0] = true;
		while(!que2.isEmpty())
		{
			node curr = nodes[que2.poll()];
			for(int i = 0;i<curr.con.size();i++)
			{
				if(!vis2[curr.con.get(i)] && fp[curr.id] - curr.cost.get(i) != fp[curr.con.get(i)])
				{
					vis2[curr.con.get(i)] = true;
					que2.add(curr.con.get(i));
					pred[curr.con.get(i)] = curr.id;
				}
			}
		}
		if(pred[1] == -1)
		{
			System.out.println("impossible");
		}else {
			int current = 1;
			LinkedList<Integer> ans = new LinkedList<Integer>();
			while(current != -1)
			{
				ans.addFirst(current);
				current = pred[current];
			}
			System.out.print(ans.size()+" ");
			for(int x: ans)
			{
				System.out.print(x+" ");
			}
		}
	}
	
	private class state implements Comparable<state>{
		
		int location;
		long cost;
		
		public state(int location, long cost)
		{
			this.location = location;
			this.cost = cost;
		}
		
		public int compareTo(state s)
		{
			return Long.compare(cost, s.cost);
		}
		
	}
	
	public void con(int a, int b, int c)
	{
		nodes[a].con.add(b);
		nodes[b].con.add(a);
		nodes[a].cost.add(c);
		nodes[b].cost.add(c);
	}
	
	private class node{
		
		int id;
		ArrayList<Integer> con;
		ArrayList<Integer> cost;
		
		public node(int id)
		{
			this.id = id;
			this.con = new ArrayList<Integer>();
			this.cost = new ArrayList<Integer>();
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