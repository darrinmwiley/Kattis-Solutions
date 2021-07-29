package codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BitcoinToss {

	public static void main(String[] args) throws IOException
	{
		new BitcoinToss().run();
	}
	
	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int zz = Integer.parseInt(file.readLine());
		for(int z = 0;z<zz;z++)
		{
			String line = file.readLine();
			System.out.println(solve(line));
		}
	}
	
	public String solve(String line)
	{
		for(int i = 10;i>0;i--)
		{
			int test = test(line,i);
			if(test!=Integer.MAX_VALUE)
			{
				return(i+" "+test);
			}
		}
		return "bad";
	}
	
	public int test(String s, int N)
	{
		int ret = Integer.MAX_VALUE;
		for(int i = 0;i<N;i++)
		{
			int win = slidingWindow(s,i,N);
			ret = Math.min(ret,win);
		}
		return ret;
	}
	
	public int slidingWindow(String s, int startingIndex, int N)
	{
		if(startingIndex+N*(1<<N)>s.length())
			return Integer.MAX_VALUE;
		int[] occurrences = new int[1<<N];
		int found = 0;
		for(int i = 0;i<(1<<N);i++)
		{
			occurrences[translate(s.substring(startingIndex+i*N, startingIndex+i*N+N))]++;
		}
		for(int i:occurrences)
			if(i!=0)
				found++;
		if(found==occurrences.length)
			return startingIndex;
		int offset = 1;
		int originalStart = startingIndex;
		int originalEnd = startingIndex+N*(1<<N);
		while(startingIndex+N*(1<<N)+offset*N<=s.length())
		{
			String rem = s.substring(originalStart+N*(offset-1),originalStart+N*offset);
			int remove = translate(rem);
			occurrences[remove]--;
			if(occurrences[remove]==0)
				found--;
			String ad = s.substring(originalEnd+N*(offset-1),originalEnd+N*offset);
			int add = translate(ad);
			if(occurrences[add]==0)
				found++;
			occurrences[add]++;
			if(found==occurrences.length)
				return originalStart+N*offset;
			offset++;
		}
		return Integer.MAX_VALUE;
	}
	
	public int translate(String str)
	{
		return Integer.parseInt(str.replace('H','0').replace('T','1'),2);
	}
	
}
