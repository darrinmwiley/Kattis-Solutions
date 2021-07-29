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
import java.util.StringTokenizer;
 
public class Alloys {
	
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
		Alloys t = new Alloys();
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
        double C = file.nextDouble();
        double floor = Math.min(1.0,C);
        System.out.println(Math.pow(floor/2, 2));
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

	public double[] tangent(double Px, double Py, double Cx, double Cy, double R)
	{
		double b = Math.sqrt((Px - Cx)*(Px - Cx) + (Py - Cy)*(Py - Cy));
		double th = Math.acos(R / b);
		double d = Math.atan2(Py - Cy, Px - Cx);
		double d1 = d + th;
		double d2 = d - th;

		double T1x = Cx + R * Math.cos(d1);
		double T1y = Cy + R * Math.sin(d1);
		double T2x = Cx + R * Math.cos(d2);
		double T2y = Cy + R * Math.sin(d2);
		return new double[] {T1x, T1y, T2x, T2y};
	}
	
private class LineInf {
        
        double A,B,C;//want line in form Ax+By = C
        boolean vert;
        public LineInf(double x1, double y1, double x2, double y2)
        {
            vert = x1==x2;
            A = y2-y1;
            B = x1-x2;
            C = A*x1+B*y1;
        }
        
        public boolean contains(int x, int y)
        {
            return A*x+B*y==C;
        }
        
        public boolean isVertical()
        {
            return vert;
        }
        
        private LineInf(double A, double B, double C)
        {
            this.A = A;
            this.B = B;
            this.C = C;
        }
        
        public double det(LineInf li)
        {
            return A*li.B-li.A*B;
        } 
        
        public String toString() {
        	return A+" "+B+" "+C;
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