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
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;
 
public class LostInTranslation {
    
    //this is used for actual solution output. It is very fast but will not show up on the console until the run() method terminates (or until you call pout.flush()).
    PrintWriter pout;
    
    //this is used for timing your code
    long startTimeMillis;
    long benchmark;
    
    //set this to false to remove all debug and timing output for submission.
    boolean DEBUG_FLAG = true;
    
    node[] nodes;
    HashMap<String,Integer> cost = new HashMap<String,Integer>();
    boolean flag = false;
    
    //this is just the entry point of your program. 
    //it will instantiate an instance of your solution class, set it's relevent variables, and then call its run() method to do the solving.
    //this also prints all output in pout's buffer for you after run() is complete.
    public static void main(String[] args) throws Exception
    {
        long entryTime = System.currentTimeMillis();
        LostInTranslation t = new LostInTranslation();
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
        int M = file.nextInt();
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        map.put("English", 0);
        for(int i = 0;i<N;i++)
        {
        	map.put(file.next(), i+1);
        }
        nodes = new node[map.size()];
        for(int i = 0;i<nodes.length;i++)
        {
        	nodes[i] = new node(i);
        }
        edge[] edges = new edge[M];
        
        for(int i = 0;i<M;i++)
        {
        	String a = file.next();
        	String b = file.next();
        	int aa = map.get(a);
        	int bb = map.get(b);
        	int next = file.nextInt();
        	con(aa, bb,next);
        	int min = Math.min(aa, bb);
        	int max = Math.max(aa, bb);
        	edges[i] = new edge(aa,bb,next);
        	cost.put(min+" "+max, next);
        }
        
        boolean flag = true;
        boolean[] vis = new boolean[map.size()];
        vis[0] = true;
        ArrayList<Integer> toAdd = new ArrayList<Integer>();
        UnionFind uf = new UnionFind(map.size());
        Arrays.sort(edges);
        int cost = 0;
        while(flag)
        {
        	flag = false;
        	for(edge e: edges)
        	{
        		if(vis[e.u] || vis[e.v])
        		{
        			boolean add = uf.union(e.u, e.v);
        			if(add)
        			{
        				toAdd.add(e.v);
        				toAdd.add(e.u);
        				flag = true;
        				cost += e.c;
        			}
        		}
        	}
        	for(int x: toAdd)
        	{
        		vis[x] = true;
        	}
        	toAdd.clear();
        }
        boolean bad = false;
        for(boolean b: vis)
        {
        	if(!b)
        	{
        		bad = true;
        		pout.println("Impossible");
        		break;
        	}
        }
        if(!bad)
        {
        	pout.println(cost);
        }
    }
    
    private class UnionFind
    {
    	int[] p;
    	
    	public UnionFind(int N)
    	{
    		p = new int[N];
    		Arrays.fill(p, -1);
    	}
    	
    	public int find(int a) {
            if(p[a] < 0)//if a is the set representative, then you can return it
                return a;
            int ans = find(p[a]);//if a is not the set representative, then ask it's parent
            p[a] = ans;//path compression
            return ans;
        }
        
        public boolean union(int a, int b) {
            int pa = find(a);//find a's set representative
            int pb = find(b);//find b's set representative
            if(pa == pb)//if a and b are already in the same set terminate
                return false;
            if(pb < pa) //if b's set is larger, swap a and b to ensure small to large merging
            {           //this is often not necessary in practice but it gives better asymptotic runtime
                int swap = pb;
                pb = pa;
                pa = swap;
            }
            p[pa] += p[pb];//increase a's set size by b's set size
            p[pb] = pa;//make b's set representative a's set representative
            return true;
        }
    }
    
    private class edge implements Comparable<edge>{
    	
    	int u, v, c;
    	
    	public edge(int u, int v, int c)
    	{
    		this.u = u;
    		this.v = v;
    		this.c = c;
    	}

		@Override
		public int compareTo(edge arg0) {
			return Integer.compare(c, arg0.c);
		}
    	
    	
    	
    }
    
    public void traceBack(TreeSet<String> set, int[] pred, int dest)
    {
    	while(pred[dest] != -1)
    	{
    		int p = pred[dest];
    		int min = Math.min(dest,p);
    		int max = Math.max(dest,p);
    		set.add(min+" "+max);
    		dest = p;
    	}
    }
    
    public void con(int a, int b, long c)
    {
    	nodes[a].con.add(b);
    	nodes[b].con.add(a);
    	nodes[a].cost2.add(c);
    	nodes[b].cost2.add(c);
    }
    
    public void fp(int start, int dest, int[] pred)
    {
    	PriorityQueue<state> que = new PriorityQueue<state>();
    	que.add(new state(start, 0, 0));
    	boolean[] vis = new boolean[nodes.length];
    	long[] fp1 = new long[nodes.length];
    	long[] fp2 = new long[nodes.length];
    	Arrays.fill(fp1, Long.MAX_VALUE / 4);
    	Arrays.fill(fp2, Long.MAX_VALUE / 4);
    	fp1[start] = 0;
    	fp2[start] = 0;
    	while(!que.isEmpty())
    	{
    		state st = que.poll();
    		if(!vis[st.location])
    		{
    			vis[st.location] = true;
    			long cost1 = st.cost1;
    			long cost2 = st.cost2;
    			int loc = st.location;
    			node n = nodes[loc];
    			for(int i = 0;i<n.con.size();i++)
    			{
    				int newnode = n.con.get(i);
    				long newcost1 = cost1+1;
    				long newcost2 = cost2+n.cost2.get(i);
    				int newloc = newnode;
    				long prevcost1 = fp1[newnode];
    				long prevcost2 = fp2[newnode];
    				if(newcost1 < prevcost1 || (newcost1 == prevcost1 && newcost2 < prevcost2))
    				{
    					pred[newnode] = loc;
    					state next = new state(newloc,newcost1, newcost2);
    					fp1[newnode] = newcost1;
    					fp2[newnode] = newcost2;
    					que.add(next);
    				}
    			}
    		}
    	}
    	if(!vis[dest])
    		flag = true;
    }
    
    private class state implements Comparable<state>{
    	
    	int location;
    	long cost1;
    	long cost2;
    	
    	public state(int loc, long c1, long c2)
    	{
    		location = loc;
    		cost1 = c1;
    		cost2 = c2;
    	}
    	
    	public int compareTo(state s)
    	{
    		int comp = Long.compare(cost1, s.cost1);
    		if(comp == 0)
    		{
    			return Long.compare(cost2, s.cost2);
    		}
    		return comp;
    	}
    	
    }
    
    private class node
    {
    	private ArrayList<Long> cost2;
    	private ArrayList<Integer> con;
    	
    	public node(int id)
    	{
    		cost2 = new ArrayList<Long>();
    		con = new ArrayList<Integer>();
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