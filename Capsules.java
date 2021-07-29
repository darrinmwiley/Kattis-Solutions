import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Capsules {
	public static void main(String[] args) {
		new Capsules().run();
	}
	
	String[][] reps;
	int[][] ints;
	int R;
	int C;
	HashMap<Integer,boolean[]> options;
	HashMap<String, Integer> groups;
	public void run()
	{
		Scanner file = new Scanner(System.in);
		R = file.nextInt();
		C = file.nextInt();
		ints = new int[R][C];
		reps = new String[R][C];
		options = new HashMap<Integer,boolean[]>();
		groups = new HashMap<String,Integer>();
		for(int i = 0;i<R;i++)
		{
			for(int j = 0;j<C;j++)
			{
				reps[i][j] = "("+(i+1)+","+(j+1)+")";
			}
		}
		for(int i = 0;i<R;i++)
		{
			for(int j = 0;j<C;j++)
			{
				String next = file.next();
				if(next.equals("-"))
				{
					ints[i][j] = 0;
				}else {
					ints[i][j] = Integer.parseInt(next);
				}
			}
		}
		int M = file.nextInt();
		for(int i = 0;i<M;i++)
		{
			int x = file.nextInt();
			for(int j = 0;j<x;j++) {	
				String next = file.next();
				groups.put(next, i);
			}
			boolean[] opt = new boolean[x];
			Arrays.fill(opt, true);
			options.put(i,opt);
		}
		for(int j = 0;j<R;j++)
		{
			for(int k = 0;k<C;k++)
			{
				if(ints[j][k]!=0)
				{
					int group = groups.get(reps[j][k]);	
					boolean[] opt = options.get(group);
					opt[ints[j][k]-1] = false;
					options.put(group,opt);
				}
			}
		}
		boolean ret = bruteForce(0,0);
		for(int[] in:ints)
		{
			for(int x:in)
			{
				System.out.print(x+" ");
			}
			System.out.println();
		}
	}
	
	public boolean[] getOptions(int r, int c)//just dont call if the item exists
	{
		int[][] d = new int[][] {{-1,-1,-1,0,0,1,1,1},{-1,0,1,-1,1,-1,0,1}};
		int group = groups.get(reps[r][c]);
		boolean[] opt = options.get(group);
		boolean[] exclude = new boolean[opt.length];
		for(int i = 0;i<8;i++)
		{
			int rr = r+d[0][i];
			int cc = c+d[1][i];
			if(val(rr,cc)) {
				int n = ints[rr][cc];
				if( n != 0 && n <= opt.length)
				{
					exclude[n-1] = true;
				}
			}
			
		}
		boolean[] ret = (boolean[]) opt.clone();
		for(int i = 0;i<exclude.length;i++)
			if(exclude[i])
				ret[i] = false;
		return ret;
	}
	
	public boolean bruteForce(int r, int c)
	{
		if(r>=ints.length)
		{
			return true;
		}
		int[] next = next(r,c);
		if(ints[r][c] != 0)
		{
			return bruteForce(next[0],next[1]);
		}
		boolean[] options = getOptions(r,c);
		for(int i = 0;i<options.length;i++)
		{
			if(options[i])
			{
				ints[r][c] = i+1;
				int group = groups.get(reps[r][c]);
				boolean[] optref = this.options.get(group);
				optref[i] = false;
				if(bruteForce(next[0],next[1]))
					return true;
				optref[i] = true;
				ints[r][c] = 0;
			}
		}
		return false;
	}
	
	public int[] next(int r, int c)
	{
		if(c == C-1)
		{
			return new int[] {r+1, 0};
		}else
			return new int[] {r, c+1};
	}
	
	public boolean val(int r, int c)
	{
		return r < R && c < C && Math.min(r,c) >= 0;
	}
}
