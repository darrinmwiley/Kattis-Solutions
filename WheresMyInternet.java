import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class WheresMyInternet {

	node[] nodes;
	boolean[] vis;

    public void run() throws Exception
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pout = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(file.readLine());
        int H = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        nodes = new node[H];
        vis = new boolean[H];
        for(int i = 0;i<nodes.length;i++)
        {
        		nodes[i] = new node(i);
        }
        for(int i = 0;i<N;i++)
        {
        		st = new StringTokenizer(file.readLine());
        		int a = Integer.parseInt(st.nextToken())-1;
        		int b = Integer.parseInt(st.nextToken())-1;
        		con(a,b);
        }
        dfs(0);
        boolean flag = false;
        for(int i = 0;i<vis.length;i++)
        {
        		if(!vis[i])
        		{
        			pout.println(i+1);
        			flag = true;
        		}
        }
        if(!flag)
        		pout.println("Connected");
        pout.flush();
    }

    public void dfs(int x)
    {
    		vis[x] = true;
    		for(int xx: nodes[x].con)
    		{
    			if(!vis[xx])
    				dfs(xx);
    		}
    }

    public void con(int a, int b)
    {
    		nodes[a].con.add(b);
    		nodes[b].con.add(a);
    }

    private class node{

    		ArrayList<Integer> con;
    		int id;

    		public node(int id)
    		{
    			this.id = id;
    			con = new ArrayList<Integer>();
    		}

    }

    public static void main(String[] args) throws Exception
    {
        new WheresMyInternet().run();
    }
}
