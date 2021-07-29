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
public class JustPassingThrough {
	
	//this is used for actual solution output. It is very fast but will not show up on the console until the run() method terminates (or until you call pout.flush()).
	PrintWriter pout;
	
	//this is used for timing your code
	long startTimeMillis;
	long benchmark;
	
	int oo = Integer.MAX_VALUE/4;
	
	//set this to false to remove all debug and timing output for submission.
	boolean DEBUG_FLAG = true;
	
	//this is just the entry point of your program. 
	//it will instantiate an instance of your solution class, set it's relevent variables, and then call its run() method to do the solving.
	//this also prints all output in pout's buffer for you after run() is complete.
	public static void main(String[] args) throws Exception
	{
		long entryTime = System.currentTimeMillis();
		JustPassingThrough t = new JustPassingThrough();
		t.benchmark = t.startTimeMillis = entryTime;
		t.pout = new PrintWriter(System.out);
		t.run();
		t.pout.flush();
		t.pout.close();
	}	
	
	int R,C,P;
	int[][] ints;
	
	//solution code goes here
	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		R = file.nextInt();
		C = file.nextInt();
		P = file.nextInt();
		ints = new int[R][C];
		for(int i = 0;i<R;i++)
		{
			for(int j = 0;j<C;j++)
			{
				ints[i][j] = file.nextInt();
			}
		}
		boolean[][] pass = new boolean[R][C];
		for(int i = 1;i<R-1;i++)
		{
			for(int j = 1;j<C-1;j++)
			{
				int up = ints[i-1][j];
				int down = ints[i+1][j];
				int left = ints[i][j-1];
				int right = ints[i][j+1];
				int current = ints[i][j];
				if(up != -1 && down != -1 && current != -1 && left != -1 && right != -1)
				{
					if(up > current && down > current && left < current && right < current)
					{
						pass[i][j] = true;
					}
				}
			}
		}
		int[][][] dp = new int[R][C+1][P+2];
		for(int i = 0;i<R;i++)
		{
			for(int j = 0;j<C+1;j++)
			{
				Arrays.fill(dp[i][j], oo);
			}
			dp[i][0][0] = 0;
		}
		int[][] d = new int[][] {{-1,0,1},{1,1,1}};
		for(int i = 0;i<C;i++)
		{
			for(int j = 0;j<R;j++)
			{
				for(int k = 0;k<P+1;k++)
				{
					if(dp[j][i][k] < oo)
					{
						for(int dir = 0;dir<3;dir++) {
							int current = dp[j][i][k];
							int rr = j+d[0][dir];
							int cc = i+d[1][dir];
							if(valid(rr,cc-1))
							{
								if(i != 0 && pass[j][i-1])
								{
									dp[rr][cc][k+1] = Math.min(dp[rr][cc][k+1],dp[j][i][k]+ints[rr][cc-1]);
								}else {
									dp[rr][cc][k] = Math.min(dp[rr][cc][k],dp[j][i][k]+ints[rr][cc-1]);
								}
							}
						}
					}
				}
			}
		}
		int ans = oo;
		for(int i = 0;i<R;i++)
		{
			ans = Math.min(ans,dp[i][dp[i].length-1][P]);
		}
		System.out.println(ans == oo? "impossible" : ans);
	}
	
	public boolean valid(int r, int c)
	{
		return r >= 0 && r < ints.length && c >= 0 && c < ints[r].length && ints[r][c] != -1;
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