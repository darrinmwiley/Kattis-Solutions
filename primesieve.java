/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/ads
TAGS: dfs 
EXPLANATION:
1) dfs from every empty edge position to determine what is outside of a frame
2) dfs from every banned character that is not marked as outside, keeping track of highest and lowest points reached in x, and y.
3) for each dfs, Delete the rectangle created by these values.
END ANNOTATION
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class primesieve {
	
	StringTokenizer st;
	BufferedReader file;
	
	public static void main(String[] args) throws Exception
	{
        new primesieve().run();
	}	
	
	public void run() throws Exception
	{	
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pout = new PrintWriter(System.out);
		
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		BitSet bits = new BitSet(N+1);
		bits.set(0);
		bits.set(1);
		int p = 0;
		int root = (int)(Math.sqrt(N));
		for(int i = 2;i<N+1;i++)
		{
			if(!bits.get(i))
			{
				p++;
				if(i <= root) {
					for(int j = i*i;j<N+1;j+=i)
					{
						bits.set(j);
					}
				}
			}
		}
		pout.println(p);
		for(int i = 0;i<q;i++)
		{
			int x = Integer.parseInt(file.readLine());
			if(!bits.get(x))
				pout.println(1);
			else
				pout.println(0);
		}
		pout.flush();
		pout.close();
	}
	
}
