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
import java.util.HashSet;
import java.util.StringTokenizer;
 
public class HighestTower {
    
    //this is used for actual solution output. It is very fast but will not show up on the console until the run() method terminates (or until you call pout.flush()).
    PrintWriter pout;
    
    //this is used for timing your code
    long startTimeMillis;
    long benchmark;
    
    //set this to false to remove all debug and timing output for submission.
    boolean DEBUG_FLAG = true;
    
    node[] nodes;
    int[] color;
    int WHITE = 0;
    int GREY = 1;
    int BLACK = 2;
    int ccc = 0;
    
    int[] p;
    
    //this is just the entry point of your program. 
    //it will instantiate an instance of your solution class, set it's relevent variables, and then call its run() method to do the solving.
    //this also prints all output in pout's buffer for you after run() is complete.
    public static void main(String[] args) throws Exception
    {
        long entryTime = System.currentTimeMillis();
        HighestTower t = new HighestTower();
        t.benchmark = t.startTimeMillis = entryTime;
        t.pout = new PrintWriter(System.out);
        t.run();
        t.pout.flush();
        t.pout.close();
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
		{			//this is often not necessary in practice but it gives better asymptotic runtime
			int swap = pb;
			pb = pa;
			pa = swap;
		}
		p[pa] += p[pb];//increase a's set size by b's set size
		p[pb] = pa;//make b's set representative a's set representative
		return true;
	}
    
    public long bruteForce(int[] W, int[] H)
    {
    		long max = 0;
    	loop:
    		for(int i = 0;i<(1<<W.length);i++)
    		{
    			int ans = 0;
    			HashSet<Integer> map = new HashSet<Integer>();
    			for(int j = 0;j<W.length;j++)
    			{
    				int bit = (1<<j)&i;
    				if(bit != 0)
    				{
    					ans += W[j];
    					boolean add = map.add(H[j]);
    					if(!add)
    						continue loop;
    				}else {
    					ans += H[j];
    					boolean add = map.add(W[j]);
    					if(!add)
    						continue loop;
    				}
    			}
    			max = Math.max(max, ans);
    		}
    		
    		return max;
    }
    
    public long solve(int[] W, int[] H)
    {
    		int N = W.length;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0;i<N;i++)
        {
	        	if(!map.containsKey(W[i]))
	        	{
	        		map.put(W[i], map.size());
	        	}
	        	if(!map.containsKey(H[i]))
	        	{
	        		map.put(H[i], map.size());
	        	}
        }
        nodes = new node[map.size()];
        color = new int[map.size()];
        p = new int[map.size()];
        Arrays.fill(p, -1);
        for(int i = 0;i<nodes.length;i++)
        {
        		nodes[i] = new node(i);
        }
        ArrayList<Integer> first = new ArrayList<Integer>();
        for(int i = 0;i<N;i++)
        {
	        	int id1 = map.get(H[i]);
	        	int id2 = map.get(W[i]);
	        	if(!union(id1, id2))
	        	{
	        		first.add(id1);
	        	}
	        	int bandaid = (int)(Math.random() * Integer.MAX_VALUE/2);
	        	nodes[id1].con.add(id2);
	        	nodes[id1].bandaid.add(bandaid);
	        	if(id1 != id2) {
	        		nodes[id2].con.add(id1);
	        		nodes[id2].bandaid.add(bandaid);
	        	}
        }
        for(int i: W)
        {
        		nodes[map.get(i)].value = i;
        }
        for(int i: H)
        {
        		nodes[map.get(i)].value = i;
        }
        
        boolean[] root = new boolean[nodes.length];
        
       // System.out.println(first);
        
        for(int x: first)
        {
        		if(color[x] == WHITE)
        		{
        			root[x] = true;
        			dfs(x,-1,0,-1);
        			ccc++;
        		}
        }
        
        for(int i = 0;i<nodes.length;i++)
        {
	        	if(color[i] == WHITE)
	        	{
	        		root[i] = true;
	        		dfs(i, -1, 0, -1);
	        		ccc++;
	        	}
        }
        
        boolean[] ccloop = new boolean[ccc];
        
        for(int i = 0;i<nodes.length;i++)
        {
        		if(nodes[i].cycle)
        			ccloop[nodes[i].cc] = true;
        }
        
        long ans = 0;
       
        //we have a problem when the root is not part of a loop
        //if we find a cycle we need to rerun the dfs starting from inside the loop.
        
