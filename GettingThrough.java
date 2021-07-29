/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/connectthedots
TAGS: implementation
EXPLANATION:
scan through the input to find the location of every dot
replace '.' with either '|' or '-'
replace '| and '-' with '+' if you travel over in both directions
be sure not to replace dot characters
END ANNOTATION
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class GettingThrough {

    StringTokenizer st;
    BufferedReader file;

    public static void main(String[] args) throws Exception
    {
        new GettingThrough().run();
    }

    public void run() throws Exception{
    	file = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pout = new PrintWriter(System.out);
    	int zz = nextInt();
    loop:
    	for(int z = 0;z<zz;z++)
    	{
    		 int W = nextInt();
    		 int N = nextInt();
    		 long[] X = new long[N];
    		 long[] Y = new long[N];
    		 long[] R = new long[N];
    		 for(int i = 0;i<N;i++)
    		 {
    			 X[i] = nextInt();
    			 Y[i] = nextInt();
    			 R[i] = nextInt();
    		 }
    		 PriorityQueue<event> que = new PriorityQueue<event>();
    		 for(int i = 0;i<N;i++)
    		 {
    			 for(int j = i+1;j<N;j++)
    			 {
    				 double d = Math.sqrt((X[i] - X[j])*(X[i] - X[j]) + (Y[i] - Y[j])*(Y[i] - Y[j])) - R[i] - R[j];
    				 que.add(new event(Math.max(0, d), i,j));
    			 }
    			 que.add(new event(X[i] - R[i], N, i));
    			 que.add(new event((W-X[i])-R[i], N+1, i));
    		 }
    		 que.add(new event(W, N, N+1));
    		 UnionFind uf = new UnionFind(N+2);
    		 while(true)
    		 {
    			 event e = que.poll();
    			 uf.union(e.a, e.b);
    			 if(uf.find(N) == uf.find(N + 1))
    			 {
    				 System.out.println(e.T/2);
    				 continue loop;
    			 }
    		 }
    	}
    }

    private class UnionFind{

    	int[] p;

    	public UnionFind(int sz) {
    		p = new int[sz];
    		Arrays.fill(p, -1);
    	}

    	public int find(int a)
    	{
    		if(p[a] < 0)
    			return a;
    		return p[a] = find(p[a]);
    	}

    	public void union(int a, int b)
    	{
    		int pa = find(a);
    		int pb = find(b);
    		if(pa == pb)
    			return;
    		p[pa] += p[pb];
    		p[pb] = pa;
    	}

    }

    private class event implements Comparable<event>{

    	double T;
    	int a, b;

    	public event(double T, int a, int b)
    	{
    		this.T = T;
    		this.a = a;
    		this.b = b;
    	}

		@Override
		public int compareTo(event arg0) {
			return Double.compare(T, arg0.T);
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
