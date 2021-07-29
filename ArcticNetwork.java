import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class ArcticNetwork {

    BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pout = new PrintWriter(System.out);
    StringTokenizer st;

    public static void main(String[] args) throws Exception
    {
        new ArcticNetwork().run();
    }

    public int countCC(boolean[][] con)
    {
    		int[] ccs = new int[con.length];
    		boolean[] vis = new boolean[con.length];
    		int curr = 0;
    		for(int i = 0;i<ccs.length;i++)
    		{
    			if(ccs[i] == 0)
    			{
    				dfs(con, vis, i, ccs, ++curr);
    			}
    		}
    		return curr;
    }

    public void dfs(boolean[][] con, boolean[] vis, int loc, int[] ccs, int cc)
    {
    		ccs[loc] = cc;
    		vis[loc] = true;
    		for(int i = 0;i<con.length;i++)
    		{
    			if(con[loc][i]&&!vis[i])
    			{
    				dfs(con, vis, i, ccs, cc);
    			}
    		}
    }

    public void run() throws Exception
    {
    		int T = Integer.parseInt(file.readLine());
    		for(int z = 0;z<T;z++)
    		{
    			st = new StringTokenizer(file.readLine());
    			int S = Integer.parseInt(st.nextToken());
    			int P = Integer.parseInt(st.nextToken());
    			int[] x = new int[P];
    			int[] y = new int[P];
    			double[][] dist2 = new double[P][P];
    			for(int i = 0;i<P;i++)
    			{
    				st = new StringTokenizer(file.readLine());
    				x[i] = Integer.parseInt(st.nextToken());
    				y[i] = Integer.parseInt(st.nextToken());
    			}
    			for(int i = 0;i<P;i++)
    			{
    				for(int j = 0;j<P;j++)
    				{
    					dist2[i][j] = dist2(x[i],y[i],x[j],y[j]);
    				}
    			}
    			double L = 0;
    			double R = 200000;
    			double M = (L+R)/2;
    			double minAns = 200000;
    			while(R - L > .00001)
    			{
    				M = (L+R)/2;
    				double d2 = M*M;
    				boolean[][] con = new boolean[P][P];
    				for(int i = 0;i<P;i++)
    				{
    					for(int j = 0;j<P;j++)
    					{
    						if(i != j && dist2[i][j] <= d2)
    							con[i][j] = true;
    					}
    				}
    				int cc = countCC(con);
    				if(cc == 1 || cc <= S)
    				{
    					minAns = M;
    					R = M;
    				}else {
    					L = M;
    				}
    			}
    			System.out.printf("%.2f%n", minAns);
    		}
    	}

    public double dist2(int x1, int y1, int x2, int y2)
    {
    		return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    }

}
