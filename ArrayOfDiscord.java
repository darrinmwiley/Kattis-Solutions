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
import java.io.PrintWriter;
import java.util.Scanner;
 
public class ArrayOfDiscord {
	
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
		ArrayOfDiscord t = new ArrayOfDiscord();
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
		int N = file.nextInt();
		String[] ints = new String[N];
		for(int i = 0;i<N;i++)
		{
			ints[i] = file.next();
		}
		for(int i = 0;i<ints.length;i++)
		{
			String orig = ints[i];
			for(int j = 0;j<ints[i].length();j++)
			{
				for(int k = 0;k<10;k++)
				{
					if(ints[i].length() != 1 && k == 0 || ints[i].charAt(j) == k+'0')
						continue;
					ints[i] = ints[i].substring(0,j)+k+ints[i].substring(j+1);
					if(!sorted(ints))
					{
						for(String s: ints)
						{
							System.out.print(s+" ");
						}
						return;
					}
					ints[i] = orig;
				}
				
			}
		}
		System.out.println("impossible");
	}
	
	public boolean sorted(String[] strs)
	{
		for(int i = 0;i<strs.length-1;i++)
		{
			long a = Long.parseLong(strs[i]);
			long b = Long.parseLong(strs[i+1]);
			if(a>b)
				return false;
		}
		return true;
	}
	
	
}