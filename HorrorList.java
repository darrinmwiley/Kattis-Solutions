import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HorrorList {

    StringTokenizer st;
    BufferedReader file;

    boolean[] vis;
    node[] nodes;

    public static void main(String[] args) throws Exception
    {
        new HorrorList().run();
    }

    public void run() throws Exception
    {
        //this is fast IO - faster than Scanner/System.out.println
        file = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pout = new PrintWriter(System.out);

        int N = nextInt();
        int H = nextInt();
        int M = nextInt();

        int[] horror = new int[H];

        for(int i = 0;i<H;i++)
        	horror[i] = nextInt();

        nodes = new node[N];

        for(int i = 0;i<N;i++)
        {
        	nodes[i] = new node(i);
        }

        for(int i = 0;i<M;i++)
        {
        	int a = nextInt();
        	int b = nextInt();
        	con(a,b);
        }

        PriorityQueue<state> que = new PriorityQueue<state>();

        for(int x: horror)
        {
        	que.add(new state(x, 0));
        }

        vis = new boolean[N];
        int[] fp = new int[N];

        Arrays.fill(fp, Integer.MAX_VALUE/4);

        while(!que.isEmpty())
        {
        	state curr = que.poll();
        	if(vis[curr.location])
        	{
        		continue;
        	}
        	vis[curr.location] = true;
        	fp[curr.location] = Math.min(fp[curr.location], curr.cost);
        	node n = nodes[curr.location];
        	for(int i = 0;i<n.con.size();i++)
        	{
        		int newCost = curr.cost + 1;
        		if(newCost < fp[n.con.get(i)])
        		{
        			fp[n.con.get(i)] = newCost;
        			state next = new state(n.con.get(i), newCost);
        			que.add(next);
        		}
        	}
        }

        int max = -1;
        int index = -1;

        for(int i = 0;i<fp.length;i++)
        {
        	if(fp[i] > max)
        	{
        		max = fp[i];
        		index = i;
        	}
        }

        System.out.println(index);


        pout.flush();
        pout.close();
    }

    public void con(int a, int b)
    {
    	nodes[a].con.add(b);
    	nodes[b].con.add(a);
    }

    private class node{

    	int id;
    	ArrayList<Integer> con;

    	public node(int id)
    	{
    		this.id = id;
    		this.con = new ArrayList<Integer>();
    	}

    }

    private class state implements Comparable<state>{

    	int location;
    	int cost;

    	public state(int l, int c)
    	{
    		this.location = l;
    				cost = c;
    	}

		@Override
		public int compareTo(state arg0) {
			return Integer.compare(cost,  arg0.cost);
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
