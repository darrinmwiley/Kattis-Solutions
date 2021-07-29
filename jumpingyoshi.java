package page;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class jumpingyoshi {

	//idea:
	// it can be shown that you never have to jump left
	// keep track of value+index and value-index for every spot
	// to get a list of possible successors
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new jumpingyoshi().run();
	}
	
	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		int[] ints = new int[N];
		String line = file.readLine();
		StringTokenizer st = new StringTokenizer(line);
		for(int i = 0;i<ints.length;i++)
			ints[i] = Integer.parseInt(st.nextToken());
		Queue<Integer> positions = new LinkedList<Integer>();
		positions.add(0);
		difference[] left = new difference[N];
		difference[] right = new difference[N];
		boolean[] vis = new boolean[N];
		for(int i = 0;i<N;i++)
		{
			left[i] = new difference(-i-ints[i],i);
			right[i] = new difference(i-ints[i],i);
		}
		Arrays.sort(left);
		Arrays.sort(right);
		int max = 0;
		while(!positions.isEmpty())
		{
			int spot = positions.poll();
			if(vis[spot])
				continue;
			max = Math.max(spot,max);
			vis[spot] = true;
			HashSet<Integer> L = new HashSet<Integer>();
			HashSet<Integer> R = new HashSet<Integer>();
			getJumps(left,ints[spot]-spot,L);
			getJumps(right,ints[spot]+spot,R);
			for(int x:L)
			{
				if(!vis[x]&&x<spot)
					positions.add(x);
			}
			for(int x:R)
			{
				if(!vis[x]&&x>spot)
					positions.add(x);
			}
		}
		System.out.println(max);
	}
	
	public void getJumps(difference[] diffs, int difference, HashSet<Integer> set)//left and right are inclusive
	{
		int L = -1;
		int R = diffs.length;
		while(R-L>1)
		{
			int M = (L+R)/2;
			if(diffs[M].diff<difference)
				L = M;
			else
				R = M;
		}
		for(int i = (L+R)/2+1;i<diffs.length;i++)
		{
			if(diffs[i].diff!=difference)
				break;
			else
				set.add(diffs[i].index);
		}
	}
	
	private class difference implements Comparable<difference>{
		
		int diff;
		int index;
		
		public difference(int d, int i)
		{
			diff = d;
			index = i;
		}

		@Override
		public int compareTo(difference arg0) {
			return Integer.compare(diff,arg0.diff);
		}
		
		
		
	}
	
}