        for(int i = 0;i<nodes.length;i++)
        {
        		//System.out.println(nodes[i].value)
	        	if(nodes[i].cycle)
	        	{
	        		//System.out.println(nodes[i].value+" adding1 "+nodes[i].value);
	        		ans += nodes[i].value;
	        	}else if(nodes[i].parent != -1 && nodes[nodes[i].parent].cycle)
	        	{
	        		//System.out.println(nodes[i].value+" adding2 "+(nodes[i].bestConstrained + nodes[nodes[i].parent].value));
	        		ans += nodes[i].bestConstrained + nodes[nodes[i].parent].value;
	        		// += nodes[i].cycleTouch;
	        	}else if(!ccloop[nodes[i].cc] && root[i])
	        	{
	        		//System.out.println(nodes[i].value+" adding3 "+(nodes[i].bestConstrained + nodes[i].add));
	        		ans += nodes[i].bestConstrained + nodes[i].add;
	        	}
        }
        
        return ans;
    }
    
/*
2
30 10
10 10
 */
    
    //solution code goes here
    public void run() throws Exception
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int[] W = new int[N];
        int[] H = new int[N];
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0;i<N;i++)
        {
	        	W[i] = file.nextInt();
	        	H[i] = file.nextInt();
        }
        System.out.println(solve(W,H));
        
    		/*while(true)
    		{
    			int[][] cas = genCase(20);
    			long bf = bruteForce(cas[0], cas[1]);
    			if(bf != 0)
    			{
    				long solve = solve(cas[0], cas[1]);
    				if(bf != solve)
    				{
    					System.out.println(Arrays.toString(cas[0])+" "+Arrays.toString(cas[1])+" "+bf+" "+solve);
    					new Scanner(System.in).nextLine();
    				}
    			}
    		}*/
        //System.out.println(bruteForce(W,H)+" "+solve(W,H));
    }
    
/*
3
23 38
23 30
30 30
 */
    
    public int[][] genCase(int N)
    {
    		int[][] ints = new int[2][N];
    		for(int i = 0;i<2;i++)
    		{
    			for(int j = 0;j<N;j++)
    			{
    				ints[i][j] = (int)(Math.random()*50);
    			}
    		}
    		return ints;
    }
    
/*
12
1 2
1 3
2 4
3 5
3 7
7 9
7 8
5 6
5 4
10 11
11 13
12 11
 */
    
/*
2
44 9
44 44
 */
    
    //returns cycleStart if in cycle
    public int dfs(int v, int p, int d, int bandaid)
    {
	    	//System.out.println(nodes[v].value+" "+p);
	    	nodes[v].parent = p;
	    	nodes[v].cc = ccc;
	    	int retval = -1;
	    	nodes[v].depth = d;
	    	for(int xx = 0;xx<nodes[v].con.size();xx++)
	    	{
	    		int x = nodes[v].con.get(xx);
	    		int ba = nodes[v].bandaid.get(xx);
	    		if(x == v)
	    		{
	    			nodes[v].cycle = true;
	    		}
	    		else if(ba != bandaid && (color[x] == GREY))
	    		{
	    			nodes[v].cycle = true;
	    			retval = x;
	    		}
	    		else if(x != p && color[x] == WHITE){
	    			nodes[v].bestConstrained += nodes[v].value;
	    			color[v] = GREY;
	    			int res = dfs(x, v, d+1, ba);
	    			if(nodes[x].cycle)
	    				nodes[v].cycleTouch = nodes[x].value;
	    			nodes[v].add = Math.max(nodes[v].add, nodes[x].add + nodes[x].value - nodes[v].value);
	    			nodes[v].bestConstrained += nodes[x].bestConstrained;
	    			if(res != -1)
	    			{
	    				nodes[v].cycle = true;
	    				if(res != v)
	    					retval = res;
	    			}
	    		}
	    	}
	    	color[v] = BLACK;
	    	return retval;
    }
    
    private class node{
    	
    		long cycleTouch = -1;
	    ArrayList<Integer> con;
	    	ArrayList<Integer> bandaid;
	    	int id;
	    	long value;
	    	int depth;
	    	boolean cycle;
	    	int cc;
	    	int parent;
	    	long add;//you can send arrows either up or down
	    	long bestConstrained;//you can only send arrows down
	    	
	    	public node(int id)
	    	{
	    		this.id = id;
	    		this.con = new ArrayList<Integer>();
	    		bandaid = new ArrayList<Integer>();
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