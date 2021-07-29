/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/hauntedgraveyard
TAGS: bellman ford
EXPLANATION:

END ANNOTATION
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
 
public class euclidsgame {
    
    StringTokenizer st;
    BufferedReader file;
    PrintWriter pout;
    HashMap<String, Boolean> map;
    
    
    
    //1) force enemy to go below next
    //2) go below next yourself
    
    public static void main(String[] args) throws Exception
    {
        new euclidsgame().run();
    }   
    
    int r,c;
    
    public void run() throws Exception
    {
        file = new BufferedReader(new InputStreamReader(System.in));
        pout = new PrintWriter(System.out);
        while(true)
        {
        		map = new HashMap<String,Boolean>();
        		int a = nextInt();
        		int b = nextInt();
        		if(a == 0 && b == 0)
        		{
        			break;
        		}
        		boolean win = rec(Math.min(a, b), Math.max(a, b));
        		if(win)
        		{
        			pout.println("Stan wins");
        		}else {
        			pout.println("Ollie wins");
        		}
        }
        pout.flush();
        pout.close();
    }
    
    //a smaller, b larger
    public boolean rec(int a, int b)
    {
    		String str = a+" "+b;
    		if(map.containsKey(str))
    		{
    			return map.get(str);
    		}
    		if(b%a == 0)
    		{
    			map.put(str, true);
    			return true;
    		}
    		int mod = b%a;
    		if(b-a == mod)
    		{
    			boolean ret = !rec(b-a,a);
    			map.put(str, ret);
    			return ret;
    		}else {
    			boolean ret = !rec(mod, a) || !rec(a, mod+a);
    			map.put(str, ret);
    			return ret;
    		}
    }
    
    public boolean val(int rr, int cc)
    {
    		 return Math.min(rr,cc) >=0 && rr < r && cc < c;
    }
    
    private class state implements Comparable<state>{
    	
    		int r, c, cost;
    		
    		public state(int r, int c, int cost)
    		{
    			this.r =r ;
    			this.c =c ;
    			this.cost =cost;
    		}

			@Override
			public int compareTo(state o) {
				return Integer.compare(cost, o.cost);
			}
    	
    }
    
    //don't worry about this, just a helper method
    public void newst()
    {
        try {
            st = new StringTokenizer(file.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //consumes the entire next line of input
    public String readLine() throws IOException
    {
        return file.readLine();
    }
    
    //get's the next word of input
    public String next()
    {
        if(st == null || !st.hasMoreTokens())
            newst();
        return st.nextToken();
    }
    
    //tries to parse the next piece of input as an int
    public int nextInt()
    {
        if(st == null || !st.hasMoreTokens())
            newst();
        return Integer.parseInt(st.nextToken());
    }
    
    //tries to parse the next piece of input as a long
    public long nextLong()
    {
        if(st == null || !st.hasMoreTokens())
            newst();
        return Long.parseLong(st.nextToken());
    }
    
}