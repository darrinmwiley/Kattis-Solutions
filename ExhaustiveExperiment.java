/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/exhaustiveexperiment
TAGS: geometry, greedy, segtree
EXPLANATION:
first stretch the space by a factor of 2 so that the area of effect for helium is a diamond
also rotate the space 45 degrees clockwise
END ANNOTATION
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ExhaustiveExperiment {
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new ExhaustiveExperiment().run();		
	}
	
	char POSITIVE = 'P';
	char NEGATIVE = 'N';
	char NONE = '-';
	
	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		StringTokenizer st;
		element[] elements = new element[N];
		for(int i = 0;i<N;i++)
		{
			st = new StringTokenizer(file.readLine());
			int origX = Integer.parseInt(st.nextToken());
			int origY = Integer.parseInt(st.nextToken());
			int x = origX*2 - origY;
			int y = origX*2 + origY;
			char type = st.nextToken().charAt(0);
			elements[i] = new element(x,y,type);
		}
		Arrays.sort(elements);
		int lowestY = Integer.MAX_VALUE;
		for(int i = elements.length - 1;i>=0;i--)
		{
			if(elements[i].type == NEGATIVE)
				lowestY = Math.min(elements[i].y, lowestY);
			if(elements[i].y>=lowestY)
				elements[i].feasible = false;
		}
		int used = 0;
		int highestY = Integer.MIN_VALUE;
		int highestYUsed = Integer.MIN_VALUE;
		for(int i = 0;i<elements.length;i++)
		{
			if(elements[i].feasible)
			{
				highestY = Math.max(elements[i].y,highestY);
			}
			if(elements[i].type == POSITIVE && highestYUsed < elements[i].y)
			{
				if(highestY >= elements[i].y)
				{
					highestYUsed = highestY;
					used++;
				}else {
					System.out.println("impossible");
					return;
				}
			}
		}
		System.out.println(used);
	}
	
	private class element implements Comparable<element>{
		
		int x;
		int y;
		char type;
		boolean feasible;
		
		public element(int x, int y, char type)
		{
			this.x = x;
			this.y = y;
			this.type = type;
			feasible = true;
		}

		@Override
		public int compareTo(element o) {
			int comp = Integer.compare(x, o.x);
			if(comp == 0)
				return Integer.compare(o.y,y);
			return comp;
		}
		
	}
}
