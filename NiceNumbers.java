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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class NiceNumbers {

	//this is used for actual solution output. It is very fast but will not show up on the console until the run() method terminates (or until you call pout.flush()).
	PrintWriter pout;

	//this is used for timing your code
	long startTimeMillis;
	long benchmark;

	//set this to false to remove all debug and timing output for submission.
	boolean DEBUG_FLAG = true;

	char[] input;
	HashMap<String, String> map;
	//this is just the entry point of your program.
	//it will instantiate an instance of your solution class, set it's relevent variables, and then call its run() method to do the solving.
	//this also prints all output in pout's buffer for you after run() is complete.
	public static void main(String[] args) throws Exception
	{
		long entryTime = System.currentTimeMillis();
		NiceNumbers t = new NiceNumbers();
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
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			input = file.next().toCharArray();
			LinkedList<number> st = new LinkedList<number>();
			for(int i = 0;i<input.length;i++)
			{
				st.addLast(new number(input[i]-'0', input[i]+""));
			}
			LinkedList<number> next = new LinkedList<number>();
			if(st.size() != 1)
			{
				while(!st.isEmpty())
				{
					//System.out.println("next: "+next);
					number pop = st.removeFirst();
					if(pop.value == 2)
					{
						if(st.isEmpty() || st.getFirst().value != 2)
						{
							next.addLast(new number(4, "22"));
						}else if(st.getFirst().value == 2)
						{
							next.addLast(new number(4, "22"));
							st.removeFirst();
						}
					}else {
						next.addLast(pop);
					}
				}
				st = next;
				if(st.size() != 1)
				{
					next = new LinkedList<number>();
					while(!st.isEmpty())
					{
						number pop = st.removeFirst();
						if(pop.value == 4)
						{
							if(st.isEmpty() || st.getFirst().value != 4)
							{
								next.addLast(new number(8, pop.expand+"4"));
							}else if(st.getFirst().value == 4)
							{
								next.addLast(new number(8, pop.expand+st.removeFirst().expand));
							}
						}else {
							next.addLast(pop);
						}
					}
					st = next;
				}
			}
			int sum = 0;
			String ans = "";
			for(number xx: st) {
				sum += xx.value;
				ans += xx.expand;
			}
			//System.out.println(ans+" "+sum);
			while(Integer.bitCount(sum) != 1)
			{
				ans += "8";
				sum += 8;
			}
			System.out.println(ans);
		}
	}

	private class number{

		public int value;
		public String expand;

		public number(int v, String e)
		{
			this.value = v;
			this.expand = e;
		}

		public String toString()
		{
			return expand;
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
