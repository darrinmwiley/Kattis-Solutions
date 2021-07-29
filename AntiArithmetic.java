import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class AntiArithmetic {

	//G - every triplet must be out of order
	//for every center, if there is a difference on the left,
	//the opposite distance also must be on the left

    public void run() throws Exception
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pout = new PrintWriter(System.out);
        StringTokenizer st;
        String line;
       loop:
        while((line = file.readLine()) != null)
        {
        		if(line.equals("0"))
        		{
        			return;
        		}
        		st = new StringTokenizer(line);
        		String str = st.nextToken();
        		int len = Integer.parseInt(str.substring(0,str.length()-1));
        		int[] loc = new int[len];
        		int[] ints = new int[len];
        		for(int i = 0;i<ints.length;i++)
        		{
        			int x = Integer.parseInt(st.nextToken());
        			loc[x] = i;
        			ints[i] = x;
        		};
        		for(int middle = 1;middle < len - 1;middle++)
        		{
        			for(int diff = 1;middle + diff < len && middle - diff >= 0;diff++)
        			{
        				int start = middle - diff;
        				int end = middle + diff;
        				int i1 = loc[start];
        				int i2 = loc[middle];
        				int i3 = loc[end];
        				if(i1 < i2 && i2 < i3 || i1 > i2 && i2 > i3)
        				{
        					System.out.println("no");
        					continue loop;
        				}
        			}
        		}
        		System.out.println("yes");
        }
    }

    public static void main(String[] args) throws Exception
    {
        new AntiArithmetic().run();
    }
}
