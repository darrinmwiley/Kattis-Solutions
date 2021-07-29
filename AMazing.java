import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class AMazing {

    BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pout = new PrintWriter(System.out);

	String[] options = new String[] {"up","down","left","right"};
	int[][] dir = new int[][] {{-1,1,0,0},{0, 0, -1,1}};
	int[] opposite = new int[] {1,0,3,2};
	boolean[][] vis = new boolean[401][401];
	boolean[][][] movesTaken = new boolean[401][401][4];
	int R = 101;
	int C = 101;

    public static void main(String[] args) throws Exception
    {
        new AMazing().run();
    }

    public void run() throws Exception
    {
    		dfs(101,101, -1);
    		pout.println("no way out");
    		pout.flush();
    	}

    public void dfs(int R, int C, int from) throws IOException
    {
    		vis[R][C] = true;
    		int back = -1;
    		if(from != -1)
    			back = opposite[from];
    		for(int i = 0;i<4;i++)
		{
    			if(i == back)
    				continue;
    			String opt = options[i];
    			int dr = dir[0][i];
    			int dc = dir[1][i];
    			String next = R+dr+" "+(C+dc);
			if(!movesTaken[R][C][i]&&!vis[R+dr][C+dc])
			{
				String makeMove = interact(R,C,opt, i);
				if(makeMove.equals("solved"))
				{
					System.exit(0);
				}else if(makeMove.equals("ok"))
				{
					dfs(R+dr, C+dc, i);
				}else if(makeMove.equals("wall"))
				{
					//nothing here I think
				}
			}
		}
    		if(from == -1)
    		{
    			System.out.println("no way out");
    			System.exit(0);
    		}
		int o = opposite[from];
		String opp = options[o];
    		interact(R,C,opp, o);
    }

    public String interact(int R, int C, String s, int i) throws IOException
    {
    		movesTaken[R][C][i] = true;
    		pout.println(s);
    		pout.flush();
    		return file.readLine();
    }
}
